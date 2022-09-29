package client;

import com.beust.jcommander.JCommander;
import server.cla.CommandLineArgument;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;

public class Main {
    private static final String ADDRESS = "127.0.0.1";
    private static final int PORT = 8000;

    public static void main(String[] args) {
        CommandLineArgument cla = new CommandLineArgument();

        JCommander.newBuilder().addObject(cla).build().parse(args);
        System.out.println("Client Started!");
        try(
        Socket socket = new Socket(InetAddress.getByName(ADDRESS), PORT);
        DataInputStream input = new DataInputStream(socket.getInputStream());
        DataOutputStream output = new DataOutputStream(socket.getOutputStream());
        ){
            String msg = cla.toJson();
            output.writeUTF(msg);
            System.out.println("Sent: " + msg);
            System.out.println("Received: " + input.readUTF());

        }catch (IOException e){
            e.printStackTrace();
        }

    }
}
