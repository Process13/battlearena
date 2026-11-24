package game_engine;

public enum Config {
    ///ammount = ammount/DEFENDING_DAMAGE_REDUCTION
    DEFENDING_DAMAGE_REDUCTION (3),
    ;

    private final int number;

    Config(int number) {
        this.number = number;
    }

    public int getNumber() {
        return number;
    }
}
