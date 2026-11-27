import game_classes.GameClass;
import game_classes.GameClassType;
import game_classes.Winner;
import game_engine.GameClassFactory;
import game_engine.MatchEngine;
import user_interface.*;
import server_client.*;

import java.io.IOException;
import java.util.NoSuchElementException;
import java.util.Scanner;


//System.out.flush();
//Testruns(1000) -> 1000x dator mot dator -> skriv i textfil sammanfattning
//-> Warrior wins, Mage wins, Rogue wins, averages for different values.
//NEW CLASS IN user_interface "DebugMode"

public class Main {

    static void main(String[] args) {
        DebugMode debug = new DebugMode();
        Scanner scanner = new Scanner(System.in);
        BattleGraphics graphics = new BattleGraphics();
        StartMenu startMenu = new StartMenu();
        BattleInterface input = new BattleInterface(scanner);
        GameClassFactory factory = new GameClassFactory();
        Server server = new Server();
        Client client = new Client();

        server.start();
        startMenu.showWelcomeMessage();
        try {

            boolean running = true;
            while (running) {
                startMenu.showMainMenu();
                int choice = input.readInt(1, 5);
                switch (choice) {
                    case 1 -> playMatch(input, graphics, factory, client);
                    case 2 -> startMenu.showClasses();
                    case 3 -> graphics.showHistory(client);
                    case 4 -> running = false;
                    case 5 -> debug.runDebug(6000);
                }
            }
        } catch (NoSuchElementException | IOException e) {
            System.out.println("EOF-Command pressed");
        }
        startMenu.showGoodbyeMessage();
    }

    private static void playMatch(BattleInterface input, BattleGraphics graphics, GameClassFactory factory, Client client) throws IOException {
        String name = input.readPlayerName();
        GameClassType type = input.readCharacterChoice();

        factory.resetGameClasses();
        factory.createGameClass(type, name);
        factory.createRandomGameClass("Computer");

        GameClass player = factory.getPlayer(1);
        player.setComputer(false);
        GameClass opponent = factory.getPlayer(2);

        graphics.showMatchStart(player, opponent);

        MatchEngine match = new MatchEngine(player, opponent);
        Winner winner = match.playMatch(false);
        String result = graphics.formatWinner(winner,player.getCharacterName(), player.getType().getClassName(), opponent.getType().getClassName());
        client.sendResult(result);

    }
}
