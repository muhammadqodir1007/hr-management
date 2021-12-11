package uz.pdp.hrmanagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import uz.pdp.hrmanagement.entity.Role;
import uz.pdp.hrmanagement.entity.User;
import uz.pdp.hrmanagement.entity.enums.Position;
import uz.pdp.hrmanagement.entity.enums.RoleName;
import uz.pdp.hrmanagement.payload.ApiResponse;
import uz.pdp.hrmanagement.payload.UserDTO;
import uz.pdp.hrmanagement.repository.RoleRepository;
import uz.pdp.hrmanagement.repository.UserRepository;
import uz.pdp.hrmanagement.service.UserService;

import javax.validation.Valid;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/user")
public class UserController {
    @Autowired
    UserService userService;
    @Autowired
    RoleRepository roleRepository;
    @Autowired
    UserRepository userRepository;

    //    manager,xodim qo'shish
    @PreAuthorize(value = "hasAnyRole('DIRECTOR','MANAGER')")
    @PostMapping("/add")
    public HttpEntity<?> addStaff(@Valid @RequestBody UserDTO userDTO) {
        ApiResponse response = userService.add(userDTO);
        return ResponseEntity.status(response.isSuccess() ? 201 : 409).body(response);
    }

    //hr manager,director pre auth
    @PreAuthorize(value = "hasAnyRole('DIRECTOR','MANAGER')")
    @GetMapping("/staffList")
    public HttpEntity<?> getStaffList() {
        List<User> byPosition = userRepository.findAllByPosition(Position.XODIM.toString());
        return ResponseEntity.ok(new ApiResponse("Xodimlar ro'yxati", true, byPosition));
    }

    //director
    @GetMapping("/managerList")
    public HttpEntity<?> getManagerList() {

        Optional<Role> byName = roleRepository.findByName("MANAGER");

        List<User> allByRoles = userRepository.findAllByRolesIn(Collections.singleton(byName.get()));

//        List<User> allByRoleName = userRepository.getAllByRoleName(RoleName.MANAGER.toString());
        return ResponseEntity.ok(new ApiResponse("Managers", true, allByRoles));
    }
}
