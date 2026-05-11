package nguyen.userservice.controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import nguyen.userservice.common.response.GenericResponse;
import nguyen.userservice.dto.ChangePasswordRequestDto;
import nguyen.userservice.dto.UpdateUserRequestDto;
import nguyen.userservice.dto.UserResponseDto;
import nguyen.userservice.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
@AllArgsConstructor
public class UserController {
    private final UserService userService;

    /**
     * Get current user's profile
     */
    @GetMapping("/profile")
    public ResponseEntity<GenericResponse<UserResponseDto>> getProfile(Authentication authentication) {
        String username = authentication.getName();
        UserResponseDto response = userService.getUserProfile(username);
        return ResponseEntity.ok(GenericResponse.success("Profile retrieved successfully", response));
    }

    /**
     * Update current user's profile (display name only)
     */
    @PutMapping("/profile")
    public ResponseEntity<GenericResponse<UserResponseDto>> updateProfile(
            Authentication authentication,
            @Valid @RequestBody UpdateUserRequestDto updateRequest
    ) {
        String username = authentication.getName();
        UserResponseDto response = userService.updateUserProfile(username, updateRequest);
        return ResponseEntity.ok(GenericResponse.success("Profile updated successfully", response));
    }

    /**
     * Change current user's password (requires old password verification)
     */
    @PostMapping("/change-password")
    public ResponseEntity<GenericResponse<Void>> changePassword(
            Authentication authentication,
            @Valid @RequestBody ChangePasswordRequestDto changePasswordRequest
    ) {
        String username = authentication.getName();
        userService.changePassword(username, changePasswordRequest);
        return ResponseEntity.ok(GenericResponse.success("Password changed successfully", null));
    }

    /**
     * Delete current user's account
     */
    @DeleteMapping("/profile")
    public ResponseEntity<GenericResponse<Void>> deleteProfile(Authentication authentication) {
        String username = authentication.getName();
        userService.deleteUser(username);
        return ResponseEntity.ok(GenericResponse.success("Account deleted successfully", null));
    }
}
