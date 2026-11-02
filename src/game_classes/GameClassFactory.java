package game_classes;

import java.util.Objects;
import java.util.Random;

// Klass som skapar en karaktär åt användaren och slumpar fram en åt datorn
public class GameClassFactory {
    private final Random random;

    public GameClassFactory(Random random) {
        this.random =  Objects.requireNonNull(random, "random");
    }

    //Metod för att generera en karaktär åt användaren
    public GameClass createGameClass(GameClassType type, String name) {
        //switch för att välja typ av karaktär?
    }
    //Genererar en karaktär åt datorn
    public GameClass createRandomGameClass(String name) {
        //ta in klasstyperna
        //plocka ut och returnera en random klasstyp till datorn
    }

}
