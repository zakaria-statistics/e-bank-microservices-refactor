import {Component, OnInit} from '@angular/core';
import {CustomerService} from "../../services/customer.service";
import {NgForOf, NgIf} from "@angular/common";

@Component({
  selector: 'app-customers',
  standalone: true,
  imports: [
    NgIf,
    NgForOf
  ],
  templateUrl: './customers.component.html',
  styleUrl: './customers.component.css'
})
export class CustomersComponent implements OnInit{
  customers: any[] = [];
  constructor(private customerService: CustomerService) { }
  ngOnInit(){
    this.fetchCustomers();
  }
  fetchCustomers(){
    this.customerService.getCustomers().subscribe({
      next: data => this.customers = data,
      error: error => console.log(error),
      complete: () => console.log('done')
    })
  }
}
