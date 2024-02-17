package sk.todo.controller;


import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
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
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginDto loginDto){
        String response = authService.login(loginDto);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
