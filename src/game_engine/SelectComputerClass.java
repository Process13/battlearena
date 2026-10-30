package game_engine;

//IN: All game classes
//OUT: One randomly selected game class

import game_classes.GameClassName;
import game_classes.GameClasses;

public class SelectComputerClass {
    private GameClasses selectedComputerClass;

    public void setComputerClass(){
        //add this in GameClassName
        //  public final static byte length = GameClassName.values().length;
        byte amountOfClasses = GameClassName.getLength();

        int classChoice = RandomRangeCalculator.getRandomNumberFromRange(1, amountOfClasses);
        // classChoice is a number that specifies witch class was chosen.
    }

}
