package uz.pdp.hrmanagement.component;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import uz.pdp.hrmanagement.entity.Company;
import uz.pdp.hrmanagement.entity.Role;
import uz.pdp.hrmanagement.entity.User;
import uz.pdp.hrmanagement.entity.enums.RoleName;
import uz.pdp.hrmanagement.repository.CompanyRepository;
import uz.pdp.hrmanagement.repository.RoleRepository;
import uz.pdp.hrmanagement.repository.UserRepository;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
public class DataLoader implements CommandLineRunner {
    @Value("${spring.datasource.initialization-mode}")
    private String initMode;

    @Autowired
    CompanyRepository companyRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    RoleRepository roleRepository;

    @Override
    public void run(String... args) throws Exception {
        Role userRole = new Role(3, RoleName.USER);
        Role director = new Role(1, RoleName.DIRECTOR);
        Role manager = new Role(2, RoleName.MANAGER);

        List<Role> roleSet = new ArrayList<>();
        roleSet.add(userRole);
        roleSet.add(director);
        roleSet.add(manager);
        roleRepository.saveAll(roleSet);

        List<Role> all = roleRepository.findAll();
        Set<Role> roles = new HashSet<>(all);
        if (initMode.equals("always")) {
            User user = new User("Jafarbek", "Turayev", "jafarbek1997@gmail.com",
                    passwordEncoder.encode("123"), roles, true);
            userRepository.save(user);
            Company company = new Company(user);
            company.setName("PDP");
            companyRepository.save(company);
        }
    }
}
