package game_classes;

import java.util.Random;

public class Rogue extends GameClass {

    public Rogue(String CharacterName, Random random) {
        super(CharacterName, GameClassType.ROGUE, 10, 18, random);
    }

    public void damageRange() {

    }
    public void specialType() {

    }
    public int healthPoints() {
        return 0;
    }

    public int maxHealthPoints() {return 110;}
}
