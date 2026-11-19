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

import javax.swing.*;


public class MatchEngine {
    private final GameClass player;
    private final GameClass opponent;
    private final PlayerAction playerAction;
    private final ComputerAction computerAction;
    private final BattleGraphics graphics;

    private int rounds;


    public MatchEngine(GameClass player, GameClass opponent, PlayerAction playerAction, ComputerAction computerAction, BattleGraphics graphics) {
        this.player = player;
        this.opponent = opponent;
        this.playerAction = playerAction;
        this.computerAction = computerAction;
        this.graphics = graphics;

    }

    public Winner playMatch() {
        graphics.showStatus(player, opponent);
        while(!matchOver()) {
            rounds++;
            graphics.showRound(rounds);

            takeTurn(player,opponent,playerAction);
            if(!matchOver()) {
              takeTurn(opponent,player,computerAction);
            }
        }
        Winner winner = getWinner();
        graphics.showWinner(winner, player.getCharacterName());
        return winner;
    }
    private void takeTurn(GameClass actor, GameClass target, GameActions actions) {
            ActionType action = actions.chooseAction(actor,target);
            ActionResult result = performAction(actor, target,action, actions);

            graphics.showActionResult(result);
            graphics.showStatus(player, opponent);

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
        return player.getAlive() ?  Winner.PLAYER : Winner.COMPUTER;
    }


}
