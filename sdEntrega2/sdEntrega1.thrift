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
    
    bool addVertice(1: i32 id, 2: i32 cor, 3: double peso, 4: string descricao, 5: bool redirect),
    bool addAresta(1: i32 id, 2: i32 va, 3: i32 vb, 4: double peso, 5: bool bidirecional, 6: string descricao, 7:bool redirect),
    bool removeVertice(1: i32 id, 2: bool redirect),
    bool removeAresta(1: i32 id, 2: bool redirect),

    string listaVerticesDeAresta(1: i32 id, 2: bool redirect),
    string listaArestasDeVertice(1: i32 id, 2: bool redirect),
    string listaVerticesVizinhos(1: i32 id, 2: bool redirect),

    bool setCorVertice(1: i32 id, 2: i32 cor, 3: bool redirect),
    bool setPesoVertice(1: i32 id, 2: double peso, 3: bool redirect),
    bool setDescricaoVertice(1: i32 id, 2: string descricao, 3: bool redirect),
    bool setPesoAresta(1: i32 id, 2: double peso, 3: bool redirect),
    bool setDescricaoAresta(1: i32 id, 2: string descricao, 3: bool redirect)
}
