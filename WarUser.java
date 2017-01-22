import java.util.ArrayList;
import cs1.Keyboard;

public class WarUser extends Player{
    public ArrayList<Card> table = new ArrayList<Card>();

    public void move() {// one turn for the user
	War.printInstructions(); // prints instructions from War
	System.out.println("This is how many cards YOU currently have: " + hand.size()); // prints out how many cards user has
	System.out.println("This is how many cards YOUR OPPONENT currently has: " + (52 - hand.size())); // prints out how many cards the AI has
	boolean turn = true;
	while ((turn == true) && (hand.size() < 52) && (hand.size() > 0)) {// runs while it is still the users turn AND the user has not won or lost yet
	    String input = Keyboard.readWord();// what the user typed in
	    if (input.equals("go")) { // if user typed in go
	        putCard(); // put card down
		turn = false; // end turn
	    } else if (input.equals("forfeit")) {//if user typed in forfeit
		turn = false; // end turn
		Woo.main(null); // return to game selection
	    } else if (input.equals("help")) { // if the user typed in help
	        War.printRules();// printRules method from War
	    } else { // anything else
		System.out.println("Sorry, but what?"); // asks again
	    }
	}
    }
    
    public void putCard(){ // puts down card
	this.table.add(this.hand.remove(0));// moves card from hand to table
    }

    public void tie() {// in case of a tie
	putCard();// puts down "mystery card"
	putCard();// puts down card to compare
    }
}
