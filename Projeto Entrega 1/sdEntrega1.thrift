namespace java package sdEntrega1

service sdEntrega1{
    bool existeVertice(1: i32 id),
    bool existeAresta(1: i32 id),
    bool addVertice(1: i32 id, 2: i32 cor, 3: double peso, 4: string descricao),
    bool addAresta(1: i32 id, 2: i32 va, 3: i32 vb, 4: double peso, 5: bool bidirecional, 6: string descricao),
    bool removeVertice(1: i32 id),
    bool removeAresta(1: i32 id),
    string listaVerticesDeAresta(1: i32 id),
    string listaArestasDeVertice(1: i32 id),
    string listaVerticesVizinhos(1: i32 id),

    bool setCorVertice(1: i32 id, 2: i32 cor),
    bool setPesoVertice(1: i32 id, 2: double peso),
    bool setDescricaoVertice(1: i32 id, 2: string descricao),
    bool setPesoAresta(1: i32 id, 2: double peso),
    bool setDescricaoAresta(1: i32 id, 2: string descricao)
}