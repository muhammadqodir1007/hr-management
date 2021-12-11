package uz.pdp.hrmanagement.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import uz.pdp.hrmanagement.entity.enums.Month;
import uz.pdp.hrmanagement.entity.template.AbsEntity;

import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.ManyToOne;

@EqualsAndHashCode(callSuper = true)
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Salary extends AbsEntity {

    @ManyToOne
    private User user;

    private double sum;

    @Enumerated
    private Month month; //jan

    private int year; //2021
}
