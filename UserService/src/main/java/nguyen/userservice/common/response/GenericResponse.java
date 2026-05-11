package nguyen.userservice.common.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class GenericResponse<T> {
    private String status;
    private String message;
    private T data;

    public static <T> GenericResponse<T> success(String message, T data) {
        return GenericResponse.<T>builder()
                .status("SUCCESS")
                .message(message)
                .data(data)
                .build();
    }

    public static <T> GenericResponse<T> success(T data) {
        return GenericResponse.<T>builder()
                .status("SUCCESS")
                .message("Operation successful")
                .data(data)
                .build();
    }

    public static <T> GenericResponse<T> error(String message) {
        return GenericResponse.<T>builder()
                .status("ERROR")
                .message(message)
                .build();
    }

    public static <T> GenericResponse<T> error(String message, T data) {
        return GenericResponse.<T>builder()
                .status("ERROR")
                .message(message)
                .data(data)
                .build();
    }
}
