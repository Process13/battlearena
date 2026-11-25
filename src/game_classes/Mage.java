package game_classes;

import game_engine.Config;

public class Mage extends GameClass {
    private static final int MAX_HEALTH_MAGE = 70;
    private static final int MAX_DAMAGE = 18;
    private static final int MIN_DAMAGE = 16;

    public Mage(String CharacterName) {
        super(CharacterName, GameClassType.MAGE);
    }

    public int[] getDamageRange() {
        return new int[]{MIN_DAMAGE, MAX_DAMAGE};
    }
    public int specialAbility(GameClass opponent) {
        int healthBefore = getHealth();
        this.heal(Config.HEAL_AMOUNT.getNumber());
        return getHealth() - healthBefore;
    }
    @Override
    public SpecialTypeName getSpecialType() {
        return SpecialTypeName.HEAL;
    }

    public int getMaxHealthPoints() {
        return MAX_HEALTH_MAGE;
    }
}
