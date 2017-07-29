package sdEntrega1;
 
import sdEntrega1.*;
import org.apache.thrift.server.TServer;
import org.apache.thrift.transport.TServerSocket;
import org.apache.thrift.transport.TServerTransport;
import org.apache.thrift.server.TServer.Args;
import org.apache.thrift.server.TSimpleServer;
import org.apache.thrift.server.TThreadPoolServer;
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

            handler = new Grafo(id, q);
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
