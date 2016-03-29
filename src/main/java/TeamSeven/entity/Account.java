package TeamSeven.entity;

import TeamSeven.common.IMessageType;

import java.io.Serializable;

/**
 * Created by joshoy on 16/3/26.
 */
public class Account implements Serializable, IMessageType {

    public static final String messageType = "ACC";

    public Account(String userName, String password) {
        this.setUserName(userName);
        this.setPassword(password);
    }

    private String userName;
    private String password;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getMessageType() {
        return this.messageType;
    }

    @Override
    public int hashCode() {
        return (this.userName + "::" + this.password).hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Account) {
            Account account = (Account) obj;
            if (null != this.getUserName()) {
                return this.getUserName().equals(account.getUserName());
            }
        }
        return false;
    }
}
