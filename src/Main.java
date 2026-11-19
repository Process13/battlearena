import user_interface.DebugMode;

public class Main {
    static void main(String[] args) {
        //System.out.flush();
        //Testruns(1000) -> 1000x dator mot dator -> skriv i textfil sammanfattning
        //-> Warrior wins, Mage wins, Rogue wins, averages for different values.
        //NEW CLASS IN user_interface "DebugMode"
        DebugMode debug = new DebugMode();
        debug.runDebug(1000);   //Perhaps have a hidden menu option for starting debug mode
    }
}
