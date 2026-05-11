package nguyen.userservice.service;

import lombok.AllArgsConstructor;
import lombok.NonNull;
import nguyen.userservice.dto.ChangePasswordRequestDto;
import nguyen.userservice.dto.UpdateUserRequestDto;
import nguyen.userservice.dto.UserResponseDto;
import nguyen.userservice.model.Users;
import nguyen.userservice.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    @NonNull
    public UserDetails loadUserByUsername(@NonNull String username) throws UsernameNotFoundException {
        return userRepository.findByUserName(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username));
    }

    public UserResponseDto getUserProfile(@NonNull String username) {
        Users user = userRepository.findByUserName(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username));

        return UserResponseDto.builder()
                .userName(user.getUsername())
                .displayName(user.getDisplayName())
                .build();
    }

    public UserResponseDto updateUserProfile(@NonNull String username, @NonNull UpdateUserRequestDto updateRequest) {
        Users user = userRepository.findByUserName(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username));

        if (updateRequest.getDisplayName() != null && !updateRequest.getDisplayName().isBlank()) {
            user.setDisplayName(updateRequest.getDisplayName());
        }

        userRepository.save(user);

        return UserResponseDto.builder()
                .userName(user.getUsername())
                .displayName(user.getDisplayName())
                .build();
    }

    public void changePassword(@NonNull String username, @NonNull ChangePasswordRequestDto changePasswordRequest) {
        // Validate that new password and confirm password match
        if (!changePasswordRequest.getNewPassword().equals(changePasswordRequest.getConfirmPassword())) {
            throw new IllegalArgumentException("New password and confirm password do not match");
        }

        Users user = userRepository.findByUserName(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username));

        // Verify current password
        if (!passwordEncoder.matches(changePasswordRequest.getCurrentPassword(), user.getPassword())) {
            throw new IllegalArgumentException("Current password is incorrect");
        }

        // Update password
        user.setPassword(passwordEncoder.encode(changePasswordRequest.getNewPassword()));
        userRepository.save(user);
    }

    public void deleteUser(@NonNull String username) {
        Users user = userRepository.findByUserName(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username));

        userRepository.delete(user);
    }

}
