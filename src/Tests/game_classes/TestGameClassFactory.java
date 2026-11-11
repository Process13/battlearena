package Tests.game_classes;

import game_classes.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Random;

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
        //Act
        GameClass warrior = gameClassFactory.createGameClass(GameClassType.WARRIOR, "Orc");
        //Assert
        assertEquals("Orc", warrior.getCharacterName());
    }
    @Test
    public void createGameClassShouldCreateMage() {
        //Act
        GameClass mage = gameClassFactory.createGameClass(GameClassType.MAGE, "Elf");
        //Assert
        assertEquals("Elf", mage.getCharacterName());
    }
    @Test
    public void createGameClassShouldCreateRogue() {
        //Act
        GameClass rogue = gameClassFactory.createGameClass(GameClassType.ROGUE, "Human");
        //Assert
        assertEquals("Human", rogue.getCharacterName());
    }
    @Test
    public void createRandomGameClassShouldReturnRandomGameClass() {
        GameClass random = gameClassFactory.createRandomGameClass("Computer");

        assertTrue(random instanceof Warrior || random instanceof Mage || random instanceof Rogue);
        assertEquals("Computer", random.getCharacterName());
    }
}


