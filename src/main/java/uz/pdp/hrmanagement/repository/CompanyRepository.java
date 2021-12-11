package uz.pdp.hrmanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.hrmanagement.entity.Company;

public interface CompanyRepository extends JpaRepository<Company, Integer> {
}
