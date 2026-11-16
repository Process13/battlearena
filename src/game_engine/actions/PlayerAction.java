package game_engine.actions;

//IN: PlayerName, GameClass
//RUN:

import game_classes.GameClass;
import game_engine.RandomRangeCalculator;

public class PlayerAction extends ComputerAction implements GameActions {
    private final RandomRangeCalculator damageCalculator = new RandomRangeCalculator();

    @Override
    public int useAttack(GameClass player, GameClass opponent) {
        int[] damageRange = player.getDamageRange();
        int damageDealt = damageCalculator.getRandomNumberFromRange(damageRange[0], damageRange[1]);
        opponent.setHealth(opponent.getHealth() - damageDealt);
        return damageDealt;
    }

    @Override
    public int useSpecial(GameClass player, GameClass opponent) {
        player.setSpecialUsed(true);
        return player.specialAbility(opponent);
    }
}
