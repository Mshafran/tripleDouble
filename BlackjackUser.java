import java.util.ArrayList;
import cs1.Keyboard;

public class BlackjackUser extends Player{
    int handSum = 0;
    public boolean passed = true;

    public boolean myTurn = false;
    public String getHand() { // getHand method
	String retStr = ""; // string
	for (int x = 0; x < hand.size() ; x++) { //loops thru hand
	    retStr += hand.get(x) + " "; // adds the card to the string
	}
	return retStr;
    }
    public int sumHand(){ // method for summing cards in hand
	handSum =0; // initially 0
	for (int i = 0; i < hand.size(); i++){//loops through cards in hand
	    handSum += hand.get(i).BJval; // adds the BJVal of the card
	}
	return handSum;
    }
    public void hitme() { // hit method
	BlackJack.pile.add(BlackJack.deck.get(0)); // adds first card from deck to the pile
	hand.add(BlackJack.deck.remove(0)); // moves first card from the deck to the user's hand
	System.out.println("<<<<<This is your new hand: " + getHand() + ">>>>>"); // prints out the new hand
    }

    public void move() { // move method
	BlackJack.printInstructions(); // prints instructions from BlackJack
	boolean turn = true; // turn is true
	passed = false; // has not passed yet
	System.out.println("This is currently your hand: " + getHand());
	while ((turn == true) && (handSum <= 21)) {
	    String input = Keyboard.readWord();
	    if (input.equals("hit")) { // if user wants to hit
		hitme(); // hit
		turn = false; // turn is ended
		sumHand(); // sums the new hand
		System.out.println("This is your sum: " + handSum);// prints out the new sum
	    } else if (input.equals("sum")) { //if the user wants to see the sum of their hand
	        sumHand(); // sums the hand
		System.out.println("" + handSum); // prints out the sum
		System.out.println("This is your hand: " + getHand()); // prints out the hand again; still the user's turn
		//break;
	    } else if (input.equals("pass")) { // pass
		BlackJack.players[0].passed = true; // pass
		turn = false; // no longer the user's turn
	    } else if (input.equals("help")) { // if the user forgot the rules
		BlackJack.printRules(); // prints out the rules
	    } else {
		System.out.println("Sorry, but what?");
	    }
	}
	if (sumHand() > 21) { // if the user busted
	    System.out.println("You busted, let's hope everyone else does too"); // tells the user they busted
	}
	return;
    }

    /*public static void main(String[] args) {
	move();
	}*/
		

    
    
}
