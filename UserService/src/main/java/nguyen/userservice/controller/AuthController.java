package nguyen.userservice.controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import nguyen.userservice.common.response.GenericResponse;
import nguyen.userservice.dto.LoginRequestDto;
import nguyen.userservice.dto.LoginResponseDto;
import nguyen.userservice.dto.UserRegisterRequestDto;
import nguyen.userservice.dto.UserResponseDto;
import nguyen.userservice.service.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<GenericResponse<UserResponseDto>> register(@Valid @RequestBody UserRegisterRequestDto requestDto) {
        UserResponseDto response = authService.register(requestDto);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(GenericResponse.success("User registered successfully", response));
    }

    @PostMapping("/login")
    public ResponseEntity<GenericResponse<LoginResponseDto>> login(@Valid @RequestBody LoginRequestDto requestDto) {
        LoginResponseDto response = authService.login(requestDto);
        return ResponseEntity.ok(GenericResponse.success("Login successful", response));
    }
}
