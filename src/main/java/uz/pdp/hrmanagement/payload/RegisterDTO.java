package uz.pdp.hrmanagement.payload;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class RegisterDTO {
    @NotNull
    private String firstName, lastName, password;

    @NotNull
    @Size(min = 3, max = 50)
    private String email;
}
