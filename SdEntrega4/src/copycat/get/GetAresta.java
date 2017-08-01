package copycat.get;

import io.atomix.copycat.Query;
import thrift.Aresta;

/**
 * Created by pedro on 31/07/17.
 */
public class GetAresta implements Query<Aresta> {
    private long id;

    public GetAresta(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }
}
