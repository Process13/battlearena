package Tests.game_classes;

import game_classes.GameClass;
import game_classes.Mage;
import game_classes.Rogue;
import game_classes.Warrior;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestGameClass {
    GameClass warrior, mage, rogue;

    @BeforeEach
    void setup() {
        warrior = new Warrior("Warrior");
        mage = new Mage("Mage");
        rogue = new Rogue("Rogue");
    }

    @Test
    void warriorShouldStartWithWarriorMaxHealth(){
        assertEquals(warrior.getMaxHealthPoints(), warrior.getHealth());
    }
    @Test
    void mageShouldStartWithMageMaxHealth(){
        assertEquals(mage.getMaxHealthPoints(), mage.getHealth());
    }
    @Test
    void rogueShouldStartWithRogueMaxHealth(){
        assertEquals(rogue.getMaxHealthPoints(), rogue.getHealth());
    }
}
