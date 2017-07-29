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

    int id;
    Set<Vertice> v;
    Set<Aresta> a;
    static double dist[];

    int numServers;

    public Grafo(int id, int q){
        this.id = id;
        this.numServers = q;

        v = new HashSet<Vertice>();
        a = new HashSet<Aresta>();
        dist = new double[300];
   }

    public sdEntrega1.Client getClient(int id) throws TException{
        TTransport transport = new TSocket("localhost", 9090+id);
        transport.open();
        TProtocol protocol = new TBinaryProtocol(transport);
        sdEntrega1.Client client = new sdEntrega1.Client(protocol); 
        return client;
    }
    
    public boolean existeVertice(int id, boolean redirect) throws TException{
        if(id % this.numServers != this.id){
            if(redirect)
                return getClient(id % this.numServers).existeVertice(id, false);
            return false;
        }

        return v.contains(new Vertice(id, 0, 0, ""));
    }

    public boolean existeAresta(int id, boolean redirect) throws TException{
        if(id % this.numServers != this.id){
            if(redirect)
                return getClient(id % this.numServers).existeAresta(id, false);
            return false;
        }

        return a.contains(new Aresta(id, 0, 0, 0, false, ""));
    }

    public synchronized int addVertice(int id, int cor, double peso, String descricao, boolean redirect) throws TException{
        if(id % this.numServers != this.id){
            if (redirect && getClient(id % this.numServers).addVertice(id, cor, peso, descricao, false) == 1)
                return 2;
            return -1;
        }

        if(this.existeVertice(id, false)) 
            return 0;

        v.add(new Vertice(id, cor, peso, descricao));

        return 1;
    }

    public synchronized int addAresta(int id, int va, int vb, double peso, boolean bidirecional, String descricao, boolean redirect) throws TException{
        if(id % this.numServers != this.id){
            if(redirect && getClient(id % this.numServers).addAresta(id, va, vb, peso, bidirecional, descricao, false) == 1)
                return 2;
            return -1;
        }

        if(!this.existeVertice(va, true) || !this.existeVertice(vb, true) || this.existeAresta(id, false)) 
            return 0;

        Aresta nova = new Aresta(id, va, vb, peso, bidirecional, descricao);

        a.add(nova);
        return 1;
    }

    public Vertice getVertice(int id, boolean redirect) throws TException{
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

    public Aresta getAresta(int id, boolean redirect) throws TException{
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

    public void removeArestasVizinhas(int v, boolean redirect) throws TException{
        if(redirect)
            for(int i=1;i<this.numServers;i++)
                getClient((this.id+i) % this.numServers).removeArestasVizinhas(v, false);

        Iterator<Aresta> it = a.iterator();
        while(it.hasNext()){
            Aresta i = it.next();
            if(i.getVa() == v || i.getVb() == v)
                this.removeAresta(i.getId(), true);
        }
    }

    public synchronized int removeVertice(int id, boolean redirect) throws TException{
        if(id % this.numServers != this.id){
            if(redirect && getClient(id % this.numServers).removeVertice(id, false) == 1)
                return 2;
            return -1;
        }

        if(!this.existeVertice(id, false))
            return 0;

        removeArestasVizinhas(id, true);
        v.remove(this.getVertice(id, false));

        return 1;
    }
        
    public synchronized int removeAresta(int id, boolean redirect) throws TException{
        if(id % this.numServers != this.id){
            if(redirect && getClient(id % this.numServers).removeAresta(id, false) == 1)
                return 2;
            return -1;
        }

        if(!this.existeAresta(id, false))
            return 0;

        a.remove(this.getAresta(id, false));
        
        return 1;
    }

    public String listaVerticesDeAresta(int id, boolean redirect) throws TException{
        if(id % this.numServers != this.id){
            if(redirect){
                String resp = getClient(id % this.numServers).listaVerticesDeAresta(id, false);
                if(resp != "")
                    return "Solicitacao repassada a outro servidor.\n" + resp;
                return "Solicitacao repassada a outro servidor.\n" + "Aresta inexistente.";
            }
            return "";
        }

        if(!this.existeAresta(id, false))
            return "Aresta inexistente.";

        String s = "";
        Aresta i = this.getAresta(id, false);
        Vertice va = this.getVertice(i.getVa(), true);
        Vertice vb = this.getVertice(i.getVb(), true);

        s += "Lista de vertices da aresta " + String.valueOf(id) + ":\n";
        if(va != null) s += "Vertice 1:\n" + va.getInformacoes() + "\n";
        if(vb != null) s += "Vertice 2:\n" + vb.getInformacoes() + "\n";

        return s;
    }

    public String listaArestasDeVertice(int id) throws TException{
        if(!this.existeVertice(id, true))
            return "Vertice inexistente.";

        int contador = 1;
        String s = "";
        List<Aresta> l = this.getArestasAdjacentes(id);
        Iterator<Aresta> it = l.iterator();

        while(it.hasNext()){
            Aresta i = it.next();
            s += String.valueOf(contador++) + "a. aresta:\n";
            s += i.getInformacoes() + "\n"; 
        }

        for(int k=1;k<this.numServers;k++){
            l = getClient((this.id+k) % this.numServers).getArestasAdjacentes(id);
            it = l.iterator();
            while(it.hasNext()){
                Aresta i = it.next();
                s += String.valueOf(contador++) + "a. aresta:\n";
                s += i.getInformacoes() + "\n"; 
            }
        }

        if(contador == 1)
            return "O vertice nao possui arestas adjacentes.";

        return s;
    }

    public String listaVerticesVizinhos(int id) throws TException{
        if(!this.existeVertice(id, true))
            return "Vertice inexistente.";

        int contador = 1;
        String s = "";
        List<Aresta> l = this.getArestasAdjacentes(id);
        Iterator<Aresta> it = l.iterator();

        while(it.hasNext()){
            Aresta i = it.next();
            if(i.getVa() == id)
                s += String.valueOf(contador++) + "o. vertice:\n" + this.getVertice(i.getVb(), true).getInformacoes() + "\n";
            else if(i.getVb() == id && i.isBidirecional())
                s += String.valueOf(contador++) + "o. vertice:\n" + this.getVertice(i.getVa(), true).getInformacoes() + "\n";
        }

        for(int k=1;k<this.numServers;k++){
            l = getClient((this.id+k) % this.numServers).getArestasAdjacentes(id);
            it = l.iterator();
            while(it.hasNext()){
                Aresta i = it.next();
                if(i.getVa() == id)
                    s += String.valueOf(contador++) + "o. vertice:\n" + this.getVertice(i.getVb(), true).getInformacoes() + "\n";
                else if(i.getVb() == id && i.isBidirecional())
                    s += String.valueOf(contador++) + "o. vertice:\n" + this.getVertice(i.getVa(), true).getInformacoes() + "\n";
            }
        }

        if(contador == 1)
            return "Nao ha vertices vizinhos para este vertice.\n";

        return s;
    }
    
    public synchronized int setCorVertice(int id, int cor, boolean redirect) throws TException{
        if(id % this.numServers != this.id){
            if(redirect && getClient(id % this.numServers).setCorVertice(id, cor, false) == 1)
                return 2;
            return -1;
        }

        if(!existeVertice(id, false))
            return 0;

        this.getVertice(id, false).setCor(cor);
        return 1;
    }

    public synchronized int setPesoVertice(int id, double peso, boolean redirect) throws TException{
        if(id % this.numServers != this.id){
            if(redirect && getClient(id % this.numServers).setPesoVertice(id, peso, false) == 1)
                return 2;
            return -1;
        }

        if(!existeVertice(id, false))
            return 0;

        this.getVertice(id, false).setPeso(peso);
        return 1;
    }

    public synchronized int setDescricaoVertice(int id, String descricao, boolean redirect) throws TException{
        if(id % this.numServers != this.id){
            if(redirect && getClient(id % this.numServers).setDescricaoVertice(id, descricao, false) == 1)
                return 2;
            return -1;
        }

        if(!existeVertice(id, false))
            return 0;

        this.getVertice(id, false).setDescricao(descricao);
        return 1;
    }

    public synchronized int setPesoAresta(int id, double peso, boolean redirect) throws TException{
        if(id % this.numServers != this.id){
            if(redirect && getClient(id % this.numServers).setPesoAresta(id, peso, false) == 1)
                return 2;
            return -1;
        }

        if(!existeAresta(id, false))
            return 0;

        this.getAresta(id, false).setPeso(peso);
        return 1;
    }

    public synchronized int setDescricaoAresta(int id, String descricao, boolean redirect) throws TException{
        if(id % this.numServers != this.id){
            if(redirect && getClient(id % this.numServers).setDescricaoAresta(id, descricao, false) == 1)
                return 2;
            return -1;
        }

        if(!existeAresta(id, false))
            return 0;

        this.getAresta(id, false).setDescricao(descricao);
        return 1;
    }

    public double menorCaminho(int va, int vb) throws TException{
        if(!existeVertice(va, true) || !existeVertice(vb, true)) // nao existe um dos vertices
            return -2;

        int inf = 0x3f3f3f3f;
        for(int i=0;i<300;i++)
            dist[i] = inf;

        Comparator<Integer> comparator = new DistComparator();
        PriorityQueue<Integer> pq = new PriorityQueue<Integer>(300, comparator);    
    
        dist[va] = 0;
        pq.add(va);

        while (pq.size() != 0){
            int i = pq.remove();

            List<Aresta> l = this.getArestasAdjacentes(i);
            Iterator<Aresta> it = l.iterator();

            while(it.hasNext()){
                Aresta aij = it.next();
                int j = (aij.getVa() == i) ? aij.getVb() : aij.getVa();

                if(j != -1 && dist[j] > dist[i] + aij.getPeso()){
                    dist[j] = dist[i] + aij.getPeso();
                    pq.add(j);
                }
            }

            // arestas adj de outros servers:
            for(int k=1;k<this.numServers;k++){
                l = getClient((this.id+k) % this.numServers).getArestasAdjacentes(i);
                it = l.iterator();
                while(it.hasNext()){
                    Aresta aij = it.next();
                    int j = (aij.getVa() == i) ? aij.getVb() : aij.getVa();

                    if(j != -1 && dist[j] > dist[i] + aij.getPeso()){
                        dist[j] = dist[i] + aij.getPeso();
                        pq.add(j);
                    }
                }
            }

        }
    
        return (dist[vb] < inf) ? dist[vb] : -1.0;
    }

    public List<Aresta> getArestasAdjacentes(int id){
        Iterator<Aresta> it = this.a.iterator();
        List<Aresta> l = new ArrayList<Aresta>();

        while(it.hasNext()){
            Aresta a = it.next();
            if(a.getVa() == id || (a.getVb() == id && a.isBidirecional()))
                l.add(a);
        }

        return l;
    }

    public static double getDist(int id){
        return dist[id];
    }
}

class DistComparator implements Comparator<Integer>{
    @Override
    public int compare(Integer x, Integer y){
        return (Grafo.getDist(x) > Grafo.getDist(y)) ? 1 : -1;
    }
}