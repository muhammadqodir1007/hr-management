package uz.pdp.hrmanagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.hrmanagement.component.MailSender;
import uz.pdp.hrmanagement.entity.Task;
import uz.pdp.hrmanagement.entity.User;
import uz.pdp.hrmanagement.payload.ApiResponse;
import uz.pdp.hrmanagement.payload.TaskDTO;
import uz.pdp.hrmanagement.repository.TaskRepository;
import uz.pdp.hrmanagement.repository.UserRepository;
import uz.pdp.hrmanagement.service.TaskService;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/task")
public class TaskController {
    @Autowired
    TaskService taskService;

    @Autowired
    MailSender mailSender;

    @Autowired
    TaskRepository taskRepository;

    @Autowired
    UserRepository userRepository;

    @PostMapping
    public HttpEntity<?> addTask(@RequestBody TaskDTO taskDTO) {
        ApiResponse apiResponse = taskService.add(taskDTO);
        return ResponseEntity.status(apiResponse.isSuccess() ? 201 : 409).body(apiResponse);


    }

    @GetMapping("/all/{id}")
    public HttpEntity<?> getTasks(@PathVariable UUID id) {
        Optional<User> optionalUser = userRepository.findById(id);
        if (!optionalUser.isPresent()) return ResponseEntity.ok(new ApiResponse("NOT", false));
        User user = optionalUser.get();
        List<Task> all = taskRepository.findAllByUsersIn(Collections.singletonList(user));

        return ResponseEntity.ok(all);
    }

    @GetMapping("/changeStatus/{id}")
    public HttpEntity<?> completed(@PathVariable UUID id) {
        ApiResponse completed = taskService.completed(id);
        return ResponseEntity.ok(completed);
    }

    @GetMapping("/{id}")
    public HttpEntity<?> getOne(@PathVariable UUID id) {
        Optional<Task> byId = taskRepository.findById(id);
        if (!byId.isPresent()) return ResponseEntity.status(404).body("Xatolik");
        return ResponseEntity.ok().body(byId.get());
    }
}
