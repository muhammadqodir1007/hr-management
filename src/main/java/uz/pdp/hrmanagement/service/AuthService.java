package uz.pdp.hrmanagement.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import uz.pdp.hrmanagement.entity.Role;
import uz.pdp.hrmanagement.entity.User;
import uz.pdp.hrmanagement.entity.enums.RoleName;
import uz.pdp.hrmanagement.payload.ApiResponse;
import uz.pdp.hrmanagement.payload.RegisterDTO;
import uz.pdp.hrmanagement.repository.RoleRepository;
import uz.pdp.hrmanagement.repository.UserRepository;

import java.util.Collections;
import java.util.Optional;
import java.util.UUID;

@Service
public class AuthService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;
    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    JavaMailSender javaMailSender;
    @Autowired
    RoleRepository roleRepository;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        Optional<User> byEmail = userRepository.findByEmail(s);
        if (byEmail.isPresent()) {
            return byEmail.get();
        }
        throw new UsernameNotFoundException("Bunday email mavjud emas!");
    }

    public ApiResponse register(RegisterDTO registerDTO) {
        if (userRepository.existsByEmail(registerDTO.getEmail()))
            return new ApiResponse("Bunday emailli user bor!", false);
        User user = new User();
        //bunga qaytamz
        user.setEmail(registerDTO.getEmail());
        user.setFirstName(registerDTO.getFirstName());
        user.setLastName(registerDTO.getLastName());
        user.setPassword(passwordEncoder.encode(registerDTO.getPassword()));
        user.setCode(UUID.randomUUID().toString().substring(0, 6));

        Optional<Role> byName = roleRepository.findByName(String.valueOf(RoleName.USER));
        user.setRoles(Collections.singleton(byName.get()));

        //shu joyida bazaga saqlashdan oldin emailga code yuborish kk
        sendCode(user.getEmail(), user.getCode());
        userRepository.save(user);
        return new ApiResponse("Success", true, user);
    }

    public boolean sendCode(String email, String code) {
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom("bootcampzoom97@gmail.com");
            message.setTo(email);
            message.setSubject("Tasdiqlash kodi un link");
            message.setText("<a href='localhost/api/auth/verifyEmail?email=" + email + "&code=" + code + "'>Tasdiqlash  kodi</a>");
            javaMailSender.send(message); //yuborish
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public ApiResponse verify(String email, String code, String password) {
        Optional<User> byEmailAndCode = userRepository.findByEmailAndCode(email, code);
        if (byEmailAndCode.isPresent()) {
            User user = byEmailAndCode.get();
            user.setEnabled(true);
            user.setPassword(passwordEncoder.encode(password));
            user.setCode(null);
            userRepository.save(user);
            return new ApiResponse(" User Tasdiqlandi!", true);
        }
        return new ApiResponse("Xatolik!", false);
    }
}
