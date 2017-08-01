package thrift;

import org.apache.thrift.server.TServer;
import org.apache.thrift.transport.TServerSocket;
import org.apache.thrift.transport.TServerTransport;
import org.apache.thrift.server.TThreadPoolServer;
import thrift.Grafo;
import thrift.sdEntrega1;

import java.util.Scanner;
 
public class Servidor {
    public static Grafo handler;
    public static sdEntrega1.Processor processor;

    public static void main(String [] args) {
        try {
            Scanner sc = new Scanner(System.in);
            System.out.println("Id do servidor: ");
            int id = sc.nextInt();

            System.out.println("Quantidade de servidores: ");
            int q = sc.nextInt();

            System.out.println("Porta do lider do cluster: ");
            int port = sc.nextInt();

            handler = new Grafo(id, q, "localhost", port);
            processor = new sdEntrega1.Processor(handler);
 

            TServerTransport serverTransport = new TServerSocket(9090+id);
            TServer server = new TThreadPoolServer(new TThreadPoolServer.Args(serverTransport).processor(processor));
 
            System.out.println("Iniciando...");
            server.serve(); 
        }
        catch (Exception x) {
            x.printStackTrace();
        }
    }
}
