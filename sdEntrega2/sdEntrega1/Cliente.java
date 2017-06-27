package sdEntrega1;

import java.util.Scanner;
import org.apache.thrift.TException;
import org.apache.thrift.transport.TTransport;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import sdEntrega1.*;
 
public class Cliente {
    public static void imprimeMenu(){
        System.out.println("\n\n ----------------------------------------------");
        System.out.println("| 1 - Adicionar um vertice                     |");
        System.out.println("| 2 - Adicionar uma aresta                     |");
        System.out.println("| 3 - Deletar um vertice                       |");
        System.out.println("| 4 - Deletar uma aresta                       |");
        System.out.println("| 5 - Listar vertices de uma aresta            |");
        System.out.println("| 6 - Listar arestas adjacentes a um vertice   |");
        System.out.println("| 7 - Listar vertices adjacentes a um vertice  |");
        System.out.println("| 8 - Modificar um vertice                     |");
        System.out.println("| 9 - Modificar uma aresta                     |");
        System.out.println("| 10 - Menor caminho entre dois vertices       |");
        System.out.println("| 0 - Finalizar o programa                     |");
        System.out.println(" ----------------------------------------------");
    }
    public static void imprimeSubMenuVertice(){
        System.out.println("\n\n ----------------------------------------");
        System.out.println("| 1 - Alterar a cor de um vertice        |");
        System.out.println("| 2 - Alterar o peso de um vertice       |");
        System.out.println("| 3 - Alterar a descricao de um vertice  |");
        System.out.println("| 0 - Cancelar                           |");
        System.out.println(" ----------------------------------------");
    }

    public static void imprimeSubMenuAresta(){
        System.out.println("\n\n ----------------------------------------");
        System.out.println("| 1 - Alterar o peso de uma aresta       |");
        System.out.println("| 2 - Alterar a descricao de uma aresta  |");
        System.out.println("| 0 - Cancelar                           |");
        System.out.println(" ----------------------------------------");

    }
    public static void main(String [] args) {
        try {
            TTransport transport;
            TProtocol protocol;
            sdEntrega1.Client client;
        
            System.out.println("Id do servidor ao qual deseja conectar: ");
            Scanner sc = new Scanner(System.in);
            int porta = sc.nextInt();

            transport = new TSocket("localhost", 9090+porta);
            transport.open();

            protocol = new TBinaryProtocol(transport);
            client = new sdEntrega1.Client(protocol);

            int op;
            do {
                imprimeMenu();

                int op2, id, cor, va, vb, bid, resp;
                double peso;
                String descricao;

                System.out.print("Escolha uma opcao: ");
                op = sc.nextInt();

                switch(op){
                    case 1: // adicionar vertice
                        System.out.print("Digite o id do novo vertice: "); id = sc.nextInt();
                        System.out.print("Digite a cor do novo vertice: "); cor = sc.nextInt();
                        System.out.print("Digite o peso do novo vertice: "); peso = sc.nextDouble();
                        System.out.print("Digite a descricao do novo vertice: "); sc.nextLine(); descricao = sc.nextLine();

                        resp = client.addVertice(id, cor, peso, descricao, true);
                        if(resp == 2 || resp == -1)
                            System.out.println("Solicitacao repassada a outro servidor.");
                        if(resp > 0)
                            System.out.println("Vertice adicionado com sucesso.");
                        else
                            System.out.println("Nao foi possivel adicionar o vertice.");
                        break;

                    case 2: // adicionar aresta
                        System.out.print("Digite o id da nova aresta: "); id = sc.nextInt();
                        System.out.print("Digite o vertice 1 da nova aresta: "); va = sc.nextInt();
                        System.out.print("Digite o vertice 2 da nova aresta: "); vb = sc.nextInt();
                        System.out.print("Digite o peso da nova aresta: "); peso = sc.nextDouble();
                        System.out.print("Digite 1 se a aresta for bidirecional ou 0, caso contrario: "); bid = sc.nextInt();
                        System.out.print("Digite a descricao da nova aresta: "); sc.nextLine();  descricao = sc.nextLine();

                        resp = client.addAresta(id, va, vb, peso, (bid == 1), descricao, true);
                        if(resp == 2 || resp == -1)
                            System.out.println("Solicitacao repassada a outro servidor.");
                        if(resp > 0)
                            System.out.println("Aresta adicionada com sucesso.");
                        else
                            System.out.println("Nao foi possivel adicionar a aresta.");
                        break;
                    
                    case 3: // remover vertice
                        System.out.print("Digite o id do vertice que deseja remover: "); id = sc.nextInt();

                        resp = client.removeVertice(id, true);
                        if(resp == 2 || resp == -1)
                            System.out.println("Solicitacao repassada a outro servidor.");
                        if(resp > 0)
                            System.out.println("Vertice deletado com sucesso.");
                        else
                            System.out.println("Nao foi possivel remover o vertice.");
                        break;

                    case 4: // remover aresta
                        System.out.print("Digite o id da aresta que deseja remover: "); id = sc.nextInt();

                        resp = client.removeAresta(id, true);
                        if(resp == 2 || resp == -1)
                            System.out.println("Solicitacao repassada a outro servidor.");
                        if(resp > 0)
                            System.out.println("Aresta deletada com sucesso.");
                        else
                            System.out.println("Nao foi possivel remover a aresta.");
                        break;

                    case 5: // listar vertices de aresta
                        System.out.print("Digite o id da aresta: "); id = sc.nextInt();
                        System.out.println(client.listaVerticesDeAresta(id, true));
                        break;

                    case 6: // listar arestas de um vertice
                        System.out.print("Digite o id do vertice: "); id = sc.nextInt();
                        System.out.println("Solicitacao realizada em conjunto com outros servidores.");
                        System.out.println(client.listaArestasDeVertice(id));
                        break;

                    case 7: // listar vertices vizinhos de um vertice
                        System.out.print("Digite o id do vertice: "); id = sc.nextInt();
                        System.out.println("Solicitacao realizada em conjunto com outros servidores.");
                        System.out.println(client.listaVerticesVizinhos(id));
                        break;

                    case 8: // modificar vertice
                        do {
                            imprimeSubMenuVertice();
                            System.out.print("Escolha uma opcao: ");
                            op2 = sc.nextInt();

                            switch(op2){
                                case 1: // alterar cor
                                    System.out.print("Digite o id do vertice: "); id = sc.nextInt();
                                    System.out.print("Digite a nova cor do vertice: "); cor = sc.nextInt();
                                    
                                    resp = client.setCorVertice(id, cor, true);
                                    if(resp == 2 || resp == -1)
                                        System.out.println("Solicitacao repassada a outro servidor.");
                                    if(resp > 0)
                                        System.out.println("A cor do vertice " + id + " foi alterada com sucesso.");
                                    else
                                        System.out.println("Nao foi possivel alterar a cor do vertice " + id);
                                    break;

                                case 2: // alterar peso
                                    System.out.print("Digite o id do vertice: "); id = sc.nextInt();
                                    System.out.print("Digite o nova peso do vertice: "); peso = sc.nextDouble();
                                    
                                    resp = client.setPesoVertice(id, peso, true);
                                    if(resp == 2 || resp == -1)
                                        System.out.println("Solicitacao repassada a outro servidor.");
                                    if(resp > 0)
                                        System.out.println("O peso do vertice " + id + " foi alterado com sucesso.");
                                    else
                                        System.out.println("Nao foi possivel alterar o peso do vertice " + id);
                                    break;
                               
                                case 3: // alterar descricao
                                    System.out.print("Digite o id do vertice: "); id = sc.nextInt();
                                    System.out.print("Digite a nova descricao do vertice: "); sc.nextLine(); descricao = sc.nextLine();

                                    resp = client.setDescricaoVertice(id, descricao, true);
                                    if(resp == 2 || resp == -1)
                                        System.out.println("Solicitacao repassada a outro servidor.");
                                    if(resp > 0)
                                        System.out.println("A descricao do vertice " + id + " foi alterada com sucesso.");
                                    else
                                        System.out.println("Nao foi possivel alterar a descricao do vertice " + id);
                                    break;

                                case 0: // cancelar
                                    System.out.println("Operacao cancelada.");
                                    break;

                                default:
                                    System.out.println("Opcao invalida.");
                            }
                        } while(op2 < 0 || op2 > 3);

                        break;

                    // modificar aresta
                    case 9:
                        do {
                            imprimeSubMenuAresta();
                            System.out.print("Escolha uma opcao: ");
                            op2 = sc.nextInt();

                            switch(op2){
                                case 1: // alterar peso
                                    System.out.print("Digite o id da aresta: "); id = sc.nextInt();
                                    System.out.print("Digite o novo peso da aresta: "); peso = sc.nextDouble();

                                    resp = client.setPesoAresta(id, peso, true);
                                    if(resp == 2 || resp == -1)
                                        System.out.println("Solicitacao repassada a outro servidor.");
                                    if(resp > 0)
                                        System.out.println("O peso da aresta " + id + " foi alterado com sucesso.");
                                    else
                                        System.out.println("Nao foi possivel alterar o peso da aresta " + id);
                                    break;

                                case 2: // alterar descricao
                                    System.out.print("Digite o id da aresta: "); id = sc.nextInt();
                                    System.out.print("Digite a nova descricao da aresta: "); sc.nextLine(); descricao = sc.nextLine();

                                    resp = client.setDescricaoAresta(id, descricao, true);
                                    if(resp == 2 || resp == -1)
                                        System.out.println("Solicitacao repassada a outro servidor.");
                                    if(resp > 0)
                                        System.out.println("A descricao da aresta " + id + " foi alterada com sucesso.");
                                    else
                                        System.out.println("Nao foi possivel alterar a descricao da aresta " + id);
                                    break;

                                case 0: // cancelar
                                    System.out.println("Operacao cancelada.");
                                    break;

                                default:
                                    System.out.println("Opcao invalida.");
                            }
                        } while(op2 < 0 || op2 > 2);
                        break;

                    // menor caminho entre 2 vertices
                    case 10:
                        System.out.print("Digite o id do vertice 1: "); va = sc.nextInt();
                        System.out.print("Digite o id do vertice 2: "); vb = sc.nextInt();

                        double ans = client.menorCaminho(va, vb);

                        System.out.println("Solicitacao realizada em conjunto com outros servidores.");
                        if(ans == -2)
                            System.out.println("Nao foi possivel encontrar o menor caminho entre o par de vertices especificado.");
                        else if(ans == -1)
                            System.out.println("Nao ha nenhum caminho entre o par de vertices especificado.");
                        else 
                            System.out.println("O menor caminho entre os vertices " + va + " e " + vb + " eh: " + ans);
                        break;

                    // end
                    case 0:
                        System.out.println("O programa sera finalizado.");
                        break;
                    
                    default:
                        System.out.println("Opcao invalida.");
                }
            } while (op != 0);
        }
        catch (TException x) {
            x.printStackTrace();
        }
    }
}
