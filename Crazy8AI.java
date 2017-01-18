import java.util.ArrayList;
import java.lang.Math;

public class Crazy8AI extends Crazy8Player{
    Card topCard;
    Card playCard;
    ArrayList<Card> matches;

    public Crazy8AI(int i){
	player = i;
    }
    
    public void move(){
        checkCard();
	System.out.println("checkCard");
        findMatches();
	System.out.println("findMatches");
        chooseCard();
	System.out.println("chooseCard");
	playCard();
    }   
    public void checkCard(){
        topCard = Crazy8s.pile.get(0);   
    }   
    public ArrayList<Card> findMatches(){
        matches= new ArrayList<Card>();
        for (int i = 0; i < hand.size(); i++){
            if ((hand.get(i).suit == topCard.suit) || (hand.get(i).value == topCard.value)) {
                matches.add(hand.get(i));   
            }
        }
	System.out.println(player +" matches: " + matches);
        return matches;
    }
    public Card chooseCard(){
	if (matches.size() == 0) {
	    drawCard();
	    System.out.println("drawCard");
	    redoDeck();
	    System.out.println(hand + "\n\n");
	    findMatches();
	    return chooseCard();
	}
        for (int i = 0; i < matches.size(); i++) {
            if (matches.size() > 1) {
                if (matches.get(i).value == 8) {
                    matches.remove(i);
                    i-=1;
                } else {
		    playCard = matches.get(i);
		    System.out.println(playCard);
                    return playCard;   
                }
            }
        }      
        playCard = matches.get((int)(matches.size()*Math.random()));
	System.out.println(playCard);
	return playCard;      
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
