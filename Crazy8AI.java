import java.util.ArrayList;
import java.lang.Math;

public class Crazy8AI extends Crazy8Player{//subclass of Crazy8Player
    Card topCard;//topCard
    Card playCard;//playCard
    ArrayList<Card> matches;//matches

    public Crazy8AI(int i){ // overloaded constructor
	player = i;
    }
    
    public void move(){ // move method
        checkCard(); // checks to see the first card in the pile
	//System.out.println("checkCard");
        findMatches(); // finds matches
	//System.out.println("findMatches");
        chooseCard(); // chooses the card to play
	//System.out.println("chooseCard");
	playCard(); // plays the card
    }   
    public void checkCard(){ // checks first card in pile
        topCard = Crazy8s.pile.get(0);   
    }   
    public ArrayList<Card> findMatches(){
        matches= new ArrayList<Card>(); // resets arraylist of matching cards
        for (int i = 0; i < hand.size(); i++){
            if ((hand.get(i).suit == topCard.suit) || (hand.get(i).value == topCard.value)  || (hand.get(i).value == 8)) { // checks for cards that are an 8, have same suit, or same value as the topCard
                matches.add(hand.get(i));   
            }
        }
	//System.out.println(player +" matches: " + matches);
        return matches;
    }
    public Card chooseCard(){
	if (matches.size() == 0) {//if there are no matches
	    drawCard(); // draws a card
	    //System.out.println("drawCard");
	    redoDeck();//redoes deck
	    //System.out.println(hand + "\n\n");
	    findMatches();//checks for matches
	    return chooseCard(); // tries again
	}
        for (int i = 0; i < matches.size(); i++) { // traverses matches
            if (matches.size() > 1) { // if more than 1 card that can be played
                if (matches.get(i).value == 8) {//only plays an 8 if there is no other choice
                    matches.remove(i); // rmeoves the 8 from list of matches
                    i-=1; // goes back
                } else { // only 1 playable card
		    playCard = matches.get(i);// sets the PlayCard to that card
		    //System.out.println(playCard);
                    return playCard; // returns the card to be played   
                }
            }
        }      
        playCard = matches.get((int)(matches.size()*Math.random())); // chooses a random card to play
	//System.out.println(playCard);
	return playCard; // returns playCard      
    }
    public void drawCard(){// draws a card
	hand.add(Crazy8s.deck.remove(0)); // moves from deck to hand
    }
    public void playCard(){ // plays card
	System.out.println("Player " + player + " played a " + playCard);
	Crazy8s.pile.add(0,hand.remove(hand.indexOf(playCard)));// moves playCard from hand to pile
    }
    public void redoDeck(){ // redoes deck
	for (int i = Crazy8s.pile.size()-1; i > 0; i--){ // moves all but first cards from pile to deck
	    Crazy8s.deck.add(Crazy8s.pile.remove(i));
	}
    }
}
