package controllers;

import entities.Account;

import java.util.ArrayList;
import java.util.List;

public class LoginController extends BaseController{
    private List<Account> accounts;

    public LoginController(){
        accounts = new ArrayList<>();
        accounts.add(new Account("admin","123", 0));
        accounts.add(new Account("hieunv", "123", 1));
    }


    public int checkAccount(Account account){
        for(int i=0; i<this.accounts.size(); i++){
            var acc = this.accounts.get(i);
            if(acc.equals(account))
                return account.getRole();
        }
        return 2;
    }
}
