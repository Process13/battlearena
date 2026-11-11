package game_classes;

import java.util.Random;

public class Rogue extends GameClass {
    private static final int MAX_HEALTH_ROUGE = 110;
    private static final int MAX_DAMAGE = 14;
    private static final int MIN_DAMAGE = 9;

    public Rogue(String CharacterName) {
        super(CharacterName, GameClassType.ROGUE);
    }

    public int[] getDamageRange() {
        return new int[]{MIN_DAMAGE, MAX_DAMAGE};
    }
    public void specialAbility() {

    }

    public int getMaxHealthPoints() {
        return MAX_HEALTH_ROUGE;
    }
}
