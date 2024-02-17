package sk.todo.security;

import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import sk.todo.entity.User;
import sk.todo.repository.UserRepository;

import java.util.Set;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {


    private UserRepository userRepository;
//145. Creating CustomUserDetailsService Class
    @Override
    public UserDetails loadUserByUsername(String usernameOrEmail) throws UsernameNotFoundException {


        /* WE GIVE THE OPTION to the user to enter username or email so pass same argument twice.
        in UserRepository class we have     Optional<User> findByUsernameOrEmail(String username,String email);

         */

        // this user is form entity package
        User user = userRepository.findByUsernameOrEmail(usernameOrEmail,usernameOrEmail)
                .orElseThrow(()-> new UsernameNotFoundException("User bit exists by name or mail id "));

        Set<GrantedAuthority> authorities = user.getRoles().stream().
                map((role)-> new SimpleGrantedAuthority(role.getName()))
                .collect(Collectors.toSet());


        return new org.springframework.security.core.userdetails.User(
                usernameOrEmail,
                user.getPassword(),
                authorities
        );

    }
}
