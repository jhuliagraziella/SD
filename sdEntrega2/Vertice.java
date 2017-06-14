package sdEntrega1;

public class Vertice {
    int id;
    int cor;
    double peso;
    String descricao;

    public Vertice(int id, int cor, double peso, String descricao){
        this.id = id;
        this.cor = cor;
        this.peso = peso;
        this.descricao = descricao;
    }

    public boolean equals(Object obj){
        Vertice aux = (Vertice) obj;
        return aux.id == this.id;
    }

    public int hashCode(){
        return this.id;
    }

    public int getId(){
        return this.id;
    }

    public int getCor(){
        return this.cor;
    }

    public double getPeso(){
        return this.peso;
    }

    public String getDescricao(){
        return this.descricao;
    }

    public String getInformacoes(){
        String s = "";
        s += "Nome: " + String.valueOf(this.id) + "\n";
        s += "Cor: " + String.valueOf(this.cor) + "\n";
        s += "Peso: " + String.valueOf(this.peso) + "\n";
        s += "Descricao: " + this.descricao + "\n";
    
        return s;
    }

    public void setCor(int cor){
        this.cor = cor;
    }

    public void setPeso(double peso){
        this.peso = peso;
    }

    public void setDescricao(String descricao){
        this.descricao = descricao;
    }
}
