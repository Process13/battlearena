package game_engine;

//Run a match between two inputted characters.
//Check getAlive() if true continue.
//Use BattleGraphics to print events
//Use BattleInterface to get inputs from user and computer
//Make sure special can't be used more than every three rounds
//Determine winner

import game_classes.GameClass;
import game_classes.Winner;
import game_engine.actions.ComputerAction;
import game_engine.actions.PlayerAction;
import user_interface.StartMenu;

public class MatchEngine {
    private final GameClass player;
    private final GameClass computer;
    private final PlayerAction playerAction;
    private final ComputerAction computerAction;
    private final StartMenu startMenu;

    public MatchEngine(GameClass player, GameClass computer, PlayerAction playerAction, ComputerAction computerAction, StartMenu startMenu) {
        this.player = player;
        this.computer = computer;
        this.playerAction = playerAction;
        this.computerAction = computerAction;
        this.startMenu = startMenu;
    }

    public boolean matchOver(){
        return !player.getAlive() || !computer.getAlive();
    }
    public Winner getWinner(){
        if(!matchOver()){
            throw new IllegalStateException("Match is not over yet.");
        }
        return player.getAlive() ?  Winner.PLAYER : Winner.COMPUTER;

    }
}
