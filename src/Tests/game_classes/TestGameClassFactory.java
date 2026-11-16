package Tests.game_classes;

import game_classes.*;
import game_engine.GameClassFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

public class TestGameClassFactory {
    private GameClassFactory gameClassFactory;

    @BeforeEach
    void setup() {
        gameClassFactory = new GameClassFactory();
    }

    @Test
    void createGameClassShouldCreateWarrior() {
        gameClassFactory.resetGameClasses();
        gameClassFactory.createGameClass(GameClassType.WARRIOR, "Orc");
        assertEquals("Orc", gameClassFactory.getPlayer(1).getCharacterName());
    }


    @Test
    void createGameClassShouldCreateMage() {
        gameClassFactory.resetGameClasses();
        gameClassFactory.createGameClass(GameClassType.MAGE, "Elf");
        assertEquals("Elf", gameClassFactory.getPlayer(1).getCharacterName());
    }

    @Test
    void createGameClassShouldCreateRogue() {
        gameClassFactory.resetGameClasses();
        gameClassFactory.createGameClass(GameClassType.ROGUE, "Human");
        assertEquals("Human", gameClassFactory.getPlayer(1).getCharacterName());
    }

    @Test
    void createRandomGameClassShouldReturnRandomGameClass() {
        gameClassFactory.resetGameClasses();
        gameClassFactory.createRandomGameClass("Computer");
        GameClass random = gameClassFactory.getPlayer(1);

        assertTrue(random instanceof Warrior || random instanceof Mage || random instanceof Rogue);
        assertEquals("Computer", random.getCharacterName());
    }
    @Test
    void createGameClassShouldStoreCreatedCharactersInTheCorrectLocation() {
        gameClassFactory.resetGameClasses();
        assertNull(gameClassFactory.getPlayer(1));
        assertNull(gameClassFactory.getPlayer(2));
        gameClassFactory.createRandomGameClass("player1");
        gameClassFactory.createRandomGameClass("player2");
        assertEquals("player1", gameClassFactory.getPlayer(1).getCharacterName());
        assertEquals("player2", gameClassFactory.getPlayer(2).getCharacterName());
        gameClassFactory.resetGameClasses();
        gameClassFactory.createRandomGameClass("rewritten1");
        assertEquals("rewritten1", gameClassFactory.getPlayer(1).getCharacterName());
        assertEquals("player2", gameClassFactory.getPlayer(2).getCharacterName());
        gameClassFactory.createRandomGameClass("rewritten2");
        assertEquals("rewritten2", gameClassFactory.getPlayer(2).getCharacterName());
    }
}


