import { Injectable } from "@angular/core";
import { HttpClient } from "@angular/common/http";
import { Observable } from "rxjs";
import { Transaction } from "./transaction";
import { environment } from "src/environments/environment";
import { Account } from "../client-accounts/Account";

@Injectable({providedIn: 'root'})
export class TransactionService {

    private apiServerUrl = environment.apiBaseUrl

    constructor(private http: HttpClient){}

    public getTransactions(): Observable<Transaction[]> {
        return this.http.get<Transaction[]>(`${this.apiServerUrl}/transactions`);
    }

    public makeConsignment(accountId: number, consignment: Transaction): Observable<Transaction> {
        return this.http.post<Transaction>(`${this.apiServerUrl}/transactions/consignment/${accountId}`, consignment)
    }

    public makeWithdrawal(accountId: number, withdrawal: Transaction): Observable<Transaction> {
        return this.http.post<Transaction>(`${this.apiServerUrl}/transactions/withdrawal/${accountId}`, withdrawal)

    }

    public makeTransfer(accountId: number, transfer: Transaction): Observable<Transaction> {
        return this.http.post<Transaction>(`${this.apiServerUrl}/transactions/transfer/${accountId}`, transfer)
    }

    public activeInactiveAccount(accountId: number, account: Account): Observable <Account>{
        return this.http.put<Account>(`${this.apiServerUrl}/accounts/status/${accountId}`, account)
    }

    public cancelAccount(accountId: number, account: Account): Observable <Account>{
        return this.http.put<Account>(`${this.apiServerUrl}/accounts/cancel/${accountId}`, account)
    }


}