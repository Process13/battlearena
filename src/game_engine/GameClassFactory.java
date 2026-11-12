package game_engine;

import game_classes.*;

import java.util.Random;

// Class for generating a character for the player and make a random character for the computer
public class GameClassFactory {


    // Generates a character for the player
    public GameClass createGameClass(GameClassType type, String name) {
        return switch (type) {
            case WARRIOR -> new Warrior(name);
            case MAGE -> new Mage(name);
            case ROGUE -> new Rogue(name);
        };
    }

    // Generates a character for the computer
    public GameClass createRandomGameClass(String name) {
        GameClassType[] types = GameClassType.values();
        GameClassType type = types[new Random().nextInt(types.length)];
        return createGameClass(type, name);
    }


}
