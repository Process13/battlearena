package game_classes;

import java.util.Random;

public abstract class GameClass implements GameClassAttributes {
    private final String CharacterName;
    private final GameClassType type;
    private int health;
    private final int minDamage;
    private final int maxDamage;

    public GameClass(String CharacterName, GameClassType type, int minDamage, int maxDamage) {
        this.CharacterName = CharacterName;
        this.type = type;
        this.minDamage = minDamage;
        this.maxDamage = maxDamage;

    }
    public int attack(GameClass target){

    }
    public int takeDamage(int amount){

    }
    public int heal(int amount){

    }
    public boolean isAlive(){

    }

    public String getCharacterName() {
        return CharacterName;
    }

    public GameClassType getType() {
        return type;
    }


    //setHealth() is needed
    public int getHealth() {
        return health;
    }

}
