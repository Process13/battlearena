package game_engine.actions;

import game_classes.*;
import game_engine.actions.PlayerAction;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestPlayerActions {
    private PlayerAction playerAction;
    private GameClass warrior;
    private GameClass mage;
    private GameClass rogue;
    private GameClass opponent;

    @BeforeEach
    void setup() {
        playerAction = new PlayerAction();
        warrior = new Warrior(GameClassType.WARRIOR.getClassName());
        mage = new Mage(GameClassType.MAGE.getClassName());
        rogue = new Rogue(GameClassType.ROGUE.getClassName());
        opponent = new Warrior("Opponent");
    }

    /////////////////////////////////////////////////////////////

    @Test
    void playerWarriorShouldBeAbleToAttack() {
        opponent.setHealth(60);
        playerAction.useAttack(warrior, opponent);
        assertTrue(opponent.getHealth() < 60);
    }
    @Test
    void playerWarriorShouldReceiveCorrectDamageRangeValues() {
        int[] damageRange = warrior.getDamageRange();
        assertEquals(9, damageRange[0]);
        assertEquals(14, damageRange[1]);
    }
    @Test
    void playerWarriorDealtDamageWithinTheCorrectRange() {
        int lowest = 60, highest = 0;
        for (int i = 0; i < 10000; i++){
            opponent.setHealth(60);
            playerAction.useAttack(warrior, opponent);
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
    void playerMageShouldBeAbleToAttack() {
        opponent.setHealth(60);
        playerAction.useAttack(mage, opponent);
        assertTrue(opponent.getHealth() < 60);
    }
    @Test
    void playerMageShouldReceiveCorrectDamageRangeValues() {
        int[] damageRange = mage.getDamageRange();
        assertEquals(10, damageRange[0]);
        assertEquals(18, damageRange[1]);
    }
    @Test
    void playerMageDealtDamageWithinTheCorrectRange() {
        int lowest = 60, highest = 0;
        for (int i = 0; i < 10000; i++){
            opponent.setHealth(60);
            playerAction.useAttack(mage, opponent);
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
    void playerRogueShouldBeAbleToAttack() {
        opponent.setHealth(60);
        playerAction.useAttack(rogue, opponent);
        assertTrue(opponent.getHealth() < 60);
    }
    @Test
    void playerRogueShouldReceiveCorrectDamageRangeValues() {
        int[] damageRange = rogue.getDamageRange();
        assertEquals(9, damageRange[0]);
        assertEquals(14, damageRange[1]);
    }
    @Test
    void playerRogueDealtDamageWithinTheCorrectRange() {
        int lowest = 60, highest = 0;
        for (int i = 0; i < 10000; i++){
            opponent.setHealth(60);
            playerAction.useAttack(rogue, opponent);
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

/*
    @Test
    void playerWarriorShouldBeAbleToDefend() {
        warrior.setHealth(80);
        playerAction.useSpecial(warrior);
        warrior.takeDamage(30);
        assertEquals(65, warrior.getHealth());
    }
    @Test
    void playerMageShouldBeAbleToHeal(){
        mage.setHealth(20);
        playerAction.useSpecial(mage);
        assertTrue(mage.getHealth() > 20);
    }
    @Test
    void playerRougeShouldBeAbleToDeal20Damage() {
        opponent.setHealth(80);
        playerAction.useSpecial(rogue);
        assertEquals(60, opponent.getHealth());
    }

     */

}
