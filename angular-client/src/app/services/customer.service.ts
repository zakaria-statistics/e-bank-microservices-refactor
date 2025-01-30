import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class CustomerService {
  private baseUrl = 'http://localhost:8888/CUSTOMER-SERVICE/api/customers/';
  constructor(private http: HttpClient) { }
  getCustomers(): Observable<any[]> {
    return this.http.get<any[]>(this.baseUrl);
  }
  getCustomerById(id: number): Observable<any> {
    return this.http.get<any>(this.baseUrl + '/' + id);
  }
}

