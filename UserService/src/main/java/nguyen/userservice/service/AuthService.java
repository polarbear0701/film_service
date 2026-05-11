package nguyen.userservice.service;

import lombok.AllArgsConstructor;
import lombok.NonNull;
import nguyen.userservice.dto.LoginRequestDto;
import nguyen.userservice.dto.LoginResponseDto;
import nguyen.userservice.dto.UserRegisterRequestDto;
import nguyen.userservice.dto.UserResponseDto;
import nguyen.userservice.model.Users;
import nguyen.userservice.repository.UserRepository;
import nguyen.userservice.util.JwtUtil;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public UserResponseDto register(@NonNull UserRegisterRequestDto requestDto) {
        // Check if user already exists
        if (userRepository.findByUserName(requestDto.getUserName()).isPresent()) {
            throw new IllegalArgumentException("User already exists with username: " + requestDto.getUserName());
        }

        Users newUser = Users.builder()
                .userName(requestDto.getUserName())
                .displayName(requestDto.getDisplayName())
                .password(passwordEncoder.encode(requestDto.getPassword()))
                .build();
        userRepository.save(newUser);

        return UserResponseDto.builder()
                .userName(newUser.getUsername())
                .displayName(newUser.getDisplayName())
                .build();
    }

    public LoginResponseDto login(@NonNull LoginRequestDto requestDto) {
        Users user = userRepository.findByUserName(requestDto.getUserName())
                .orElseThrow(() -> new BadCredentialsException("Invalid username or password"));

        if (!passwordEncoder.matches(requestDto.getPassword(), user.getPassword())) {
            throw new BadCredentialsException("Invalid username or password");
        }

        String token = jwtUtil.generateToken(user.getUsername());
        return new LoginResponseDto(token);
    }
}
