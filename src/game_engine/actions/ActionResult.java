package game_engine.actions;

import game_classes.ActionType;

public class ActionResult {
    private final String actorName;
    private final GameActions actions;
    private final int amount;
    private final ActionType actionType;
    private final String description;

    public ActionResult(String actorName, GameActions actions, int amount, ActionType actionType, String description) {
        this.actorName = actorName;
        this.actions = actions;
        this.amount = amount;
        this.actionType = actionType;
        this.description = description;
    }

    public String getActorName() {
        return actorName;
    }

    public String getDescription() {
        return description;
    }

    public GameActions getActions() {
        return actions;
    }

    public int getAmount() {
        return amount;
    }

    public ActionType getActionType() {
        return  actionType;
    }
}
