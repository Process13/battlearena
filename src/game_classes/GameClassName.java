package game_classes;

public enum GameClassName {
    WARRIOR ("Warrior"),
    MAGE ("Mage"),
    ROUGE ("Rouge");

    private String name;

    GameClassName(String name) {
        this.name = name;
    }

    public String getGameClassName() {
        return name;
    }

}
