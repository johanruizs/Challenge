import { HttpClientModule } from '@angular/common/http';
import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { FormsModule } from '@angular/forms';
import { ClientAccountsComponent } from './client-accounts/client-accounts.component';
import { ClientsPageComponent } from './clients-page/clients-page.component';
import { RouterModule } from '@angular/router';
import { AccountTransactionsComponent } from './account-transactions/account-transactions.component';
import { DeveloperProfileComponent } from './developer-profile/developer-profile.component';

@NgModule({
  declarations: [
    AppComponent,
    ClientAccountsComponent,
    ClientsPageComponent,
    AccountTransactionsComponent,
    DeveloperProfileComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    FormsModule,
    AppRoutingModule,
    RouterModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
