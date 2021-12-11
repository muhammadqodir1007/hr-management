package uz.pdp.hrmanagement.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.hrmanagement.entity.Salary;
import uz.pdp.hrmanagement.entity.User;
import uz.pdp.hrmanagement.entity.enums.Month;
import uz.pdp.hrmanagement.payload.ApiResponse;
import uz.pdp.hrmanagement.payload.SalaryDTO;
import uz.pdp.hrmanagement.repository.SalaryRepository;
import uz.pdp.hrmanagement.repository.UserRepository;

import java.util.Optional;

@Service
public class SalaryService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    SalaryRepository salaryRepository;

    public ApiResponse add(SalaryDTO salaryDTO) {
        Salary salary = new Salary();
        salary.setMonth(Month.valueOf(salaryDTO.getMonth()));
        salary.setYear(salaryDTO.getYear());
        salary.setSum(salaryDTO.getSumma());
        Optional<User> byId = userRepository.findById(salaryDTO.getUserId());
        if (!byId.isPresent()) return new ApiResponse("Not found", false);
        salary.setUser(byId.get());

        salaryRepository.save(salary);
        return new ApiResponse("Saved", true);
    }
}
