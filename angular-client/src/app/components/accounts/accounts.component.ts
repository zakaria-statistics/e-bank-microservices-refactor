import {Component, OnInit} from '@angular/core';
import {CommonModule} from '@angular/common';
import {AccountService} from "../../services/account.service";


@Component({
  selector: 'app-accounts',
  standalone: true,
  imports: [
    CommonModule
  ],
  templateUrl: './accounts.component.html',
  styleUrl: './accounts.component.css'
})
export class AccountsComponent implements OnInit {
  accounts: any[] = [];
  constructor(private accountService: AccountService) { }
  ngOnInit() {
    this.fetchAccounts();
  }
  fetchAccounts() {
    this.accountService.getAllAccounts().subscribe({
      next: data => this.accounts = data,
      error: error => console.log(error),
      complete: () => console.log('done')
    });
  }
}
