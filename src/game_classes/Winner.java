package game_classes;

public enum Winner {
    PLAYER ("player"),
    COMPUTER("computer");

    private final String name;

    Winner(String name) {
        this.name = name;
    }
}
