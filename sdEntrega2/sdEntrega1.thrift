namespace java sdEntrega1

struct Vertice{
    1: i32 id,
    2: i32 cor,
    3: double peso,
    4: string descricao,
}

struct Aresta{
    1: i32 id,
    2: i32 va,
    3: i32 vb,
    4: double peso,
    5: bool bidirecional,
    6: string descricao
}

service sdEntrega1{
    bool existeVertice(1: i32 id, 2: bool redirect),
    bool existeAresta(1: i32 id, 2: bool redirect),

    Vertice getVertice(1: i32 id, 2: bool redirect),
    Aresta getAresta(1: i32 id, 2: bool redirect),

    list<Aresta> getArestasAdjacentes(1: i32 id),
    
    i32 addVertice(1: i32 id, 2: i32 cor, 3: double peso, 4: string descricao, 5: bool redirect),
    i32 addAresta(1: i32 id, 2: i32 va, 3: i32 vb, 4: double peso, 5: bool bidirecional, 6: string descricao, 7:bool redirect),
    i32 removeVertice(1: i32 id, 2: bool redirect),
    i32 removeAresta(1: i32 id, 2: bool redirect),

    void removeArestasVizinhas(1: i32 v, 2: bool redirect),

    string listaVerticesDeAresta(1: i32 id, 2: bool redirect),
    string listaArestasDeVertice(1: i32 id),
    string listaVerticesVizinhos(1: i32 id),

    i32 setCorVertice(1: i32 id, 2: i32 cor, 3: bool redirect),
    i32 setPesoVertice(1: i32 id, 2: double peso, 3: bool redirect),
    i32 setDescricaoVertice(1: i32 id, 2: string descricao, 3: bool redirect),
    i32 setPesoAresta(1: i32 id, 2: double peso, 3: bool redirect),
    i32 setDescricaoAresta(1: i32 id, 2: string descricao, 3: bool redirect),

    double menorCaminho(1: i32 va, 2: i32 vb)
}