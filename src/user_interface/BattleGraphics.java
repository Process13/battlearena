package user_interface;

//Contains methods for printing out battles to the console

import game_classes.GameClass;
public class BattleGraphics {
    private final int BAR_LENGTH = 20;
    // Visar namn och klass på player och opponent
    public void showMatchStart(GameClass player, GameClass opponent) {
        System.out.println(player.getCharacterName() + " (" + player.getType().getClassName() + ")" +
                " VS " + opponent.getCharacterName() + " (" + opponent.getType().getClassName() + ")");
    }
    // Visar nummer för runda
    public void showRound(int round) {
        System.out.println("---- Round: " + round + "----");
    }
    // Visar resultat av handling
    public void showActionResult(){

    }
    //Skapar en healthbar likt #######-------
    public String hpBar(int hp, int maxhp) {
        int filled = (int) Math.round((double) hp / maxhp * BAR_LENGTH);
        return "[" + "#".repeat(filled) + "-".repeat(BAR_LENGTH - filled) + "]";
    }
    //printar ut healthbar
    public void printHp(GameClass character){
        System.out.println(character.getCharacterName() + " "
                + hpBar(character.getHealth(), character.getType().getClassMaxHp()) + character.getHealth() + "/" + character.getType().getClassMaxHp() + "HP");
    }
    // printar status (healthbars) för player och opponent
    public void showStatus(GameClass player, GameClass opponent) {
        System.out.println();
        printHp(player);
        printHp(opponent);
        System.out.println();

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
