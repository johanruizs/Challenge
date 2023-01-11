package com.sophos.bankapp.repository;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sophos.bankapp.entity.Account;


@Repository

public interface AccountRepository extends JpaRepository<Account, Integer>{

    Account findByAccountNumber (String accountNumber);
    
}
