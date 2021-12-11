package uz.pdp.hrmanagement.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import uz.pdp.hrmanagement.entity.enums.TaskStatus;
import uz.pdp.hrmanagement.entity.template.AbsEntity;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToMany;
import java.util.Date;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Task extends AbsEntity {

    private String title, description;

    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date deadLine;

    @ManyToMany
    private List<User> users;

    @Enumerated(EnumType.STRING)
    private TaskStatus taskStatus;

    private boolean completed = false;


}
