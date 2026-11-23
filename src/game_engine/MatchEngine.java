package game_engine;

//Run a match between two inputted characters.
//Check getAlive() if true continue.
//Use BattleGraphics to print events
//Use BattleInterface to get inputs from user and computer
//Make sure special can't be used more than every three rounds
//Determine winner

import game_classes.ActionType;
import game_classes.GameClass;
import game_classes.Winner;
import game_engine.actions.ActionResult;
import game_engine.actions.ComputerAction;
import game_engine.actions.GameActions;
import game_engine.actions.PlayerAction;
import user_interface.BattleGraphics;
import user_interface.BattleInterface;

import javax.swing.*;
import java.util.Scanner;


public class MatchEngine {
    private final GameClass player;
    private final GameClass opponent;
    private final BattleGraphics graphics = new BattleGraphics();
    private final Scanner scanner = new Scanner(System.in);
    private final BattleInterface input = new BattleInterface(scanner);
    private final GameActions playerActions;
    private final GameActions opponentActions;

    private byte rounds = 0;
    byte playerRoundsSinceSpecial = 0;
    byte opponentRoundsSinceSpecial = 0;


    public MatchEngine(GameClass player, GameClass opponent) {
        this.player = player;
        this.opponent = opponent;

        if (player.isComputer()) {
            this.playerActions = new ComputerAction();
        }else {
            this.playerActions = new PlayerAction(input, graphics);
        }
        if (opponent.isComputer()) {
            this.opponentActions = new ComputerAction();
        }else {
            this.opponentActions = new PlayerAction(input, graphics);
        }

    }

    public Winner playMatch(Boolean debug) {
        if (!debug) {
            graphics.showStatus(player, opponent);
        }

        while (!matchOver()) {
            rounds++;
            if (playerRoundsSinceSpecial >= 3){
                player.setSpecialUsed(false);
                playerRoundsSinceSpecial = 0;
            }
            if (opponentRoundsSinceSpecial >= 3){
                opponent.setSpecialUsed(false);
                opponentRoundsSinceSpecial = 0;
            }
            if (!debug) {
                graphics.showRound(rounds);
            }
            takeTurn(player, opponent, playerActions, debug);
            if (player.isSpecialUsed()){
                playerRoundsSinceSpecial++;
            }
            if (!matchOver()) {
                takeTurn(opponent, player, opponentActions,debug);
                if (opponent.isSpecialUsed()){
                    opponentRoundsSinceSpecial++;
                }
            }
        }
        Winner winner = getWinner();
        if (!debug) {
            graphics.showWinner(winner, player.getCharacterName());
        }
        return winner;
    }

    private void takeTurn(GameClass actor, GameClass target, GameActions actions,boolean debug) {
        ActionType choice = actions.chooseAction(actor, target);
        ActionResult result = performAction(actor, target, choice, actions);
        if(!debug) {
            graphics.showActionResult(result);
            graphics.showStatus(player, opponent);
        }
    }

    // performing an Action based on enum and generating printable results.
    public ActionResult performAction(GameClass actor, GameClass target, ActionType actionType, GameActions actions) {
        return switch (actionType) {
            case ATTACK -> {
                int damage = actions.useAttack(actor, target);
                yield ActionResult.attack(actor.getCharacterName(), target.getCharacterName(), damage);
            }
            case SPECIAL -> {
                int amount = actions.useSpecial(actor, target);
                yield ActionResult.special(actor.getCharacterName(), target.getCharacterName(), amount, actor.getSpecialType());
            }
        };
    }

    // Return true if getAlive is false
    public boolean matchOver() {
        return !player.getAlive() || !opponent.getAlive();
    }

    // Returns the correct winner
    public Winner getWinner() {
        if (!matchOver()) {
            throw new IllegalStateException("Match is not over yet.");
        }
        return player.getAlive() ? Winner.PLAYER1 : Winner.PLAYER2;
    }


}
