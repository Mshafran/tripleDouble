import java.util.ArrayList;
public class WarAI extends Player{ // subclass of Player
    public ArrayList<Card> table = new ArrayList<Card>(); // has an ArrayList called table, which has all the cards it plays


    public void move() { // move method for AI
	putCard(); // puts a card on the table
	}
    
    public void putCard(){
	this.table.add(this.hand.remove(0));// moves a card from the hand to the deck
    }

    public void tie() {// in case of a tie
	if (hand.size() > 1) { // if can put down the cards
	    putCard();//puts down "mystery" card
	    putCard();// puts down card that is compared for the War
	}
	else { // if not enough cards
	    War.finish(); // end the game
	}
    }
}

		

    

