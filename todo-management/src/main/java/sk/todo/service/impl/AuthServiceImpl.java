package sk.todo.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import sk.todo.dto.LoginDto;
import sk.todo.dto.RegisterDto;
import sk.todo.entity.Role;
import sk.todo.entity.User;
import sk.todo.exception.TodoAPIException;
import sk.todo.repository.RoleRepository;
import sk.todo.repository.UserRepository;
import sk.todo.security.JwtTokenProvider;
import sk.todo.service.AuthService;

import java.util.HashSet;
import java.util.Set;


/*
So this @Service annotation will automatically create a spring bean for this class.

we don't have to use @Autowired annotation because spring will automatically inject the dependency
whenever it will find a spring bean with only one constructor. *********
149. Build Register Rest API

 */
@Service
@AllArgsConstructor
public class AuthServiceImpl implements AuthService {

    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;
    private AuthenticationManager authenticationManager;
    //172. Change Login REST API to Return JWT Token
    private JwtTokenProvider jwtTokenProvider;



    @Override
    public String register(RegisterDto registerDto) {
        // check username is already exists in database
        //System.out.println("came here");
        if(userRepository.existsByUsername(registerDto.getUsername())){
            throw new TodoAPIException(HttpStatus.BAD_REQUEST, "Username already exists!");
        }

        // check email is already exists in database
        if(userRepository.existsByEmail(registerDto.getEmail())){
            throw new TodoAPIException(HttpStatus.BAD_REQUEST, "Email is already exists!.");
        }

        User user = new User();
        user.setName(registerDto.getName());
        user.setUsername(registerDto.getUsername());
        user.setEmail(registerDto.getEmail());
        user.setPassword(passwordEncoder.encode(registerDto.getPassword()));

        Set<Role> roles = new HashSet<>();
        Role userRole = roleRepository.findByName("ROLE_USER");

        System.out.println(userRole.getId());
        roles.add(userRole);

        user.setRoles(roles);

        userRepository.save(user);

        return "User Registered Successfully!.";
    }

    @Override //151. Build Login REST API
    public String login(LoginDto loginDto) {
        try {
            Thread.sleep(10000); 
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                loginDto.getUsernameOrEmail(),
                loginDto.getPassword()
        ));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtTokenProvider.generateJwtToken(authentication);
        //return "User loggedin Successfully";
        return token;
    }
}
