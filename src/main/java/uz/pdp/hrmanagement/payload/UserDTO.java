package uz.pdp.hrmanagement.payload;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class UserDTO {
    @NotNull
    private String firstName, lastName, email, position;

}
