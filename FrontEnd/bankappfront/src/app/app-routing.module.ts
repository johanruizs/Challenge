import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ClientAccountsComponent } from './client-accounts/client-accounts.component';
import { ClientsPageComponent } from './clients-page/clients-page.component';

const routes: Routes = [
  {path: '', redirectTo: '/clients', pathMatch: 'full'},
  {path: 'clients', component: ClientsPageComponent, pathMatch: 'full'},
  {path: 'accounts/:id', component: ClientAccountsComponent, pathMatch: 'full'} //ruta para cada cliente
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
