import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ClientAccountsComponent } from './client-accounts/client-accounts.component';

const routes: Routes = [
  {path: 'accounts', component: ClientAccountsComponent, pathMatch: 'full'}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
