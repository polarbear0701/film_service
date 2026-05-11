package nguyen.userservice.service;

import lombok.AllArgsConstructor;
import lombok.NonNull;
import nguyen.userservice.dto.UserRegisterRequestDto;
import nguyen.userservice.dto.UserResponseDto;
import nguyen.userservice.model.Users;
import nguyen.userservice.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;

    @Override
    @NonNull
    public UserDetails loadUserByUsername(@NonNull String username) throws UsernameNotFoundException {
        return userRepository.findByUserName(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username));
    }

}
