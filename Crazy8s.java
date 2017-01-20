import java.util.ArrayList;
import cs1.Keyboard;

public class Crazy8s extends CardGame{
    //public static Crazy8Player[] players;// = new Player[4];
    static int turns = 0;
    public static ArrayList<Player> winners = new ArrayList<Player>();
    
    //public static boolean allPassed;

    public static void main( String[] args){
	setup();
	System.out.println("finished setup");
	firstCard();
	play();
	playAgain();
    }
    public static void setup(){
	addCards();
	shuffleDeck();
	makePlayers();
	deal();
    }

    //deals two cards to each player initally
    public static void deal(){
	int origDeckSize =  deck.size();
	int i = 0;
	//while (players[players.length-1].hand.size()<2){
	while (deck.size() > origDeckSize - (players.length*8) ){
	//players[deck.size()%players.length].hand.add(deck.remove(0));
	    players[i].hand.add(deck.remove(0));
	    i++;
	    i %= players.length;
	}
    }
    
    public static void makePlayers(){
	System.out.println("How many players?");
	int numPlayers = Keyboard.readInt();
	if (numPlayers > 7 || numPlayers < 2){
	    System.out.println("Try a reasonable amount");
	    makePlayers();
	    System.out.println("skrt");
	    return;
	} else {
	    if (numPlayers > 5){
		addCards();
		shuffleDeck();
	    }
	    players = new Crazy8Player[numPlayers];
	    players[0] = new Crazy8Player();
	    for (int i = 1; i <players.length; i++){
		players[i] = new Crazy8AI(i);
	    }
	    System.out.println("\n\n\nWelcome to Crazy 8s\n\n");
	    return;
	}
    }
    public static void firstCard(){
	pile.add(deck.remove(0));
    }
    public static void play(){
	boolean emptyHand = false;
	while (!emptyHand){
	    print();
	    System.out.println("\n\n\n");
	    ((Crazy8Player)players[0]).move();
	    System.out.println("\n\n\n");
	    for (int i = 1; i < players.length; i++){
		((Crazy8AI)players[i]).move();
		//System.out.println("Player " + i + ": " + players[i].hand);
		System.out.println("\n\n");
		print();
		System.out.println("\n\n");
	    }
	    System.out.println("\n\nEveryone has moved once\n\n");

	    for (Player player: players){
		if (player.hand.size() == 0) {
		    emptyHand = true;
		    System.out.println("Player " + ((Crazy8Player)player).player + " has won");
		}
	    }
	}
    }
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
	turns = 0;
	winners = new ArrayList<Player>();
    }
}
