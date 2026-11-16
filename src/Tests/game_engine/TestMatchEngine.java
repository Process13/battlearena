package Tests.game_engine;

import game_classes.*;

import game_engine.MatchEngine;
import game_engine.actions.ActionResult;
import game_engine.actions.ComputerAction;
import game_engine.actions.GameActions;
import game_engine.actions.PlayerAction;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import user_interface.BattleGraphics;
import user_interface.StartMenu;

import static org.junit.jupiter.api.Assertions.*;


public class TestMatchEngine {
private MatchEngine matchEngine;;
private GameClass  player;
private GameClass  computer;
private PlayerAction  playerAction;
private ComputerAction computerAction;
private BattleGraphics graphics;
private ActionResult actionResult;

    //plocka in player and oponent
    //checka getalive
    //om false, vinner motståndaren och matchen avslutas
    //annars startar runda
    //om användare = låta göra val av action
    //om dator = random action
    //skriv ut action
    @BeforeEach
    public void setup(){
        matchEngine = new MatchEngine(player,computer,playerAction,computerAction,graphics);

    }

    @Test
    public void matchOverShouldReturnTrueIfGetAliveIsFalse() {
        player = new Warrior("player");
        computer = new Warrior("computer");
        player.setHealth(5);
        player.takeDamage(6,false);

        matchEngine = new MatchEngine(player,computer,playerAction,computerAction,graphics);

        assertFalse(player.getAlive());
        assertTrue(matchEngine.matchOver());
    }
    @Test
    public void getWinnerShouldReturnPlayerWinner(){
        player = new Warrior("Player");
        computer = new Warrior("Computer");
        computer.setHealth(5);
        computer.takeDamage(10,false);

        matchEngine = new MatchEngine(player, computer,  playerAction, computerAction, graphics);

        assertTrue(matchEngine.matchOver());
        assertEquals(Winner.PLAYER,matchEngine.getWinner());
    }
    @Test
    public void getWinnerShouldReturnComputerWinner(){
        player = new Warrior("Player");
        computer = new Warrior("Computer");
        player.setHealth(5);
        player.takeDamage(10,false);

        matchEngine = new MatchEngine(player, computer,playerAction,computerAction,graphics);

        assertTrue(matchEngine.matchOver());
        assertEquals(Winner.COMPUTER,matchEngine.getWinner());
    }
    @Test
    public void performAttackShouldUseCorrectAction(){
        player = new Warrior("Player");
        computer = new Warrior("Computer");
        computer.setHealth(100);


        MatchEngine engine = new MatchEngine(player,computer,playerAction,computerAction,graphics);
        engine.performAction(player,computer,ActionType.ATTACK,new PlayerAction());
        ActionResult result = matchEngine.performAction(player,computer,ActionType.ATTACK, new PlayerAction());

        assertEquals(ActionType.ATTACK,result.getActionType());
        assertTrue(computer.getHealth()<100);
    }

    @Test
    public void performSpecialShouldUseCorrectAction(){
        var mage = new Mage("Mage");
        mage.setHealth(10);
        var target = new Warrior("Computer");

        ActionResult result = matchEngine.performAction(mage,target,ActionType.SPECIAL, new PlayerAction());

        assertEquals(ActionType.SPECIAL,result.getActionType());
        assertEquals(35, mage.getHealth());
        assertEquals(25,result.getAmount());
    }

}
