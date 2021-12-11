package uz.pdp.hrmanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.hrmanagement.entity.Task;
import uz.pdp.hrmanagement.entity.User;

import java.util.List;
import java.util.UUID;

public interface TaskRepository extends JpaRepository<Task, UUID> {
 List<Task> findAllByUsersIn(List<User> users);
}
