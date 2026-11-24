package user_interface;

//Contains methods for printing out battles to the console

import game_classes.ActionType;
import game_classes.GameClass;
import game_classes.GameClassType;
import game_classes.Winner;
import game_engine.actions.ActionResult;

public class BattleGraphics {
    private final int BAR_LENGTH = 20;
    // Visar namn och klass på player och opponent
    public void showMatchStart(GameClass player, GameClass opponent) {
        System.out.println(player.getCharacterName() + " (" + player.getType().getClassName() + ")" +
                " VS " + opponent.getCharacterName() + " (" + opponent.getType().getClassName() + ")");
    }
    // Visar nummer för runda
    public void showRound(int round) {
        System.out.println("---- Round: " + round + " ----");
    }
    // Visar resultat av handling
    public void showActionResult(ActionResult result) {
    String message = switch (result.getActionType()) {
        case ATTACK -> result.getActorName() + " attacked " +  result.getTargetName() + " for " + result.getAmount() + " damage!";
        case SPECIAL -> result.getActorName() + " used their special ability " + result.getSpecialType().getName() + " (" + result.getAmount() + ")";
    };
        System.out.println(message);
    }
    public void showActionChoices(GameClass player, boolean specialAvailable) {
        System.out.println("Choose an action:");
        System.out.println("1. " + ActionType.ATTACK.getDisplayName());
        if (specialAvailable) {
            System.out.println("2. " + ActionType.SPECIAL.getDisplayName() + " (" + player.getSpecialType().getName() + ") ");
        }
    }
    //Skapar en healthbar likt #######-------
    public String hpBar(int hp, int maxHp) {
        if (maxHp <= 0) {
            return "[" + "-".repeat(BAR_LENGTH) + "]";
        }
        int filled = (int) Math.round((double) hp / maxHp * BAR_LENGTH);
        filled = Math.clamp(filled, 0, BAR_LENGTH);
        return "[" + "#".repeat(filled) + "-".repeat(BAR_LENGTH - filled) + "]";
    }
    //printar ut healthbar
    public void printHp(GameClass character){
        System.out.println(character.getCharacterName() + " "
                + hpBar(character.getHealth(), character.getMaxHealthPoints()) + character.getHealth() + "/" + character.getMaxHealthPoints() + "HP" + " (" + character.getType().getClassName() + ")" );
    }
    // printar status (healthbars) för player och opponent
    public void showStatus(GameClass player, GameClass opponent, String message1, String message2) {
        System.out.println();
        printHp(player);
        System.out.println();
        System.out.println(message1);
        System.out.println(message2);
        System.out.println();
        printHp(opponent);
        System.out.println();


    }
    public void showWinner(Winner winner, String playerName) {
        System.out.println("--------------------------");
        if (winner == Winner.PLAYER1) {
            System.out.println("Player " + playerName + " won!");
        } else {
            System.out.println("The Computer won!");
        }
    }

    /*
    Methods:
        showCurrentBattle()
        damageTaken()
        damageGiven()
        specialUsedOpponent()
        specialUsedSelf()
        battleFinished(Character)

        75% ############----
     */

}
