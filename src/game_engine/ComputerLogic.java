package game_engine;

import game_classes.ActionType;
import game_classes.GameClass;
import game_classes.GameClassType;
import game_engine.actions.ComputerAction;

public class ComputerLogic {
    private ComputerAction computerAction = new ComputerAction();
    private int logicReturnInt;


    public ActionType chooseAction(GameClass computer, GameClass opponent) {
        return switch (computer.getType()) {
            case WARRIOR -> computerWarriorLogic(computer, opponent);
            case MAGE -> computerMageLogic(computer, opponent);
            case ROGUE -> computerRogueLogic(computer, opponent);
        };
    }

    public ActionType computerWarriorLogic(GameClass computer, GameClass opponent) {
        if (!computer.isSpecialUsed()) {
            logicReturnInt = computerAction.useSpecial(computer, opponent);
            return ActionType.SPECIAL;
        } else {
            logicReturnInt = computerAction.useAttack(computer, opponent);
            return ActionType.ATTACK;
        }
    }

    public ActionType computerMageLogic(GameClass computer, GameClass opponent) {
        if (!computer.isSpecialUsed() && computer.getHealth() <= computer.getMaxHealthPoints() - 25) {
            logicReturnInt = computerAction.useSpecial(computer, opponent);
            return ActionType.SPECIAL;
        } else {
            logicReturnInt = computerAction.useAttack(computer, opponent);
            return ActionType.ATTACK;
        }
    }

    public ActionType computerRogueLogic(GameClass computer, GameClass opponent) {
        if (!computer.isSpecialUsed() && opponent.getType() != GameClassType.WARRIOR) {
            logicReturnInt = computerAction.useSpecial(computer, opponent);
            return ActionType.SPECIAL;
        } else if (!computer.isSpecialUsed() && opponent.getDefending()) {
            logicReturnInt = computerAction.useSpecial(computer, opponent);
            return ActionType.SPECIAL;
        } else {
            logicReturnInt = computerAction.useAttack(computer, opponent);
            return ActionType.ATTACK;
        }
    }

    public int getLogicReturnInt() {
        return logicReturnInt;
    }
}
