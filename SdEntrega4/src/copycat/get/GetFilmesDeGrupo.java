package copycat.get;

import io.atomix.copycat.Query;

import java.util.List;

/**
 * Created by pedro on 31/07/17.
 */
public class GetFilmesDeGrupo implements Query<Object> {
    private List<Long> ids;

    public GetFilmesDeGrupo(List<Long> ids) {
        this.ids = ids;
    }

    public List<Long> getIds() {
        return ids;
    }
}
