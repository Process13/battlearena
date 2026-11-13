package Tests.game_engine;

import game_classes.GameClass;

import game_classes.Warrior;
import game_classes.Winner;
import game_engine.MatchEngine;
import game_engine.actions.ComputerAction;
import game_engine.actions.PlayerAction;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import user_interface.StartMenu;

import static org.junit.jupiter.api.Assertions.*;


public class TestMatchEngine {
private MatchEngine matchEngine;;
private GameClass  player;
private GameClass  computer;
private PlayerAction  playerAction;
private ComputerAction computerAction;
private StartMenu startMenu;

    //plocka in player and oponent
    //checka getalive
    //om false, vinner motståndaren och matchen avslutas
    //annars startar runda
    //om användare = låta göra val av action
    //om dator = random action
    //skriv ut action
    @BeforeEach
    public void setup(){
        matchEngine = new MatchEngine(player,computer,playerAction,computerAction,startMenu);

    }

    @Test
    public void matchOverShouldReturnTrueGetAliveIsFalse() {
        player = new Warrior("player");
        computer = new Warrior("computer");
        player.setHealth(5);
        player.takeDamage(6,false);

        matchEngine = new MatchEngine(player,computer,playerAction,computerAction,startMenu);

        assertFalse(player.getAlive());
        assertTrue(matchEngine.matchOver());
    }
    @Test
    public void getWinnerShouldReturnPlayerWinner(){
        player = new Warrior("Player");
        computer = new Warrior("Computer");
        computer.setHealth(5);
        computer.takeDamage(10,false);

        matchEngine = new MatchEngine(player, computer,  playerAction, computerAction, startMenu);

        assertTrue(matchEngine.matchOver());
        assertEquals(Winner.PLAYER,matchEngine.getWinner());
    }
    @Test
    public void getWinnerShouldReturnComputerWinner(){
        player = new Warrior("Player");
        computer = new Warrior("Computer");
        player.setHealth(5);
        player.takeDamage(10,false);

        matchEngine = new MatchEngine(player, computer,playerAction,computerAction,startMenu);

        assertTrue(matchEngine.matchOver());
        assertEquals(Winner.COMPUTER,matchEngine.getWinner());
    }

}
