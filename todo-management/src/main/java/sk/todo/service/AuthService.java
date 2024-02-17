package sk.todo.service;

import sk.todo.dto.LoginDto;
import sk.todo.dto.RegisterDto;

public interface AuthService {
    String register(RegisterDto registerDto);
    String login(LoginDto loginDto); //151. Build Login REST API

}
