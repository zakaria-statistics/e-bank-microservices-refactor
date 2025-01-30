import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class AccountService {
  private baseUrl = 'http://localhost:8888/ACCOUNT-SERVICE/api/accounts/';
  constructor(private http: HttpClient) { }
  getAllAccounts(): Observable<any[]> {
    return this.http.get<any[]>(this.baseUrl);
  }
  getAccountById(id: number): Observable<any> {
    return this.http.get<any>(this.baseUrl + '/' + id);
  }
}
