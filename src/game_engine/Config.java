package game_engine;

public enum Config {
    ///ammount = ammount/DEFENDING_DAMAGE_REDUCTION
    DEFENDING_DAMAGE_REDUCTION (3),
    DEFENDING_TURNS_ACTIVE (2),
    HEAL_AMOUNT (25),
    ABILITY_COOLDOWN_ROUNDS (4);

    private final int number;

    Config(int number) {
        this.number = number;
    }

    public int getNumber() {
        return number;
    }
}
