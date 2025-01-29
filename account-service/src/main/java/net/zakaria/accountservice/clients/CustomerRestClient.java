package net.zakaria.accountservice.clients;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import net.zakaria.accountservice.model.Customer;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
@FeignClient(name = "CUSTOMER-SERVICE")
public interface CustomerRestClient {
    @GetMapping("/api/customers/{id}")
    @CircuitBreaker(name = "customerService", fallbackMethod = "fallbackFindCustomerById")
    Customer findCustomerById(@PathVariable Long id);
    @GetMapping("/api/customers/")
    @CircuitBreaker(name = "customerService", fallbackMethod = "fallbackAllCustomers")
    List<Customer> allCustomers();

    default Customer fallbackFindCustomerById(Long id, Exception exception){
        Customer customer = new Customer();
        customer.setId(id);
        customer.setFirstName("Unknown!");
        customer.setLastName("Unknown!");
        customer.setEmail("Unknown!");
        return customer;
    };
    default List<Customer> fallbackAllCustomers(Exception exception){
        return List.of();
    };
}
