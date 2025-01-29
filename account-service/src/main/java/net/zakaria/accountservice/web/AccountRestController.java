package net.zakaria.accountservice.web;

import net.zakaria.accountservice.entities.BankAccount;
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
    public AccountRestController(BankAccountRepository bankAccountRepository) {
        this.accountRepository = bankAccountRepository;
    }

    @GetMapping("/")
    public List<BankAccount> accountList() {
        return accountRepository.findAll();
    }
    @GetMapping("/{id}")
    public BankAccount accountById(@PathVariable String id) {
        return accountRepository.findById(id).get();
    }
}
