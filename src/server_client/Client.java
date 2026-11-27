package server_client;

import game_classes.Winner;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class Client {
    // Skickar kommantod SAVE och matchresultatet till servern och väntar på ett OK.
    public void sendResult (String result) throws IOException {
        try(Socket socket = new Socket("localhost", 8080)) {
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream(), StandardCharsets.UTF_8));
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

            out.println(Command.SAVE.name());
            out.println(result);

            if(out.checkError()) {
                throw new IOException("Server could not send result");
            }

            String response = in.readLine();
            if(!Command.OK.name().equals(response)) {
                throw new IOException("Server could not save result");
            }
        }
    }
    // Begär matchhistoriken från servern och returnerar den som en lista.
    public List<String> receiveHistory() throws IOException {
        try(Socket socket = new Socket("localhost", 8080)) {
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream(), StandardCharsets.UTF_8));
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

            out.println(Command.HISTORY.name());

            String countLine = in.readLine();
            if(countLine == null){
                throw new IOException("Server could not receive history");
            }
            int count = Integer.parseInt(countLine);
            List<String> history = new ArrayList<>();
            for(int i = 0; i < count; i++){
                String result = in.readLine();
                if(result == null) {
                    throw new IOException("The result was incomplete");
                }
                history.add(result);
            }
            return history;
        }
    }
}
