package sdEntrega1;

import sdEntrega1.*;
import org.apache.thrift.TException;
 
public class Grafo implements sdEntrega1.Iface{
    public static Grafo instance = null;

    Vertice vertices[] = new Vertice[200]; 
    boolean verticesAtivos[] = new boolean[200]; 

    Aresta arestas[] = new Aresta[200*200]; 
    boolean arestasAtivas[] = new boolean[200*200]; 

    Aresta g[][] = new Aresta[200][200];
    
    public Grafo(){
        for(int i = 0; i < 200; i++){
            verticesAtivos[i] = false;

            for(int j = 0; j < 200; j++){
                g[i][j] = null;
                arestasAtivas[i*200+j] = false;
            }
        }
    }
    
    public synchronized boolean existeVertice(int id){
        return verticesAtivos[id];
    }

    public synchronized boolean existeAresta(int id){
        return arestasAtivas[id];
    }

    public synchronized boolean addVertice(int id, int cor, double peso, String descricao){
        if(this.existeVertice(id)) 
            return false;

        verticesAtivos[id] = true;
        vertices[id] = new Vertice(id, cor, peso, descricao);

        return true;
    }

    // "defeito": nao pode ter arestas duplas!
    public synchronized boolean addAresta(int id, int va, int vb, double peso, boolean bidirecional, String descricao){
        if(!this.existeVertice(va) || !this.existeVertice(vb)) 
            return false;

        arestasAtivas[id] = true;
        arestas[id] = new Aresta(id, va, vb, peso, bidirecional, descricao);
        
        if(bidirecional){
            g[va][vb] = new Aresta(id, va, vb, peso, bidirecional, descricao);        
            g[vb][va] = new Aresta(id, va, vb, peso, bidirecional, descricao);
        }
        else if(g[va][vb].getPeso() > peso)
            g[va][vb] = new Aresta(id, va, vb, peso, bidirecional, descricao);

        return true;
    }

    public synchronized boolean removeVertice(int id){
        if(!this.existeVertice(id))
            return false;

        for (int i = 0; i < 200; i++){
            if(g[id][i] != null){
                removeAresta(g[id][i].getId());                
                g[id][i] = null;
            }
        }

        verticesAtivos[id] = false;
        vertices[id] = null;
        
        return true;
    }
        
    public synchronized boolean removeAresta(int id){
        if(!this.existeAresta(id))
            return false;

        int va = arestas[id].getVa();
        int vb = arestas[id].getVb();

        if(arestas[id].isBidirecional()){
            g[va][vb] = null;
            g[va][vb] = null;
        }
        else
            g[va][vb] = null;

        arestasAtivas[id] = false;
        arestas[id] = null;

        return true;
    }

    public synchronized String listaVerticesDeAresta(int id){
        if(!this.existeAresta(id))
            return "Aresta inexistente.";

        String s = "";

        Vertice va = vertices[arestas[id].getVa()];
        Vertice vb = vertices[arestas[id].getVb()];

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
        for(int i = 0; i < 200; i++){
            if(g[id][i] != null){
                s += String.valueOf(contador++) + "a. aresta:\n";
                s += g[i][j].getInformacoes() + "\n"; 
            }
        }
        return s;
    }

    public synchronized String listaVerticesVizinhos(int id){
        if(!this.existeVertice(id))
            return "Vertice inexistente.";

        int contador = 1;
        String s = "";
        for(int i = 0; i < 200; i++){
            if(g[id][i] != null){
                s += String.valueOf(contador++) + "o. vertice:\n";
                s += vertices[i].getInformacoes() + "\n";
            }
        }
        return s;
    }
    
    public synchronized boolean setCorVertice(int id, int cor){
        if(!existeVertice(id))
            return false;
        vertices[id].setCor(cor);
        return true;
    }

    public synchronized boolean setPesoVertice(int id, double peso){
        if(!existeVertice(id))
            return false;
        vertices[id].setPeso(peso);
        return true;
    }

    public synchronized boolean setDescricaoVertice(int id, String descricao){
        if(!existeVertice(id))
            return false;
        vertices[id].setDescricao(descricao);
        return true;
    }

    public synchronized boolean setPesoAresta(int id, double peso){
        if(!existeAresta(id))
            return false;
        arestas[id].setPeso(peso);
        return true;
    }

    public synchronized boolean setDescricaoAresta(int id, String descricao){
        if(!existeAresta(id))
            return false;
        arestas[id].setDescricao(descricao);
        return true;
    }
}
