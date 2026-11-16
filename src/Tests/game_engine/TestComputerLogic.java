package Tests.game_engine;

import game_classes.GameClass;
import game_classes.Mage;
import game_classes.Rogue;
import game_classes.Warrior;
import game_engine.ComputerLogic;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TestComputerLogic {
    ComputerLogic computerLogic;
    GameClass computerWarrior;
    GameClass computerMage;
    GameClass computerRogue;
    GameClass opponentW , opponentM, opponentR;

    @BeforeEach
    void setup(){
        computerLogic = new ComputerLogic();
        computerWarrior = new Warrior("ComputerW");
        computerMage = new Mage("ComputerM");
        computerRogue = new Rogue("ComputerR");
        opponentW = new Warrior("OpponentW");
        opponentM = new Mage("OpponentM");
        opponentR = new Rogue("OpponentR");
    }

    /////////////////////////////////////////////////////////////
    /// Computer Warrior Logic

    @Test
    void computerWarriorShouldUseSpecialActionIfAvailable(){
        assertFalse(computerWarrior.isSpecialUsed());
        computerLogic.computerWarriorLogic(computerWarrior, opponentW);
        assertTrue(computerWarrior.isSpecialUsed());
    }
    @Test
    void computerWarriorShouldUseAttackActionIfSpecialActionIsUnavailable(){
        computerWarrior.setSpecialUsed(true);
        opponentW.setHealth(60);
        computerLogic.computerWarriorLogic(computerWarrior, opponentW);
        assertTrue(opponentW.getHealth()<60);
    }

    /////////////////////////////////////////////////////////////
    /// Computer Mage Logic

    @Test
    void computerMageShouldNotUseSpecialActionIfHealthIsFull(){
        computerMage.setHealth(computerMage.getMaxHealthPoints());
        assertFalse(computerMage.isSpecialUsed());
        computerLogic.computerMageLogic(computerMage, opponentW);
        assertFalse(computerMage.isSpecialUsed());
    }
    @Test
    void computerMageShouldNotUseSpecialActionIfHealingWouldPutHpAboveMaxHealth() {
        computerMage.setHealth(computerMage.getMaxHealthPoints()-24);
        assertFalse(computerMage.isSpecialUsed());
        computerLogic.computerMageLogic(computerMage, opponentW);
        assertFalse(computerMage.isSpecialUsed());
    }
    @Test
    void computerMageShouldUseSpecialActionIfHealingWouldNotPutItAboveMaxHealth() {
        computerMage.setHealth(computerMage.getMaxHealthPoints()-25);
        assertFalse(computerMage.isSpecialUsed());
        computerLogic.computerMageLogic(computerMage, opponentW);
        assertEquals(computerMage.getMaxHealthPoints(), computerMage.getHealth());
        computerMage.setHealth(5);
        computerMage.setSpecialUsed(false);
        computerLogic.computerMageLogic(computerMage, opponentW);
        assertEquals(30, computerMage.getHealth());
    }
    @Test
    void computerMageShouldUseAttackActionIfSpecialActionIsUnavailable(){
        computerMage.setSpecialUsed(true);
        opponentW.setHealth(60);
        computerLogic.computerMageLogic(computerMage, opponentW);
        assertTrue(opponentW.getHealth()<60);
    }
    @Test
    void computerMageShouldUseAttackActionIfHealingWouldPutHpAboveMaxHealth() {
        computerMage.setSpecialUsed(false);
        computerMage.setHealth(computerMage.getMaxHealthPoints()-20);
        opponentW.setHealth(100);
        computerLogic.computerMageLogic(computerMage, opponentW);
        assertFalse(computerMage.isSpecialUsed());
        assertTrue(opponentW.getHealth()<100);
    }

    /////////////////////////////////////////////////////////////
    /// Computer Rouge Logic

    @Test
    void computerRogueShouldUseSpecialIfAvailableAndEnemyIsMageOrRouge() {
        computerRogue.setSpecialUsed(false);
        computerLogic.computerRogueLogic(computerRogue, opponentM);
        assertEquals(opponentM.getMaxHealthPoints()-20, opponentM.getHealth());
        computerRogue.setSpecialUsed(false);
        computerLogic.computerRogueLogic(computerRogue, opponentR);
        assertEquals(opponentR.getMaxHealthPoints()-20, opponentR.getHealth());
    }
    @Test
    void computerRougeShouldSaveSpecialAbilityForWhenWarriorDefends() {
        computerRogue.setSpecialUsed(false);
        computerLogic.computerRogueLogic(computerRogue, opponentW);
        assertNotEquals(opponentW.getMaxHealthPoints()-20, opponentW.getHealth());
        assertTrue(opponentW.getHealth() < opponentW.getMaxHealthPoints());
        assertFalse(computerRogue.isSpecialUsed());

        opponentW.setHealth(opponentW.getMaxHealthPoints());
        opponentW.specialAbility(computerRogue);
        assertTrue(opponentW.getDefending());
        computerRogue.setSpecialUsed(false);
        computerLogic.computerRogueLogic(computerRogue, opponentW);
        assertEquals(opponentW.getMaxHealthPoints()-20, opponentW.getHealth());
    }
}
