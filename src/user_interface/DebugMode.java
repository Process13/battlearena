package user_interface;

import game_classes.GameClass;
import game_engine.GameClassFactory;
import game_engine.MatchEngine;

///DebugMode is used to make a debug file with the following information.
///* Win and loss statistics for each character combination
///* Average rounds per game
///
///

public class DebugMode{
    GameClassFactory factory;

    ///Runs the debugger, int represents the amount of rounds of computer vs computer games that should be run.
    public void runDebug(int tests) {
        for (int i = 1; i<=tests; i++) {
            factory.createRandomGameClass("Computer 1");
            factory.createRandomGameClass("Computer 2");
            GameClass computer1 = factory.getPlayer(1);
            GameClass computer2 = factory.getPlayer(2);
            //KÖR RUNDA
            factory.resetGameClasses();
        }
    }
}
