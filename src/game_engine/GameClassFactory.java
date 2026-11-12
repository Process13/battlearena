package game_engine;

import game_classes.*;

import java.util.Random;

// Class for generating a character for the player and make a random character for the computer
public class GameClassFactory {
    GameClass player1, player2;

    // Generates a character for the player
    public void createGameClass(GameClassType type, String name) {
        if (player1 != null) {
            switcher(type, player1, name);
            }
        }
        if (player2 == null) {
            return switch (type) {
                case WARRIOR -> player2 = new Warrior(name);
                case MAGE -> player2 = new Mage(name);
                case ROGUE -> player2 = new Rogue(name);
            };
        }
        return;
    }

    // Generates a character for the computer
    public GameClass createRandomGameClass(String name) {
        GameClassType[] types = GameClassType.values();
        GameClassType type = types[new Random().nextInt(types.length)];
        return createGameClass(type, name);
    }

    private void switcher(GameClassType type, GameClass player, String name){
        switch (type) {
            case WARRIOR -> player = new Warrior(name);
            case MAGE -> player = new Mage(name);
            case ROGUE -> player = new Rogue(name);
        };
    }

    public GameClass getPlayer1() {
        return player1;
    }

    public GameClass getPlayer2() {
        return player2;
    }
}
