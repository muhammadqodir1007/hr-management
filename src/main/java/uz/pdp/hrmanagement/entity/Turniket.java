package uz.pdp.hrmanagement.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import uz.pdp.hrmanagement.entity.template.AbsEntity;

import javax.persistence.Entity;
import javax.persistence.OneToOne;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Turniket extends AbsEntity {

    @OneToOne
    private User user;

    private Boolean active = null; //null kirmagan true in false out

    private String number;
}
