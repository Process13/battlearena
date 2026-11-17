package game_classes;

public class Warrior extends GameClass {
    private static final int MAX_HEALTH_WARRIOR = 150;
    private static final int MAX_DAMAGE = 14;
    private static final int MIN_DAMAGE = 9;

    public Warrior(String CharacterName) {
        super(CharacterName, GameClassType.WARRIOR,  SpecialTypeName.DEFEND);
    }

    public int[] getDamageRange() {
        return new int[]{MIN_DAMAGE, MAX_DAMAGE};
    }

    public int specialAbility(GameClass opponent) {
        defending = true;
        return 0;
    }

    public int getMaxHealthPoints() {
        return MAX_HEALTH_WARRIOR;
    }
}
