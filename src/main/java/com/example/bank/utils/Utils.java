package com.bankservice.bank.utils;

import com.bankservice.bank.dao.ActivityDAO;
import com.bankservice.bank.dao.BankAccountDAO;
import com.bankservice.bank.model.Activity;
import com.bankservice.bank.model.BankAccount;
import org.springframework.util.DigestUtils;

import java.util.ArrayList;
import java.util.List;

public class Utils {

    public static String encryptMd5(String text) {
        return DigestUtils.md5DigestAsHex(text.getBytes()).toUpperCase();
    }

    public static List <BankAccountDAO> toBankAccountDAO(List <BankAccount> list) {
        List <BankAccountDAO> result = new ArrayList <>();
        for (BankAccount bankAccount : list) {
            result.add(new BankAccountDAO(bankAccount));
        }
        return result;
    }

    public static List <ActivityDAO> toActivityDAO(List <Activity> list) {
        List <ActivityDAO> result = new ArrayList <>();
        for (Activity activity : list) {
            result.add(new ActivityDAO(activity));
        }
        return result;
    }

}
