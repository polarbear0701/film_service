package nguyen.userservice.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class UserResponseDto {
    private String username;
    private String loginName;
}
