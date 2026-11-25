package game_engine.actions;

//IN: PlayerName, GameClass
//RUN:

import game_classes.ActionType;
import game_classes.GameClass;
import game_engine.RandomRangeCalculator;
import user_interface.BattleGraphics;
import user_interface.BattleInterface;

public class PlayerAction extends ComputerAction implements GameActions {
    private final RandomRangeCalculator damageCalculator = new RandomRangeCalculator();
    private final BattleInterface input;
    private final BattleGraphics graphics;


    public PlayerAction(BattleInterface input, BattleGraphics graphics) {
        this.input = input;
        this.graphics = graphics;
    }
    @Override
    public int useAttack(GameClass player, GameClass opponent) {
        int[] damageRange = player.getDamageRange();
        int damageDealt = damageCalculator.getRandomNumberFromRange(damageRange[0], damageRange[1]);
        return opponent.takeDamage(damageDealt, false);
    }

    @Override
    public int useSpecial(GameClass player, GameClass opponent) {
        player.setSpecialUsed(true);
        return player.specialAbility(opponent);
    }
    @Override
    public ActionType chooseAction(GameClass player,  GameClass opponent) {
        boolean specialAvailable = !player.isSpecialUsed();

        graphics.showActionChoices(player,specialAvailable);

        int choice = input.readInt(1, specialAvailable ? 2 : 1);
        return choice == 1 ? ActionType.ATTACK : ActionType.SPECIAL;
    }
}
