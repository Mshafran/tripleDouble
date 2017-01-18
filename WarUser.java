import java.util.ArrayList;
import cs1.Keyboard;

public class WarUser extends Player{
    public ArrayList<Card> table = new ArrayList<Card>();
    public boolean Tie = false;

    
    public void putCard(){
	this.table.add(this.hand.remove(0));
    }

public void tie() {
    putCard();
    putCard();
}

    
    public void move() {
	System.out.println("This is how many cards YOU currently have: " + hand.size());
	System.out.println("This is how many cards YOUR OPPONENT currently has: " + (52 - hand.size()));
	boolean turn = true;
	while ((turn == true) && (hand.size() < 52) && (hand.size() > 0)) {
	    String input = Keyboard.readWord();
	    if (input.equals("go")) {
	        putCard();
		turn = false;
	    } else if (input.equals("forfeit")) {
		turn = false;
		Woo.main(null);
	    } else if (input.equals("help")) {
	        War.printInstructions();
	    } else {
		System.out.println("Sorry, but what?");
	    }
	}
    }

    /*public static void main(String[] args) {
	move();
	}*/
		

    
    
}
