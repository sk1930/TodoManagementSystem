// this is for Method level security

package sk.todo.config;

import ch.qos.logback.core.encoder.Encoder;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.crypto.password.PasswordEncoder;



@Configuration
@EnableMethodSecurity // for method security
@AllArgsConstructor
public class SpringSecurityConfig {


    private UserDetailsService userDetailsService;
    // to inject CustomUserDetailsService using AllArgsConstructor


    //let us annotate this method with @Bean annotation so that spring container maintain the object
    //of this AuthenticationManager. It says Unhandled exception.
    //So go ahead and click on this.
    //So this will add exception as a method signature.
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();



    }
    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {


        http.csrf((csrf) -> {
            try {
                csrf.disable()
                        .authorizeHttpRequests((authorize)-> {
                                    /*
                                    this is for role based security
                                    authorize.requestMatchers(HttpMethod.POST,"/api/**").hasRole("ADMIN");


                                    authorize.requestMatchers(HttpMethod.PUT,"/api/**").hasRole("ADMIN");
                                    authorize.requestMatchers(HttpMethod.DELETE,"/api/**").hasRole("ADMIN");

                                    //http://localhost:8090/api/todos/fdffc000-944b-4912-ab1c-33739fdb2223/in-complete
                                    authorize.requestMatchers(HttpMethod.PATCH,"/api/**").hasAnyRole("ADMIN","USER");



                            // making Get request public so comment exisitng and add code for permitAll
                            //with permitAll we can use No auth in authentication
                              //                          authorize.requestMatchers(HttpMethod.GET,"/api/**").hasAnyRole("ADMIN","USER");


                                    authorize.requestMatchers(HttpMethod.GET,"/api/**").permitAll();
                            */
                            authorize.requestMatchers("/api/auth/**").permitAll();
                            // to make register and login api  public to all users
                            authorize.anyRequest().authenticated();
                                }
                        ).httpBasic(Customizer.withDefaults());
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });

        /*
        Deprecated csrf() method:
        http.csrf().disable()
        New version of csrf() method:
        http.csrf((csrf) -> csrf.disable())

        http.csrf().disable()
                .authorizeHttpRequests((authorize)-> {
                            authorize.anyRequest().authenticated();
                        }
                ).httpBasic(Customizer.withDefaults());

         */

        return http.build();
    }


    @Bean
    public static PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }



    /* this is for in-memory authentication
    we dont need this as we use database authentication
    @Bean
    public UserDetailsService userDetailsService(){

        UserDetails saikrishna = User.builder()
                .username("saikrishna")
                .password(passwordEncoder().encode("password"))
                .roles("USER")
                .build();

        UserDetails admin = User.builder()
                .username("admin")
                .password(passwordEncoder().encode("admin"))
                .roles("ADMIN")
                .build();

        return new InMemoryUserDetailsManager(saikrishna, admin);
    }*/

}
