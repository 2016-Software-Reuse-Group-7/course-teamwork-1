package TeamSeven.entity;

import TeamSeven.common.IMessageType;

import java.io.Serializable;

/**
 * Created by joshoy on 16/3/26.
 */
public class Chat implements Serializable, IMessageType {

    public static String messageType = "MSG";
    private String content;
    private String userName;

    public Chat(IMessageType parsedObj) {
        this.setContent("");
        this.setUserName("");
    }

    public Chat(String message, String userName) {
        this.setContent(message);
        this.setUserName(userName);
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Override
    public String toString() {
        return "[" + this.userName + "]: " + content;
    }

    public String getMessageType() {
        return messageType;
    }
}
