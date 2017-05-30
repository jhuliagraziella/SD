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

    public int getId(){
        return this.id;
    }

    public String getCor(){
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
        s += "Nome: " + String.valueOf(va.getNome()) + "\n"
        s += "Cor: " + String.valueOf(va.getCor()) + "\n"
        s += "Peso: " + String.valueOf(va.getPeso()) + "\n"
        s += "Descrição: " + va.getDescricao() + "\n";
    
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