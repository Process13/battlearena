package game_engine.actions;

import game_classes.GameClass;

public interface GameActions {
    void useAttack(GameClass self, GameClass oponent);
    void useSpecial();
}
