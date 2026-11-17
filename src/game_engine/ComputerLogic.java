package game_engine;

import game_classes.ActionType;
import game_classes.GameClass;
import game_classes.GameClassType;
import game_engine.actions.ComputerAction;

public class ComputerLogic {
    private ComputerAction computerAction = new ComputerAction();

   public ActionType chooseAction(GameClass computer, GameClass opponent){
        if(computer.isSpecialUsed()) {
            return ActionType.ATTACK;
        }
        return switch (computer.getType()) {
            case WARRIOR -> computerWarriorLogic(computer, opponent) ;
            case MAGE -> computerMageLogic(computer, opponent) ;
            case ROGUE -> computerRogueLogic(computer, opponent) ;
        };

    }

    public void computerWarriorLogic(GameClass computer, GameClass opponent){
        if (!computer.isSpecialUsed()){
            computerAction.useSpecial(computer, opponent);
        } else {
            computerAction.useAttack(computer, opponent);
        }
    }

    public void computerMageLogic(GameClass computer, GameClass opponent) {
        if (!computer.isSpecialUsed() && computer.getHealth()<=computer.getMaxHealthPoints()-25) {
            computerAction.useSpecial(computer, opponent);
        } else {
            computerAction.useAttack(computer, opponent);
        }
    }

    public void computerRogueLogic(GameClass computer, GameClass opponent) {
        if (!computer.isSpecialUsed() && opponent.getType() != GameClassType.WARRIOR){
            computerAction.useSpecial(computer, opponent);
        } else if (!computer.isSpecialUsed() && opponent.getDefending()) {
            computerAction.useSpecial(computer, opponent);
        } else {
            computerAction.useAttack(computer, opponent);
        }
    }
}
