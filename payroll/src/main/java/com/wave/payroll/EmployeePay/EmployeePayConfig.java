package com.wave.payroll.EmployeePay;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.util.List;

@Configuration
public class EmployeePayConfig {

    @Bean
    CommandLineRunner commandLineRunner(EmployeePayRepository repository)
    {
        return args -> {};
        /*return args -> {
            EmployeePay a = new EmployeePay(
                    LocalDate.now(),
                    1L,
                    44L,
                    "A",
                    7.5F
            );

            repository.saveAll(List.of(a));
        };*/
    }

}

