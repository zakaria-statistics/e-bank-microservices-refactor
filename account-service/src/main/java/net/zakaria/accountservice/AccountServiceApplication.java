package net.zakaria.accountservice;

import net.zakaria.accountservice.clients.CustomerRestClient;
import net.zakaria.accountservice.entities.BankAccount;
import net.zakaria.accountservice.enums.AccountType;
import net.zakaria.accountservice.repository.BankAccountRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@SpringBootApplication
@EnableFeignClients
public class AccountServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(AccountServiceApplication.class, args);
	}

	@Bean
	CommandLineRunner commandLineRunner(BankAccountRepository bankAccountRepository, CustomerRestClient customerRestClient) {
		return args -> {
			customerRestClient.allCustomers().forEach(c -> {
				List<BankAccount> bankAccountList = List.of(
						BankAccount.builder()
								.accountId(UUID.randomUUID().toString())
								.currency("MAD")
								.balance(1200)
								.createAt(LocalDate.now())
								.type(AccountType.SAVING_ACCOUNT)
								.customerId(c.getId())
								.build(),
						BankAccount.builder()
								.accountId(UUID.randomUUID().toString())
								.currency("MAD")
								.balance(2400)
								.createAt(LocalDate.now())
								.type(AccountType.CURRENT_ACCOUNT)
								.customerId(c.getId())
								.build()
				);
				bankAccountRepository.saveAll(bankAccountList);
			});
		};
	}

}
