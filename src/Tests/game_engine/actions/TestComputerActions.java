package Tests.game_engine.actions;

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
    /// Computer Warrior Attack Action

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
    /// Computer Mage Attack Action

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
    /// Computer Rogue Attack Action

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
    /// Computer Warrior Special Ability

    @Test
    void computerWarriorShouldBeAbleToHalveDamageFromNextIncomingAttack() {
        warrior.setHealth(80);
        computerAction.useSpecial(warrior, opponent);
        warrior.takeDamage(30, false);
        assertEquals(65, warrior.getHealth());
    }
    @Test
    void computerWarriorShouldOnlyBeAbleToHalveDamageOnOneAttack() {
        warrior.setHealth(100);
        warrior.takeDamage(20, false);
        assertEquals(80, warrior.getHealth());
        computerAction.useSpecial(warrior, opponent);
        warrior.takeDamage(20, false);
        assertEquals(70, warrior.getHealth());
        warrior.takeDamage(20, false);
        assertEquals(50, warrior.getHealth());
    }
    @Test
    void computerWarriorShouldNotBeAbleToBlockPiercingAttacks() {
        warrior.setHealth(80);
        computerAction.useSpecial(warrior, opponent);
        warrior.takeDamage(40, true);
        assertEquals(40, warrior.getHealth());
    }
    @Test
    void computerWarriorShouldRetainBlockAfterAPiercingAttack() {
        warrior.setHealth(80);
        computerAction.useSpecial(warrior, opponent);
        warrior.takeDamage(40, true);
        assertEquals(40, warrior.getHealth());
        warrior.takeDamage(40, false);
        assertEquals(20, warrior.getHealth());
    }

    /////////////////////////////////////////////////////////////
    /// Computer Mage Special Ability

    @Test
    void computerMageShouldBeAbleToHeal25(){
        mage.setHealth(20);
        computerAction.useSpecial(mage, opponent);
        assertEquals(45, mage.getHealth());
    }
    @Test
    void computerMageHealShouldNotExceedMaxHitPoints(){
        mage.setHealth(mage.getMaxHealthPoints()-5);
        computerAction.useSpecial(mage, opponent);
        assertEquals(mage.getMaxHealthPoints(), mage.getHealth());
    }

    /////////////////////////////////////////////////////////////
    /// Computer Rogue Special Ability

    @Test
    void computerRogueSpecialActionShouldDeal20Damage() {
        opponent.setHealth(80);
        computerAction.useSpecial(rogue, opponent);
        assertEquals(60, opponent.getHealth());
    }
    @Test
    void computerRogueSpecialActionShouldDealDamageThroughDefending() {
        opponent.setHealth(80);
        computerAction.useSpecial(opponent, rogue);
        computerAction.useSpecial(rogue, opponent);
        assertTrue(opponent.getDefending());
        assertEquals(60, opponent.getHealth());
    }


}
