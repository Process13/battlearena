package game_classes;

public enum Winner {
    PLAYER1 ("player 1"),
    PLAYER2 ("player 2");

    private final String name;

    Winner(String name) {
        this.name = name;
    }
}
