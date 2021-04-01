package com.bankservice.bank.repository;

import com.bankservice.bank.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository <User, Long> {

//    @Query(value = "select * from BANK_ACCOUNT where id in (SELECT bank_accounts_id FROM USER_BANK_ACCOUNTS  where user_id = ?1)", nativeQuery = true)
//    List <BankAccount> getBankAccountsFromUserId(Long userId);


}
