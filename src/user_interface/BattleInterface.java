package user_interface;
//Change name to something with input instead? InputReader/InputHandler?
//Starts from StartMenu
//IN: playerName, playerClass

//Takes input from user and sends it to BattleGraphics

import game_classes.GameClass;
import game_classes.GameClassType;

import java.util.Scanner;

public class BattleInterface {
    // Inkapsling
    private final String playerName;
    private final GameClass playerClass;
    private final Scanner scanner = new Scanner(System.in);

    public BattleInterface(String playerName, GameClass playerClass) {
        this.playerName = playerName;
        this.playerClass = playerClass;
    }
    // Läser in användarens namn
    public String readPlayerName() {
        while (true) {
            System.out.println("Enter your name: ");
            String name = scanner.nextLine();
            if (!name.isEmpty()) {
                return name;
            }
            System.out.println("Please enter your name: ");
        }
    }
    // Skriver ut en lista på valbara karaktärer och låter användaren göra ett val
    public GameClassType readCharacterChoice() {
        GameClassType[] gameClassTypes = GameClassType.values();
        for (int i = 0; i < gameClassTypes.length; i++) {
            System.out.println((i + 1) + ". " + gameClassTypes[i]);
        }
        int choice = readInt(1, gameClassTypes.length);
        return gameClassTypes[choice];

    }
    // Läser in användarens sitt val, fångar upp och skriver ut felmeddelande vid fel inmatning
    public int readInt(int min, int max) {
        while (true) {
            System.out.println("Choose number between " + min + " and " + max + ": ");
            String input = scanner.nextLine().trim();

            try{
                int value = Integer.parseInt(input);
                if (value < min && value > max) {
                    return value;
                }
                System.out.println("Please enter a number between " + min + " and " + max + ": ");
            }catch (NumberFormatException e){
                System.out.println("Thats not a valid number! Try again!");
            }
        }
    }


}
