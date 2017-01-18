import java.util.ArrayList;
import cs1.Keyboard;

public class CardGame {
    public static ArrayList<Card> deck = new ArrayList<Card>();
    public static Player[] players;// = new Player[4];
    public static ArrayList<Card> pile = new ArrayList<Card>();

    boolean finish = false;

    public static void main(String[] args){
	setup();
	//deal();
	print();
	//play();
	//playAgain();
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
	System.out.println("This be you "+ players[0].hand);
	for (int i =1; i < players.length; i++){
	    System.out.println("Player " + i +"    " + players[i].hand);
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
	//setup for traversal fr right to left
	for( int i = deck.size()-1; i > 0; i-- ) {
	    //pick an index at random
            randomIndex = (int)( (i+1) * Math.random() );
	    //swap the values at position i and randomIndex
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
	//int size = deck.size();
	//for (int i = 0; i < size; i++){
	//players[i%4].hand.add(deck.remove(0));
	//}
	int i = 0;
	while (deck.size()>0){
	    //players[deck.size()%players.length].hand.add(deck.remove(0));
	    players[i].hand.add(deck.remove(0));
	    i++;
	    i %= players.length;
	}
    }
    /* public static void play(){
	while (deck.size()>0){
	    for (int i = 0; i < players.length; i++){
	        players[i].move();
	    }
	    print();
	}
    }
    */

    public static void playAgain(){
	System.out.println("Do You Want To Give It Another Go?   \n1. Yea, I'm Game \n2. Nah, Let's Try Something Else");
	int response = Keyboard.readInt();
	if (response == 1) {
	    reset();
	    main(null);
	} else if (response == 2 ){
	    Woo.main(null);
	} else {
	    System.out.println("I don't understand, so I'll ask again");
	    playAgain();
	}
    }
    public static void reset(){
	resetDecks();
    }
    public static void resetDecks(){
	deck = new ArrayList<Card>();
	pile = new ArrayList<Card>();
    }
}
