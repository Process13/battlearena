package game_classes;

public enum ActionType {
    ATTACK ("attack"),
    SPECIAL ("special ability");

    private final String displayName;
    ActionType(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
