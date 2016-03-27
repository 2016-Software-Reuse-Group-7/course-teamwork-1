package TeamSeven.entity;

import TeamSeven.common.IMessageType;

import java.io.Serializable;

/**
 * Created by joshoy on 16/3/27.
 */
public class ServerResponse implements Serializable, IMessageType {

    public static String messageType = "RESP";

    private boolean accessGrant;

    public ServerResponse(boolean accessGrant) {
        this.accessGrant = accessGrant;
    }

    public String getMessageType() {
        return this.messageType;
    }

    public boolean isAccessGrant() {
        return this.accessGrant;
    }

}
