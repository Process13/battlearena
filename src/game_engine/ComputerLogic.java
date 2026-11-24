package game_engine;

import game_classes.*;

public class ComputerLogic {

    public ActionType chooseAction(GameClass computer, GameClass opponent) {
        return switch (computer.getType()) {
            case WARRIOR -> computerWarriorLogic(computer, opponent);
            case MAGE -> computerMageLogic(computer, opponent);
            case ROGUE -> computerRogueLogic(computer, opponent);
        };
    }

    public ActionType computerWarriorLogic(GameClass computer, GameClass opponent) {
      if (!computer.isSpecialUsed()) {
            return ActionType.SPECIAL;
        } else {
            return ActionType.ATTACK;
        }
    }

    public ActionType computerMageLogic(GameClass computer, GameClass opponent) {
        if (!computer.isSpecialUsed() && computer.getHealth() <= computer.getMaxHealthPoints() - Config.HEAL_AMOUNT.getNumber()) {
            return ActionType.SPECIAL;
        } else {
            return ActionType.ATTACK;
        }
    }

    public ActionType computerRogueLogic(GameClass computer, GameClass opponent) {
        if (!computer.isSpecialUsed() && opponent.getType() != GameClassType.WARRIOR) {
            return ActionType.SPECIAL;
        } else if (!computer.isSpecialUsed() && opponent.getDefending()) {
            return ActionType.SPECIAL;
        } else {
            return ActionType.ATTACK;
        }
    }
}
