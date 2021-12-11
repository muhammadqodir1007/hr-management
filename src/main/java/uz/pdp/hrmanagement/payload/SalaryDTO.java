package uz.pdp.hrmanagement.payload;

import lombok.Data;

import java.util.UUID;

@Data
public class SalaryDTO {
    private UUID userId;
    private double summa;
    private String month;
    private int year;
}
