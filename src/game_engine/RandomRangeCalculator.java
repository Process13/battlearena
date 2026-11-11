package game_engine;

//IN: Range of possible damage
//OUT: Random damage value from the range

import java.util.Random;

public class RandomRangeCalculator {
    private Random random = new Random();

    public int getRandomNumberFromRange(int minRange, int maxRange) {
        return random.nextInt(minRange, maxRange+1);
    }

}
