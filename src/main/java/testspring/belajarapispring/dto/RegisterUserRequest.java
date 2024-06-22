package testspring.belajarapispring.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RegisterUserRequest {

    @NotBlank
    @Size(max = 100)
    private String username;

    @Size(max = 100)
    @NotBlank
    private String password;

    @Size(max = 100)
    @NotBlank
    private String email;

    @Size(max = 100)
    @NotBlank
    private String name;

}
