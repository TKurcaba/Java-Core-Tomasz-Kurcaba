package server;


import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {

    private static final String ADDRESS = "127.0.0.1";
    private static final int PORT = 8000;
    private final static int POOL_SIZE = 4;


    public static void main(String[] args) throws IOException {
        System.out.println("Server started!");

        ServerSocket server = new ServerSocket(PORT, 50, InetAddress.getByName(ADDRESS));
        Database db = new Database();
        ExecutorService executor = Executors.newFixedThreadPool(POOL_SIZE);
        while (!server.isClosed()) {

                try {
                    Socket socket = server.accept();
                    DataInputStream input = new DataInputStream(socket.getInputStream());
                    DataOutputStream output = new DataOutputStream(socket.getOutputStream());
                    ClientHandler clientHandler = new ClientHandler(socket, input, output, server, db);
                    executor.submit(clientHandler::run);
                }catch (IOException e ){
                    e.printStackTrace();
                    try {
                        server.close();
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                }



        }
        executor.shutdown();
    }
}
