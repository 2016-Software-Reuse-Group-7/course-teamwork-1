package TeamSeven.entity;

import TeamSeven.common.IMessageType;

import java.io.Serializable;

/**
 * Created by joshoy on 16/3/27.
 */
public class ServerResponseRelogin implements Serializable, IMessageType {

    public static final String messageType = "RELOGIN";

    public String getMessageType() {
        return this.messageType;
    }
}
