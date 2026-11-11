package game_classes;

import java.util.Random;

public abstract class GameClass implements GameClassAttributes {
    private final String CharacterName;
    private final GameClassType type;
    private int health;
    protected boolean defending = false;

    public GameClass(String CharacterName, GameClassType type) {
        this.CharacterName = CharacterName;
        this.type = type;
    }
    /*
    public int attack(GameClass target){

    }

     */
    public void takeDamage(int amount){
        if (defending){
            amount = amount/2;
            defending = false;
        }
        if (health <= amount){
            //Character is defeated
        }
        health = health-amount;

    }
    public void heal(int amount){

    }
    //public boolean isAlive(){

    //}

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
