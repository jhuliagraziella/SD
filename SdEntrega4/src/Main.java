import copycat.Server;

/**
 * Created by pedro on 31/07/17.
 */
public class Main {


    public static void main(String[] args) {
        //Iniciando 1 cluster
        new Server("localhost", 9060, "localhost", 9060, true).run();
        new Server("localhost", 9061, "localhost", 9060, false).run();
        new Server("localhost", 9062, "localhost", 9060, false).run();

        //Iniciando 2 cluster
        new Server("localhost", 9070, "localhost", 9070, true).run();
        new Server("localhost", 9071, "localhost", 9070, false).run();
        new Server("localhost", 9072, "localhost", 9070, false).run();

        //Iniciando 3 cluster
        new Server("localhost", 9080, "localhost", 9080, true).run();
        new Server("localhost", 9081, "localhost", 9080, false).run();
        new Server("localhost", 9082, "localhost", 9080, false).run();


    }
}
