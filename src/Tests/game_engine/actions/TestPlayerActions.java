package Tests.game_engine.actions;

import game_classes.*;
import game_engine.Config;
import game_engine.actions.PlayerAction;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TestPlayerActions {
    private PlayerAction playerAction;
    private GameClass warrior;
    private GameClass mage;
    private GameClass rogue;
    private GameClass opponent;
    private int[] mageDamageRange, warriorDamageRange, rogueDamageRange;

    @BeforeEach
    void setup() {
        playerAction = new PlayerAction(null, null);
        warrior = new Warrior(GameClassType.WARRIOR.getClassName());
        mage = new Mage(GameClassType.MAGE.getClassName());
        rogue = new Rogue(GameClassType.ROGUE.getClassName());
        opponent = new Warrior("Opponent");
    }

    /////////////////////////////////////////////////////////////
    /// Player Warrior Attack Action

    @Test
    void playerWarriorShouldBeAbleToAttack() {
        opponent.setHealth(60);
        playerAction.useAttack(warrior, opponent);
        assertTrue(opponent.getHealth() < 60);
    }
//    @Test
//    void playerWarriorShouldReceiveCorrectDamageRangeValues() {
//        int[] damageRange = warrior.getDamageRange();
//        assertEquals(9, damageRange[0]);
//        assertEquals(14, damageRange[1]);
//    }
    @Test
    void playerWarriorDealtDamageWithinTheCorrectRange() {
        int lowestDamage = opponent.getMaxHealthPoints(), highestDamage = 0;
        warriorDamageRange = warrior.getDamageRange();
        for (int i = 0; i < 10000; i++) {
            opponent.setHealth(opponent.getMaxHealthPoints());
            playerAction.useAttack(warrior, opponent);
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

    /////////////////////////////////////////////////////////////
    /// Player Mage Attack Action

    @Test
    void playerMageShouldBeAbleToAttack() {
        opponent.setHealth(60);
        playerAction.useAttack(mage, opponent);
        assertTrue(opponent.getHealth() < 60);
    }
//    @Test
//    void playerMageShouldReceiveCorrectDamageRangeValues() {
//        int[] damageRange = mage.getDamageRange();
//        assertEquals(16, damageRange[0]);
//        assertEquals(18, damageRange[1]);
//    }
    @Test
    void playerMageDealtDamageWithinTheCorrectRange() {
        int lowestDamage = opponent.getMaxHealthPoints(), highestDamage = 0;
        mageDamageRange = mage.getDamageRange();
        for (int i = 0; i < 10000; i++) {
            opponent.setHealth(opponent.getMaxHealthPoints());
            playerAction.useAttack(mage, opponent);
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

    /////////////////////////////////////////////////////////////
    /// Player Rogue Attack Action

    @Test
    void playerRogueShouldBeAbleToAttack() {
        opponent.setHealth(60);
        playerAction.useAttack(rogue, opponent);
        assertTrue(opponent.getHealth() < 60);
    }
//    @Test
//    void playerRogueShouldReceiveCorrectDamageRangeValues() {
//        int[] damageRange = rogue.getDamageRange();
//        assertEquals(6, damageRange[0]);
//        assertEquals(18, damageRange[1]);
//    }
    @Test
    void playerRogueDealtDamageWithinTheCorrectRange() {
        int lowestDamage = opponent.getMaxHealthPoints(), highestDamage = 0;
        rogueDamageRange = rogue.getDamageRange();
        for (int i = 0; i < 10000; i++) {
            opponent.setHealth(opponent.getMaxHealthPoints());
            playerAction.useAttack(rogue, opponent);
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

    /////////////////////////////////////////////////////////////
    /// Player Warrior Special Action

    @Test
    void playerWarriorShouldBeAbleToReduceDamageFromNextIncomingAttack() {
        playerAction.useSpecial(warrior, opponent);
        warrior.takeDamage(30, false);
        assertEquals(warrior.getMaxHealthPoints()-(30/Config.DEFENDING_DAMAGE_REDUCTION.getNumber()), warrior.getHealth());
    }
    @Test
    void playerWarriorShouldNotBeAbleToBlockPiercingAttacks() {
        playerAction.useSpecial(warrior, opponent);
        warrior.takeDamage(30, true);
        assertEquals(warrior.getMaxHealthPoints()-30, warrior.getHealth());
    }
    @Test
    void playerWarriorShouldRetainBlockAfterAPiercingAttack() {
        playerAction.useSpecial(warrior, opponent);
        warrior.takeDamage(40, true);
        assertEquals(warrior.getMaxHealthPoints()-40, warrior.getHealth());
        warrior.setHealth(warrior.getMaxHealthPoints());
        warrior.takeDamage(40, false);
        assertEquals(warrior.getMaxHealthPoints()-(40/Config.DEFENDING_DAMAGE_REDUCTION.getNumber()), warrior.getHealth());
    }

    /////////////////////////////////////////////////////////////
    /// Player Mage Special Action

    @Test
    void playerMageShouldBeAbleToHeal(){
        mage.setHealth(20);
        playerAction.useSpecial(mage, opponent);
        assertEquals(20 + Config.HEAL_AMOUNT.getNumber(), mage.getHealth());
    }
    @Test
    void playerMageHealShouldNotExceedMaxHitPoints(){
        mage.setHealth(mage.getMaxHealthPoints()-5);
        playerAction.useSpecial(mage, opponent);
        assertEquals(mage.getMaxHealthPoints(), mage.getHealth());
    }

    /////////////////////////////////////////////////////////////
    /// Player Rogue Special Action

    @Test
    void playerRogueSpecialActionShouldDeal20Damage() {
        opponent.setHealth(80);
        playerAction.useSpecial(rogue, opponent);
        assertEquals(60, opponent.getHealth());
    }
    @Test
    void playerRogueSpecialActionShouldDealDamageThroughDefending() {
        opponent.setHealth(80);
        playerAction.useSpecial(opponent, rogue);
        assertTrue(opponent.getDefending());
        playerAction.useSpecial(rogue, opponent);
        assertEquals(60, opponent.getHealth());
    }
}
