import java.util.ArrayList;
import cs1.Keyboard;

public class Crazy8Player extends Player{
    int player = 0; // has a player number
    Card topCard; // has a top card
    Card playCard; // has a card that can be played
    String cardIndex; // each card in hand has an index
    ArrayList<Card> matches;// ArrayList of cards with Matches
    

    public void move(){// move method
        checkCard(); // checks the top card of the pile
	//System.out.println("checkCard");
	System.out.println("The card is " + topCard); // prints out top card of pile
	askCard(); // asks the user to input a card to play
	//checkCard();
        //findMatches();
	System.out.println("findMatches");
        //chooseCard();
	System.out.println("chooseCard");
	playCard();
    }   
    
    public void checkCard(){ 
        topCard = Crazy8s.pile.get(0); // sets the topCard to equal the first card in the pile   
    }
    public void askCard(){
	System.out.println("This is your hand " + hand); // tells the user their hand
	System.out.print(  "                  ");
	for (int i = 0; i < hand.size(); i++){ // loop for printing out corresponding number to each card in the hand
	    if (hand.get(i).value == 10) {
		System.out.print("  " + i+ "  ");
	    } else {
		System.out.print(" " + i+ "  ");
	    }
	}
	System.out.println();
	if (findMatches().size() == 0) { // if there are no possibly playable cards
	    System.out.println("You have to draw a card\n1. Draw");
	    int draw = Keyboard.readInt();
	    if (draw == 1){
		drawCard();// draws a Card
		redoDeck(); // places all but first card in the pile into the deck
		askCard(); // checks for playable cards again
		return;
	    } else { // anything else
		System.out.println("I don't understand which you want to do");
		askCard();// asks again
		return;
	    }
	    
	}
	
	System.out.println("Which card would you like to play?"); 
	System.out.println("Type 'options' if you want to see all the possibilities \nType 'draw' if you want to pick up a card\nType 'forfeit' if you want to quit the game"); // instructions
	cardIndex = Keyboard.readString();
	Card playCardInit = playCard;
	if (cardIndex.equals("options")){ // if user wants to see all options
	    System.out.println("Here are all the cards you can play: " + findMatches()); // tells the user all cards they can play
	    askCard(); // asks again
	    return;
	} else if (cardIndex.equals("draw")){ // if user wants to draw
	    drawCard(); // draws card
	    redoDeck(); // redoes deck
	    askCard(); // asks again
	    return;
	}
	else if (cardIndex.equals("forfeit")){ // if user wants to forfeit
	    Crazy8s.reset(); // resets deck
	    Woo.main(null); // goes back to Woo
	    
	}
	try{
	    if (Integer.parseInt(cardIndex) < 0 || Integer.parseInt(cardIndex) >= hand.size()){// if number is too large or too small
		System.out.println("You cannot choose that number!!!");
	    }
	}
	catch(NumberFormatException e){ // if it is not an acceptable answer
	    System.out.println("You need to choose a number!!");
	    askCard();// asks again
	    return;
	}
	for (int i = 0; i < hand.size(); i++){ // loop for checking if card is playable and then playing it
	    if (cardIndex.equals("" + i)){ // if card is in range
	        if (findMatches().indexOf(hand.get(i)) != -1){ // if card is acceptable
		    playCard = hand.get(i); // plays card
		    return;
		} else {
		    System.out.println("Not a valid card choice. Please try again");
		}
	    }
	}
	//if (playCard.equals(null) || playCard.equals(playCardInit)){
	askCard();
	//}
    }
    
    public ArrayList<Card> findMatches(){ // looks for matches
        matches= new ArrayList<Card>(); // resets the arraylist of matches
        for (int i = 0; i < hand.size(); i++){ // checks for matching cards
            if ((hand.get(i).suit == topCard.suit) || (hand.get(i).value == topCard.value) || (hand.get(i).value == 8)) { // if the card is an 8, of the same suit, or of the same value
                matches.add(hand.get(i));   // adds it to the arraylist of matches
            }
        }
	//System.out.println(player +" matches: " + matches);
        return matches;
    }

    public void drawCard(){// draws a card
	hand.add(Crazy8s.deck.remove(0));
    }
    public void playCard(){ // plays card
	Crazy8s.pile.add(0,hand.remove(hand.indexOf(playCard))); // moves from hand to pile
    }
    public void redoDeck(){ // redoes deck
	for (int i = Crazy8s.pile.size()-1; i > 0; i--){ // moves all but first card from pile to deck
	    Crazy8s.deck.add(Crazy8s.pile.remove(i));
	}
    }
}

