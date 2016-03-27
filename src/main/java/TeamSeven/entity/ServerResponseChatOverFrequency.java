package TeamSeven.entity;

import TeamSeven.common.IMessageType;

import java.io.Serializable;

/**
 * Created by joshoy on 16/3/27.
 */
public class ServerResponseChatOverFrequency implements Serializable, IMessageType {
    public static final String messageType = "OVER_FREQUENCY";
    
    public String getMessageType() {
        return this.messageType;
    }
}
