// this is for Role based security

package sk.todo.config;

import ch.qos.logback.core.encoder.Encoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.crypto.password.PasswordEncoder;



@Configuration
public class SpringSecurityConfig {
    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {


        http.csrf((csrf) -> {
            try {
                csrf.disable()
                        .authorizeHttpRequests((authorize)-> {
                                    authorize.requestMatchers(HttpMethod.POST,"/api/**").hasRole("ADMIN");
                                    authorize.requestMatchers(HttpMethod.PUT,"/api/**").hasRole("ADMIN");
                                    authorize.requestMatchers(HttpMethod.DELETE,"/api/**").hasRole("ADMIN");

                                    //http://localhost:8090/api/todos/fdffc000-944b-4912-ab1c-33739fdb2223/in-complete
                                    authorize.requestMatchers(HttpMethod.PATCH,"/api/**").hasAnyRole("ADMIN","USER");



                            /* making Get request public so comment exisitng and add code for permitAll
                            with permitAll we can use No auth in authentication
                                                        authorize.requestMatchers(HttpMethod.GET,"/api/**").hasAnyRole("ADMIN","USER");

                             */
                                    authorize.requestMatchers(HttpMethod.GET,"/api/**").permitAll();
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
    }

}
