package game_engine;

import game_classes.GameClass;

public class CharacterStatus {
    public int getRemainingHp(GameClass character){
        if (remainingHp <= 0){
            battleFinished(character);
            return 0;
        }
        return remainingHp;
    }

    //if 0 -> character loose
        //battleFinished(character)
    public int getMaxHp(GameClass character){
    return maxHp;
    }
    public float getPercentageHp(int maxHp, int remainingHp){
        return percentageHp;
    }
}
