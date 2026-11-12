package game_engine;

//IN:

import game_classes.GameClass;
import game_classes.Mage;
import game_classes.Rogue;
import game_classes.Warrior;
import game_engine.actions.ComputerAction;

public class ComputerLogic {
    private GameClassFactory gameClassFactory = new GameClassFactory();
    private GameClass opponent = new Warrior("opponent");
    private ComputerAction computerAction = new ComputerAction();

    public GameClass createComputerCharacter(){
        return gameClassFactory.createRandomGameClass("Computer");
    }

    public void computerWarrior(GameClass computer){
        if (!computer.isSpecialUsed()){
            computerAction.useSpecial(computer, opponent);
        } else {
            computerAction.useAttack(computer, opponent);
        }
    }
}
