package game_engine.actions;

//IN:

import game_classes.ActionType;
import game_classes.GameClass;
import game_engine.ComputerLogic;
import game_engine.RandomRangeCalculator;

public class ComputerAction implements GameActions {
    private final RandomRangeCalculator damageCalculator = new RandomRangeCalculator();
    private final  ComputerLogic classLogic = new  ComputerLogic();

    public int useAttack(GameClass computer, GameClass opponent) {
        int[] damageRange = computer.getDamageRange();
        int damageDealt = damageCalculator.getRandomNumberFromRange(damageRange[0],damageRange[1]);
        return opponent.takeDamage(damageDealt, false);

    }
    public int useSpecial(GameClass computer, GameClass opponent){
        computer.setSpecialUsed(true);
        return computer.specialAbility(opponent);
    }
    @Override
    public ActionType chooseAction(GameClass computer,  GameClass opponent) {
       return classLogic.chooseAction(computer,opponent);
    }

}
