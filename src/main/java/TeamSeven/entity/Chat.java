package TeamSeven.entity;

import TeamSeven.common.IMessageType;

import java.io.Serializable;

/**
 * Created by joshoy on 16/3/26.
 */
public class Chat implements Serializable, IMessageType {

    public static final String messageType = "MSG";
    private Account account;
    private String content;

    public Chat(String message, Account account) {
        this.setContent(message);
        this.setAccount(account);
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "[" + this.account.getUserName() + "]: " + content;
    }

    public String getMessageType() {
        return messageType;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }
}
