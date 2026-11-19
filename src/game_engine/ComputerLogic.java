package game_engine;

import game_classes.ActionType;
import game_classes.GameClass;
import game_classes.GameClassType;
import game_engine.actions.ComputerAction;

public class ComputerLogic {
    //private ComputerAction computerAction = new ComputerAction();

/*
public ActionType chooseAction(GameClass computer, GameClass opponent){

        return switch (computer.getType()) {
            case WARRIOR -> computerWarriorLogic(computer, opponent) ;
            case MAGE -> computerMageLogic(computer, opponent) ;
            case ROGUE -> computerRogueLogic(computer, opponent) ;
        };


    }*/

    public ActionType computerWarriorLogic(GameClass computer, GameClass opponent){
        /*if (!computer.isSpecialUsed()){
            computerAction.useSpecial(computer, opponent);
        } else {
            computerAction.useAttack(computer, opponent);
        }*/return computer.isSpecialUsed() ? ActionType.ATTACK:  ActionType.SPECIAL;
    }

    public ActionType computerMageLogic(GameClass computer, GameClass opponent) {
        /*if (!computer.isSpecialUsed() && computer.getHealth()<=computer.getMaxHealthPoints()-25) {
            computerAction.useSpecial(computer, opponent);
        } else {
            computerAction.useAttack(computer, opponent);
        }*/
        boolean canHeal = computer.getHealth() <= computer.getMaxHealthPoints() - 25;
        return !computer.isSpecialUsed() && canHeal
                ? ActionType.ATTACK:  ActionType.SPECIAL;
    }

    public ActionType computerRogueLogic(GameClass computer, GameClass opponent) {
        /*if (!computer.isSpecialUsed() && opponent.getType() != GameClassType.WARRIOR){
            computerAction.useSpecial(computer, opponent);
        } else if (!computer.isSpecialUsed() && opponent.getDefending()) {
            computerAction.useSpecial(computer, opponent);
        } else {
            computerAction.useAttack(computer, opponent);
        }*/
        boolean opportunity = opponent.getType() != GameClassType.WARRIOR || opponent.getDefending();
        return !computer.isSpecialUsed() && opportunity ? ActionType.ATTACK:  ActionType.SPECIAL;
    }
}
