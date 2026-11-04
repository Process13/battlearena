package game_classes;

import java.util.Random;

public class Rogue extends GameClass {

    public Rogue(String CharacterName) {
        super(CharacterName, GameClassType.ROGUE, 10, 18);
    }

    public void damageRange() {

    }
    public void specialType() {

    }
    public int healthPoints() {
        return 0;
    }
    public int getMaxHealthPoints() {
        return GameClassType.ROGUE.getClassMaxHp();
    }
}
