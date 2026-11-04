package game_engine.actions;

import game_classes.;
import game_classes.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestPlayerActions {
    private GameClass gameClass;
    private Warrior warrior;
    private Mage mage;
    private Rogue rogue;

    @BeforeEach
    void setup() {
        playerActions = new PlayerAction();
        warrior = new Warrior("Gork");
        mage = new Mage("Gary");
        rogue = new Rogue("James");


    }

    @Test
    void playerShouldBeAbleToHeal(){
        //Arrange


        //Act


        //Assert
        assertEquals(1, 1);
    }

}
