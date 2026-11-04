package game_engine.actions;

import game_classes.;
import game_classes.*;
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
    @Test
    void playerWarriorShouldBeAbleToAttack() {
        opponent.setHealth(60);
        playerAction.useAttack(warrior, opponent);
        assertTrue(opponent.getHealth() < 60);
    }
    @Test
    void playerMageShouldBeAbleToAttack() {
        opponent.setHealth(60);
        playerAction.useAttack(mage, opponent);
        assertTrue(opponent.getHealth() < 60);
    }
    @Test
    void playerRogueShouldBeAbleToAttack() {
        opponent.setHealth(60);
        playerAction.useAttack(rogue, opponent);
        assertTrue(opponent.getHealth() < 60);
    }

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

}
