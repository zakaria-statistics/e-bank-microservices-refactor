package net.zakaria.customerservice;

import net.zakaria.customerservice.entities.Customer;
import net.zakaria.customerservice.repository.CustomerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class CustomerServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(CustomerServiceApplication.class, args);
    }

    @Bean
    CommandLineRunner commandLineRunner(CustomerRepository customerRepository) {
        return args -> {
            List<Customer> customers = List.of(
                    Customer.builder()
                            .firstName("Hassan")
                            .lastName("bohadou")
                            .email("hassan@gmail.com")
                            .build(),
                    Customer.builder()
                            .firstName("Zakaria")
                            .lastName("El-Khalifa")
                            .email("zakaria@gmail.com")
                            .build()
            );
            customerRepository.saveAll(customers);
        };
    }

}
