package server_client;


import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {
    // Metod för att starta server via main i en separat tråd
    public void start() {
        Thread serverThread = new Thread(this::listen, "Match-results-server");
        serverThread.setDaemon(true);
        serverThread.start();
    }
    // Lyssnar efter klientanslutningar och startar varje anslutning i en separat ClientHandler-tråd.
    public void listen()  {
        ExecutorService pool = Executors.newFixedThreadPool(10);
        try(ServerSocket serverSocket = new ServerSocket( 8080)) {

            while (true) {
                Socket client = serverSocket.accept();
                pool.execute(new ClientHandler(client));
            }
        }catch (IOException e) {
            System.out.println("Servererror: " + e.getMessage());
        }finally {
            pool.shutdown();
        }
    }
}
