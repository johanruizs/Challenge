import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';

import { ActivatedRoute } from '@angular/router';
import { Client } from '../clients-page/client';
import { ClientService } from '../clients-page/client.service';
import { Account } from './Account';
import { AccountService } from './account.service'


@Component({
  selector: 'app-client-accounts',
  templateUrl: './client-accounts.component.html',
  styleUrls: ['./client-accounts.component.css']
})

export class ClientAccountsComponent implements OnInit{

  public accounts: Account[] = [];

  client: Client;
  clientId: number;

  constructor(
    private route: ActivatedRoute,
    private clientService: ClientService,
    private accountService: AccountService, 
  ){}

  ngOnInit(): void {

    const id = this.route.snapshot.paramMap.get('id')!;
    this.clientService.getClientById(id).subscribe({
      next: (response: Client) => {
        this.client = response;
      },
      error: (error: HttpErrorResponse) => {
        alert(error.message);
      }
    });

  }

  public getAccounts(): void {
    this.accountService.getAccounts().subscribe({
      next: (response: Account[]) => {
        this.accounts = response;
      },
      error: (error: HttpErrorResponse) => {
        alert(error.message);
      }
    });
  }

  public onAddAccount(addForm: NgForm): void {
    document.getElementById('add-account-form')?.click();
    this.accountService.addAccount(this.client.id, addForm.value).subscribe(
      {
        next:(response: Account) => {
          console.log(response);
          alert('Account Created')
          location.reload();
          addForm.reset();
        },
        error:() => {
          alert('Please try again');
          addForm.reset();
        }
      });
  }

  public onOpenModal(mode: string): void {
    const container = document.getElementById('main-container');
    const button = document.createElement('button');
    button.type = 'button';
    button.style.display = 'none';
    button.setAttribute('data-toggle', 'modal');
    if (mode === 'add') {
      button.setAttribute('data-target', '#addAccountModal');
    }
    container?.appendChild(button);
    button.click();
  }

}
