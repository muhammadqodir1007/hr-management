package uz.pdp.hrmanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.hrmanagement.entity.Role;

import java.util.Optional;


public interface RoleRepository extends JpaRepository<Role, Integer> {
    Optional<Role> findByName(String name);
}
