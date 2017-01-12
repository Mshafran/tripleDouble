import java.util.ArrayList;
import cs1.Keyboard;

public class BlackjackUser extends Player{
    int handSum = 0;

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
	    handSum += hand.get(i).value;
	}
	return handSum;
    }
    public void hitme() {
	BlackJack.pile.add(BlackJack.deck.get(0));
	hand.add(BlackJack.deck.remove(0));
	System.out.println("This is your new hand: " + getHand());
    }

    public void move() {
	boolean turn = true;
	System.out.println("This is currently your hand: " + getHand());
	System.out.println("To hit, type 'hit'");
	System.out.println("To see the sum of your hand, type 'sum'");
	System.out.println("To end your turn, type 'end'");
	while ((turn == true) && (handSum <= 21)) {
	    String input = Keyboard.readWord();
	    if (input.equals("hit")) {
		hitme();
	    } else if (input.equals("sum")) {
	        sumHand();
		System.out.println("" + handSum);
	    } else if (input.equals("end")) {
		turn = false;
	    }
	}
	return;
    }

    /*public static void main(String[] args) {
	move();
	}*/
		

    
    
}
