import java.util.ArrayList;
import cs1.Keyboard;

public class CardGame {
    public static ArrayList<Card> deck = new ArrayList<Card>(); // deck
    public static Player[] players;// = new Player[4];
    public static ArrayList<Card> pile = new ArrayList<Card>(); // pile

    boolean finish = false;

    public static void main(String[] args){
	setup();
	//deal();
	print();
	//play();
	//playAgain();
    }
    public static void setup(){
	addCards(); // adds a deck
	shuffleDeck(); // shuffles the deck
	makePlayers(); // makes players
	deal(); // divides cards evenly between all players
    }
    public static void print(){ // prints out all hands, decks, and piles
	System.out.println("DECK: "+deck); // prints deck
	System.out.println("PILE: "+pile); // prints pile
	System.out.println("This be you "+ players[0].hand); // prints user's hand
	for (int i =1; i < players.length; i++){ 
	    System.out.println("Player " + i +"    " + players[i].hand); // prints AI's hand
	}
    }
    public static void addCards(){ // adds a deck
	for (int val = 1; val <= 13; val++){ // one of each number
	    for (int suit = 0; suit < 4; suit++){ // for every suit
		deck.add(new Card(val,suit)); // adds one of each card to the deck
	    }
	}
    }
    public static void shuffleDeck(){ // randomizes the deck
	int randomIndex;
	//setup for traversal fr right to left
	for( int i = deck.size()-1; i > 0; i-- ) {
	    //pick an index at random
            randomIndex = (int)( (i+1) * Math.random() );
	    //swap the values at position i and randomIndex
            deck.set( i, deck.set( randomIndex, deck.get(i) ) );
        }
    }
    public static void makePlayers(){ // makes players
	System.out.println("How many players?");
	players = new Player[Keyboard.readInt()];
	for (int i = 0; i <players.length; i++){
	    players[i] = new Player(); // makes as many players as instructed
	}
    }
    public static void deal(){ // deals out as deck evenly between players
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
	if (response == 1) { // if user wants to play again, resets decks and goes back to main
	    reset();
	    main(null);
	} else if (response == 2 ){ // if user doesnt want to play again, resets deck and goes back to Woo
	    reset();
	    Woo.main(null);
	} else { // anything else
	    System.out.println("I don't understand, so I'll ask again");
	    playAgain();
	}
    }
    public static void reset(){ // resets deck
	resetDecks();
    }
    public static void resetDecks(){ //resets decks
	deck = new ArrayList<Card>(); // empties deck
	pile = new ArrayList<Card>(); // empties pile
    }
}
