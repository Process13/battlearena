package game_classes;

public class Rogue extends GameClass {
    private static final int MAX_HEALTH_ROUGE = 110;
    private static final int MAX_DAMAGE = 14;
    private static final int MIN_DAMAGE = 9;
    private static final int STATIC_DAMAGE = 20;

    public Rogue(String CharacterName) {
        super(CharacterName, GameClassType.ROGUE);
    }

    public int[] getDamageRange() {
        return new int[]{MIN_DAMAGE, MAX_DAMAGE};
    }
    public void specialAbility(GameClass opponent) {
        opponent.takeDamage(STATIC_DAMAGE, true);
    }

    public int getMaxHealthPoints() {
        return MAX_HEALTH_ROUGE;
    }
}
