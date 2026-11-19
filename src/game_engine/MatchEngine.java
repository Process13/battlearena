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
    private final boolean isPlayerComputer;
    private final GameActions playerAction;
    private final GameClass opponent;
    private final GameActions opponentAction;
    private final boolean isOpponentComputer;
    private final BattleGraphics graphics = new BattleGraphics();
    private final Scanner scanner = new Scanner(System.in);
    private final BattleInterface input = new BattleInterface(scanner);

    private int rounds;


//    public MatchEngine(GameClass player, GameClass opponent, BattleGraphics graphics) {
//        this.player = player;
//        this.opponent = opponent;
//        this.graphics = graphics;
//    }
    public MatchEngine(GameClass player, GameClass opponent){
        this.player = player;
        this.isPlayerComputer = player.isComputer();
        this.opponent = opponent;
        this.isOpponentComputer = opponent.isComputer();

        if (player.isComputer()){
            this.playerAction = new ComputerAction();
        } else {
            this.playerAction = new PlayerAction(input, graphics);
        }

        if (player.isComputer()){
            this.opponentAction = new ComputerAction();
        } else {
            this.opponentAction = new PlayerAction(input, graphics);
        }
    }

    public Winner playMatch(Boolean debug) {
        if (!debug) {
            graphics.showStatus(player, opponent);
        }
        while(!matchOver()) {
            rounds++;
            if (!debug) {
                graphics.showRound(rounds);
            }

            takeTurn(player,opponent, playerAction, debug);
            if(!matchOver()) {
              takeTurn(opponent,player, opponentAction, debug);
            }
        }
        Winner winner = getWinner();
        if (!debug) {
            graphics.showWinner(winner, player.getCharacterName());
        }
        return winner;
    }
    private void takeTurn(GameClass actor, GameClass target, GameActions actions, boolean debug) {
            ActionType action = actions.chooseAction(actor,target);
            ActionResult result = performAction(actor, target,action, actions);

            if (!debug) {
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
                int amount = actions.useSpecial(actor,target);
                yield ActionResult.special(actor.getCharacterName(),target.getCharacterName(), amount, actor.getSpecialType());
            }
        };
    }

    // Return true if getAlive is false
    public boolean matchOver(){
        return !player.getAlive() || !opponent.getAlive();
    }
    // Returns the correct winner
    public Winner getWinner(){
        if(!matchOver()){
            throw new IllegalStateException("Match is not over yet.");
        }
        //             \                |                /
        //              v               v               v
        return player.getAlive() ?  Winner.PLAYER1 : Winner.PLAYER2;
        //                  LOOK HERE AND CHECK ME!!!!!!!!
        //              A               A               A
        //             /                |                \
    }


}
