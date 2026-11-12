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
        GameClass warrior = gameClassFactory.createGameClass(GameClassType.WARRIOR, "Orc");
        assertEquals("Orc", warrior.getCharacterName());
    }

    @Test
    public void createGameClassShouldCreateMage() {
        GameClass mage = gameClassFactory.createGameClass(GameClassType.MAGE, "Elf");
        assertEquals("Elf", mage.getCharacterName());
    }

    @Test
    public void createGameClassShouldCreateRogue() {
        GameClass rogue = gameClassFactory.createGameClass(GameClassType.ROGUE, "Human");
        assertEquals("Human", rogue.getCharacterName());
    }

    @Test
    public void createRandomGameClassShouldReturnRandomGameClass() {
        GameClass random = gameClassFactory.createRandomGameClass("Computer");

        assertTrue(random instanceof Warrior || random instanceof Mage || random instanceof Rogue);
        assertEquals("Computer", random.getCharacterName());
    }
}


