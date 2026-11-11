package game_classes;

import java.util.Random;

public class Warrior extends GameClass {
    private static final int MAX_HEALTH_WARRIOR = 150;
    private static final int MAX_DAMAGE = 14;
    private static final int MIN_DAMAGE = 9;

    public Warrior(String CharacterName) {
        super(CharacterName, GameClassType.WARRIOR);
    }

    public int[] getDamageRange() {
        return new int[]{MIN_DAMAGE, MAX_DAMAGE};
    }

    public void specialAbility() {
        defending = true;
    }

    public int getMaxHealthPoints() {
        return MAX_HEALTH_WARRIOR;
    }
}
