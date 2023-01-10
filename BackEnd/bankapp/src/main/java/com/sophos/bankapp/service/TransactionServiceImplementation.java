package com.sophos.bankapp.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sophos.bankapp.entity.Account;
import com.sophos.bankapp.entity.Transaction;
import com.sophos.bankapp.repository.AccountRepository;
import com.sophos.bankapp.repository.TransactionRepository;

@Service
public class TransactionServiceImplementation implements TransactionService {

    @Autowired 
    TransactionRepository transactionRepository;

    @Autowired
    AccountRepository accountRepository;

    @Override
    public Transaction createTransaction(Transaction transaction){
        return transactionRepository.save(transaction);
    }

    @Override
    public List<Transaction> getAllTransactions() {
        return transactionRepository.findAll();
    }

    @Override
    public Optional<Transaction> getTransactionById(int id) {
        return transactionRepository.findById(null);
    }

    @Override
    public boolean deleteTransactionById(int id) {
        return getTransactionById(id).map(transaction -> {
            transactionRepository.deleteById(id);
            return true;
        }).orElse(false);
    }

    @Override
    public Transaction makeConsignment(int accountId, Transaction consignment){

        Account accountToConsignment = accountRepository.findById(accountId).get();

        if (accountToConsignment.getAccountStatus() != null ){
            accountToConsignment.setBalance(accountToConsignment.getBalance().floatValue()  + consignment.getTransactionValue().floatValue());
            if (accountToConsignment.getTaxFree()){
                accountToConsignment.setAvailableBalance(accountToConsignment.getBalance());
            } else {
                accountToConsignment.setAvailableBalance(996);
            }

            consignment.setTransactionDate(LocalDate.now());
            consignment.setMovementType("Credit");
            consignment.setTransactionType("Consignment");
            consignment.setLinkedAccount(accountToConsignment);

            return transactionRepository.save(consignment);

        }

        return null;
    }

    @Override
    public Transaction makeWithdrawal(int accountId, Transaction withdrawal){

        Account accountToWithdrawal = accountRepository.findById(accountId).get();

        if (accountToWithdrawal.getAccountStatus().equalsIgnoreCase("Active")){

            // Saving accounts
            if(accountToWithdrawal.getAccountType().equalsIgnoreCase("Saving") && (accountToWithdrawal.getBalance().floatValue() - withdrawal.getTransactionValue().floatValue()>0)){

                accountToWithdrawal.setBalance(accountToWithdrawal.getBalance().floatValue() - withdrawal.getTransactionValue().floatValue());
                if (accountToWithdrawal.getTaxFree()){
                    accountToWithdrawal.setAvailableBalance(accountToWithdrawal.getBalance());
                } // } else {
                //     accountToConsignment.setAvailableBalance(996);
                // }

                withdrawal.setTransactionDate(LocalDate.now());
                withdrawal.setMovementType("Debit");
                withdrawal.setTransactionType("Withdraw");
                withdrawal.setLinkedAccount(accountToWithdrawal);

                return transactionRepository.save(withdrawal);

            // Checking accounts
            } else if (accountToWithdrawal.getAccountType().equalsIgnoreCase("Checking") && (accountToWithdrawal.getBalance().floatValue() - withdrawal.getTransactionValue().floatValue()> -3000000)){

                accountToWithdrawal.setBalance(accountToWithdrawal.getBalance().floatValue() - withdrawal.getTransactionValue().floatValue());
                if (accountToWithdrawal.getTaxFree()){
                    accountToWithdrawal.setAvailableBalance(accountToWithdrawal.getBalance());
                } // } else {
                //     accountToConsignment.setAvailableBalance(996);
                // }

                withdrawal.setTransactionDate(LocalDate.now());
                withdrawal.setMovementType("Debit");
                withdrawal.setTransactionType("Withdraw");

                return transactionRepository.save(withdrawal);

            }
            
        }

        return null;
    }

    @Override
    public Transaction makeTransfer(int accountId, Transaction transfer){ // String accountToTransfer, 

        Account senderAccount = accountRepository.findById(accountId).get();
        Account receiverAccount = accountRepository.findByAccountNumber(transfer.getAccountToTransfer());

        if (receiverAccount != null && senderAccount.getAccountStatus().equalsIgnoreCase("Active")){

            // Saving accounts
            if(senderAccount.getAccountType().equalsIgnoreCase("Saving") && (senderAccount.getBalance().floatValue() - transfer.getTransactionValue().floatValue()>0)){

                // sender Account
                senderAccount.setBalance(senderAccount.getBalance().floatValue() - transfer.getTransactionValue().floatValue());
                if (senderAccount.getTaxFree()){
                    senderAccount.setAvailableBalance(senderAccount.getBalance());
                } // } else {
                //     accountToConsignment.setAvailableBalance(996);
                // }

                transfer.setTransactionDate(LocalDate.now());
                transfer.setMovementType("Debit");
                transfer.setTransactionType("Transfer");
                transfer.setLinkedAccount(senderAccount);

                // receiver account
                receiverAccount.setBalance(receiverAccount.getBalance().floatValue() + transfer.getTransactionValue().floatValue());
                if (receiverAccount.getTaxFree()){
                    receiverAccount.setAvailableBalance(receiverAccount.getBalance());
                }

                Transaction receiverTransfer = new Transaction();
                receiverTransfer.setLinkedAccount(receiverAccount);
                receiverTransfer.setMovementType("Credit");
                receiverTransfer.setTransactionDate(LocalDate.now());
                receiverTransfer.setTransactionType(transfer.getTransactionType());
                receiverTransfer.setTransactionValue(transfer.getTransactionValue());

                transactionRepository.save(receiverTransfer);
                transactionRepository.save(transfer);

                return transfer;


            // Checking accounts
            } else if (senderAccount.getAccountType().equalsIgnoreCase("Checking") && (senderAccount.getBalance().floatValue() - transfer.getTransactionValue().floatValue()> -3000000)){

                // sender Account
                senderAccount.setBalance(senderAccount.getBalance().floatValue() - transfer.getTransactionValue().floatValue());
                if (senderAccount.getTaxFree()){
                    senderAccount.setAvailableBalance(senderAccount.getBalance());
                } // } else {
                //     accountToConsignment.setAvailableBalance(996);
                // }

                transfer.setTransactionDate(LocalDate.now());
                transfer.setMovementType("Debit");
                transfer.setTransactionType("Transfer");
                transfer.setLinkedAccount(senderAccount);

                // receiver account
                receiverAccount.setBalance(receiverAccount.getBalance().floatValue() + transfer.getTransactionValue().floatValue());
                if (receiverAccount.getTaxFree()){
                    receiverAccount.setAvailableBalance(receiverAccount.getBalance());
                }

                Transaction receiverTransfer = new Transaction();
                receiverTransfer.setLinkedAccount(receiverAccount);
                receiverTransfer.setMovementType("Credit");
                receiverTransfer.setTransactionDate(LocalDate.now());
                receiverTransfer.setTransactionType(transfer.getTransactionType());
                receiverTransfer.setTransactionValue(transfer.getTransactionValue());

                transactionRepository.save(receiverTransfer);
                transactionRepository.save(transfer);

                return transfer;
                
            }  

        }
        return null;
    }
    
    
}
