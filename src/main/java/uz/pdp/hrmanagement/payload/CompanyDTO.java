package uz.pdp.hrmanagement.payload;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.UUID;

@Data
public class CompanyDTO {

    @NotNull
    private String name;
    @NotNull
    private UUID directorId;

}
