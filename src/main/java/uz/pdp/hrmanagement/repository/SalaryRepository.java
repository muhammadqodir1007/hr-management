package uz.pdp.hrmanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.hrmanagement.entity.Salary;

import java.util.List;
import java.util.UUID;

public interface SalaryRepository extends JpaRepository<Salary, UUID> {

    //xodimni oyliklari
//    List<Salary> findAllByYearAndUser_Id(int year, UUID userId); //2021 jafar //4 => 4mln
//
//    List<Salary> findAllByYear(int year);
//
//    List<Salary> findSalariesByCreatedAt_Year(int year);

}
