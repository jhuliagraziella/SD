package sdEntrega1;

import org.apache.thrift.TException;
import org.apache.thrift.transport.TTransport;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;

import sdEntrega1.*;
import java.util.*;
 
// nao ta repassando pros outros servidores de maneira transparente

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
            if(redirect){
                for(int i=1;i<this.numServers;i++){
                    boolean ok = getClient((this.id+i) % this.numServers).existeVertice(id, false);
                    if(ok)
                        return true;
                }
            }
            return false;
        }

        return v.contains(new Vertice(id, 0, 0, ""));
    }

    public boolean existeAresta(int id, boolean redirect) throws TException{
        if(id % this.numServers != this.id){
            if(redirect){
                for(int i=1;i<this.numServers;i++){
                    boolean ok = getClient((this.id+i) % this.numServers).existeAresta(id, false);
                    if(ok)
                        return true;
                }
            }
            return false;
        }

        return a.contains(new Aresta(id, 0, 0, 0, false, ""));
    }

    public synchronized boolean addVertice(int id, int cor, double peso, String descricao, boolean redirect) throws TException{
        if(id % this.numServers != this.id){
            if (redirect){
                for(int i=1;i<this.numServers;i++){
                    boolean ok = getClient((this.id+i) % this.numServers).addVertice(id, cor, peso, descricao, false);
                    if(ok)
                        return true;
                }
            }
            return false;
        }

        if(this.existeVertice(id, false)) 
            return false;

        v.add(new Vertice(id, cor, peso, descricao));

        return true;
    }

    public synchronized boolean addAresta(int id, int va, int vb, double peso, boolean bidirecional, String descricao, boolean redirect) throws TException{
        if(id % this.numServers != this.id){
            if(redirect){
                for(int i=1;i<this.numServers;i++){
                    boolean ok = getClient((this.id+i) % this.numServers).addAresta(id, va, vb, peso, bidirecional, descricao, false);
                    if(ok)
                        return true;
                }
            }
            return false;
        }

        if(!this.existeVertice(va, true) || !this.existeVertice(vb, true) || this.existeAresta(id, false)) 
            return false;

        Aresta nova = new Aresta(id, va, vb, peso, bidirecional, descricao);

        a.add(nova);
        return true;
    }

    public Vertice getVertice(int id, boolean redirect) throws TException{
        if(id % this.numServers != this.id){
            if(redirect){
                for(int i=1;i<this.numServers;i++){
                    Vertice v = getClient((this.id+i) % this.numServers).getVertice(id, false);
                    if(v != null)
                        return v;
                }
            }
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
            if(redirect){
                for(int i=1;i<this.numServers;i++){
                    Aresta a = getClient((this.id+i) % this.numServers).getAresta(id, false);
                    if(a != null)
                        return a;
                }
            }
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

    public synchronized boolean removeVertice(int id, boolean redirect) throws TException{
        if(id % this.numServers != this.id){
            if(redirect){
                for(int i=1;i<this.numServers;i++){
                    boolean ok = getClient((this.id+i) % this.numServers).removeVertice(id, false);
                    if(ok)
                        return true;
                }
            }
            return false;
        }

        if(!this.existeVertice(id, false))
            return false;

        removeArestasVizinhas(id, true);
        v.remove(this.getVertice(id, false));

        return true;
    }
        
    public synchronized boolean removeAresta(int id, boolean redirect) throws TException{
        if(id % this.numServers != this.id){
            if(redirect){
                for(int i=1;i<this.numServers;i++){
                    boolean ok = getClient((this.id+i) % this.numServers).removeAresta(id, false);
                    if(ok)
                        return true;
                }
            }
            return false;
        }

        if(!this.existeAresta(id, false))
            return false;

        a.remove(this.getAresta(id, false));
        
        return true;
    }

    public synchronized String listaVerticesDeAresta(int id, boolean redirect) throws TException{
        if(id % this.numServers != this.id){
            if(redirect){
                for(int i=1;i<this.numServers;i++){
                    String resp = getClient((this.id+i) % this.numServers).listaVerticesDeAresta(id, false);
                    if(resp != "")
                        return resp;
                }
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

    public synchronized String listaArestasDeVertice(int id) throws TException{
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

    public synchronized String listaVerticesVizinhos(int id) throws TException{
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
    
    public synchronized boolean setCorVertice(int id, int cor, boolean redirect) throws TException{
        if(id % this.numServers != this.id){
            if(redirect){
                for(int i=1;i<this.numServers;i++){
                    boolean ok = getClient((this.id+i) % this.numServers).setCorVertice(id, cor, false);
                    if(ok)
                        return true;
                }
            }
            return false;
        }

        if(!existeVertice(id, false))
            return false;

        this.getVertice(id, false).setCor(cor);
        return true;
    }

    public synchronized boolean setPesoVertice(int id, double peso, boolean redirect) throws TException{
        if(id % this.numServers != this.id){
            if(redirect){
                for(int i=1;i<this.numServers;i++){
                    boolean ok = getClient((this.id+i) % this.numServers).setPesoVertice(id, peso, false);
                    if(ok)
                        return true;
                }
            }
            return false;
        }

        if(!existeVertice(id, false))
            return false;

        this.getVertice(id, false).setPeso(peso);
        return true;
    }

    public synchronized boolean setDescricaoVertice(int id, String descricao, boolean redirect) throws TException{
        if(id % this.numServers != this.id){
            if(redirect){
                for(int i=1;i<this.numServers;i++){
                    boolean ok = getClient((this.id+i) % this.numServers).setDescricaoVertice(id, descricao, false);
                    if(ok)
                        return true;
                }
            }
            return false;
        }

        if(!existeVertice(id, false))
            return false;

        this.getVertice(id, false).setDescricao(descricao);
        return true;
    }

    public synchronized boolean setPesoAresta(int id, double peso, boolean redirect) throws TException{
        if(id % this.numServers != this.id){
            if(redirect){
                for(int i=1;i<this.numServers;i++){
                    boolean ok = getClient((this.id+i) % this.numServers).setPesoAresta(id, peso, false);
                    if(ok)
                        return true;
                }
            }
            return false;
        }

        if(!existeAresta(id, false))
            return false;

        this.getAresta(id, false).setPeso(peso);
        return true;
    }

    public synchronized boolean setDescricaoAresta(int id, String descricao, boolean redirect) throws TException{
        if(id % this.numServers != this.id){
            if(redirect){
                for(int i=1;i<this.numServers;i++){
                    boolean ok = getClient((this.id+i) % this.numServers).setDescricaoAresta(id, descricao, false);
                    if(ok)
                        return true;
                }
            }
            return false;
        }

        if(!existeAresta(id, false))
            return false;

        this.getAresta(id, false).setDescricao(descricao);
        return true;
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
