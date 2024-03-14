package sk.todo.controller;


import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sk.todo.dto.JwtAuthResponse;
import sk.todo.dto.LoginDto;
import sk.todo.dto.RegisterDto;
import sk.todo.service.AuthService;

@CrossOrigin("*")
@AllArgsConstructor
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private AuthService authService;

    // Build Register REST API
    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody RegisterDto registerDto){
        String response = authService.register(registerDto);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }



    /* Before 175. Change Login Rest Api to return role along with token
    we only returned the token.
    @PostMapping("/login")
    public ResponseEntity<JwtAuthResponse> login(@RequestBody LoginDto loginDto){
        String token = authService.login(loginDto);

        JwtAuthResponse jwtAuthResponse = new JwtAuthResponse();
        jwtAuthResponse.setAccessToken(token);;

        return new ResponseEntity<>(jwtAuthResponse, HttpStatus.OK);
    }*/

    // Before 175. Change Login Rest Api to return role along with token
    @PostMapping("/login")
    public ResponseEntity<JwtAuthResponse> login(@RequestBody LoginDto loginDto){
        JwtAuthResponse jwtAuthResponse = authService.login(loginDto);

        return new ResponseEntity<>(jwtAuthResponse, HttpStatus.OK);
    }

}

