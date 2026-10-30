package game_classes;

public enum SpecialTypeName {
    DEFEND ("defend"),
    HEAL ("heal"),
    BACKSTAB ("backstab");

    private String name;
    SpecialTypeName(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }

}
