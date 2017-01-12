import java.util.ArrayList;
import cs1.Keyboard;

public class CardGame {
    public static ArrayList<Card> deck = new ArrayList<Card>();
    public static Player[] players;// = new Player[4];
    public static ArrayList<Card> pile = new ArrayList<Card>();

    boolean finish = false;

    public static void main(String[] args){
	setup();
	print();
    }
    public static void setup(){
	addCards();
	shuffleDeck();
	makePlayers();
	deal();
    }
    public static void print(){
	System.out.println("DECK: "+deck);
	System.out.println("PILE: "+pile);
	for (int i = 0; i < players.length; i++){
	    System.out.println("Player " + i + players[i].hand);
	}
    }
    public static void addCards(){
	for (int val = 1; val <= 13; val++){
	    for (int suit = 0; suit < 4; suit++){
		deck.add(new Card(val,suit));
	    }
	}
    }
    public static void shuffleDeck(){
	int randomIndex;
	for( int i = deck.size()-1; i > 0; i-- ) {
            randomIndex = (int)( (i+1) * Math.random() );
            deck.set( i, deck.set( randomIndex, deck.get(i) ) );
        }
    }
    public static void makePlayers(){
	System.out.println("How many players?");
	players = new Player[Keyboard.readInt()];
	for (int i = 0; i <players.length; i++){
	    players[i] = new Player();
	}
    }
    public static void deal(){
	int i = 0;
	while (deck.size()>0){
	    players[i].hand.add(deck.remove(0));
	    i++;
	    i %= players.length;
	}
    }
}
