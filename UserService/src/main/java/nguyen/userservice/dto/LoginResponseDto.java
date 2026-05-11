package nguyen.userservice.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class LoginResponseDto {
    private String token;
}
