package game_engine;

import game_classes.GameClasses;

public class CharacterStatus {
    public int getRemainingHP(GameClasses character){
        return remainingHp;
    }

    //if 0 -> character loose
        //battleFinished(character)
    public int getMaxHP(GameClasses character){
    return maxHp;
    }
    public float getPercentageHP(int maxHp, int remainingHp){
        return percentageHp;
    }
}
