package game_classes;

import java.util.Random;

public class Warrior extends GameClass {

    public Warrior(String CharacterName) {
        super(CharacterName, GameClassType.WARRIOR,9, 14);
    }

    public void damageRange() {

    }
    //rename to specialAbility
    public void specialType() {

    }
    public int healthPoints() {
        return 0;
    }

    public int getMaxHealthPoints() {
        return GameClassType.WARRIOR.getClassMaxHp();
    }
}
