package game_classes;

import java.util.Random;

public class Mage extends GameClass {

    public Mage(String CharacterName) {
        super(CharacterName, GameClassType.MAGE,10, 18);
    }

    public void damageRange() {

    }
    public void specialType() {

    }
    public int healthPoints() {
        return 0;
    }

    public int getMaxHealthPoints() {
        return GameClassType.MAGE.getClassMaxHp();
    }
}
