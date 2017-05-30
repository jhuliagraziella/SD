package sdEntrega1;
 
public class Aresta {
    int id, va, vb;
    double peso;
    boolean bidirecional;
    String descricao;

    public Aresta(int id, int va, int vb, double peso, boolean bidirecional, String descricao){
        this.id = id;
        this.va = va;
        this.vb = vb;
        this.peso = peso;
        this.bidirecional = bidirecional;
        this.descricao = descricao;
    }

    public int getId(){
        return this.id;
    }
    
    public int getVa(){
        return this.va;
    }
    
    public int getVb(){
        return this.vb;
    }
        
    public double getPeso(){
        return this.peso;
    }
    
    public boolean isBidirecional(){
        return this.bidirecional;
    }

    public String getDescricao(){
        return this.descricao;
    }

    public String getInformacoes(){
        String s = "";
        s += "Id: " + String.valueOf(g[i][j].getId()) + "\n";
        s += "Vertice 1: " + String.valueOf(g[i][j].getVa());
        s += "Vertice 2: " + String.valueOf(g[i][j].getVb());
        s += "Peso: " + String.valueOf(g[i][j].getPeso()) + "\n";
        s += "Bidirecional: " + (g[i][j].isBidirecional() ? "sim" : "nao") + "\n";
        s += "Descricao: " + g[i][j].getDescricao() + "\n";

        return s;
    }

    public void setPeso(double peso){
        this.peso = peso;
    }

    public void setDescricao(String descricao){
        this.descricao = descricao;
    }
}