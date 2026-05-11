package nguyen.userservice.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class LoginRequestDto {
    @NotBlank(message = "Username cannot be blank")
    private String userName;

    @NotBlank(message = "Password cannot be blank")
    private String password;
}
