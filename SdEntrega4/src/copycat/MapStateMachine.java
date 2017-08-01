package copycat;

import copycat.get.*;
import copycat.put.PutAddAresta;
import copycat.put.PutAddVertice;
import copycat.put.PutSetAvaliacaoFilme;
import io.atomix.copycat.server.Commit;
import io.atomix.copycat.server.StateMachine;
import thrift.Aresta;
import thrift.Vertice;

import java.util.*;

/**
 * Created by pedro on 31/07/17.
 */
public class MapStateMachine extends StateMachine {

    Map<Long, Vertice> mapVertices = new HashMap<>();
    Map<Long, Aresta> mapArestas = new HashMap<>();

    public Vertice getVertice(Commit<GetVertice> commit) {
        try {
            if(mapVertices.containsKey(commit.operation().getId())) {
                return mapVertices.get(commit.operation().getId());
            } else {
                return null;
            }
        } finally {
            commit.close();
        }
    }

    public Aresta getAresta(Commit<GetAresta> commit) {
        try {
            if(mapArestas.containsKey(commit.operation().getId())) {
                return mapArestas.get(commit.operation().getId());
            } else {
                return null;
            }
        } finally {
            commit.close();
        }
    }

    public boolean existeVertice(Commit<GetExisteVertice> commit) {
        try {
            return mapVertices.containsKey(commit.operation().getId());
        } finally {
            commit.close();
        }

    }

    public boolean existeAresta(Commit<GetExisteAresta> commit) {
        try {
            return mapArestas.containsKey(commit.operation().getId());
        } finally {
            commit.close();
        }
    }

    public int addVertice(Commit<PutAddVertice> commit) {
        try {
            if(mapVertices.containsKey(commit.operation().getVertice().getId())) {
                return 0;
            } else {
                mapVertices.put(commit.operation().getVertice().getId(), commit.operation().getVertice());
                return 1;
            }
        } finally {
            commit.close();
        }
    }

    public int addAresta(Commit<PutAddAresta> commit) {
        try {
            if(mapArestas.containsKey(commit.operation().getAresta().getId())) {
                return 0;
            } else {
                mapArestas.put(commit.operation().getAresta().getId(), commit.operation().getAresta());
                return 1;
            }
        } finally {
            commit.close();
        }
    }

    public List<Aresta> getArestaAdjacentes(Commit<GetArestasAdjacentes> commit) {
        try {
            return findArestasAdjacentes(commit.operation().getId());
        } finally {
            commit.close();
        }
    }

    private List<Aresta> findArestasAdjacentes(long id) {
        ArrayList<Aresta> arestasAdjacentes = new ArrayList<>();

        for (Map.Entry<Long, Aresta> entry: mapArestas.entrySet()) {
            if(entry.getValue().getVa() == id ||
                    entry.getValue().getVb() == id &&
                            entry.getValue().isBidirecional()) {
                arestasAdjacentes.add(entry.getValue());
            }
        }

        return arestasAdjacentes;
    }

    public String getAvaliacaoMedia(Commit<GetAvalicaoMedia> commit) {
        try {
           if(!mapVertices.containsKey(commit.operation().getId())
                   || mapVertices.get(commit.operation().getId()).getTipo() == 0) {
               return null;
           }

            double soma = 0;
            int contador = 0;
            String s = "";

            List<Aresta> arestasAdjacentes = findArestasAdjacentes(commit.operation().getId());

            for (Aresta i : arestasAdjacentes) {
                if (i.getVa() == commit.operation().getId() || i.getVb() == commit.operation().getId() && i.isBidirecional()) {
                    soma += i.getPeso();
                    contador++;
                }
            }


            if(contador == 0)
                return "O filme nao possui avaliacoes.";

            return "Avaliacao media do filme: " + String.valueOf(soma/contador);

        } finally {
            commit.close();
        }
    }

    public String getFilmesDeCliente(Commit<GetFilmesDeCliente> commit) {

        try{

            if(!mapVertices.containsKey(commit.operation().getId())
                    || mapVertices.get(commit.operation().getId()).getTipo() == 1) {
                return "Filme inexistente";
            }

            int contador = 1;
            String s = "";
            List<Aresta> arestasAdjacentes = findArestasAdjacentes(commit.operation().getId());

            for (Aresta aresta : arestasAdjacentes) {
                if (aresta.getVa() == commit.operation().getId())
                    s += String.valueOf(contador++) + "o. filme:\n" + mapVertices.get(aresta.getVb()).getInformacoes() + "\n";
                else if (aresta.getVb() == commit.operation().getId() && aresta.isBidirecional())
                    s += String.valueOf(contador++) + "o. filme:\n" + mapVertices.get(aresta.getVb()).getInformacoes() + "\n";
            }

            if(contador == 1)
                return "Este cliente nao assistiu nenhum filme.\n";

            return s;

        } finally {
            commit.close();
        }
    }

    public int setAvaliacaoFilme(Commit<PutSetAvaliacaoFilme> commit) {

        try {
            if(!mapArestas.containsKey(commit.operation().getId())) {
                return 0;
            } else {
                mapArestas.get(commit.operation().getId()).setPeso(commit.operation().getPeso());
                return 1;
            }
        } finally {
            commit.close();
        }
    }

}
