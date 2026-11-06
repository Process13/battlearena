package game_classes;

import java.util.Random;

public class Mage extends GameClass {
    private static final int MAX_HEALTH_MAGE = 80;
    private static final int MAX_DAMAGE = 18;
    private static final int MIN_DAMAGE = 10;

    public Mage(String CharacterName) {
        super(CharacterName, GameClassType.MAGE);
    }

    public int[] getDamageRange() {
        return new int[]{MIN_DAMAGE, MAX_DAMAGE};
    }
    public void specialAbility() {

    }

    public int getMaxHealthPoints() {
        return MAX_HEALTH_MAGE;
    }
}
