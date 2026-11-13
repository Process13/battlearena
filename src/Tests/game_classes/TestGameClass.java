package Tests.game_classes;

import game_classes.GameClass;
import game_classes.Warrior;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TestGameClass {
    private GameClass player;
    private GameClass oponent;

    @BeforeEach
    public void setup(){
        player=new Warrior("player");
        oponent=new Warrior("oponent");
    }
    @Test
    public void getAliveShouldReturnFalse(){
        assertTrue(player.getAlive());
        player.setHealth(5);
        player.takeDamage(10,false);
        assertEquals(0,player.getHealth());
        assertFalse(player.getAlive());
    }
}
