package game_classes;

import java.util.Random;

public abstract class GameClass implements GameClassAttributes {
    private final String CharacterName;
    private final GameClassType type;
    private final int maxHealth;
    private int health;
    private final int minDamage;
    private final int maxDamage;
    private final Random random;

    public GameClass(String CharacterName, GameClassType type, int maxHealth, int minDamage, int maxDamage, Random random) {
        this.CharacterName = CharacterName;
        this.type = type;
        this.maxHealth = maxHealth;
        this.minDamage = minDamage;
        this.maxDamage = maxDamage;
        this.random = random;

    }
    public int attack(){

    }
    public int takeDamage(){

    }
    public int heal(){

    }
    public boolean isAlive(){

    }

    public String getCharacterName() {
        return CharacterName;
    }

    public GameClassType getType() {
        return type;
    }

    public int getMaxHealth() {
        return maxHealth;
    }

    public int getHealth() {
        return health;
    }
}
