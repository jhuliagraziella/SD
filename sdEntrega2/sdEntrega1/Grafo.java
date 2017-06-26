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
    Set<Aresta> g[];

    int numServers;

    public Grafo(int id, int q){
        this.id = id;
        this.numServers = q;

        v = new HashSet<Vertice>();
        a = new HashSet<Aresta>();
        g = new HashSet[300];

        for(int i = 0; i < 300; i++)
            g[i] = new HashSet<Aresta>();
    }

    public synchronized sdEntrega1.Client getClient(int id) throws TException{
        TTransport transport = new TSocket("localhost", 9090+id);
        transport.open();
        TProtocol protocol = new TBinaryProtocol(transport);
        sdEntrega1.Client client = new sdEntrega1.Client(protocol); 
        return client;
    }
    
    public synchronized boolean existeVertice(int id, boolean redirect) throws TException{
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

    public synchronized boolean existeAresta(int id, boolean redirect) throws TException{
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
        g[va].add(nova);

        if(bidirecional)
            g[vb].add(nova);

        return true;
    }

    public synchronized Vertice getVertice(int id, boolean redirect) throws TException{
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

    public synchronized Aresta getAresta(int id, boolean redirect) throws TException{
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

        Iterator<Aresta> it = g[id].iterator();
        while(it.hasNext()){
            Aresta i = it.next();
            this.removeAresta(i.getId(), true);
        }

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

        Aresta i = this.getAresta(id, false);

        int va = i.getVa();
        Iterator<Aresta> it = g[va].iterator();
        while(it.hasNext()){
            Aresta j = it.next();
            if(j.getId() == id){
                g[va].remove(j);
                break;
            }
        }
        if(i.isBidirecional()){
            int vb = i.getVb();
            it = g[vb].iterator();

            while(it.hasNext()){
                Aresta j = it.next();
                if(j.getId() == id){
                    g[vb].remove(j);
                    break;
                }
            }
        }

        a.remove(i);
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
        s += "Vertice 1:\n" + va.getInformacoes() + "\n";
        s += "Vertice 2:\n" + vb.getInformacoes() + "\n";
        return s;
    }

    public synchronized String listaArestasDeVertice(int id, boolean redirect) throws TException{
        if(id % this.numServers != this.id){
            if(redirect){
                for(int i=1;i<this.numServers;i++){
                    String resp = getClient((this.id+i) % this.numServers).listaArestasDeVertice(id, false);
                    if(resp != "")
                        return resp;
                }
            }
            return "";
        }

        if(!this.existeVertice(id, false))
            return "Vertice inexistente.";

        int contador = 1;
        String s = "";
        Iterator<Aresta> it = g[id].iterator();
        while(it.hasNext()){
            Aresta i = it.next();
            s += String.valueOf(contador++) + "a. aresta:\n";
            s += i.getInformacoes() + "\n"; 
        }

        if(contador == 1)
            return "Nao ha arestas adjacentes a esse vertice.\n";

        return s;
    }

    // MARCADOR
    public synchronized String listaVerticesVizinhos(int id, boolean redirect) throws TException{
        if(id % this.numServers != this.id){
            if(redirect){
                for(int i=1;i<this.numServers;i++){
                    String resp = getClient((this.id+i) % this.numServers).listaVerticesVizinhos(id, false);
                    if(resp != "")
                        return resp;
                }
            }
            return "";
        }
        if(!this.existeVertice(id, false))
            return "Vertice inexistente.";

        int contador = 1;
        String s = "";
        Iterator<Aresta> it = g[id].iterator();
        while(it.hasNext()){
            Aresta i = it.next();
            Vertice viz = this.getVertice(i.getVa() == id ? i.getVb() : i.getVa(), true);

            s += String.valueOf(contador++) + "o. vertice:\n";
            s += viz.getInformacoes() + "\n";
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
}