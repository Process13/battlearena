import game_classes.GameClass;
import game_classes.GameClassType;
import game_classes.Winner;
import game_engine.GameClassFactory;
import game_engine.MatchEngine;
import user_interface.*;
import java.util.NoSuchElementException;
import java.util.Scanner;


//System.out.flush();
//Testruns(1000) -> 1000x dator mot dator -> skriv i textfil sammanfattning
//-> Warrior wins, Mage wins, Rogue wins, averages for different values.
//NEW CLASS IN user_interface "DebugMode"

public class Main {

    static void main(String[] args) {



       /*DebugMode debug = new DebugMode();
       debug.runDebug(6000);*/

        Scanner scanner = new Scanner(System.in);

        BattleGraphics graphics = new BattleGraphics();
        StartMenu startMenu = new StartMenu();
        BattleInterface input = new BattleInterface(scanner);
        GameClassFactory factory = new GameClassFactory();

        startMenu.showWelcomeMessage();
        try {
            boolean running = true;
            while (running) {
                startMenu.showMainMenu();
                int choice = input.readInt(1,3);
                switch (choice) {
                    case 1 -> playMatch(input, graphics, factory);
                    case 2 -> startMenu.showClasses();
                    case 3 -> running = false;
                }
            }
        }catch (NoSuchElementException e) {
            System.out.println("EOF-Command pressed");
        }
        startMenu.showGoodbyeMessage();
    }

    private static void playMatch(BattleInterface input, BattleGraphics graphics, GameClassFactory factory) {
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
    }
}
