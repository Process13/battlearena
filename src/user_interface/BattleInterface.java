package user_interface;

//Starts from StartMenu
//IN: playerName, playerClass

//Takes input from user and sends it to BattleGraphics

import game_classes.GameClass;
import game_classes.GameClass;

public class BattleInterface {
    private final String playerName;
    private final GameClass playerClass;

    public BattleInterface(String playerName, GameClass playerClass) {
        this.playerName = playerName;
        this.playerClass = playerClass;
    }
}
