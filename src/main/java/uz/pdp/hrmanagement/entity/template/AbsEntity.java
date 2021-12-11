package uz.pdp.hrmanagement.entity.template;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.sql.Timestamp;
import java.util.UUID;

@MappedSuperclass
@Data
public abstract class AbsEntity {
    @Id
    @GeneratedValue
    private UUID id;

    @CreationTimestamp
    @Column(updatable = false, nullable = false)
    private Timestamp createdAt;

    @UpdateTimestamp
    private Timestamp updatedAt;

    @CreatedBy
    @Column(updatable = false, nullable = false)
    private UUID createdBy;

    @LastModifiedBy
    @Column(nullable = false)
    private UUID updatedBy;

}
