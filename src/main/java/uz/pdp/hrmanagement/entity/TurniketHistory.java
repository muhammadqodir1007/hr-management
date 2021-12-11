package uz.pdp.hrmanagement.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import uz.pdp.hrmanagement.entity.template.AbsEntity;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import java.sql.Timestamp;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TurniketHistory extends AbsEntity {

    @ManyToOne
    private Turniket turniket;

    private Timestamp dateIn;

    private Timestamp dateOut;

}
