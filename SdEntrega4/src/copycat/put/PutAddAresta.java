package copycat.put;

import io.atomix.copycat.Command;
import thrift.Aresta;

/**
 * Created by pedro on 31/07/17.
 */
public class PutAddAresta implements Command<Integer> {
    private Aresta aresta;

    public PutAddAresta(Aresta aresta) {
        this.aresta = aresta;
    }

    public Aresta getAresta() {
        return aresta;
    }
}
