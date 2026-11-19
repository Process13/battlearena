package user_interface;

import game_classes.GameClass;
import game_classes.GameClassType;
import game_classes.Winner;
import game_engine.GameClassFactory;
import game_engine.MatchEngine;

import java.util.logging.Logger;

///DebugMode is used to make a debug file with the following information.
///* Win and loss statistics for each character combination
///* Average rounds per game
///
///

public class DebugMode {
    GameClassFactory factory;
    MatchEngine match;
    Winner winner;
    private GameClass computer1;
    private GameClass computer2;
    private static final Logger LOGGER =Logger.getLogger(DebugMode.class.getName());

    ///Runs the debugger, int represents the amount of rounds of computer vs computer games that should be run.
    public void runDebug(int tests) {
        int wWinM, wWinR, mWinW, mWinR, rWinW, rWinM;
        for (int i = 1; i<=tests; i++) {
            factory.createRandomGameClass("Computer 1");
            factory.createRandomGameClass("Computer 2");
            computer1 = factory.getPlayer(1);
            computer2 = factory.getPlayer(2);
            while (computer1.getType()!=computer2.getType()) {
                //KÖR RUNDA
            }
            winner = match.playMatch();
            if (computer1.getType() == GameClassType.WARRIOR && computer2.getType() == GameClassType.MAGE){

            }
            factory.resetGameClasses();
        }
        LOGGER.fine("Tests complete. " + tests + " tests where run.");

    }
}
