package copycat.get;

import io.atomix.copycat.Query;

/**
 * Created by pedro on 31/07/17.
 */
public class GetExisteAresta implements Query<Boolean> {
    private long id;

    public GetExisteAresta(long id) {
        this.id = id;
    }

    public long getId() {
        return this.id;
    }
}
