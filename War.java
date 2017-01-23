import java.util.ArrayList;
import cs1.Keyboard;

public class War extends CardGame {//war is a subclass of CardGame
    static int turns = 0;
    public static ArrayList<Player> winners = new ArrayList<Player>();
    
    public static WarUser user; // uses the user class
    public static WarAI AI;
    public static void main(String[] args){
	printRules();// prints the rules, defined in the printRules method
	setup();//sets up the game
	play();// controls the gameplay
	finish(); // finishes the game
	playAgain(); // allows the user to play again
	
	
    }
    //---------------------------Step 1 of main method--------------------------------------

    
    public static void printRules() { // prints rules
	System.out.println("Each player turns up a card at the same time and the player with the higher card takes both cards and puts them, face down, on the bottom of his stack. If the cards are the same rank, it is War. Each player turns up one card face down and one card face up. The player with the higher cards takes both piles. At the end of the game, the person left with the entire deck wins!");
    }

    //--------------------------------------------------------------------------------------


    
    //---------------------------Step 2 of main method--------------------------------------
    
    public static void setup(){ // sets up the game
	addCards(); // adds cards into the deck using the inherited method from CardGame
	shuffleDeck(); // inherited method from CardGame, randomizes positions of cards in deck
	makePlayers();// makes two players, one is a WarUser and the other is a WarAI
	deal();// overwritten method from CardGame
     }

    public static void makePlayers(){ // makes two players: the AI and the User
	System.out.println("This is a 2 player game");
        user  = new WarUser();
        AI = new WarAI();
	System.out.println("\n\n\nPrepare For Battle, My Good Sir!\n\n");
	return;
    }

    public static void deal(){ // deals out entire deck between the user and AI
	while (deck.size()>0){
	    user.hand.add(deck.remove(0));
	    AI.hand.add(deck.remove(0));
	}
    }

    //--------------------------------------------------------------------------------------    


    
    //---------------------------Step 3 of main method--------------------------------------    
    
    public static void play(){
	while ((((WarAI)AI).hand.size() < 52) && (((WarUser)user).hand.size() < 52)){ // runs while both players have cards
	    ((WarUser)user).move(); // lets the user move
	    ((WarAI) AI).move(); // lets the AI move
	    if (((WarAI) AI).table.get(0).value > ((WarUser)user).table.get(0).value) { // if the AI has the higher card
		System.out.println("Thou hast LOST this endeavor!"); // tells user they lost
		System.out.println("Your card was: " + ((WarUser)user).table.get(0)); // prints out user's card
		System.out.println("Your opponents card was: " + ((WarAI) AI).table.get(0)); // prints out AI's card
	        ((WarAI) AI).hand.add(((WarAI) AI).table.remove(0)); // adds the AI's card to its hand
	        ((WarAI) AI).hand.add(((WarUser)user).table.remove(0)); // adds the user's card to the AI's hand
	    }
	    else if (((WarUser)user).table.get(0).value > ((WarAI) AI).table.get(0).value) {// if the user has the higher card
		System.out.println("Thou hast emerged VICTORIOUS (this time)"); // tells user they won
		System.out.println("Your card was: " + ((WarUser)user).table.get(0));// prints out user's card
		System.out.println("Your opponents card was: " + ((WarAI) AI).table.get(0));// prints out AI's card
	        ((WarUser)user).hand.add(((WarAI) AI).table.remove(0));// adds the AI's card to the user's hand
	        ((WarUser)user).hand.add(((WarUser)user).table.remove(0));// adds the user's card to their hand
	    }
	    else if (((WarUser)user).table.get(0).value == ((WarAI) AI).table.get(0).value) { // if the cards are equal
		System.out.println("There be'ist a tie! THIS MEANS WAR!"); // announces that there is a war
		System.out.println("Your card was: " + ((WarUser)user).table.get(0)); // prints out user's card
		System.out.println("Your opponents card was: " + ((WarAI) AI).table.get(0)); // prints out AI's card
		boolean tie = true; 
		int tieNo = 1; // tie counter in case of multiple ties
		while (tie == true){ // while there is still a tie
		    if (((WarUser)user).hand.size()<2) {
			for (int x = 0; x < ((WarUser)user).hand.size() ; x++ ) {
			    ((WarAI) AI).hand.add(((WarUser) user).hand.remove(x));
			}
			for (int x = user.table.size()-1; x >0  ; x--) {// loop for printint out all cards played in the tie
			    ((WarAI) AI).hand.add(((WarAI) AI).table.remove(x));// adds card to AI's hand
			    ((WarAI) AI).hand.add(((WarUser)user).table.remove(x));// adds card to user's hand
				}
			return;
		    }
		    if (((WarAI)AI).hand.size()<2) {
			for (int x = 0; x < ((WarAI)AI).hand.size() ; x++ ) {
			    ((WarUser) user).hand.add(((WarAI) AI).hand.remove(x));
			}
			for (int x = user.table.size()-1; x >0  ; x--) {// loop for printint out all cards played in the tie
			    ((WarUser) user).hand.add(((WarAI) AI).table.remove(x));// adds card to user's hand
			    ((WarUser) user).hand.add(((WarUser)user).table.remove(x));// adds card to user's hand
				}
			return;
		    }
		   
		    ((WarAI) AI).tie();//AI's tie method
		    ((WarUser)user).tie();//User's tie method
		    if (((WarUser)user).table.get(2*tieNo).value == ((WarAI) AI).table.get(2*tieNo).value) { // if there is still a tie
			System.out.println("There is still a tie! The war continues!"); // announces there is still a tie
			System.out.println("Your card was: " + ((WarUser)user).table.get(2*tieNo)); // prints out the user's tie card
			System.out.println("Your opponents card was: " + ((WarAI) AI).table.get(2*tieNo)); // prints out the AI's tie card
			tieNo += 1;// increases tie counter
		    }
		    else if (((WarUser)user).table.get(2*tieNo).value > ((WarAI) AI).table.get(2*tieNo).value) { // if the User has a higher card
			System.out.println("Thou hast won the tie!"); // announces the User won the tie
			System.out.println("Your card was: " + ((WarUser)user).table.get(2*tieNo)); // prints out the winning card
			System.out.println("Your opponents card was: " + ((WarAI) AI).table.get(2*tieNo));// prints out losing card
			System.out.println("Your winnings are: ");
			for (int x = user.table.size() -1; x > 0  ; x--) { // loop for printing out all the cards played in the tie
			    System.out.println("" + ((WarAI) AI).table.get(x));
			    System.out.println("" + ((WarUser)user).table.get(x));
			    ((WarUser)user).hand.add(((WarAI) AI).table.remove(x));
			    ((WarUser)user).hand.add(((WarUser)user).table.remove(x));
				}
			
			//System.out.println("etstests");
			tie = false; // no longer a tie
		    }
		    else if (((WarUser)user).table.get(2*tieNo).value < ((WarAI) AI).table.get(2*tieNo).value) {//If AI has the higher card
			System.out.println("Thou hast Lost the tie!");// announces the AI won the tie
			System.out.println("Your card was: " + ((WarUser)user).table.get(2*tieNo)); // prints out losing card
			System.out.println("Your opponents card was: " + ((WarAI) AI).table.get(2*tieNo)); // prints out winning card
			System.out.println("Your opponent's winnings are: "); 
			for (int x = user.table.size()-1; x >0  ; x--) {// loop for printint out all cards played in the tie
			    System.out.println("" + ((WarAI) AI).table.get(x));
			    System.out.println("" + ((WarUser)user).table.get(x));
			    ((WarAI) AI).hand.add(((WarAI) AI).table.remove(x));// adds card to AI's hand
			    ((WarAI) AI).hand.add(((WarUser)user).table.remove(x));// adds card to user's hand
				}
			//System.out.println("etst");
			tie = false;// no longer a tie
		    }
		}
	    }
	    }
    }
    
    //--------------------------------------------------------------------------------------    



    //---------------------------Step 4 of main method--------------------------------------
    
    public static void finish(){// finishes the game
	if (((WarUser)user).hand.size() > ((WarAI)AI).hand.size()) { // if the user has all the cards
	    System.out.println("YOU have emerged victorious from this WORLD WAR!");
	    System.out.println("YOU ARE NOW KING OF THE LAND!");
	}
	else { // if AI has all the cards
	    System.out.println("Your OPPONENT has emerged victorious from this WORLD WAR!");
	    System.out.println("You have been BANISHED banished from the kingdom!");
	}
    }

    //--------------------------------------------------------------------------------------    
    


    //---------------------------Step 5 of main method--------------------------------------
    
    public static void playAgain(){
	System.out.println("Do You Want To Give It Another Go?   \n1. Yea, I'm Game \n2. Nah, Let's Try Something Else");
	int response = Keyboard.readInt();
	if (response == 1) {
	    winners = new ArrayList<Player>();
	    main(null);
	} else if (response == 2 ){
	    Woo.main(null);
	} else {
	    System.out.println("I don't understand, so I'll ask again");
	    playAgain();
	}
    }

    //--------------------------------------------------------------------------------------    

    

 
    //Prints instructions for the user
    //Used in WarUser
    public static void printInstructions(){
	System.out.println("These are the rules");
	System.out.println("To go to battle, type 'go'");
	System.out.println("If you want to forfeit the game, type 'forfeit'");
	System.out.println("If you forget how to play, type 'help'");
    }


}
	    
