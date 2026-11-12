package Tests.game_engine;

import game_classes.GameClass;
import game_classes.Mage;
import game_classes.Rogue;
import game_classes.Warrior;
import game_engine.ComputerLogic;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestComputerLogic {
    ComputerLogic computerLogic;
    GameClass computerWarrior;
    GameClass computerMage;
    GameClass computerRogue;
    GameClass opponent;

    @BeforeEach
    void setup(){
        computerLogic = new ComputerLogic();
        computerWarrior = new Warrior("ComputerW");
        computerMage = new Mage("ComputerM");
        computerRogue = new Rogue("ComputerR");
        opponent = new Warrior("Opponent");
    }

    @Test
    void computerWarriorShouldUseSpecialActionIfAvailable(){
        assertFalse(computerWarrior.isSpecialUsed());
        computerLogic.computerWarrior(computerWarrior);
        assertTrue(computerWarrior.isSpecialUsed());
    }
    @Test
    void computerWarriorShouldUseAttackActionIfSpecialActionIsUnavailable(){
        computerWarrior.setSpecialUsed(true);
        computerLogic.computerWarrior(computerWarrior);
        computerLogic.
    }
}
