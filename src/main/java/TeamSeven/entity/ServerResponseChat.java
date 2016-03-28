package TeamSeven.entity;

import TeamSeven.common.IMessageType;

import java.io.Serializable;

/**
 * Created by joshoy on 16/3/28.
 */
public class ServerResponseChat implements Serializable, IMessageType {

    public static final String messageType = "RESP_MSG";

    private String userName;
    private String message;

    public String getMessageType() {
        return this.messageType;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
