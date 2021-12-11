package uz.pdp.hrmanagement.payload;

import lombok.Data;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Data
public class TaskDTO {
    private String title, description;

    private Date deadline;

    private List<UUID> usersId;



}
