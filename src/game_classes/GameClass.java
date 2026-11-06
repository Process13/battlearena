package game_classes;

import java.util.Random;

public abstract class GameClass implements GameClassAttributes {
    private final String CharacterName;
    private final GameClassType type;
    private int health;

    public GameClass(String CharacterName, GameClassType type) {
        this.CharacterName = CharacterName;
        this.type = type;
    }
    /*
    public int attack(GameClass target){

    }
    public int takeDamage(int amount){

    }
    public int heal(int amount){

    }
    public boolean isAlive(){

    }

     */

    public String getCharacterName() {
        return CharacterName;
    }

    public GameClassType getType() {
        return type;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

}
