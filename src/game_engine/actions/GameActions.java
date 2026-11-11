package game_engine.actions;

import game_classes.GameClass;

public interface GameActions {
    void useAttack(GameClass self, GameClass opponent);
    void useSpecial(GameClass self, GameClass opponent);
}
