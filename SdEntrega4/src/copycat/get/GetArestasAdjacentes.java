package copycat.get;

import io.atomix.copycat.Query;
import thrift.Aresta;

import java.util.List;

/**
 * Created by pedro on 31/07/17.
 */
public class GetArestasAdjacentes implements Query<List<Aresta>> {
    private long id;

    public GetArestasAdjacentes(long id) {
        this.id = id;
    }

    public long getId() {
        return this.id;
    }
}
