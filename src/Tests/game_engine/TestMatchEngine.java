package Tests.game_engine;

import game_classes.*;

import game_engine.GameClassFactory;
import game_engine.MatchEngine;
import game_engine.actions.ActionResult;
import game_engine.actions.PlayerAction;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.*;


public class TestMatchEngine {
    private MatchEngine matchEngine;
    private GameClass player;
    private GameClass opponent;
    private GameClassFactory factory;
    private PlayerAction playerAction;
    private ActionResult result;

    //plocka in player and oponent
    //checka getalive
    //om false, vinner motståndaren och matchen avslutas
    //annars startar runda
    //om användare = låta göra val av action
    //om dator = random action
    //skriv ut action
    @BeforeEach
    public void setup() {
        factory = new GameClassFactory();
        factory.resetGameClasses();
        factory.createRandomGameClass("Player1");
        factory.createRandomGameClass("Player2");
        player = factory.getPlayer(1);
        opponent = factory.getPlayer(2);
        matchEngine = new MatchEngine(player, opponent);
        playerAction = new PlayerAction(null, null);
    }
    @AfterEach
    void shutdown(){
        factory.resetGameClasses();
    }

    @Test
    public void matchOverShouldReturnTrueIfGetAliveIsFalse() {
        player.setHealth(5);
        assertTrue(player.getAlive());
        player.takeDamage(6, false);
        assertFalse(player.getAlive());
        assertTrue(matchEngine.matchOver());
    }

    @Test
    public void getWinnerShouldReturnPlayerWinner() {
        opponent.setHealth(5);
        assertTrue(opponent.getAlive());
        opponent.takeDamage(10, false);
        assertTrue(player.getAlive());
        assertTrue(matchEngine.matchOver());
        assertEquals(Winner.PLAYER1, matchEngine.getWinner());
    }

    @Test
    public void getWinnerShouldReturnComputerWinner() {
        player.setHealth(5);
        assertTrue(player.getAlive());
        player.takeDamage(10, false);
        assertTrue(opponent.getAlive());
        assertTrue(matchEngine.matchOver());
        assertEquals(Winner.PLAYER2, matchEngine.getWinner());
    }

    @Test
    public void performAttackShouldUseCorrectAction() {
        opponent.setHealth(100);
        result = matchEngine.performAction(player, opponent, ActionType.ATTACK, playerAction);
        assertEquals(ActionType.ATTACK, result.getActionType());
        assertTrue(opponent.getHealth() < 100);
    }

    @Test
    public void performSpecialShouldHealMage() {
        factory.resetGameClasses();
        factory.createGameClass(GameClassType.MAGE, "Mage");
        factory.createRandomGameClass("Player2");
        var mage = factory.getPlayer(1);
        var target = factory.getPlayer(2);
        mage.setHealth(10);
        result = matchEngine.performAction(mage, target, ActionType.SPECIAL, playerAction);
        assertEquals(ActionType.SPECIAL, result.getActionType());
        assertEquals(35, mage.getHealth());
        assertEquals(25, result.getAmount());
    }

    @Test
    public void performSpecialShouldUseStaticDmgWithRogue() {
        factory.resetGameClasses();
        factory.createGameClass(GameClassType.ROGUE, "Rogue");
        factory.createRandomGameClass("Player2");
        var rogue = factory.getPlayer(1);
        var target = factory.getPlayer(2);
        result = matchEngine.performAction(rogue, target, ActionType.SPECIAL, playerAction);
        assertEquals(ActionType.SPECIAL, result.getActionType());
        assertEquals(target.getMaxHealthPoints()-20, target.getHealth());
    }
    @Test
    public void performSpecialShouldActivateDefendForWarrior() {
        factory.resetGameClasses();
        factory.createGameClass(GameClassType.WARRIOR, "Warrior");
        factory.createRandomGameClass("Player2");
        var warrior = factory.getPlayer(1);
        var target = factory.getPlayer(2);
        assertFalse(warrior.getDefending());
        result = matchEngine.performAction(warrior, target, ActionType.SPECIAL, playerAction);
        assertEquals(ActionType.SPECIAL, result.getActionType());
        assertTrue(warrior.getDefending());
    }

}
