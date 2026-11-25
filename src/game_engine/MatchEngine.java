package game_engine;

//Run a match between two inputted characters.
//Check getAlive() if true continue.
//Use BattleGraphics to print events
//Use BattleInterface to get inputs from user and computer
//Make sure special can't be used more than every three rounds
//Determine winner

import game_classes.ActionType;
import game_classes.GameClass;
import game_classes.GameClassType;
import game_classes.Winner;
import game_engine.actions.ActionResult;
import game_engine.actions.ComputerAction;
import game_engine.actions.GameActions;
import game_engine.actions.PlayerAction;
import user_interface.BattleGraphics;
import user_interface.BattleInterface;

import javax.swing.*;
import java.io.IOException;
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
    private byte playerRoundsSinceSpecial = 0;
    private byte opponentRoundsSinceSpecial = 0;

    private String message1 = "", message2 = "";


    public MatchEngine(GameClass player, GameClass opponent) {
        this.player = player;
        this.opponent = opponent;

        if (player.isComputer()) {
            this.playerActions = new ComputerAction();
        } else {
            this.playerActions = new PlayerAction(input, graphics);
        }
        if (opponent.isComputer()) {
            this.opponentActions = new ComputerAction();
        } else {
            this.opponentActions = new PlayerAction(input, graphics);
        }

    }

    public Winner playMatch(Boolean debug) {
        if (!debug) {
            waitForEnterKey();
            graphics.clearTerminal();
        }
        while (!matchOver()) {
            rounds++;
            playerRoundsSinceSpecial = prepareRound(player, playerRoundsSinceSpecial);
            opponentRoundsSinceSpecial = prepareRound(opponent, opponentRoundsSinceSpecial);

            if (!debug) {
                graphics.clearTerminal();
                graphics.showRound(rounds);
                graphics.showStatus(player, opponent, message1, message2);
                waitForEnterKey();
            }
            message1 = takeTurn(player, opponent, playerActions, debug);
            if (player.isSpecialUsed()) {
                playerRoundsSinceSpecial++;
            }
            if (!matchOver()) {
                if (!debug){
                    graphics.clearTerminal();
                    graphics.showRound(rounds);
                    graphics.showStatus(player, opponent, message1, message2);
                    waitForEnterKey();
                }
                message2 = takeTurn(opponent, player, opponentActions, debug);
                if (opponent.isSpecialUsed()) {
                    opponentRoundsSinceSpecial++;
                }
                if (!debug){
                    graphics.clearTerminal();
                    graphics.showRound(rounds);
                    graphics.showStatus(player, opponent, message1, message2);
                    waitForEnterKey();
                }
            }
            message1 = "";
            message2 = "";
        }
        Winner winner = getWinner();
        if (!debug) {
            graphics.showWinner(winner, player.getCharacterName(), player.getType().getClassName(), opponent.getType().getClassName());
        }
        return winner;
    }

    private String takeTurn(GameClass actor, GameClass target, GameActions actions, boolean debug) {
        if (!actor.isComputer()) {
            graphics.showActionChoices(actor, !actor.isSpecialUsed());
            graphics.showRound(rounds);
            graphics.showStatus(actor, target, message1, message2);
        }
        ActionType choice = actions.chooseAction(actor, target);
        ActionResult result = performAction(actor, target, choice, actions);
        if (!debug) {
            return graphics.showActionResult(result);
        } else {
            return "";
        }
    }

    private byte prepareRound(GameClass actor, byte roundsSinceSpecial) {
        if (actor.getType() == GameClassType.WARRIOR && playerRoundsSinceSpecial <= Config.DEFENDING_TURNS_ACTIVE.getNumber() && playerRoundsSinceSpecial != 0) {
            actor.setDefending(true);
        }
        if (roundsSinceSpecial >= Config.ABILITY_COOLDOWN_ROUNDS.getNumber()) {
            actor.setSpecialUsed(false);
            return 0;
        }
        return roundsSinceSpecial;
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
    public void sleep() {
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new IllegalStateException("Match has been interrupted!");
        }
    }
    public void waitForEnterKey() {
        try {
            while (System.in.available() == 0) {
                sleep();
            }
            sleep();
        } catch (IOException e){
            System.out.println("Error: " + e);
        }
    }


}
