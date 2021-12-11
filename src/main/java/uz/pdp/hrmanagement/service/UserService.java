package uz.pdp.hrmanagement.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import uz.pdp.hrmanagement.component.MailSender;
import uz.pdp.hrmanagement.entity.Role;
import uz.pdp.hrmanagement.entity.User;
import uz.pdp.hrmanagement.entity.enums.Position;
import uz.pdp.hrmanagement.entity.enums.RoleName;
import uz.pdp.hrmanagement.payload.ApiResponse;
import uz.pdp.hrmanagement.payload.UserDTO;
import uz.pdp.hrmanagement.repository.RoleRepository;
import uz.pdp.hrmanagement.repository.UserRepository;

import java.util.*;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;
    @Autowired
    MailSender mailSender;

    @Autowired
    RoleRepository roleRepository;

    public ApiResponse add(UserDTO userDTO) {

        User user = new User();
        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        user.setEmail(userDTO.getEmail());
        user.setCode(UUID.randomUUID().toString().substring(0, 6));
        user.setPassword(user.getCode());
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();

        for (GrantedAuthority authority : authorities) {
            if (authority.getAuthority().equals(RoleName.DIRECTOR.toString())) {
                //manager qo'shish
                Set<Role> roleSet = new HashSet<Role>();

                Optional<Role> manager = roleRepository.findByName("MANAGER");
                Optional<Role> roleUser = roleRepository.findByName("USER");
                roleSet.add(manager.get());
                roleSet.add(roleUser.get());
                user.setRoles(roleSet);
                user.setPosition(Position.valueOf(userDTO.getPosition()));
            }
            boolean hr = Position.HR_MANAGER.toString().equals(userDTO.getPosition());
            if (hr && authority.getAuthority().equals(RoleName.MANAGER.toString())) {
                //faqat hr xodim qo'shyapti
                user.setRoles(Collections.singleton(new Role(RoleName.USER)));
                user.setPosition(Position.XODIM);
            }
        }

        mailSender.sendVerify(userDTO.getEmail(), user.getCode());
        userRepository.save(user);

        return new ApiResponse("Saved Staff", true);
    }


}
