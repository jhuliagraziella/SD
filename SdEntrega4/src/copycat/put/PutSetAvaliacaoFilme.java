package copycat.put;

import io.atomix.copycat.Command;

/**
 * Created by pedro on 31/07/17.
 */
public class PutSetAvaliacaoFilme implements Command<Integer> {
    private long id;
    private double peso;

    public PutSetAvaliacaoFilme(long id, double peso) {
        this.id = id;
        this.peso = peso;
    }

    public long getId() {
        return id;
    }

    public double getPeso() {
        return peso;
    }
}
