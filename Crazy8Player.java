import java.util.ArrayList;
import cs1.Keyboard;

public class Crazy8Player extends Player{
    int player = 0;
    Card topCard;
    Card playCard;
    String cardIndex;
    ArrayList<Card> matches;
    

    public void move(){
        checkCard();
	System.out.println("checkCard");
	System.out.println("The card is " + topCard);
	askCard();
	//checkCard();
        //findMatches();
	System.out.println("findMatches");
        //chooseCard();
	System.out.println("chooseCard");
	playCard();
    }   
    
    public void checkCard(){
        topCard = Crazy8s.pile.get(0);   
    }
    public void askCard(){
	System.out.println("This is your hand " + hand);
	System.out.print(  "                  ");
	for (int i = 0; i < hand.size(); i++){
	    if (hand.get(i).value == 10) {
		System.out.print("  " + i+ "  ");
	    } else {
		System.out.print(" " + i+ "  ");
	    }
	}
	System.out.println();
	if (findMatches().size() == 0) {
	    System.out.println("You have to draw a card\n1. Draw\n2. Challenge");
	    int draw = Keyboard.readInt();
	    if (draw == 1){
		drawCard();
		redoDeck();
		askCard();
		return;
	    } else if ( draw == 2 ){

	    } else {
		System.out.println("I don't understand which you want to do");
		askCard();
		return;
	    }
	    
	}
	
	System.out.println("Which card would you like to play?");
	System.out.println("Type 'options' if you want to see all the possibilities \nType 'draw' if you want to pick up a card");
	cardIndex = Keyboard.readString();
	Card playCardInit = playCard;
	if (cardIndex.equals("options")){
	    System.out.println("Here are all the cards you can play: " + findMatches());
	    askCard();
	    return;
	} else if (cardIndex.equals("draw")){
	    drawCard();
	    redoDeck();
	    askCard();
	    return;
	}
	try{
	    if (Integer.parseInt(cardIndex) < 0 || Integer.parseInt(cardIndex) >= hand.size()){
		System.out.println("You cannot choose that number!!!");
	    }
	}
	catch(NumberFormatException e){
	    System.out.println("You need to choose a number!!");
	    askCard();
	    return;
	}
	for (int i = 0; i < hand.size(); i++){
	    if (cardIndex.equals("" + i)){
	        if (findMatches().indexOf(hand.get(i)) != -1){
		    playCard = hand.get(i);
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
    
    public ArrayList<Card> findMatches(){
        matches= new ArrayList<Card>();
        for (int i = 0; i < hand.size(); i++){
            if ((hand.get(i).suit == topCard.suit) || (hand.get(i).value == topCard.value) || (hand.get(i).value == 8)) {
                matches.add(hand.get(i));   
            }
        }
	//System.out.println(player +" matches: " + matches);
        return matches;
    }

    public void drawCard(){
	hand.add(Crazy8s.deck.remove(0));
    }
    public void playCard(){
	Crazy8s.pile.add(0,hand.remove(hand.indexOf(playCard)));
    }
    public void redoDeck(){
	for (int i = Crazy8s.pile.size()-1; i > 0; i--){
	    Crazy8s.deck.add(Crazy8s.pile.remove(i));
	}
    }
}

