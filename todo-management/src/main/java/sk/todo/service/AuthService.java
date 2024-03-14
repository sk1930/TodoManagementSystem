package sk.todo.service;

import sk.todo.dto.JwtAuthResponse;
import sk.todo.dto.LoginDto;
import sk.todo.dto.RegisterDto;

public interface AuthService {
    String register(RegisterDto registerDto);
    JwtAuthResponse login(LoginDto loginDto); //151. Build Login REST API

}
