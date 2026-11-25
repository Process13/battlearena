package game_classes;

import game_engine.Config;

public abstract class GameClass implements GameClassAttributes {
    private final String CharacterName;
    private final GameClassType type;
    private int health;
    protected boolean defending = false;
    private boolean alive = true;
    private boolean specialUsed = false;
    private boolean isComputer = true;

    public GameClass(String CharacterName, GameClassType type) {
        this.CharacterName = CharacterName;
        this.type = type;
        this.setHealth(getMaxHealthPoints());

    }
    /*
    public int attack(GameClass target){

    }

     */
    public int takeDamage(int amount, boolean piercing){
        if (defending && !piercing){
            amount = amount/Config.DEFENDING_DAMAGE_REDUCTION.getNumber();
            defending = false;
        }
        if (health <= amount){
            alive = false;
            health = 0;
            return amount - health;
            //Character is defeated
        }
        health = health - amount;
        return  amount;
    }
    public void heal(int amount){
        if (health >= getMaxHealthPoints() - amount) {
            health = getMaxHealthPoints();
            return;
        }
        health = health + amount;
    }
    public abstract SpecialTypeName getSpecialType();

    public boolean getAlive(){
        return alive;
    }

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

    public boolean getDefending() {
        return defending;
    }

    public void setDefending(boolean defending) {
        this.defending = defending;
    }

    public boolean isSpecialUsed() {
        return specialUsed;
    }

    public void setSpecialUsed(boolean specialUsed) {
        this.specialUsed = specialUsed;
    }

    public void setComputer(boolean computer) {
        isComputer = computer;
    }

    public boolean isComputer() {
        return isComputer;
    }
}

