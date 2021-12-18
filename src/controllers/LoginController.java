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


    /**
     * Check 1 tài khoản người dùng khi đăng nhập
     * @param account
     * @return 0 là admin hợp lệ, 1 là renter hợp lệ, 2 là tài khoản không hợp lệ
     * @author HieuNV
     */
    public int checkAccount(Account account){
        for(int i=0; i<this.accounts.size(); i++){
            Account acc = this.accounts.get(i);
            if(acc.equals(account))
                return account.getRole();
        }
        return 2;
    }
}
