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
import java.util.ArrayList;
import java.util.List;

public class MatchEngine {
    private final GameClass player;
    private final GameClass opponent;
    private final PlayerAction playerAction;
    private final ComputerAction computerAction;
    private final ComputerLogic computerLogic;
    private final BattleGraphics graphics;
    private final BattleInterface battleInterface;
    ;
    private int rounds;


    public MatchEngine(GameClass player, GameClass opponent, PlayerAction playerAction, ComputerAction computerAction, ComputerLogic computerLogic, BattleGraphics graphics, BattleInterface battleInterface) {
        this.player = player;
        this.opponent = opponent;
        this.playerAction = playerAction;
        this.computerAction = computerAction;
        this.computerLogic = computerLogic;
        this.graphics = graphics;
        this.battleInterface = battleInterface;

    }

    public Winner playMatch() {
        graphics.showStatus(player, opponent);
        while(!matchOver()) {
            rounds++;
            graphics.showRound(rounds);

            ActionType playerChoice = battleInterface.readActionChoice();
            takeTurn(player,opponent,playerAction, playerChoice);
            if(!matchOver()) {
               // ActionType computerChoice = computerLogic.chooseAction(opponent, player);
              //  takeTurn(opponent,player,computerAction, computerChoice);
            }
        }
        Winner winner = getWinner();
        graphics.showWinner(winner, player.getCharacterName());
        return winner;
    }
    private void takeTurn(GameClass actor, GameClass target, GameActions actions, ActionType choice) {
            ActionResult result = performAction(actor, target,choice, actions);

            graphics.showActionResult(result);
            graphics.showStatus(actor, opponent);

    }
    // performing an Action based on enum and generating printable results.
    public ActionResult performAction(GameClass actor, GameClass target, ActionType actionType, GameActions actions) {
        return switch (actionType) {
            case ATTACK -> {
                int damage = actions.useAttack(actor, target);
                yield new ActionResult(actor.getCharacterName(), target.getCharacterName(), actions,damage, actionType,actor.getSpecialTypeName());
            }
            case SPECIAL -> {
                int amount = actions.useSpecial(actor,target);
                yield new ActionResult(actor.getCharacterName(),target.getCharacterName(), actions, amount, actionType, actor.getSpecialTypeName());
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
        return player.getAlive() ?  Winner.PLAYER : Winner.COMPUTER;
    }


}
