import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AccountTransactionsComponent } from './account-transactions/account-transactions.component';
import { ClientAccountsComponent } from './client-accounts/client-accounts.component';
import { ClientsPageComponent } from './clients-page/clients-page.component';
import { DeveloperProfileComponent } from './developer-profile/developer-profile.component';

const routes: Routes = [
  {path: '', redirectTo: '/clients', pathMatch: 'full'},
  {path: 'return', redirectTo: '/accounts/:id', pathMatch: 'full'},
  {path: 'clients', component: ClientsPageComponent, pathMatch: 'full'},
  {path: 'accounts/:id', component: ClientAccountsComponent, pathMatch: 'full'}, //ruta para cada cliente
  {path: 'transactions/:id', component: AccountTransactionsComponent, pathMatch: 'full'},
  {path: 'developer', component: DeveloperProfileComponent, pathMatch: 'full'}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
