package uz.pdp.hrmanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.hrmanagement.entity.TurniketHistory;

import java.util.UUID;

public interface TurniketHistoryRepository extends JpaRepository<TurniketHistory, UUID> {
}
