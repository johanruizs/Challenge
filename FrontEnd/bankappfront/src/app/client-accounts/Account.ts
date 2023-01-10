import { Transaction } from "../account-transactions/transaction"

export interface Account {
    id: number,
    accountType: String,
    accountNumber: String,
    accountStatus: String,
    balance: number,
    availableBalance: number,
    taxFree: boolean,
    creationDate: Date
    creationUser: String,
    updateDate: Date,
    updateUser: String,
    transactionsList: Transaction[]
}