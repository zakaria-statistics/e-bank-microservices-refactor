package net.zakaria.accountservice.web;

import net.zakaria.accountservice.clients.CustomerRestClient;
import net.zakaria.accountservice.entities.BankAccount;
import net.zakaria.accountservice.model.Customer;
import net.zakaria.accountservice.repository.BankAccountRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/accounts")
public class AccountRestController {
    private BankAccountRepository accountRepository;
    private CustomerRestClient customerRestClient;
    public AccountRestController(BankAccountRepository bankAccountRepository, CustomerRestClient customerRestClient) {
        this.accountRepository = bankAccountRepository;
        this.customerRestClient = customerRestClient;
    }

    @GetMapping("/")
    public List<BankAccount> accountList() {
        List<BankAccount> bankAccountList = accountRepository.findAll();
        bankAccountList.forEach(b -> {
            Customer customer = customerRestClient.findCustomerById(b.getCustomerId());
            b.setCustomer(customer);
        });
        return bankAccountList;
    }
    @GetMapping("/{id}")
    public BankAccount accountById(@PathVariable String id) {
        BankAccount bankAccount = accountRepository.findById(id).get();
        Customer customer = customerRestClient.findCustomerById(bankAccount.getCustomerId());
        bankAccount.setCustomer(customer);
        return bankAccount;
    }
}
