package user_interface;

//Contains methods for printing out battles to the console

import game_classes.ActionType;
import game_classes.GameClass;
import game_classes.Winner;
import game_engine.actions.ActionResult;
import server_client.Client;
import java.io.IOException;
import java.util.List;

public class BattleGraphics {

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
    public String showActionResult(ActionResult result) {
        return switch (result.getActionType()) {
            case ATTACK -> result.getActorName() + " attacked " + result.getTargetName() + " for " + result.getAmount() + " damage!";
            case SPECIAL -> result.getActorName() + " used their special ability " + result.getSpecialType().getName() + " (" + result.getAmount() + ")";
        };
    }

    public void showActionChoices(GameClass player, boolean specialAvailable) {
        clearTerminal();
        System.out.println("\nChoose an action:");
        System.out.println("1. " + ActionType.ATTACK.getDisplayName());
        if (specialAvailable) {
            System.out.println("2. " + ActionType.SPECIAL.getDisplayName() + " (" + player.getSpecialType().getName() + ") ");
        }
    }

    //Skapar en healthbar likt #######-------
    public String hpBar(int hp, int maxHp) {
        int BAR_LENGTH = 20;
        if (maxHp <= 0) {
            return "[" + "-".repeat(BAR_LENGTH) + "]";
        }
        int filled = (int) Math.round((double) hp / maxHp * BAR_LENGTH);
        filled = Math.clamp(filled, 0, BAR_LENGTH);
        return "[" + "#".repeat(filled) + "-".repeat(BAR_LENGTH - filled) + "]";
    }

    //printar ut healthbar
    public void printHp(GameClass character) {
        System.out.println(character.getCharacterName() + " "
                + hpBar(character.getHealth(), character.getMaxHealthPoints()) + character.getHealth() + "/" + character.getMaxHealthPoints() + "HP" + " (" + character.getType().getClassName() + ")");
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
    public String formatWinner(Winner winner, String playerName, String playerClassType, String opponentClassType) {
        if(winner == Winner.PLAYER1) {
            return "Player " + playerName + " (" + playerClassType + ") won against the computer (" + opponentClassType + ")" ;
        }
        return "The computer" + " (" + opponentClassType + ") won against the player " + playerName + " (" + playerClassType + ")";


    }
    public void showWinner(Winner winner, String playerName, String playerClassType, String opponentClassType) {
        System.out.println(formatWinner(winner, playerName, playerClassType, opponentClassType));
    }
    public void showHistory(Client client) throws IOException {
        List<String> history = client.receiveHistory();

        System.out.println("\n--- Match History ---");
        if(history.isEmpty()) {
            System.out.println("No history found!");
            return;
        }
        for (int i =  0; i < history.size(); i++) {
            System.out.println((i + 1) + ". " + history.get(i));
        }
        System.out.println("--------------------------------\n");
    }

    public void clearTerminal() {
        System.out.println("\n".repeat(80));
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
