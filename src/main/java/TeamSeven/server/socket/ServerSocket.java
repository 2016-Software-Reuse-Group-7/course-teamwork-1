package TeamSeven.server.socket;

/**
 * Created by joshoy on 16/3/22.
 */

import org.java_websocket.WebSocket;
import org.java_websocket.drafts.Draft;
import org.java_websocket.server.WebSocketServer;

import java.net.InetSocketAddress;
import java.net.UnknownHostException;
import java.util.Collection;
import java.util.List;

public abstract class ServerSocket extends WebSocketServer {

    public ServerSocket(InetSocketAddress address, int decodercount, List<Draft> drafts, Collection<WebSocket> connectionscontainer) {
        super(address, decodercount, drafts, connectionscontainer);
    }

    public ServerSocket(InetSocketAddress address) {
        super(address);
    }

    public ServerSocket(InetSocketAddress address, int decoders) {
        super(address, decoders);
    }

    public ServerSocket(InetSocketAddress address, List<Draft> drafts) {
        super(address, drafts);
    }

    public ServerSocket(InetSocketAddress address, int decodercount, List<Draft> drafts) {
        super(address, decodercount, drafts);
    }

    public ServerSocket() throws UnknownHostException {
    }


}
