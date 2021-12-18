package entities;

import java.util.Objects;

public class Account {
    private String userName;
    private String password;
    private int role;

    public Account(String userName, String password, int role) {
        this.userName = userName;
        this.password = password;
        this.role = role;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Account account = (Account) o;
        return role == account.role && Objects.equals(userName, account.userName) && Objects.equals(password, account.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userName, password, role);
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }
}
