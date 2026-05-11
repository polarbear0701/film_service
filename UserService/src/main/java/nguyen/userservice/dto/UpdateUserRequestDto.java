package nguyen.userservice.dto;

import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateUserRequestDto {
    @Size(min = 1, max = 100, message = "Display name must be between 1 and 100 characters")
    private String displayName;
}
