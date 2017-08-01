package copycat;

import io.atomix.copycat.server.CopycatServer;
import io.atomix.catalyst.transport.Address;
import io.atomix.copycat.server.storage.Storage;
import io.atomix.copycat.server.storage.StorageLevel;

import java.util.Scanner;

/**
 * Created by pedro on 31/07/17.
 */
public class Server {

    private String ip;
    private int port;
    private String ipLeader;
    private int portLeader;
    private boolean isLeader;

    public Server(String ip, int port, String ipLeader, int portLeader, boolean isLeader) {
        this.ip = ip;
        this.port = port;
        this.ipLeader = ipLeader;
        this.portLeader = portLeader;
        this.isLeader = isLeader;
    }
    public void run() {
        CopycatServer server;

        server = CopycatServer
                .builder(new Address(ip, port))
                .withStorage(Storage.builder().withStorageLevel(StorageLevel.MEMORY).build())
                .withStateMachine(MapStateMachine::new).build();

        if(isLeader) {
            server.bootstrap().join();
            System.out.println("Lider iniciado");
        } else {
            server.join(new Address(ipLeader, portLeader)).join();
            System.out.println("Seguidor iniciado");
        }
    }
}
