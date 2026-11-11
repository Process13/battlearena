package game_engine.actions;

import game_classes.*;
import game_engine.actions.ComputerAction;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestComputerActions {
    private ComputerAction computerAction;
    private GameClass warrior;
    private GameClass mage;
    private GameClass rogue;
    private GameClass opponent;

    @BeforeEach
    void setup() {
        computerAction = new ComputerAction();
        warrior = new Warrior(GameClassType.WARRIOR.getClassName());
        mage = new Mage(GameClassType.MAGE.getClassName());
        rogue = new Rogue(GameClassType.ROGUE.getClassName());
        opponent = new Warrior("Opponent");
    }

    /////////////////////////////////////////////////////////////

    @Test
    void computerWarriorShouldBeAbleToAttack() {
        opponent.setHealth(60);
        computerAction.useAttack(warrior, opponent);
        assertTrue(opponent.getHealth() < 60);
    }
    @Test
    void computerWarriorShouldReceiveCorrectDamageRangeValues() {
        int[] damageRange = warrior.getDamageRange();
        assertEquals(9, damageRange[0]);
        assertEquals(14, damageRange[1]);
    }
    @Test
    void computerWarriorDealtDamageWithinTheCorrectRange() {
        int lowest = 60, highest = 0;
        for (int i = 0; i < 10000; i++){
            opponent.setHealth(60);
            computerAction.useAttack(warrior, opponent);
            if (opponent.getHealth()<lowest)
                lowest = opponent.getHealth();
            if (opponent.getHealth()>highest)
                highest = opponent.getHealth();
            assertTrue(opponent.getHealth() >= 46 && opponent.getHealth() <= 51);
        }
        System.out.println(warrior.getType().getClassName() +
                "\nMost damage taken: " + (60 - lowest) +
                "\nLeast damage taken: " + (60 - highest) + '\n');
    }

    /////////////////////////////////////////////////////////////

    @Test
    void computerMageShouldBeAbleToAttack() {
        opponent.setHealth(60);
        computerAction.useAttack(mage, opponent);
        assertTrue(opponent.getHealth() < 60);
    }
    @Test
    void computerMageShouldReceiveCorrectDamageRangeValues() {
        int[] damageRange = mage.getDamageRange();
        assertEquals(10, damageRange[0]);
        assertEquals(18, damageRange[1]);
    }
    @Test
    void computerMageDealtDamageWithinTheCorrectRange() {
        int lowest = 60, highest = 0;
        for (int i = 0; i < 10000; i++){
            opponent.setHealth(60);
            computerAction.useAttack(mage, opponent);
            if (opponent.getHealth()<lowest)
                lowest = opponent.getHealth();
            if (opponent.getHealth()>highest)
                highest = opponent.getHealth();
            assertTrue(opponent.getHealth() >= 42 && opponent.getHealth() <= 50);
        }
        System.out.println(mage.getType().getClassName() +
                "\nMost damage taken: " + (60 - lowest) +
                "\nLeast damage taken: " + (60 - highest) + '\n');
    }

    /////////////////////////////////////////////////////////////

    @Test
    void computerRogueShouldBeAbleToAttack() {
        opponent.setHealth(60);
        computerAction.useAttack(rogue, opponent);
        assertTrue(opponent.getHealth() < 60);
    }
    @Test
    void computerRogueShouldReceiveCorrectDamageRangeValues() {
        int[] damageRange = rogue.getDamageRange();
        assertEquals(9, damageRange[0]);
        assertEquals(14, damageRange[1]);
    }
    @Test
    void computerRogueDealtDamageWithinTheCorrectRange() {
        int lowest = 60, highest = 0;
        for (int i = 0; i < 10000; i++){
            opponent.setHealth(60);
            computerAction.useAttack(rogue, opponent);
            if (opponent.getHealth()<lowest)
                lowest = opponent.getHealth();
            if (opponent.getHealth()>highest)
                highest = opponent.getHealth();
            assertTrue(opponent.getHealth() >= 46 && opponent.getHealth() <= 51);
        }
        System.out.println(rogue.getType().getClassName() +
                "\nMost damage taken: " + (60 - lowest) +
                "\nLeast damage taken: " + (60 - highest) + '\n');
    }

    /////////////////////////////////////////////////////////////

    @Test
    void computerWarriorShouldBeAbleToHalveDamageFromNextIncomingAttack() {
        warrior.setHealth(80);
        computerAction.useSpecial(warrior);
        warrior.takeDamage(30);
        assertEquals(65, warrior.getHealth());
    }
    /*
    @Test
    void computerMageShouldBeAbleToHeal(){
        mage.setHealth(20);
        computerAction.useSpecial(mage);
        assertTrue(mage.getHealth() > 20);
    }
    @Test
    void computerRougeSpecialActionShouldDeal20Damage() {
        opponent.setHealth(80);
        computerAction.useSpecial(rogue);
        assertEquals(60, opponent.getHealth());
    }

     */


}
