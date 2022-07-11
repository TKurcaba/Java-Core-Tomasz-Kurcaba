package server;

import com.google.gson.Gson;
import command.*;
import server.json.Request;
import server.json.Response;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ClientHandler {


    final DataInputStream dataInputStream;
    final DataOutputStream dataOutputStream;
    final Socket socket;
    private final ServerSocket server;
    final private Database db;

    public ClientHandler(Socket socket, DataInputStream dataInputStream, DataOutputStream dataOutputStream, ServerSocket server, Database db) {
        this.socket = socket;
        this.dataInputStream = dataInputStream;
        this.dataOutputStream = dataOutputStream;
        this.server = server;
        this.db = db;
    }

    public void run() {
        Controller controller = new Controller();
        try {
            while (true) {
                Request request = new Gson().fromJson(dataInputStream.readUTF(), Request.class);
                Response response = new Response();

                Action action = new Action(db);
                try {

                    switch (request.getType()) {
                        case "get": {
                            GetCommand getCommand = new GetCommand(action, request.getKey());
                            controller.setCommand(getCommand);
                            controller.executeCommand();
                            response.setMsg(Response.STATUS_OK);

                            response.setValue(getCommand.GetResult().toString());
                            break;
                        }
                        case "set": {
                            SetCommand setCommand = new SetCommand(action, request.getKey(), request.getValue());
                            controller.setCommand(setCommand);
                            controller.executeCommand();
                            response.setMsg(Response.STATUS_OK);
                            break;
                        }
                        case "delete": {
                            DeleteCommand deleteCommand = new DeleteCommand(action, request.getKey());
                            controller.setCommand(deleteCommand);
                            controller.executeCommand();
                            response.setMsg(Response.STATUS_OK);
                            break;
                        }
                        case "exit": {
                            response.setMsg(Response.STATUS_OK);
                            dataOutputStream.writeUTF(response.toJSON());
                            server.close();
                            return;
                        }
                    }

                } catch (Exception e) {
                    response.setMsg(Response.STATUS_ERROR);
                    response.setReason(e.getMessage());
                } finally {
                    dataOutputStream.writeUTF(response.toJSON());
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                dataInputStream.close();
                dataOutputStream.close();
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

    }
}
