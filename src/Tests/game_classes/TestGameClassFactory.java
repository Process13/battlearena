package Tests.game_classes;

import game_classes.*;
import game_engine.GameClassFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestGameClassFactory {
    private GameClassFactory gameClassFactory;

    @BeforeEach
    public void setup() {
        gameClassFactory = new GameClassFactory();
    }

    @Test
    public void createGameClassShouldCreateWarrior() {
        gameClassFactory.resetGameClasses();
        gameClassFactory.createGameClass(GameClassType.WARRIOR, "Orc");
        gameClassFactory.getPlayer(1);
        GameClass warrior = gameClassFactory.getPlayer(1);
        assertEquals("Orc", warrior.getCharacterName());
    }


    @Test
    public void createGameClassShouldCreateMage() {
        gameClassFactory.resetGameClasses();
        gameClassFactory.createGameClass(GameClassType.MAGE, "Elf");
        gameClassFactory.getPlayer(1);
        GameClass mage = gameClassFactory.getPlayer(1);
        assertEquals("Elf", mage.getCharacterName());
    }

    @Test
    public void createGameClassShouldCreateRogue() {
        gameClassFactory.resetGameClasses();
        gameClassFactory.createGameClass(GameClassType.ROGUE, "Human");
        gameClassFactory.getPlayer(1);
        GameClass rogue = gameClassFactory.getPlayer(1);
        assertEquals("Human", rogue.getCharacterName());
    }

    @Test
    public void createRandomGameClassShouldReturnRandomGameClass() {
        gameClassFactory.resetGameClasses();
        gameClassFactory.createRandomGameClass("Computer");
        GameClass random = gameClassFactory.getPlayer(1);

        assertTrue(random instanceof Warrior || random instanceof Mage || random instanceof Rogue);
        assertEquals("Computer", random.getCharacterName());
    }
}


