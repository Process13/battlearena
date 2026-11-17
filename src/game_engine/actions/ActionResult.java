package game_engine.actions;

import game_classes.ActionType;
import game_classes.SpecialTypeName;

public class ActionResult {
    private final String actorName;
    private final String targetName;
    private final GameActions actions;
    private final int amount;
    private final ActionType actionType;
    private final SpecialTypeName specialType;


    public ActionResult(String actorName, String targetName,GameActions actions, int amount, ActionType actionType,  SpecialTypeName specialType) {
        this.actorName = actorName;
        this.targetName = targetName;
        this.actions = actions;
        this.amount = amount;
        this.actionType = actionType;
        this.specialType = specialType;
    }

    public String getActorName() {
        return actorName;
    }


    public GameActions getActions() {
        return actions;
    }

    public SpecialTypeName getSpecialType() {
        return specialType;
    }

    public int getAmount() {
        return amount;
    }

    public ActionType getActionType() {
        return  actionType;
    }

    public String getTargetName() {
        return targetName;
    }
}
