package game_engine.actions;

//IN:

import game_classes.GameClass;
import game_engine.RandomRangeCalculator;

public class ComputerAction implements GameActions {
    private final RandomRangeCalculator damageCalculator = new RandomRangeCalculator();

    public int useAttack(GameClass computer, GameClass opponent) {
        int[] damageRange = computer.getDamageRange();
        int damageDealt = damageCalculator.getRandomNumberFromRange(damageRange[0],damageRange[1]);
        opponent.takeDamage(damageDealt, false);
        return damageDealt;
    }
    public int useSpecial(GameClass computer, GameClass opponent){
        return computer.specialAbility(opponent);
    }
}
