package sdEntrega1;

import org.apache.thrift.TException;
import sdEntrega1.*;
import java.util.*;
 
public class Grafo implements sdEntrega1.Iface{
    public static Grafo instance = null;

    Set<Vertice> v;
    Set<Aresta> a;
    Set<Aresta> g[];

    public Grafo(){
        v = new HashSet<Vertice>();
        a = new HashSet<Aresta>();
        g = new HashSet[300];

        for(int i = 0; i < 300; i++)
            g[i] = new HashSet<Aresta>();
    }
    
    public synchronized boolean existeVertice(int id){
        return v.contains(new Vertice(id, 0, 0, ""));
    }

    public synchronized boolean existeAresta(int id){
        return a.contains(new Aresta(id, 0, 0, 0, false, ""));
    }

    public synchronized boolean addVertice(int id, int cor, double peso, String descricao){
        if(this.existeVertice(id)) 
            return false;

        v.add(new Vertice(id, cor, peso, descricao));
        return true;
    }

    public synchronized boolean addAresta(int id, int va, int vb, double peso, boolean bidirecional, String descricao){
        if(!this.existeVertice(va) || !this.existeVertice(vb) || this.existeAresta(id)) 
            return false;

        Aresta nova = new Aresta(id, va, vb, peso, bidirecional, descricao);

        a.add(nova);
        g[va].add(nova);

        if(bidirecional)
            g[vb].add(nova);

        return true;
    }

    public synchronized Vertice getVertice(int id){
        if(!this.existeVertice(id))
            return null;

        Iterator<Vertice> it = v.iterator();
        while(it.hasNext()){
            Vertice i = it.next();
            if(i.getId() == id)
                return i;
        }
        return null;
    }

    public synchronized Aresta getAresta(int id){
        if(!this.existeAresta(id))
            return null;

        Iterator<Aresta> it = a.iterator();
        while(it.hasNext()){
            Aresta i = it.next();
            if(i.getId() == id)
                return i;
        }
        return null;
    }

    public synchronized boolean removeVertice(int id){
        if(!this.existeVertice(id))
            return false;

        Iterator<Aresta> it = g[id].iterator();
        while(it.hasNext()){
            Aresta i = it.next();
            this.removeAresta(i.getId());
        }

        v.remove(this.getVertice(id));

        return true;
    }
        
    public synchronized boolean removeAresta(int id){
        if(!this.existeAresta(id))
            return false;

        Aresta i = this.getAresta(id);

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

    public synchronized String listaVerticesDeAresta(int id){
        if(!this.existeAresta(id))
            return "Aresta inexistente.";

        String s = "";
        Aresta i = this.getAresta(id);
        Vertice va = this.getVertice(i.getVa());
        Vertice vb = this.getVertice(i.getVb());

        s += "Lista de vertices da aresta " + String.valueOf(id) + ":\n";
        s += "Vertice 1:\n" + va.getInformacoes() + "\n";
        s += "Vertice 2:\n" + vb.getInformacoes() + "\n";
        return s;
    }

    public synchronized String listaArestasDeVertice(int id){
        if(!this.existeVertice(id))
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

    public synchronized String listaVerticesVizinhos(int id){
        if(!this.existeVertice(id))
            return "Vertice inexistente.";

        int contador = 1;
        String s = "";
        Iterator<Aresta> it = g[id].iterator();
        while(it.hasNext()){
            Aresta i = it.next();
            Vertice viz = this.getVertice(i.getVa() == id ? i.getVb() : i.getVa());

            s += String.valueOf(contador++) + "o. vertice:\n";
            s += viz.getInformacoes() + "\n";
        }

        if(contador == 1)
            return "Nao ha vertices vizinhos para este vertice.\n";

        return s;
    }
    
    public synchronized boolean setCorVertice(int id, int cor){
        if(!existeVertice(id))
            return false;

        this.getVertice(id).setCor(cor);
        return true;
    }

    public synchronized boolean setPesoVertice(int id, double peso){
        if(!existeVertice(id))
            return false;
        this.getVertice(id).setPeso(peso);
        return true;
    }

    public synchronized boolean setDescricaoVertice(int id, String descricao){
        if(!existeVertice(id))
            return false;
        this.getVertice(id).setDescricao(descricao);
        return true;
    }

    public synchronized boolean setPesoAresta(int id, double peso){
        if(!existeAresta(id))
            return false;
        this.getAresta(id).setPeso(peso);
        return true;
    }

    public synchronized boolean setDescricaoAresta(int id, String descricao){
        if(!existeAresta(id))
            return false;
        this.getAresta(id).setDescricao(descricao);
        return true;
    }
}