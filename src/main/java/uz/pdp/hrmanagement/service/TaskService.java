package uz.pdp.hrmanagement.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.hrmanagement.component.MailSender;
import uz.pdp.hrmanagement.entity.Task;
import uz.pdp.hrmanagement.entity.User;
import uz.pdp.hrmanagement.entity.enums.TaskStatus;
import uz.pdp.hrmanagement.payload.ApiResponse;
import uz.pdp.hrmanagement.payload.TaskDTO;
import uz.pdp.hrmanagement.repository.TaskRepository;
import uz.pdp.hrmanagement.repository.UserRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class TaskService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    TaskRepository taskRepository;

    @Autowired
    MailSender mailSender;

    public ApiResponse add(TaskDTO taskDTO) {
        Task task = new Task();
        task.setTitle(taskDTO.getTitle());
        task.setDescription(taskDTO.getDescription());
        task.setTaskStatus(TaskStatus.NEW);
        List<User> userList = userRepository.findAllById(taskDTO.getUsersId());
        if (!userList.isEmpty()) {
            task.setUsers(userList);
        }
        task.setDeadLine(taskDTO.getDeadline());


        taskRepository.save(task);
        for (User user : userList) {
            mailSender.sendTask(user.getEmail(), task.getTitle(), task.getId());

        }
        return new ApiResponse("Success", true);

    }

    public ApiResponse completed(UUID id) {
        Optional<Task> byId = taskRepository.findById(id);
        if (!byId.isPresent()) return new ApiResponse("Xatolik", false);

        Task task = byId.get();
        task.setCompleted(true);
        taskRepository.save(task);
        //kim task qo'shgan
        Optional<User> byId1 = userRepository.findById(task.getCreatedBy());
        mailSender.completedTask(byId1.get().getEmail(), task.getTitle(), id);

        return new ApiResponse("Task completed!", true);
    }
}
