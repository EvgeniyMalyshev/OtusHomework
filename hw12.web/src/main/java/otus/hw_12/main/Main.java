package otus.hw_12.main;


import org.eclipse.jetty.server.Server;
import otus.hw_12.ServerUtil;

public class Main {

    public static void main(String[] args) throws Exception {

        Server server = ServerUtil.createServer();
        server.start();
        server.join();
    }
}
