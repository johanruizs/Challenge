import { Account } from "../client-accounts/Account";

export interface Transaction {
    id: number,
    linkedAccount: Account,
    accountToTransfer: String,
    transactionDate: Date,
    transactionType: String,
    description: String,
    transactionValue: number,
    movementType: String
}