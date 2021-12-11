package uz.pdp.hrmanagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.pdp.hrmanagement.payload.ApiResponse;
import uz.pdp.hrmanagement.payload.SalaryDTO;
import uz.pdp.hrmanagement.repository.SalaryRepository;
import uz.pdp.hrmanagement.service.SalaryService;

@RestController
@RequestMapping("/api/salary")
public class SalaryController {

    @Autowired
    SalaryRepository salaryRepository;
    @Autowired
    SalaryService salaryService;

    @PostMapping
    public HttpEntity<?> add(@RequestBody SalaryDTO salaryDTO) {
        ApiResponse response = salaryService.add(salaryDTO);
        return ResponseEntity.status(response.isSuccess() ? 201 : 409).body(response);
    }


    //malum bir xodim oyliklari



    //2021 yilda qancha kimlarga oylik berilgan

}
