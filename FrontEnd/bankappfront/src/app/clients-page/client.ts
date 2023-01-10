import { Account } from "../client-accounts/Account";

export interface Client {
    id: number,
    typeId: String,
    numberId: String,
    name: String,
    lastName: String,
    email: String,
    birthDate: Date,
    creationDate: Date,
    creationUser: String,
    updateDate: Date,
    updateUser: String,
    accounts: Account[]
}