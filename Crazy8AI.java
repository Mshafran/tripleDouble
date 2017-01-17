import java.util.ArrayList;

public class Crazy8AI extends Crazy8Player{
    Card topCard;    
    ArrayList<Card> matches;
        
    public static void play(){
        checkCard();
        findMatches();
        chooseCard();
    }   
    public static void checkCard(){
        topCard = Crazy8s.pile.get(0);   
    }   
    public static ArrayList<Card> findMatches(){
        matches= new Arraylist<Card>();
        for (int i = 0; i < hand.size(); i++){
            if ((hand.get(i).suit == topCard.suit) || (hand.get(i).value == topCard.value)) {
                matches.add(hand.get(i));   
            }
        }
        return matches;
    }
    public static Card chooseCard(){
        for (int i = 0; i < matches.size(); i++) {
            if (matches.size() > 1) {
                if (matches.get(i).value == 8) {
                    matches.remove(i);
                    i-=1;
                } else {
                    return matches.get(i);   
                }
            }
        }
        return matches.get((int)matches.size()*Math.random());
        
    }
}
