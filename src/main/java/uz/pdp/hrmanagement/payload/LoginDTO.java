package uz.pdp.hrmanagement.payload;

import lombok.Data;

@Data
public class LoginDTO {
    private String email;
    private String password;
}
