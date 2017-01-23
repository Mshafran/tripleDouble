import java.util.ArrayList;
import cs1.Keyboard;

public class ChaseUser extends Player{
    public int lives;
    public boolean trade;
    public boolean discard;

    public ChaseUser(int liv) {
	this.lives = liv;
    }
    
    public void move() {
	System.out.println("This is how many lives YOU currently have: " + this.lives);
	System.out.println("This is how many players are left (INCLUDING YOU): " + Chase.playersLeft() );
	System.out.println("This is your card: " + this.hand.get(0) );
	Chase.printInstructions();
	boolean turn = true;
	while ((turn == true) && (lives > 0)) {
	    System.out.print("What will you do?: ");
	    String input = Keyboard.readWord();
	    if (input.equals("trade")) {
	        this.trade = true;
		turn = false;
	    }else if (input.equals("discard")) {
	        this.discard = true;
		turn = false;
	    }else if (input.equals("pass")) {
		System.out.println("You have passed");
		turn = false;
	    }  else if (input.equals("forfeit")) {
		turn = false;
		Woo.main(null);
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
