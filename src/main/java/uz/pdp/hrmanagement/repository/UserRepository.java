package uz.pdp.hrmanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.hrmanagement.entity.Role;
import uz.pdp.hrmanagement.entity.User;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {
    boolean existsByEmail(String email);

    Optional<User> findByEmail(String email);

    Optional<User> findByEmailAndCode(String email, String code);

    List<User> findAllByPosition(String position);

    List<User> findAllByRolesIn(Set<Role> roles);

//    @Query(value = "select u from users u inner join users_roles ur on u.id = ur.users_id inner join role r on r.id = ur.roles_id where r.name=:name")
//    List<User> getAllByRoleName(@Param("name") String name);

}
