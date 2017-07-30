namespace java sdEntrega1

struct Vertice{
    1: i64 id,
    2: i32 cor,
    3: double peso,
    4: string descricao,
    5: i32 tipo
}

struct Aresta{
    1: i64 id,
    2: i64 va,
    3: i64 vb,
    4: double peso,
    5: bool bidirecional,
    6: string descricao
}

service sdEntrega1{
    bool existeVertice(1: i64 id, 2: bool redirect),
    bool existeAresta(1: i64 id, 2: bool redirect),

    Vertice getVertice(1: i64 id, 2: bool redirect),
    Aresta getAresta(1: i64 id, 2: bool redirect),

    list<Aresta> getArestasAdjacentes(1: i64 id),
    
    i32 addVertice(1: i64 id, 2: i32 cor, 3: double peso, 4: string descricao, 5: i32 tipo, 6: bool redirect),
    i32 addAresta(1: i64 id, 2: i64 va, 3: i64 vb, 4: double peso, 5: bool bidirecional, 6: string descricao, 7:bool redirect),
    
    // i32 removeVertice(1: i64 id, 2: bool redirect),
    // i32 removeAresta(1: i64 id, 2: bool redirect),

    // void removeArestasVizinhas(1: i64 v, 2: bool redirect),

    // string listaVerticesDeAresta(1: i64 id, 2: bool redirect),
    // string listaArestasDeVertice(1: i64 id),
    // string listaVerticesVizinhos(1: i64 id),

    string listaFilmesDeCliente(1: i64 id),
    string listaFilmesDeGrupo(1: list<i64> ids),
    string getAvaliacaoMedia(1: i64 id),

    // i32 setCorVertice(1: i64 id, 2: i32 cor, 3: bool redirect),
    // i32 setPesoVertice(1: i64 id, 2: double peso, 3: bool redirect),
    // i32 setDescricaoVertice(1: i64 id, 2: string descricao, 3: bool redirect),
    // i32 setPesoAresta(1: i64 id, 2: double peso, 3: bool redirect),
    // i32 setDescricaoAresta(1: i64 id, 2: string descricao, 3: bool redirect),

    i32 setAvaliacaoFilme(1: i64 id, 2: double peso, 3: bool redirect),

    string menorCaminho(1: i64 va, 2: i64 vb)
}