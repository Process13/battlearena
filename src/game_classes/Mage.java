package game_classes;

public class Mage extends GameClass {
    private static final int MAX_HEALTH_MAGE = 80;
    private static final int MAX_DAMAGE = 18;
    private static final int MIN_DAMAGE = 10;
    private static final int HEAL_AMOUNT = 25;

    public Mage(String CharacterName) {
        super(CharacterName, GameClassType.MAGE,  SpecialTypeName.HEAL);
    }

    public int[] getDamageRange() {
        return new int[]{MIN_DAMAGE, MAX_DAMAGE};
    }
    public int specialAbility(GameClass opponent) {
        int healthBefore = getHealth();
        this.heal(HEAL_AMOUNT);
        return getHealth() - healthBefore;
    }

    public int getMaxHealthPoints() {
        return MAX_HEALTH_MAGE;
    }
}
