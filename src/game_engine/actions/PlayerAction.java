package game_engine.actions;

//IN: PlayerName, GameClass
//RUN:

import game_classes.ActionType;
import game_classes.GameClass;
import game_classes.SpecialTypeName;
import game_engine.RandomRangeCalculator;
import user_interface.BattleInterface;

public class PlayerAction extends ComputerAction implements GameActions {
    private final RandomRangeCalculator damageCalculator = new RandomRangeCalculator();
    private final BattleInterface input;


    public PlayerAction(BattleInterface input) {
        this.input = input;
    }
    @Override
    public int useAttack(GameClass player, GameClass opponent) {
        int[] damageRange = player.getDamageRange();
        int damageDealt = damageCalculator.getRandomNumberFromRange(damageRange[0], damageRange[1]);
        opponent.setHealth(opponent.getHealth() - damageDealt);
        return damageDealt;
    }

    @Override
    public int useSpecial(GameClass player, GameClass opponent) {
        player.setSpecialUsed(true);
        return player.specialAbility(opponent);
    }
    @Override
    public ActionType chooseAction(GameClass player,  GameClass opponent) {
        ActionType[] actions = ActionType.values();

        String specialName = switch(player.getType()){
            case WARRIOR -> SpecialTypeName.DEFEND.getName();
            case MAGE -> SpecialTypeName.HEAL.getName();
            case ROGUE -> SpecialTypeName.BACKSTAB.getName();
        };

        System.out.println("Choose an action: ");
        for(int i = 0; i < actions.length; i++) {
            String label = actions[i].getDisplayName();
            if(actions[i] == ActionType.SPECIAL) {
                label += " (" +  specialName + ")";
            }
            System.out.println((i + 1) +  ". " + label);
        }
        boolean specialAvailable = !player.isSpecialUsed();
        if (specialAvailable) {
            player.getSpecialType();
        }
        int choice = input.readInt(1, specialAvailable ? 2 : 1);
        return choice == 1 ? ActionType.ATTACK : ActionType.SPECIAL;
    }
}
