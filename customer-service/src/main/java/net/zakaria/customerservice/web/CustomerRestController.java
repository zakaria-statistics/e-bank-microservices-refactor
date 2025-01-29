package net.zakaria.customerservice.web;

import net.zakaria.customerservice.entities.Customer;
import net.zakaria.customerservice.repository.CustomerRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/customers") // Removed extra trailing slash
public class CustomerRestController {
    private final CustomerRepository customerRepository;

    public CustomerRestController(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @GetMapping("/") // Matches `/api/customers`
    public List<Customer> customerList() {
        return customerRepository.findAll();
    }

    @GetMapping("/{id}") //  Matches `/api/customers/{id}`
    public Customer customerById(@PathVariable Long id) {
        return customerRepository.findById(id).orElse(null);
    }
}
