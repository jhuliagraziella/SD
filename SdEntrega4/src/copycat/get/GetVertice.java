package copycat.get;

import io.atomix.copycat.Query;
import thrift.Vertice;

/**
 * Created by pedro on 31/07/17.
 */
public class GetVertice implements Query<Vertice> {

    private long id;

    public GetVertice(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }
}
