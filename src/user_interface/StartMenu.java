package user_interface;

import game_classes.GameClassType;
import game_classes.SpecialTypeName;
import game_classes.Winner;

public class StartMenu {

    public void showWelcomeMessage() {
        printLine();
        System.out.println("            BATTLE ARENA            ");
        printLine();
    }
    public void showMainMenu() {
        System.out.println("1. Play");
        System.out.println("2. Show classes");
        System.out.println("3. Quit");
    }
    public void showGoodbyeMessage() {
        System.out.println("Goodbye!");

    }
    public void showClasses() {
        printLine();
        System.out.println("1. [" + GameClassType.WARRIOR.getClassName() + "] Special ability: " + SpecialTypeName.DEFEND.getName());
        System.out.println("2. [" + GameClassType.MAGE.getClassName() + "] Special ability: " + SpecialTypeName.HEAL.getName());
        System.out.println("3. [" + GameClassType.ROGUE.getClassName() +  "] Special ability: " + SpecialTypeName.BACKSTAB.getName());
        printLine();
    }
    public void printLine() {
        System.out.println("-------------------------------------");
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
