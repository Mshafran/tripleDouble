import java.util.ArrayList;
import cs1.Keyboard;

public class BlackjackUser extends Player{
    int handSum = 0;
    public boolean passed = true;

    public boolean myTurn = false;
    public String getHand() {
	String retStr = "";
	for (int x = 0; x < hand.size() ; x++) {
	    retStr += hand.get(x) + " ";
	}
	return retStr;
    }
    public int sumHand(){
	handSum =0;
	for (int i = 0; i < hand.size(); i++){
	    handSum += hand.get(i).BJval;
	}
	return handSum;
    }
    public void hitme() {
	BlackJack.pile.add(BlackJack.deck.get(0));
	hand.add(BlackJack.deck.remove(0));
	System.out.println("<<<<<This is your new hand: " + getHand() + ">>>>>");
    }

    public void move() {
	BlackJack.printInstructions();
	boolean turn = true;
	passed = false;
	System.out.println("This is currently your hand: " + getHand());
	while ((turn == true) && (handSum <= 21)) {
	    String input = Keyboard.readWord();
	    if (input.equals("hit")) {
		hitme();
		turn = false;
		sumHand();
		System.out.println("This is your sum: " + handSum);
	    } else if (input.equals("sum")) {
	        sumHand();
		System.out.println("" + handSum);
		System.out.println("This is your hand: " + getHand());
		//break;
	    } else if (input.equals("end")) {
		BlackJack.players[0].passed = true;
		turn = false;
	    } else if (input.equals("help")) {
		BlackJack.printRules();
	    } else {
		System.out.println("Sorry, but what?");
	    }
	}
	if (sumHand() > 21) {
	    System.out.println("You busted, let's hope everyone else does too");
	}
	return;
    }

    /*public static void main(String[] args) {
	move();
	}*/
		

    
    
}
