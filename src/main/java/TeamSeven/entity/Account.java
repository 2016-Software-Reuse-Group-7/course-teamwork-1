package TeamSeven.entity;

import java.io.Serializable;

/**
 * Created by joshoy on 16/3/26.
 */
public class Account implements Serializable {

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
}
