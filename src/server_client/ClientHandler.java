package server_client;

import java.io.*;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.List;

public class ClientHandler implements Runnable {
    private static final Path RESULTS_FILE = Path.of("Matchresults.txt");
    // Trådsäkrar textfilen så att handler inte kan läsa och skriva ur textfilen samtidigt.
    private static final Object FILE_LOCK = new Object();

    private final Socket socket;

    public ClientHandler(Socket socket) {
        this.socket = socket;
    }
    // Läser klientens kommando och skickar vidare arbetet till rätt metod
    @Override
    public void run() {
        try(Socket client = socket) {
            BufferedReader input = new BufferedReader(
                    new InputStreamReader(client.getInputStream(), StandardCharsets.UTF_8));
            PrintWriter output = new PrintWriter(
                    new OutputStreamWriter(client.getOutputStream(), StandardCharsets.UTF_8), true);
            Command command = Command.parse(input.readLine());

            switch (command) {
                case SAVE -> saveResult(input, output);
                case HISTORY -> sendHistory(output);
                default -> output.println(Command.ERROR.name());
            }
        } catch (IOException e) {
            System.out.println("could not handle client: " + e.getMessage());
        }
    }
    // Läser resultatet från klienten och lägger in det i textfilen.
    private void saveResult(BufferedReader input, PrintWriter output) throws IOException {
        String result = input.readLine();

        if (result == null || result.isBlank()) {
            output.println(Command.ERROR.name());
            return;
        }

        synchronized (FILE_LOCK) {
            Files.writeString(RESULTS_FILE, result + System.lineSeparator(), StandardCharsets.UTF_8, StandardOpenOption.APPEND, StandardOpenOption.CREATE);
        }
        output.println(Command.OK.name());
    }
    // Läser alla sparade resultat och skickar dom till klienten
    private void sendHistory(PrintWriter output) throws IOException {
        List<String> history;

        synchronized (FILE_LOCK) {
            if(Files.exists(RESULTS_FILE)) {
                history = Files.readAllLines(RESULTS_FILE,StandardCharsets.UTF_8);
            }else {
                history = List.of();
            }
            output.println(history.size());
            for(String result : history) {
                output.println(result);
            }
        }
    }
}
