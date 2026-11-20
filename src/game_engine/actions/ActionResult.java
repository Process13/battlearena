package game_engine.actions;

import game_classes.ActionType;
import game_classes.SpecialTypeName;

public class ActionResult {
    private final String actorName;
    private final String targetName;
    private final int amount;
    private final ActionType actionType;
    private final SpecialTypeName specialType;



    public ActionResult(String actorName, String targetName,
                        int amount, ActionType actionType,  SpecialTypeName specialType) {
        this.actorName = actorName;
        this.targetName = targetName;
        this.amount = amount;
        this.actionType = actionType;
        this.specialType = specialType;

    }
    public static ActionResult attack (String actor, String target, int damage) {
        return new ActionResult(actor, target, damage, ActionType.ATTACK,null);
    }
    public static ActionResult special (String actor, String target, int damage, SpecialTypeName specialType) {
        return new ActionResult(actor, target, damage, ActionType.SPECIAL, specialType);
    }


    public String getActorName() {
        return actorName;
    }

    public int getAmount() {
        return amount;
    }

    public SpecialTypeName getSpecialType() {
        return specialType;
    }

    public ActionType getActionType() {
        return  actionType;
    }

    public String getTargetName() {
        return targetName;
    }
}
