package game_classes;

public class Rogue extends GameClass {
    private static final int MAX_HEALTH_ROUGE = 110;
    private static final int MAX_DAMAGE = 18;
    private static final int MIN_DAMAGE = 6;
    private static final int STATIC_DAMAGE = 20;

    public Rogue(String CharacterName) {
        super(CharacterName, GameClassType.ROGUE);
    }

    public int[] getDamageRange() {
        return new int[]{MIN_DAMAGE, MAX_DAMAGE};
    }

    public int specialAbility(GameClass opponent) {
        int healthBefore = opponent.getHealth();
        opponent.takeDamage(STATIC_DAMAGE, true);
        opponent.defending = false;
        return healthBefore - opponent.getHealth();
    }

    @Override
    public SpecialTypeName getSpecialType() {
        return SpecialTypeName.BACKSTAB;
    }

    public int getMaxHealthPoints() {
        return MAX_HEALTH_ROUGE;
    }
}
