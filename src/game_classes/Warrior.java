package game_classes;

import java.util.Random;

public class Warrior extends GameClass {

    public Warrior(String CharacterName, Random random) {
        super(CharacterName, GameClassType.WARRIOR,9, 14, random);
    }

    public void damageRange() {

    }
    public void specialType() {

    }
    public int healthPoints() {
        return 0;
    }

    public int maxHealthPoints() {return 150;}
}
