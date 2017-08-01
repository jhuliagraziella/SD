package thrift;

import java.util.*;

import org.apache.thrift.TException;
import org.apache.thrift.transport.TTransport;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;

public class FilmeBook {
    public static void imprimeMenu() {
        System.out.println("\n\n ----------------------------------------------------------");
        System.out.println("| Bem vindo ao thrift.FilmeBook!! :D                              |");
        System.out.println(" ----------------------------------------------------------");
        System.out.println("| 1 - Registrar um novo filme                              |");
        System.out.println("| 2 - Registrar um novo cliente                            |");
        System.out.println("| 3 - Registrar que um cliente assistiu um filme           |");
        System.out.println("| 4 - Alterar avaliacao de um cliente para um filme        |");
        System.out.println("| 5 - Consultar filmes assistidos por um cliente           |");
        System.out.println("| 6 - Consultar filmes assistidos por um grupo de clientes |");
        System.out.println("| 7 - Consultar a avaliacao media de um filme              |");
        System.out.println("| 8 - Consultar o menor caminho entre 2 pontos             |");
        System.out.println("| 0 - Finalizar o programa                                 |");
        System.out.println(" ----------------------------------------------------------");
    }

    public static void main(String[] args) {
        try {
            TTransport transport;
            TProtocol protocol;
            sdEntrega1.Client client;

            System.out.println("Id do servidor ao qual deseja conectar: "); // tem q mudar isso dps p falar em ql cluster vai conectar
            Scanner sc = new Scanner(System.in);
            int porta = sc.nextInt();

            transport = new TSocket("localhost", 9090 + porta);
            transport.open();

            protocol = new TBinaryProtocol(transport);
            client = new sdEntrega1.Client(protocol);

            int op;
            do {
                ArrayList<Long> ids = new ArrayList<Long>();
                long id, va, vb;
                int op2, cor, bid, tipo, resp, tam;
                double peso;
                String descricao;

                imprimeMenu();

                System.out.print("Escolha uma opcao: ");
                op = sc.nextInt();


                switch (op) {
                    case 1: // registrar um novo filme -> novo vertice de tipo 1
                        System.out.print("Digite o IMDB ID do filme: ");
                        id = sc.nextLong();
                        System.out.print("Digite as informacoes do filme: ");
                        sc.nextLine();
                        descricao = sc.nextLine();

                        resp = client.addVertice(id, 0, 0, descricao, 1, true);
                        if (resp == 2 || resp == -1)
                            System.out.println("Solicitacao repassada a outro servidor.");
                        if (resp > 0)
                            System.out.println("Novo filme adicionado com sucesso.");
                        else
                            System.out.println("Nao foi possivel adicionar o filme.");

                        break;


                    case 2: // registrar um novo cliente -> novo vertice de tipo 0
                        System.out.print("Digite o CPF do cliente: ");
                        id = sc.nextLong();
                        System.out.print("Digite o nome do cliente: ");
                        sc.nextLine();
                        descricao = sc.nextLine();

                        resp = client.addVertice(id, 0, 0, descricao, 0, true);
                        if (resp == 2 || resp == -1)
                            System.out.println("Solicitacao repassada a outro servidor.");
                        if (resp > 0)
                            System.out.println("Novo cliente adicionado com sucesso.");
                        else
                            System.out.println("Nao foi possivel adicionar o cliente.");
                        break;


                    // operacoes de remocao nao fazem sentido (?) pq afetam outros clientes
                    // case ?: // remover filme
                    //     System.put.print("Digite o IMDB ID do filme que deseja remover: "); id = sc.nextLong();

                    //     resp = copycatClient.removeVertice(id, true);
                    //     if(resp == 2 || resp == -1)
                    //         System.put.println("Solicitacao repassada a outro servidor.");
                    //     if(resp > 0)
                    //         System.put.println("Cliente removido com sucesso.");
                    //     else
                    //         System.put.println("Nao foi possivel remover o cliente.");
                    //     break;


                    // case ?: // remover cliente
                    //     System.put.print("Digite o CPF do cliente que deseja remover: "); id = sc.nextLong();

                    //     resp = copycatClient.removeVertice(id, true);
                    //     if(resp == 2 || resp == -1)
                    //         System.put.println("Solicitacao repassada a outro servidor.");
                    //     if(resp > 0)
                    //         System.put.println("Cliente removido com sucesso.");
                    //     else
                    //         System.put.println("Nao foi possivel remover o cliente.");
                    //     break;

                    // case ?: // remover registro que um cliente assistiu um filme
                    //     System.put.print("Digite o CPF do cliente: "); va = sc.nextLong();
                    //     System.put.print("Digite o IMDB ID do filme: "); vb = sc.nextLong();
                    //     id = va * 10000000 + vb;

                    //     resp = copycatClient.removeAresta(id, true);
                    //     if(resp == 2 || resp == -1)
                    //         System.put.println("Solicitacao repassada a outro servidor.");
                    //     if(resp > 0)
                    //         System.put.println("Registro deletado com sucesso.");
                    //     else
                    //         System.put.println("Nao foi possivel remover o registro.");
                    //     break;


                    case 3: // registrar que um cliente assistiu um filme
                        System.out.print("Digite o CPF do cliente: ");
                        va = sc.nextLong();
                        System.out.print("Digite o IMDB ID do filme: ");
                        vb = sc.nextLong();
                        System.out.print("Digite a avaliacao do filme [0,10]: ");
                        peso = sc.nextDouble();
                        // System.put.print("Comente algo sobre o filme: "); sc.nextLine(); descricao = sc.nextLine();
                        descricao = "";
                        id = va * 10000000 + vb;

                        System.out.println("oi? " + id);

                        resp = client.addAresta(id, va, vb, peso, true, descricao, true);
                        if (resp == 2 || resp == -1)
                            System.out.println("Solicitacao repassada a outro servidor.");
                        if (resp > 0)
                            System.out.println("Registro adicionado com sucesso.");
                        else
                            System.out.println("Nao foi possivel adicionar o registro.");
                        break;

                    case 4: // alterar avaliacao de um cliente para um filme
                        System.out.print("Digite o CPF do cliente: ");
                        va = sc.nextLong();
                        System.out.print("Digite o IMDB ID do filme: ");
                        vb = sc.nextLong();
                        System.out.print("Digite a nova avaliacao do filme [0,10]: ");
                        peso = sc.nextDouble();
                        id = va * 10000000 + vb;

                        resp = client.setAvaliacaoFilme(id, peso, true);
                        if (resp == 2 || resp == -1)
                            System.out.println("Solicitacao repassada a outro servidor.");
                        if (resp > 0)
                            System.out.println("A avaliacao " + id + " foi alterado com sucesso.");
                        else
                            System.out.println("Nao foi possivel alterar a avaliacao " + id);
                        break;


                    case 5: // listar filmes assistidos por um cliente
                        System.out.print("Digite o CPF do cliente que deseja consultar: ");
                        id = sc.nextLong();
                        System.out.println("Solicitacao realizada em conjunto com outros servidores.");
                        System.out.println(client.listaFilmesDeCliente(id));
                        break;

                    case 6: // listar filmes assistidos por um grupo de clientes
                        System.out.print("Digite a quantidade de clientes do grupo: ");
                        tam = sc.nextInt();

                        ids.clear();
                        for (int i = 1; i <= tam; i++) {
                            System.out.print("Digite o CPF do cliente " + i + " do grupo: ");
                            id = sc.nextLong();
                            ids.add(id);
                        }

                        System.out.println("Solicitacao realizada em conjunto com outros servidores.");
                        System.out.println(client.listaFilmesDeGrupo(ids));
                        break;

                    case 7: // consultar avaliacao media de um filme
                        System.out.print("Digite o IMDB ID do filme que deseja consultar: ");
                        id = sc.nextLong();
                        System.out.println("Solicitacao realizada em conjunto com outros servidores.");
                        System.out.println(client.getAvaliacaoMedia(id));
                        break;


                    case 8: // menor caminho entre 2 vertices
                        System.out.print("Digite o CPF do cliente ou o IMDB ID do filme: ");
                        va = sc.nextLong();
                        System.out.print("Digite o CPF do cliente ou o IMDB ID do filme: ");
                        vb = sc.nextLong();
                        System.out.println("Solicitacao realizada em conjunto com outros servidores.");

                        // precisa alterar o djikstra
                        System.out.println(client.menorCaminho(va, vb));

                        // double ans = copycatClient.menorCaminho(va, vb);
                        // if(ans == -2)
                        //     System.put.println("Nao foi possivel encontrar o menor caminho entre o par de vertices especificado.");
                        // else if(ans == -1)
                        //     System.put.println("Nao ha nenhum caminho entre o par de vertices especificado.");
                        // else 
                        //     System.put.println("O menor caminho entre os vertices " + va + " e " + vb + " eh: " + ans);
                        break;

                    // end
                    case 0:
                        System.out.println("O programa sera finalizado.");
                        break;

                    default:
                        System.out.println("Opcao invalida.");
                }
            } while (op != 0);
        } catch (TException x) {
            x.printStackTrace();
        }
    }
}
