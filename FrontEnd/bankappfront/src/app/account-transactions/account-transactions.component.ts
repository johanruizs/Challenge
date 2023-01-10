import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';

import { ActivatedRoute } from '@angular/router';
import { Account } from '../client-accounts/Account';
import { AccountService } from '../client-accounts/account.service';
import { Transaction } from './transaction';
import { TransactionService } from './transaction.service'


@Component({
  selector: 'app-account-transactions',
  templateUrl: './account-transactions.component.html',
  styleUrls: ['./account-transactions.component.css']
})
export class AccountTransactionsComponent implements OnInit{

  public transactions: Transaction[] = [];

  account: Account;


  constructor(
    private route: ActivatedRoute,
    private accountService: AccountService, 
    private transactionService: TransactionService,
  ){}

  ngOnInit(): void {

    const id = this.route.snapshot.paramMap.get('id')!;
    this.accountService.getAccountById(id).subscribe({
      next: (response: Account) => {
        this.account = response;
      },
      error: (error: HttpErrorResponse) => {
        alert(error.message);
      }
    });

  }

  public getTransactions(): void {
    this.transactionService.getTransactions().subscribe({
      next: (response: Transaction[]) => {
        this.transactions = response;
      },
      error: (error: HttpErrorResponse) => {
        alert(error.message);
      }
    });
  }

  public onActiveInactiveAccount(clientId: number, account: Account): void {
    this.account = account;
    this.account.id = clientId
    this.transactionService.activeInactiveAccount(this.account.id, this.account).subscribe({
      next:(response: Account) => {
        console.log(response);
        alert('Status changed');
        location.reload();
      },
      error:(error: HttpErrorResponse) => {
        alert(error.message);
      }
    });
  }

  public onCancelAccount(clientId: number, account: Account): void {
    this.account = account;
    this.account.id = clientId
    this.transactionService.cancelAccount(this.account.id, this.account).subscribe({
      next:(response: Account) => {
        console.log(response);
        alert('This account has been cancelled')
        location.reload();
      },
      error:(error: HttpErrorResponse) => {
        alert(error.message);
      }
    });
  }

  public onAddConsignment(consignmentForm: NgForm): void {
    document.getElementById('add-consignment-form')?.click();
    this.transactionService.makeConsignment(this.account.id, consignmentForm.value).subscribe(
      {
        next:(response: Transaction) => {
          console.log(response);
          alert('Successfull consignment');
          location.reload();
          consignmentForm.reset();
        },
        error:() => {
          alert('The account must be activated');
          alert('Failed consignment');
          consignmentForm.reset();
        }
      });
  }


  public onAddWithdrawal(withdrawalForm: NgForm): void {
    document.getElementById('add-withdrawal-form')?.click();
    this.transactionService.makeWithdrawal(this.account.id, withdrawalForm.value).subscribe(
      {
        next:(response: Transaction) => {
          console.log(response);
          alert('Successfull withdrawal');
          location.reload();
          withdrawalForm.reset();
        },
        error:() => {
          alert('The account must be activated or Insufficient funds');
          alert('Failed withdrawal');
          withdrawalForm.reset();
        }
      });
  }

  public onAddTransfer(transferForm: NgForm): void {
    document.getElementById('add-transfer-form')?.click();
    this.transactionService.makeTransfer(this.account.id, transferForm.value).subscribe(
      {
        next:(response: Transaction) => {
          console.log(response);
          alert('Successfull transfer');
          location.reload();
          transferForm.reset();
        },
        error:() => {
          alert('Failed transfer');
          alert('Please check if the account is activate, the Receiver Account Number or the funds on this account')
          transferForm.reset();
        }
      });
  }

  public onOpenModal(mode: string): void {
    const container = document.getElementById('main-container');
    const button = document.createElement('button');
    button.type = 'button';
    button.style.display = 'none';
    button.setAttribute('data-toggle', 'modal');
    if (mode === 'consignment') {
      button.setAttribute('data-target', '#addConsignmentModal');
    } else if (mode === 'withdrawal') {
      button.setAttribute('data-target', '#addWithdrawalModal');
    } else if (mode === 'transfer') {
      button.setAttribute('data-target', '#addTransferModal');
    }
    container?.appendChild(button);
    button.click();
  }



}
