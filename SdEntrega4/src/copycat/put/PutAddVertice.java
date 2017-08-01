package copycat.put;

import io.atomix.copycat.Command;
import thrift.Vertice;

/**
 * Created by pedro on 31/07/17.
 */
public class PutAddVertice implements Command<Integer> {

    private Vertice vertice;

    public PutAddVertice(Vertice vertice) {
        this.vertice = vertice;
    }

    public Vertice getVertice() {
        return vertice;
    }
}
