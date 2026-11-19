package Tests.game_engine;

import game_classes.*;

import game_engine.ComputerLogic;
import game_engine.GameClassFactory;
import game_engine.MatchEngine;
import game_engine.actions.ActionResult;
import game_engine.actions.ComputerAction;
import game_engine.actions.PlayerAction;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import user_interface.BattleGraphics;
import user_interface.BattleInterface;


import static org.junit.jupiter.api.Assertions.*;


public class TestMatchEngine {
    private MatchEngine matchEngine;
    private GameClass player;
    private GameClass opponent;
    private GameClassFactory factory;
    private PlayerAction playerAction;
    private ComputerAction computerAction;
    private BattleGraphics graphics;
    private ActionResult actionResult;
    private ActionType actionType;
    private ComputerLogic computerLogic;
    private BattleInterface battleInterface;

    //plocka in player and oponent
    //checka getalive
    //om false, vinner motståndaren och matchen avslutas
    //annars startar runda
    //om användare = låta göra val av action
    //om dator = random action
    //skriv ut action
    @BeforeEach
    public void setup() {
        matchEngine = new MatchEngine(player, opponent);
        factory = new GameClassFactory();

    }

    @Test
    public void matchOverShouldReturnTrueIfGetAliveIsFalse() {
        factory.resetGameClasses();
        factory.createGameClass(GameClassType.WARRIOR, "player");
        player = factory.getPlayer(1);
        player.setHealth(5);
        player.takeDamage(6, false);
        assertFalse(player.getAlive());
        assertTrue(matchEngine.matchOver());
    }

    @Test
    public void getWinnerShouldReturnPlayerWinner() {
        player = new Warrior("Player");
        opponent = new Warrior("Computer");
        opponent.setHealth(5);
        opponent.takeDamage(10, false);

        matchEngine = new MatchEngine(player, opponent);


        assertTrue(matchEngine.matchOver());
        assertEquals(Winner.PLAYER1, matchEngine.getWinner());
    }

    @Test
    public void getWinnerShouldReturnComputerWinner() {
        player = new Warrior("Player");
        opponent = new Warrior("Computer");
        player.setHealth(5);
        player.takeDamage(10, false);

        matchEngine = new MatchEngine(player, opponent);

        assertTrue(matchEngine.matchOver());
        assertEquals(Winner.PLAYER2, matchEngine.getWinner());
    }

    @Test
    public void performAttackShouldUseCorrectAction() {
        player = new Warrior("Player");
        opponent = new Warrior("Computer");
        opponent.setHealth(100);

        ActionResult result = matchEngine.performAction(player, opponent, ActionType.ATTACK, new PlayerAction(null, null));

        assertEquals(ActionType.ATTACK, result.getActionType());
        assertTrue(opponent.getHealth() < 100 && opponent.getHealth() > 86);

    }

    @Test
    public void performSpecialShouldHealMage() {
        var mage = new Mage("Mage");
        mage.setHealth(10);
        var target = new Warrior("Warrior");

        ActionResult result = matchEngine.performAction(mage, target, ActionType.SPECIAL, new PlayerAction(null, null));

        assertEquals(ActionType.SPECIAL, result.getActionType());
        assertEquals(35, mage.getHealth());
        assertEquals(25, result.getAmount());
    }

    @Test
    public void performSpecialShouldUseStaticDmgWithRogue() {
        var rogue = new Rogue("Rogue");
        var target = new Warrior("Warrior");

        ActionResult result = matchEngine.performAction(rogue, target, ActionType.SPECIAL, new PlayerAction(null, null));

        assertEquals(ActionType.SPECIAL, result.getActionType());
        assertEquals(130, target.getHealth());
    }
    @Test
    public void performSpecialShouldActivateDefendForWarrior() {
        var warrior = new Warrior("Warrior");
        var target = new Warrior("Warrior");

        ActionResult result = matchEngine.performAction(warrior, target, ActionType.SPECIAL, new PlayerAction(null, null));

        assertEquals(ActionType.SPECIAL, result.getActionType());
        assertTrue(warrior.getDefending());
    }

}
