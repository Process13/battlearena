package user_interface;

//Starts from StartMenu
//IN: playerName, playerClass

//Takes input from user and sends it to BattleGraphics

import game_classes.GameClassName;

public class BattleInterface {
    private final String playerName;
    private final GameClassName playerClass;

    public BattleInterface(String playerName, GameClassName playerClass) {
        this.playerName = playerName;
        this.playerClass = playerClass;
    }
}
