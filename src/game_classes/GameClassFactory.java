package game_classes;

import java.util.Objects;
import java.util.Random;

// Klass som skapar en karaktär åt användaren och slumpar fram en åt datorn
public class GameClassFactory {


    //Metod för att generera en karaktär åt användaren
    public GameClass createGameClass(GameClassType type, String name) {
        //switch för att välja typ av karaktär
        return switch(type) {
            case WARRIOR -> new Warrior(name);
            case MAGE -> new Mage(name);
            case ROGUE -> new Rogue(name);
        };
    }
    //Genererar en karaktär åt datorn
    public GameClass createRandomGameClass(String name) {
        //ta in klasstyperna
        GameClassType[] types = GameClassType.values();
        //plocka ut och returnera en random klasstyp till datorn
        GameClassType type =  types[new Random().nextInt(types.length)];
        return createGameClass(type,name);
    }



}
