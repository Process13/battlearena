package game_classes;

public abstract class GameClasses implements GameClassAttributes {
    private final String CharacterName;

    public GameClasses(String CharacterName) {
        this.CharacterName = CharacterName;
    }

    public String getCharacterName() {
        return CharacterName;
    }
}
