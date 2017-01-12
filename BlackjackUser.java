import java.util.ArrayList;
import cs1.Keyboard;

public class BlackjackUser extends Player{
 
    public boolean myTurn = false;
    public String getHand() {
	String retStr = "";
	for (int x = 0; x < hand.size() ; x++) {
	    retStr += hand.get(x);
	}
	return retStr;
    }

    public void hitme() {
	BlackJack.pile.add(BlackJack.deck.get(0));
	hand.add(BlackJack.deck.remove(0));
	System.out.println("This is your new hand: " + getHand());
	}

    public void move() {
	int handsum = 0;
	boolean turn = true;
	while ((turn == true) || (handSum <= 21)) {
	    System.out.println("This is currently your hand: " + getHand());
	    System.out.println("To hit, type 'hit'");
	    System.out.println("To see the sum of your hand, type 'sum'");
	    System.out.println("To end your turn, type 'end'");
	    if (Keyboard.readWord().equals("hit")) {
		hitme();
		for (int i = 0; i < hand.size(); i++){
		    handSum += hand.get(i).value;
		}
	    }
	    if (Keyboard.readWord().equals("sum")) {
		for (int i = 0; i < hand.size(); i++){
		    handSum += hand.get(i).value;
		}
		System.out.println("" + handsum);
	    }
	    if (Keyboard.readWord().equals("end")) {
		turn = false;
	    }
	    }
	return;
    }

    public static void main(String[] args) {
	move();
    }
		

    
    
}
