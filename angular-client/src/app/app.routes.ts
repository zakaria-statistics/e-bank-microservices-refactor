import { Routes } from '@angular/router';
import {AccountsComponent} from "./components/accounts/accounts.component";
import {CustomersComponent} from "./components/customers/customers.component";

export const routes: Routes = [
  {path: 'accounts', component: AccountsComponent},
  {path: 'customers', component: CustomersComponent},
  //{ path: '', redirectTo: 'accounts', pathMatch: 'full' }
];
