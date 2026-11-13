package game_engine;

import game_classes.*;

import java.util.*;

// Class for generating a character for the player and make a random character for the computer
public class GameClassFactory {
    private final List<GameClass> createClass = new ArrayList<>(Arrays.asList(null,null));
    private int player = 0;

    //Creates a character
    public void createGameClass(GameClassType type, String name) {
        if (player == 2) {
            return;
        }
        switch (type) {
            case WARRIOR -> createClass.set(player, new Warrior(name));
            case MAGE -> createClass.set(player, new Mage(name));
            case ROGUE -> createClass.set(player, new Rogue(name));
        }
        player++;
    }

    //Selects character type for the computer
    public void createRandomGameClass(String name) {
        GameClassType[] types = GameClassType.values();
        GameClassType type = types[new Random().nextInt(types.length)];
        createGameClass(type, name);
    }

    public GameClass getPlayer(int player) {
        return createClass.get(player - 1);
    }

    public void resetGameClasses(){
        player = 0;
    }
}
