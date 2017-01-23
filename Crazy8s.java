import java.util.ArrayList;
import cs1.Keyboard;

public class Crazy8s extends CardGame{
    //public static Crazy8Player[] players;// = new Player[4];
    static int turns = 0;
    public static ArrayList<Player> winners = new ArrayList<Player>();
    
    //public static boolean allPassed;

    public static void main( String[] args){
	printRules();// prints out rules
	setup();// sets up game
	firstCard();
	play();
	playAgain();
    }

    //---------------------------Step 1 of main method--------------------------------------

    public static void printRules() { //prints out rules
	System.out.println("Eight cards are dealt to each player. The remaining cards of the deck are placed face down at the center of the table. The top card is then turned face up to start the game.Players discard by matching rank or suit with the top card of the discard pile, starting with the player left of the dealer. If a player is unable to match the rank or suit of the top card of the discard pile and does not have an eight, they draw cards from the stockpile until getting a playable card. When a player plays an eight, they must declare the suit that the next player is to play; that player must then follow the named suit or play another eight.The game ends as soon as one player has emptied their hand.\n\n");
    }

    //--------------------------------------------------------------------------------------


    
    //---------------------------Step 2 of main method--------------------------------------

    public static void setup(){
	addCards();// adds card to deck
	shuffleDeck(); // randomizes positions of cards in deck
	makePlayers(); // makes players
	deal(); // deals out the cards
    }

    
    public static void makePlayers(){ //makes players
	System.out.println("How many players?");
	System.out.print("Pick a Number: ");
	int numPlayers = Keyboard.readInt();// reads how many players the user wants
	if (numPlayers > 7 || numPlayers < 2){ // if there are too many or too few players
	    System.out.println("Try a reasonable amount");
	    makePlayers();// tries again
	    //System.out.println("skrt");
	    return;
	} else { // if number of players is within bounds
	    if (numPlayers > 5){ // if there are more than 5 players
		addCards(); // adds another deck
		shuffleDeck(); // shuffles deck again
	    }
	    players = new Crazy8Player[numPlayers]; // makes an array with Crazy8Players of size numPlayers
	    players[0] = new Crazy8Player(); // first player is the User
	    for (int i = 1; i <players.length; i++){ // all other players
		players[i] = new Crazy8AI(i); // AI's
	    }
	    System.out.println("\n\n\nWelcome to Crazy 8s\n\n");
	    return;
	}
    }

    //deals two cards to each player initally
    public static void deal(){ // deals cards to all players
	int origDeckSize =  deck.size(); // stores original deck size
	int i = 0;
	//while (players[players.length-1].hand.size()<2){
	while (deck.size() > origDeckSize - (players.length*8) ){ // deals out cards evenly
	//players[deck.size()%players.length].hand.add(deck.remove(0));
	    players[i].hand.add(deck.remove(0));
	    i++;
	    i %= players.length;
	}
    }

    //--------------------------------------------------------------------------------------


    
    //---------------------------Step 3 of main method--------------------------------------
    
    public static void firstCard(){ // puts down the first card into the pile
	pile.add(deck.remove(0));
    }

    //--------------------------------------------------------------------------------------


    
    //---------------------------Step 4 of main method--------------------------------------
   
    public static void play(){ // controls gameplay
	boolean emptyHand = false; 
	while (!emptyHand){ // as long as no one has an empty 
	    //print();
	    System.out.println("\n\n\n");
	    System.out.println("There are " + deck.size() + " cards in the deck.");
	    ((Crazy8Player)players[0]).move(); // the user moves
	    System.out.println("\n\n\n");
	    for (int i = 1; i < players.length; i++){ // goes through the array of AIs
		((Crazy8AI)players[i]).move(); // each AI moves
		//System.out.println("Player " + i + ": " + players[i].hand);
		//System.out.println("\n\n");
		//print();
		System.out.println("\n\n");
	    }
	    System.out.println("Everyone has moved once\n\n");

	    for (Player player: players){ // to check if any player has won
		if (player.hand.size() == 0) { // a player does not have any cards
		    emptyHand = true; // their hand is deemed empty
		    System.out.println("Player " + ((Crazy8Player)player).player + " has won"); // they are announced the winner
		}
	    }
	}
    }

    //--------------------------------------------------------------------------------------


    
    //---------------------------Step 5 of main method--------------------------------------

    public static void playAgain(){ // checks if user wants to play again
	System.out.println("Do You Want To Give It Another Go?   \n1. Yea, I'm Game \n2. Nah, Let's Try Something Else");
	System.out.print("Pick a Number: ");
	int response = Keyboard.readInt();
	if (response == 1) { // if user wants to play again
	    reset(); // resets
	    main(null); // goes back to main method
	} else if (response == 2 ){ // if user does not want to play again
	    reset(); // resets
	    Woo.main(null);// goes back to Woo
	} else { // anything else
	    System.out.println("I don't understand, so I'll ask again");
	    playAgain();// asks again
	}
    }
    public static void reset(){// reset method
	resetDecks();//resets deck
	turns = 0; // turns are now 0 again
	winners = new ArrayList<Player>();// no winners yet
    }

    //--------------------------------------------------------------------------------------

}
