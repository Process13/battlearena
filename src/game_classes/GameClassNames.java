package game_classes;

public enum GameClassNames {
    WARRIOR ("Warrior"),
    MAGE ("Mage"),
    ROUGE ("Rouge");

    private String name;

    GameClassNames(String name) {
        this.name = name;
    }

    public String getGameClassName() {
        return name;
    }

}
