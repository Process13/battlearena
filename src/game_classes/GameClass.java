package game_classes;

public abstract class GameClass implements GameClassAttributes {
    private final String CharacterName;
    private final GameClassType type;
    private int health;
    protected boolean defending = false;
    private boolean alive = true;
    private boolean specialUsed = false;

    public GameClass(String CharacterName, GameClassType type) {
        this.CharacterName = CharacterName;
        this.type = type;
    }
    /*
    public int attack(GameClass target){

    }

     */
    public void takeDamage(int amount, boolean piercing){
        if (defending && !piercing){
            amount = amount/2;
            defending = false;
        }
        if (health <= amount){
            alive = false;
            health = 0;
            return;
            //Character is defeated
        }
        health = health - amount;

    }
    public void heal(int amount){
        if (health >= getMaxHealthPoints() - amount) {
            health = getMaxHealthPoints();
            return;
        }
        health = health + amount;
    }
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

    public boolean isSpecialUsed() {
        return specialUsed;
    }

    public void setSpecialUsed(boolean specialUsed) {
        this.specialUsed = specialUsed;
    }
}
