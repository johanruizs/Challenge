import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Account } from './Account'
import { environment } from 'src/environments/environment';


@Injectable({providedIn: 'root'})
export class AccountService {

    private apiServerUrl = environment.apiBaseUrl

    constructor(private http: HttpClient){}


    public addAccount(clientId: number, account: Account): Observable<Account> {
        return this.http.post<Account>(`${this.apiServerUrl}/accounts/${clientId}`, account);
    }

    public getAccounts(): Observable<Account[]> {
        return this.http.get<Account[]>(`${this.apiServerUrl}/accounts`);
    }

    public getAccountById(id: String): Observable<Account>{
        return this.http.get<Account>(`${this.apiServerUrl}/accounts/${id}`)
    }
 
}
