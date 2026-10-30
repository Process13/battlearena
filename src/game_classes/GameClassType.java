package game_classes;

public enum GameClassType {
    WARRIOR ("Warrior"),
    MAGE ("Mage"),
    ROGUE ("Rogue");

    private String name;

    GameClassType(String name) {
        this.name = name;
    }

    public String getGameClassType() {
        return name;
    }

}
