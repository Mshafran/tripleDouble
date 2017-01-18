import java.util.ArrayList;
import cs1.Keyboard;

public class War extends CardGame {
    static int turns = 0;
    public static ArrayList<Player> winners = new ArrayList<Player>();
    
    public static WarUser user;
    public static WarAI AI;
    public static void main(String[] args){
	setup();
	//print();
	System.out.println("setup");
	play();
System.out.println("play");
	finish();
	//	System.out.println("You currently have $" + Woo.money + ".");
	playAgain();
	
	
    }
    public static void playAgain(){
	System.out.println("Do You Want To Give It Another Go?   \n1. Yea, I'm Game \n2. Nah, Let's Try Something Else");
	int response = Keyboard.readInt();
	if (response == 1) {
	    turns = 0;
	    winners = new ArrayList<Player>();
	    main(null);
	} else if (response == 2 ){
	    Woo.main(null);
	} else {
	    System.out.println("I don't understand, so I'll ask again");
	    playAgain();
	}
    }
     public static void setup(){
	addCards();
	shuffleDeck();
	makePlayers();
	deal();
     }
    public static void makePlayers(){
	System.out.println("This is a 2 player game");
        user  = new WarUser();
        AI = new WarAI();
	System.out.println("\n\n\nPrepare For Battle, My Good Sir!\n\n");
	return;
	}

    
    //deals two cards to each player initally
    public static void deal(){
        while (deck.size()>0){
	    user.hand.add(deck.remove(0));
	    AI.hand.add(deck.remove(0));
	}
    }
    public static void play(){
	printInstructions();
	while ((((WarAI)AI).hand.size() < 52) && (((WarUser)user).hand.size() < 52)){
	    ((WarUser)user).move();
	    ((WarAI) AI).move();
	    if (((WarAI) AI).table.get(0).value > ((WarUser)user).table.get(0).value) {
		System.out.println("Thou hast LOST this endeavor!");
		System.out.println("Your card was: " + ((WarUser)user).table.get(0));
		System.out.println("Your opponents card was: " + ((WarAI) AI).table.get(0));
	        ((WarAI) AI).hand.add(((WarAI) AI).table.remove(0));
	        ((WarAI) AI).hand.add(((WarUser)user).table.remove(0));
	    }
	    else if (((WarUser)user).table.get(0).value > ((WarAI) AI).table.get(0).value) {
		System.out.println("Thou hast emerged VICTORIOUS (this time)");
		System.out.println("Your card was: " + ((WarUser)user).table.get(0));
		System.out.println("Your opponents card was: " + ((WarAI) AI).table.get(0));
	        ((WarUser)user).hand.add(((WarAI) AI).table.remove(0));
	        ((WarUser)user).hand.add(((WarUser)user).table.remove(0));
	    }
	    else if (((WarUser)user).table.get(0).value == ((WarAI) AI).table.get(0).value) {
		System.out.println("There be'ist a tie! THIS MEANS WAR!");
		System.out.println("Your card was: " + ((WarUser)user).table.get(0));
		System.out.println("Your opponents card was: " + ((WarAI) AI).table.get(0));
		boolean tie = true;
		int tieNo = 1;
		while (tie == true) {
		    ((WarAI) AI).tie();
		    ((WarUser)user).tie();
		    if (((WarUser)user).table.get(2*tieNo).value == ((WarAI) AI).table.get(2*tieNo).value) {
				System.out.println("There is still a tie! The war continues!");
				System.out.println("Your card was: " + ((WarUser)user).table.get(2*tieNo));
				System.out.println("Your opponents card was: " + ((WarAI) AI).table.get(2*tieNo));
				tieNo += 1;
		    }
		    else if (((WarUser)user).table.get(2*tieNo).value > ((WarAI) AI).table.get(2*tieNo).value) {
				System.out.println("Thou hast won the tie!");
				System.out.println("Your card was: " + ((WarUser)user).table.get(2*tieNo));
				System.out.println("Your opponents card was: " + ((WarAI) AI).table.get(2*tieNo));
				System.out.println("Your winnings are: ");
				for (int x = user.table.size() -1; x > 0  ; x--) {
				    System.out.println("" + ((WarAI) AI).table.get(x));
				    System.out.println("" + ((WarUser)user).table.get(x));
				    ((WarUser)user).hand.add(((WarAI) AI).table.remove(x));
				    ((WarUser)user).hand.add(((WarUser)user).table.remove(x));
				}
				tie = false;
		    }
		    else if (((WarUser)user).table.get(2*tieNo).value < ((WarAI) AI).table.get(2*tieNo).value) {
				System.out.println("Thou hast Lost the tie!");
				System.out.println("Your card was: " + ((WarUser)user).table.get(2*tieNo));
				System.out.println("Your opponents card was: " + ((WarAI) AI).table.get(2*tieNo));
				System.out.println("Your opponent's winnings are: ");
				for (int x = user.table.size()-1; x >0  ; x--) {
				    System.out.println("" + ((WarAI) AI).table.get(x));
				    System.out.println("" + ((WarUser)user).table.get(x));
				    ((WarAI) AI).hand.add(((WarAI) AI).table.remove(x));
				    ((WarAI) AI).hand.add(((WarUser)user).table.remove(x));
				}
				System.out.println("etst");
				tie = false;
		    }
		}
	    }
	    }
    }


	   


    public static void printInstructions(){
	System.out.println("These are the rules");
	System.out.println("To go to battle, type 'go'");
	System.out.println("If you want to forfeit the game, type 'forfeit'");
	System.out.println("If you forget how to play, type 'help'");
    }
    public static void finish(){
	if (((WarUser)user).hand.size() == 52) {
	    System.out.println("YOU have emerged victorious from this WORLD WAR!");
	    System.out.println("YOU ARE NOW KING OF THE LAND!");
	}
	else {
	    System.out.println("Your OPPONENT has emerged victorious from this WORLD WAR!");
	    System.out.println("You have been BANISHED banished from the kingdom!");
	}
    }
}
	    
