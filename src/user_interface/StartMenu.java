package user_interface;

import game_classes.GameClassType;

public class StartMenu {
    private final String line = "--------------------------";

    public void showWelcomeMessage() {
        System.out.println(line);
        System.out.println("       BATTLE ARENA       ");
        System.out.println(line);
    }
    public void showMainMenu() {
        System.out.println("1. Play");
        System.out.println("2. Quit");
    }
    public void showGoodbyeMessage() {
        System.out.println("Goodbye!");

    }
    public void showClasses() {
        System.out.println("1. " + GameClassType.WARRIOR.getClassName());
        System.out.println("2. " + GameClassType.MAGE.getClassName());
        System.out.println("3. " + GameClassType.ROGUE.getClassName());
    }

    // Flush()
    // 1. Start
        //  -1-
        // Flush()
        // Choose name:
        // Flush()
        // Classes shown
        // Choose Class:
        // Flush()
        // Show Name and Class (ex. Ulf the Mage)
        // 1. Start Battle
            //Start the battle
            //BattleInterface(name, class)
        // 2. Return to Menu
        //Flush()
    // 2. Classes
        // Show all classes in detail
    // 3. Tutorial
        // Give instructions on how to play
    // 4. Exit


}
