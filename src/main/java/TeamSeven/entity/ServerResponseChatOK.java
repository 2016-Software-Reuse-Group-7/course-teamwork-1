package TeamSeven.entity;

import TeamSeven.common.IMessageType;

import java.io.Serializable;

/**
 * Created by joshoy on 16/3/27.
 */
public class ServerResponseChatOK implements Serializable, IMessageType {

    public static final String messageType = "OK";

    public String getMessageType() {
        return this.messageType;
    }
}
