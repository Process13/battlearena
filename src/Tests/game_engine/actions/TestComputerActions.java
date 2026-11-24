package Tests.game_engine.actions;

import game_classes.*;
import game_engine.Config;
import game_engine.actions.ComputerAction;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TestComputerActions {
    private ComputerAction computerAction;
    private GameClass warrior;
    private GameClass mage;
    private GameClass rogue;
    private GameClass opponent;
    private int[] mageDamageRange, warriorDamageRange, rogueDamageRange;

    @BeforeEach
    void setup() {
        computerAction = new ComputerAction();
        warrior = new Warrior(GameClassType.WARRIOR.getClassName());
        mage = new Mage(GameClassType.MAGE.getClassName());
        rogue = new Rogue(GameClassType.ROGUE.getClassName());
        opponent = new Warrior("Opponent");
    }

    /// //////////////////////////////////////////////////////////
    /// Computer Warrior Attack Action

    @Test
    void computerWarriorShouldBeAbleToAttack() {
        opponent.setHealth(60);
        computerAction.useAttack(warrior, opponent);
        assertTrue(opponent.getHealth() < 60);
    }

//    @Test
//    void computerWarriorShouldReceiveCorrectDamageRangeValues() {
//        int[] damageRange = warrior.getDamageRange();
//        assertEquals(9, damageRange[0]);
//        assertEquals(14, damageRange[1]);
//    }

    @Test
    void computerWarriorDealtDamageWithinTheCorrectRange() {
        int lowestDamage = opponent.getMaxHealthPoints(), highestDamage = 0;
        warriorDamageRange = warrior.getDamageRange();
        for (int i = 0; i < 10000; i++) {
            opponent.setHealth(opponent.getMaxHealthPoints());
            computerAction.useAttack(warrior, opponent);
            if (opponent.getHealth() < lowestDamage)
                lowestDamage = opponent.getHealth();
            if (opponent.getHealth() > highestDamage)
                highestDamage = opponent.getHealth();
            assertTrue(opponent.getHealth() >= opponent.getMaxHealthPoints() - warriorDamageRange[1]
                    && opponent.getHealth() <= opponent.getMaxHealthPoints() - warriorDamageRange[0]);
        }
        System.out.println(mage.getType().getClassName() +
                "\nMost damage taken from warrior: " + (opponent.getMaxHealthPoints() - lowestDamage) +
                "\nLeast damage taken from warrior: " + (opponent.getMaxHealthPoints() - highestDamage) + '\n');
    }

    /// //////////////////////////////////////////////////////////
    /// Computer Mage Attack Action


    @Test
    void computerMageShouldBeAbleToAttack() {
        opponent.setHealth(60);
        computerAction.useAttack(mage, opponent);
        assertTrue(opponent.getHealth() < 60);
    }
//    @Test
//    void computerMageShouldReceiveCorrectDamageRangeValues() {
//        int[] damageRange = mage.getDamageRange();
//        assertEquals(16, damageRange[0]);
//        assertEquals(18, damageRange[1]);
//    }

    @Test
    void computerMageDealtDamageWithinTheCorrectRange() {
        int lowestDamage = opponent.getMaxHealthPoints(), highestDamage = 0;
        mageDamageRange = mage.getDamageRange();
        for (int i = 0; i < 10000; i++) {
            opponent.setHealth(opponent.getMaxHealthPoints());
            computerAction.useAttack(mage, opponent);
            if (opponent.getHealth() < lowestDamage)
                lowestDamage = opponent.getHealth();
            if (opponent.getHealth() > highestDamage)
                highestDamage = opponent.getHealth();
            assertTrue(opponent.getHealth() >= opponent.getMaxHealthPoints() - mageDamageRange[1]
                    && opponent.getHealth() <= opponent.getMaxHealthPoints() - mageDamageRange[0]);
        }
        System.out.println(mage.getType().getClassName() +
                "\nMost damage taken from mage: " + (opponent.getMaxHealthPoints() - lowestDamage) +
                "\nLeast damage taken from mage: " + (opponent.getMaxHealthPoints() - highestDamage) + '\n');
    }

    /// //////////////////////////////////////////////////////////
    /// Computer Rogue Attack Action

    @Test
    void computerRogueShouldBeAbleToAttack() {
        opponent.setHealth(60);
        computerAction.useAttack(rogue, opponent);
        assertTrue(opponent.getHealth() < 60);
    }

//    @Test
//    void computerRogueShouldReceiveCorrectDamageRangeValues() {
//        int[] damageRange = rogue.getDamageRange();
//        assertEquals(6, damageRange[0]);
//        assertEquals(18, damageRange[1]);
//    }

    @Test
    void computerRogueDealtDamageWithinTheCorrectRange() {
        int lowestDamage = opponent.getMaxHealthPoints(), highestDamage = 0;
        rogueDamageRange = rogue.getDamageRange();
        for (int i = 0; i < 10000; i++) {
            opponent.setHealth(opponent.getMaxHealthPoints());
            computerAction.useAttack(rogue, opponent);
            if (opponent.getHealth() < lowestDamage)
                lowestDamage = opponent.getHealth();
            if (opponent.getHealth() > highestDamage)
                highestDamage = opponent.getHealth();
            assertTrue(opponent.getHealth() >= opponent.getMaxHealthPoints() - rogueDamageRange[1]
                    && opponent.getHealth() <= opponent.getMaxHealthPoints() - rogueDamageRange[0]);
        }
        System.out.println(mage.getType().getClassName() +
                "\nMost damage taken from rouge: " + (opponent.getMaxHealthPoints() - lowestDamage) +
                "\nLeast damage taken from rouge: " + (opponent.getMaxHealthPoints() - highestDamage) + '\n');
    }

    /// //////////////////////////////////////////////////////////
    /// Computer Warrior Special Ability

    @Test
    void computerWarriorShouldBeAbleToReduceDamageFromNextIncomingAttack() {
        computerAction.useSpecial(warrior, opponent);
        warrior.takeDamage(30, false);
        assertEquals(warrior.getMaxHealthPoints()-(30/Config.DEFENDING_DAMAGE_REDUCTION.getNumber()), warrior.getHealth());
    }

    @Test
    void computerWarriorShouldNotBeAbleToBlockPiercingAttacks() {
        computerAction.useSpecial(warrior, opponent);
        warrior.takeDamage(30, true);
        assertEquals(warrior.getMaxHealthPoints()-30, warrior.getHealth());
    }

    @Test
    void computerWarriorShouldRetainDefendingAfterAPiercingAttack() {
        computerAction.useSpecial(warrior, opponent);
        warrior.takeDamage(40, true);
        assertEquals(warrior.getMaxHealthPoints()-40, warrior.getHealth());
        warrior.setHealth(warrior.getMaxHealthPoints());
        warrior.takeDamage(40, false);
        assertEquals(warrior.getMaxHealthPoints()-(40/Config.DEFENDING_DAMAGE_REDUCTION.getNumber()), warrior.getHealth());
    }

    /// //////////////////////////////////////////////////////////
    /// Computer Mage Special Ability

    @Test
    void computerMageShouldBeAbleToHeal() {
        mage.setHealth(20);
        computerAction.useSpecial(mage, opponent);
        assertEquals(20 + Config.HEAL_AMOUNT.getNumber(), mage.getHealth());
    }

    @Test
    void computerMageHealShouldNotExceedMaxHitPoints() {
        mage.setHealth(mage.getMaxHealthPoints() - 5);
        computerAction.useSpecial(mage, opponent);
        assertEquals(mage.getMaxHealthPoints(), mage.getHealth());
    }

    /// //////////////////////////////////////////////////////////
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
        assertTrue(opponent.getDefending());
        computerAction.useSpecial(rogue, opponent);
        assertEquals(60, opponent.getHealth());
    }
}
