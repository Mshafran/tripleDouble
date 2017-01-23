import java.util.ArrayList;
import cs1.Keyboard;

public class ChaseUser extends Player{
    public int lives;
    public boolean trade;
    public boolean discard;

    public ChaseUser(int liv) { // constructor
	this.lives = liv; // sets lives to liv
    }
    
    public void move() {
	System.out.println("This is how many lives YOU currently have: " + this.lives); // how many lives user has
	System.out.println("This is how many players are left (INCLUDING YOU): " + Chase.playersLeft() ); // how many players are left
	System.out.println("This is your card: " + this.hand.get(0) ); // user's card
	Chase.printInstructions(); // prints instructions
	boolean turn = true;
	while ((turn == true) && (lives > 0)) {
	    String input = Keyboard.readWord();
	    if (input.equals("trade")) { // if user wants to trade
	        this.trade = true; // sets trade to true
		turn = false;
	    }else if (input.equals("discard")) { // if user wants to discard
	        this.discard = true; // sets discard to true
		turn = false;
	    }else if (input.equals("pass")) { // if user wants to pass
		System.out.println("You have passed");
		turn = false;
	    }  else if (input.equals("forfeit")) { // if user wants to forfeit
		turn = false;
		Chase.reset(); // reset
		Woo.main(null); // go back to main
	    } else if (input.equals("help")) {
	        Chase.printRules();
	    } else {
		System.out.println("Sorry, but what?");
	    }
	}
    }

    public void setLives( int liv ) {
	this.lives = liv;
    }

    /*public static void main(String[] args) {
	move();
	}*/
		

    
    
}
