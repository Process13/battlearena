package Tests.game_engine;

import game_classes.*;
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
       assertEquals(ActionType.SPECIAL, computerLogic.computerWarriorLogic(computerWarrior, opponentW));
    }
    @Test
    void computerWarriorShouldUseAttackActionIfSpecialActionIsUnavailable(){
        computerWarrior.setSpecialUsed(true);
        assertEquals(ActionType.ATTACK, computerLogic.computerWarriorLogic(computerWarrior, opponentW));
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
        assertEquals(ActionType.SPECIAL, computerLogic.computerMageLogic(computerMage, opponentW));
    }
    @Test
    void computerMageShouldUseAttackActionIfSpecialActionIsUnavailable(){
        computerMage.setSpecialUsed(true);
        assertEquals(ActionType.ATTACK, computerLogic.computerMageLogic(computerMage, opponentW));
    }
    @Test
    void computerMageShouldUseAttackActionIfHealingWouldPutHpAboveMaxHealth() {
        computerMage.setSpecialUsed(false);
        computerMage.setHealth(computerMage.getMaxHealthPoints()-20);
        opponentW.setHealth(100);
        computerLogic.computerMageLogic(computerMage, opponentW);
        assertFalse(computerMage.isSpecialUsed());
        assertEquals(ActionType.ATTACK, computerLogic.computerMageLogic(computerMage, opponentW));
    }

    /////////////////////////////////////////////////////////////
    /// Computer Rouge Logic

    @Test
    void computerRogueShouldUseSpecialIfAvailableAndEnemyIsMageOrRouge() {
        computerRogue.setSpecialUsed(false);
        assertEquals(ActionType.SPECIAL, computerLogic.computerRogueLogic(computerRogue, opponentM));
        computerRogue.setSpecialUsed(false);
        assertEquals(ActionType.SPECIAL, computerLogic.computerRogueLogic(computerRogue, opponentR));
    }
    @Test
    void computerRougeShouldSaveSpecialAbilityForWhenWarriorDefends() {
        computerRogue.setSpecialUsed(false);
        assertNotEquals(opponentW.getMaxHealthPoints()-20, opponentW.getHealth());
        assertEquals(ActionType.ATTACK,  computerLogic.computerRogueLogic(computerRogue, opponentW));
        assertFalse(computerRogue.isSpecialUsed());
        opponentW.setHealth(opponentW.getMaxHealthPoints());
        opponentW.specialAbility(computerRogue);
        assertTrue(opponentW.getDefending());
        computerRogue.setSpecialUsed(false);
        assertEquals(ActionType.SPECIAL,computerLogic.computerRogueLogic(computerRogue, opponentW));
    }
}
