package game_engine.actions;

import game_classes.ActionType;
import game_classes.GameClass;

public interface GameActions {
    int useAttack(GameClass self, GameClass opponent);
    int useSpecial(GameClass self, GameClass opponent);
    ActionType chooseAction(GameClass self, GameClass opponent);
}
