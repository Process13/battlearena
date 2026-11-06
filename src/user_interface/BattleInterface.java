package user_interface;

//Starts from StartMenu
//IN: playerName, playerClass

//Takes input from user and sends it to BattleGraphics

import game_classes.GameClassType;

public class BattleInterface {
    private final String playerName;
    private final GameClassType playerClass;

    public BattleInterface(String playerName, GameClassType playerClass) {
        this.playerName = playerName;
        this.playerClass = playerClass;
    }
}
