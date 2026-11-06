package game_classes;

public enum GameClassType {
    WARRIOR ("Warrior"),
    MAGE ("Mage"),
    ROGUE ("Rogue");


    private final static int UNIQUE_CLASSES = GameClassType.values().length;
    private final String name;

    GameClassType(String name) {
        this.name = name;
    }


    public static int getUniqueClasses() {
        return UNIQUE_CLASSES;
    }
    public String getClassName() {
        return name;
    }

}