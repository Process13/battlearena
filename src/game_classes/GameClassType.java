package game_classes;

public enum GameClassType {
    WARRIOR ("Warrior", 150),
    MAGE ("Mage", 80),
    ROGUE ("Rogue", 110);


    private final static int UNIQUE_CLASSES = GameClassType.values().length;
    private final String name;
    private final int maxHp;

    GameClassType(String name, int maxHp) {
        this.name = name;
        this.maxHp = maxHp;
    }


    public static int getUniqueClasses() {
        return UNIQUE_CLASSES;
    }
    public String getClassName() {
        return name;
    }
    public int getClassMaxHp() {
        return maxHp;
    }

}