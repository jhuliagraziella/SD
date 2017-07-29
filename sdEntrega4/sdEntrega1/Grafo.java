package sdEntrega1;

import org.apache.thrift.TException;
import org.apache.thrift.transport.TTransport;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;

import sdEntrega1.*;
import java.util.*;
 
public class Grafo implements sdEntrega1.Iface{
    public static Grafo instance = null;

    long id;
    Set<Vertice> v;
    Set<Aresta> a;
    static double dist[];

    long numServers;

    public Grafo(long id, long q){
        this.id = id;
        this.numServers = q;

        v = new HashSet<Vertice>();
        a = new HashSet<Aresta>();
        dist = new double[30000];
   }

    public sdEntrega1.Client getClient(long id) throws TException{
        TTransport transport = new TSocket("localhost", (int) (9090+id));
        transport.open();
        TProtocol protocol = new TBinaryProtocol(transport);
        sdEntrega1.Client client = new sdEntrega1.Client(protocol); 
        return client;
    }
    
    public boolean existeVertice(long id, boolean redirect) throws TException{
        if(id % this.numServers != this.id){
            if(redirect)
                return getClient(id % this.numServers).existeVertice(id, false);
            return false;
        }

        return v.contains(new Vertice(id, 0, 0, "", 0));
    }

    public boolean existeAresta(long id, boolean redirect) throws TException{
        if(id % this.numServers != this.id){
            if(redirect)
                return getClient(id % this.numServers).existeAresta(id, false);
            return false;
        }

        return a.contains(new Aresta(id, 0, 0, 0, false, ""));
    }

    public synchronized int addVertice(long id, int cor, double peso, String descricao, int tipo, boolean redirect) throws TException{
        if(id % this.numServers != this.id){
            if (redirect && getClient(id % this.numServers).addVertice(id, cor, peso, descricao, tipo, false) == 1)
                return 2;
            return -1;
        }

        if(this.existeVertice(id, false)) 
            return 0;

        v.add(new Vertice(id, cor, peso, descricao, tipo));

        return 1;
    }

    public synchronized int addAresta(long id, long va, long vb, double peso, boolean bidirecional, String descricao, boolean redirect) throws TException{
        if(id % this.numServers != this.id){
            if(redirect && getClient(id % this.numServers).addAresta(id, va, vb, peso, bidirecional, descricao, false) == 1)
                return 2;
            return -1;
        }

        if(!this.existeVertice(va, true) || !this.existeVertice(vb, true) || this.existeAresta(id, false) || this.getVertice(va, true).getTipo() == this.getVertice(va, true).getTipo()) 
            return 0;

        Aresta nova = new Aresta(id, va, vb, peso, bidirecional, descricao);

        a.add(nova);
        return 1;
    }

    public Vertice getVertice(long id, boolean redirect) throws TException{
        if(id % this.numServers != this.id){
            if(redirect)
                return getClient(id % this.numServers).getVertice(id, false);
            return null;
        }

        if(!this.existeVertice(id, false))
            return null;

        Iterator<Vertice> it = v.iterator();
        while(it.hasNext()){
            Vertice i = it.next();
            if(i.getId() == id)
                return i;
        }
        return null;
    }

    public Aresta getAresta(long id, boolean redirect) throws TException{
        if(id % this.numServers != this.id){
            if(redirect)
                return getClient(id % this.numServers).getAresta(id, false);
            return null;
        }

        if(!this.existeAresta(id, false))
            return null;

        Iterator<Aresta> it = a.iterator();
        while(it.hasNext()){
            Aresta i = it.next();
            if(i.getId() == id)
                return i;
        }
        return null;
    }

    // public void removeArestasVizinhas(long v, boolean redirect) throws TException{
    //     if(redirect)
    //         for(int i=1;i<this.numServers;i++)
    //             getClient((this.id+i) % this.numServers).removeArestasVizinhas(v, false);

    //     Iterator<Aresta> it = a.iterator();
    //     while(it.hasNext()){
    //         Aresta i = it.next();
    //         if(i.getVa() == v || i.getVb() == v)
    //             this.removeAresta(i.getId(), true);
    //     }
    // }

    // public synchronized int removeVertice(long id, boolean redirect) throws TException{
    //     if(id % this.numServers != this.id){
    //         if(redirect && getClient(id % this.numServers).removeVertice(id, false) == 1)
    //             return 2;
    //         return -1;
    //     }

    //     if(!this.existeVertice(id, false))
    //         return 0;

    //     removeArestasVizinhas(id, true);
    //     v.remove(this.getVertice(id, false));

    //     return 1;
    // }
        
    // public synchronized int removeAresta(long id, boolean redirect) throws TException{
    //     if(id % this.numServers != this.id){
    //         if(redirect && getClient(id % this.numServers).removeAresta(id, false) == 1)
    //             return 2;
    //         return -1;
    //     }

    //     if(!this.existeAresta(id, false))
    //         return 0;

    //     a.remove(this.getAresta(id, false));
        
    //     return 1;
    // }

    // public String listaVerticesDeAresta(long id, boolean redirect) throws TException{
    // public String listaVerticesDeAresta(long id, boolean redirect) throws TException{
    //     if(id % this.numServers != this.id){
    //         if(redirect){
    //             String resp = getClient(id % this.numServers).listaVerticesDeAresta(id, false);
    //             if(resp != "")
    //                 return "Solicitacao repassada a outro servidor.\n" + resp;
    //             return "Solicitacao repassada a outro servidor.\n" + "Aresta inexistente.";
    //         }
    //         return "";
    //     }

    //     if(!this.existeAresta(id, false))
    //         return "Aresta inexistente.";

    //     String s = "";
    //     Aresta i = this.getAresta(id, false);
    //     Vertice va = this.getVertice(i.getVa(), true);
    //     Vertice vb = this.getVertice(i.getVb(), true);

    //     s += "Lista de vertices da aresta " + String.valueOf(id) + ":\n";
    //     if(va != null) s += "Vertice 1:\n" + va.getInformacoes() + "\n";
    //     if(vb != null) s += "Vertice 2:\n" + vb.getInformacoes() + "\n";

    //     return s;
    // }

    // public String listaArestasDeVertice(long id) throws TException{
    //     if(!this.existeVertice(id, true))
    //         return "Vertice inexistente.";

    //     int contador = 1;
    //     String s = "";
    //     List<Aresta> l = this.getArestasAdjacentes(id);
    //     Iterator<Aresta> it = l.iterator();

    //     while(it.hasNext()){
    //         Aresta i = it.next();
    //         s += String.valueOf(contador++) + "a. aresta:\n";
    //         s += i.getInformacoes() + "\n"; 
    //     }

    //     for(int k=1;k<this.numServers;k++){
    //         l = getClient((this.id+k) % this.numServers).getArestasAdjacentes(id);
    //         it = l.iterator();
    //         while(it.hasNext()){
    //             Aresta i = it.next();
    //             s += String.valueOf(contador++) + "a. aresta:\n";
    //             s += i.getInformacoes() + "\n"; 
    //         }
    //     }

    //     if(contador == 1)
    //         return "O vertice nao possui arestas adjacentes.";

    //     return s;
    // }

    // public String listaVerticesVizinhos(long id) throws TException{
    public String listaFilmesDeCliente(long id) throws TException{
        if(!this.existeVertice(id, true) || this.getVertice(id, true).getTipo() == 1)
            return "Cliente inexistente.";

        int contador = 1;
        String s = "";
        List<Aresta> l = this.getArestasAdjacentes(id);
        Iterator<Aresta> it = l.iterator();

        while(it.hasNext()){
            Aresta i = it.next();
            if(i.getVa() == id)
                s += String.valueOf(contador++) + "o. filme:\n" + this.getVertice(i.getVb(), true).getInformacoes() + "\n";
            else if(i.getVb() == id && i.isBidirecional())
                s += String.valueOf(contador++) + "o. filme:\n" + this.getVertice(i.getVa(), true).getInformacoes() + "\n";
        }

        for(int k=1;k<this.numServers;k++){
            l = getClient((this.id+k) % this.numServers).getArestasAdjacentes(id);
            it = l.iterator();
            while(it.hasNext()){
                Aresta i = it.next();
                if(i.getVa() == id)
                    s += String.valueOf(contador++) + "o. filme:\n" + this.getVertice(i.getVb(), true).getInformacoes() + "\n";
                else if(i.getVb() == id && i.isBidirecional())
                    s += String.valueOf(contador++) + "o. filme:\n" + this.getVertice(i.getVa(), true).getInformacoes() + "\n";
            }
        }

        if(contador == 1)
            return "Este cliente nao assistiu nenhum filme.\n";

        return s;
    }

    public String listaFilmesDeGrupo(List<Long> ids) throws TException{
        Set<Long> idsExistentes = new HashSet<Long>();
        Set<Long> idsInexistentes = new HashSet<Long>();
        Set<Long> filmes = new HashSet<Long>();

        Iterator<Long> it = ids.iterator();

        while(it.hasNext()){
            long id = it.next();

            if(!this.existeVertice(id, true) || this.getVertice(id, true).getTipo() == 1)
                idsInexistentes.add(id);
            else{
                idsExistentes.add(id);
            
                List<Aresta> l = this.getArestasAdjacentes(id);
                Iterator<Aresta> it2 = l.iterator();

                while(it2.hasNext()){
                    Aresta i = it2.next();
                    if(i.getVa() == id)
                        filmes.add(i.getVb());
                    else if(i.getVb() == id && i.isBidirecional())
                        filmes.add(i.getVa());
                }

                for(int k=1;k<this.numServers;k++){
                    l = getClient((this.id+k) % this.numServers).getArestasAdjacentes(id);
                    it2 = l.iterator();

                    while(it2.hasNext()){
                        Aresta i = it2.next();
                        if(i.getVa() == id)
                            filmes.add(i.getVb());
                        else if(i.getVb() == id && i.isBidirecional())
                            filmes.add(i.getVa());
                    }
                }
            }
        }

        String s = "";

        if(idsInexistentes.size() != 0){
            s += "Clientes inexistentes do grupo: ";

            it = idsInexistentes.iterator();
            boolean first = true;
            while(it.hasNext()){
                long id = it.next();

                if(!first)
                    s += ", ";
                s += String.valueOf(id);
            
                first = false;
            }
            s += "\n";
        }

        if(idsExistentes.size() != 0){
            s += "Clientes existentes do grupo: ";

            it = idsExistentes.iterator();
            boolean first = true;
            while(it.hasNext()){
                long id = it.next();

                if(!first)
                    s += ", ";
                s += String.valueOf(id);
            
                first = false;
            }
            s += "\n";
        }

        if(filmes.size() == 0)
            s += "Nenhum filme foi assistido pelo grupo.\n";
        else{
            s += "Filmes assistidos pelo grupo: \n";

            int contador = 1;
            it = filmes.iterator();
            while(it.hasNext()){
                long id = it.next();
                s += String.valueOf(contador++) + "o. filme:\n" + this.getVertice(id, true).getInformacoes() + "\n";
            }
        }

        return s;
    }

    public String getAvaliacaoMedia(long id) throws TException{
        if(!this.existeVertice(id, true) || this.getVertice(id, true).getTipo() == 0)
            return "Filme inexistente.";

        double soma = 0;
        int contador = 0;

        String s = "";
        List<Aresta> l = this.getArestasAdjacentes(id);
        Iterator<Aresta> it = l.iterator();

        while(it.hasNext()){
            Aresta i = it.next();
            if(i.getVa() == id || i.getVb() == id && i.isBidirecional()){
                soma += i.getPeso();
                contador++;
            }
        }


        for(int k=1;k<this.numServers;k++){
            l = getClient((this.id+k) % this.numServers).getArestasAdjacentes(id);
            it = l.iterator();

            while(it.hasNext()){
                Aresta i = it.next();
                if(i.getVa() == id || i.getVb() == id && i.isBidirecional()){
                    soma += i.getPeso();
                    contador++;
                }
            }
        }

        if(contador == 0)
            return "O filme nao possui avaliacoes.";

        return "Avaliacao media do filme: " + String.valueOf(soma/contador);
    }
    
    // public synchronized int setCorVertice(long id, int cor, boolean redirect) throws TException{
    //     if(id % this.numServers != this.id){
    //         if(redirect && getClient(id % this.numServers).setCorVertice(id, cor, false) == 1)
    //             return 2;
    //         return -1;
    //     }

    //     if(!existeVertice(id, false))
    //         return 0;

    //     this.getVertice(id, false).setCor(cor);
    //     return 1;
    // }

    // public synchronized int setPesoVertice(long id, double peso, boolean redirect) throws TException{
    //     if(id % this.numServers != this.id){
    //         if(redirect && getClient(id % this.numServers).setPesoVertice(id, peso, false) == 1)
    //             return 2;
    //         return -1;
    //     }

    //     if(!existeVertice(id, false))
    //         return 0;

    //     this.getVertice(id, false).setPeso(peso);
    //     return 1;
    // }

    // public synchronized int setDescricaoVertice(long id, String descricao, boolean redirect) throws TException{
    //     if(id % this.numServers != this.id){
    //         if(redirect && getClient(id % this.numServers).setDescricaoVertice(id, descricao, false) == 1)
    //             return 2;
    //         return -1;
    //     }

    //     if(!existeVertice(id, false))
    //         return 0;

    //     this.getVertice(id, false).setDescricao(descricao);
    //     return 1;
    // }

    // i32 setAvaliacaoFilme(1: i64 id, 2: double peso, 3: bool redirect),
    // public synchronized int setPesoAresta(long id, double peso, boolean redirect) throws TException{
    public synchronized int setAvaliacaoFilme(long id, double peso, boolean redirect) throws TException{
        if(id % this.numServers != this.id){
            if(redirect && getClient(id % this.numServers).setAvaliacaoFilme(id, peso, false) == 1)
                return 2;
            return -1;
        }

        if(!existeAresta(id, false))
            return 0;

        this.getAresta(id, false).setPeso(peso);
        return 1;
    }

    // public synchronized int setDescricaoAresta(long id, String descricao, boolean redirect) throws TException{
    //     if(id % this.numServers != this.id){
    //         if(redirect && getClient(id % this.numServers).setDescricaoAresta(id, descricao, false) == 1)
    //             return 2;
    //         return -1;
    //     }

    //     if(!existeAresta(id, false))
    //         return 0;

    //     this.getAresta(id, false).setDescricao(descricao);
    //     return 1;
    // }

    public double menorCaminho(long va, long vb) throws TException{
        if(!existeVertice(va, true) || !existeVertice(vb, true)) // nao existe um dos vertices
            return -2;

        // int inf = 0x3f3f3f3f;
        // for(int i=0;i<30000;i++)
        //     dist[i] = inf;

        // Comparator<Integer> comparator = new DistComparator();
        // PriorityQueue<Integer> pq = new PriorityQueue<Integer>(30000, comparator);    
    
        // dist[va] = 0;
        // pq.add(va);

        // while (pq.size() != 0){
        //     int i = pq.remove();

        //     List<Aresta> l = this.getArestasAdjacentes(i);
        //     Iterator<Aresta> it = l.iterator();

        //     while(it.hasNext()){
        //         Aresta aij = it.next();
        //         int j = (aij.getVa() == i) ? aij.getVb() : aij.getVa();

        //         if(j != -1 && dist[j] > dist[i] + aij.getPeso()){
        //             dist[j] = dist[i] + aij.getPeso();
        //             pq.add(j);
        //         }
        //     }

        //     // arestas adj de outros servers:
        //     for(int k=1;k<this.numServers;k++){
        //         l = getClient((this.id+k) % this.numServers).getArestasAdjacentes(i);
        //         it = l.iterator();
        //         while(it.hasNext()){
        //             Aresta aij = it.next();
        //             int j = (aij.getVa() == i) ? aij.getVb() : aij.getVa();

        //             if(j != -1 && dist[j] > dist[i] + aij.getPeso()){
        //                 dist[j] = dist[i] + aij.getPeso();
        //                 pq.add(j);
        //             }
        //         }
        //     }

        // }
    
        // return (dist[vb] < inf) ? dist[vb] : -1.0;
    
        return 0.0;
    }

    public List<Aresta> getArestasAdjacentes(long id){
        Iterator<Aresta> it = this.a.iterator();
        List<Aresta> l = new ArrayList<Aresta>();

        while(it.hasNext()){
            Aresta a = it.next();
            if(a.getVa() == id || (a.getVb() == id && a.isBidirecional()))
                l.add(a);
        }

        return l;
    }

    public static double getDist(long id){
        return 0.0;
        // return dist[id];
    }
}

class DistComparator implements Comparator<Integer>{
    @Override
    public int compare(Integer x, Integer y){
        return (Grafo.getDist(x) > Grafo.getDist(y)) ? 1 : -1;
    }
}