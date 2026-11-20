package user_interface;

import game_classes.GameClass;
import game_classes.GameClassType;
import game_classes.Winner;
import game_engine.GameClassFactory;
import game_engine.MatchEngine;

import java.util.logging.Logger;

/// DebugMode is used to make a debug file with the following information.
/// * Win and loss statistics for each character combination
/// * Average rounds per game
///
///

public class DebugMode {
    GameClassFactory factory;
    MatchEngine match;
    Winner winner;
    private GameClass computer1;
    private GameClass computer2;
    private static final Logger LOGGER = Logger.getLogger(DebugMode.class.getName());

    /// Runs the debugger, int represents the amount of rounds of computer vs computer games that should be run.
    public void runDebug(int tests) {
        int wWinM = 0, wWinR = 0, mWinW = 0, mWinR = 0, rWinW = 0, rWinM = 0;
        factory = new GameClassFactory();
        for (int i = 1; i <= tests; i++) {
            factory.createRandomGameClass("Computer 1");
            factory.createRandomGameClass("Computer 2");
            computer1 = factory.getPlayer(1);
            computer2 = factory.getPlayer(2);
            if (computer1.getType() != computer2.getType()) {
                match = new MatchEngine(computer1, computer2);
                winner = match.playMatch(true);
                GameClass won = null, lost = null;
                switch (winner) {
                    case PLAYER1 -> {
                        won = computer1;
                        lost = computer2;
                    }
                    case PLAYER2 -> {
                        won = computer2;
                        lost = computer1;
                    }
                }
                switch (won.getType()) {
                    case WARRIOR -> {
                        switch (lost.getType()) {
                            case MAGE -> wWinM++;
                            case ROGUE -> wWinR++;
                        }
                    }
                    case MAGE -> {
                        switch (lost.getType()) {
                            case WARRIOR -> mWinW++;
                            case ROGUE -> mWinR++;
                        }
                    }
                    case ROGUE -> {
                        switch (lost.getType()) {
                            case WARRIOR -> rWinW++;
                            case MAGE -> rWinM++;
                        }
                    }
                }
            } else {
                i--;
            }
            factory.resetGameClasses();
        }
        LOGGER.fine("Tests complete. " + tests + " tests where run.");

    }
}
