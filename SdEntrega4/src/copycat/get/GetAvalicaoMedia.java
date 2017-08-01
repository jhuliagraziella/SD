package copycat.get;

import io.atomix.copycat.Query;

/**
 * Created by pedro on 31/07/17.
 */
public class GetAvalicaoMedia implements Query<Object> {
    private long id;

    public GetAvalicaoMedia(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }
}
