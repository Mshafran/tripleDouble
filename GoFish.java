import java.util.ArrayList;
import cs1.Keyboard;

public class GoFish extends CardGame{

    static int turns = 0;
    public static ArrayList<String> winners = new ArrayList<String>();
    
    //public static boolean allPassed;

    public static void main( String[] args){
	printRules();
	setup();
	System.out.println("finished setup");
	play();
	playAgain();
    }
    //basic rules
    public static void printRules() {
		System.out.println("Go Fish is a classic card game for kids. The concept behind this game is simple. The dealer will deal each player five or seven cards face down and place the remaining cards in the center of the table. The first player will choose one of their cards and a player, and ask them 'Do you have any X'. The player will then respond with either a yes, and give that player the card they asked for, or with a no and say 'go fish'. If the player responds with a 'go fish', you need to pick up one card from the pile. If the pick-up card matches the card you asked for, you can remove both cards from your hand and place them face down. The object of the game is to match all of the cards in your hand. The player who runs out of cards is the winner. If you asked for a certain card and the player gives it to you, you get to go again. You can also play where only full sets count, so instead of getting two of a kind you have to get four of a kind.\n\n");
    }

    //basic setup (makes a deck, shuffles it, makes the players, and deals out the 
    public static void setup(){
	addCards();
	shuffleDeck();
	makePlayers();
	deal();
    }

    //deals 7 cards to each player initally
    public static void deal(){
	int origDeckSize =  deck.size();
	int i = 0;
	while (deck.size() > origDeckSize - (players.length*7) ){
	    players[i].hand.add(deck.remove(0));
	    i++;
	    i %= players.length;
	}
    }

    //makes the players according to how many the user specifies
    public static void makePlayers(){
	System.out.println("How many players? (up to 6)");
	System.out.print("Pick a Number: ");
	int numPlayers = Keyboard.readInt();
	if (numPlayers > 6 || numPlayers < 2){
	    System.out.println("Try a reasonable amount");
	    makePlayers();
	    return;
	} else {
	    
	    players = new GoFishPlayer[numPlayers];
	    players[0] = new GoFishPlayer();
	    for (int i = 1; i <players.length; i++){
		players[i] = new GoFishAI(i);
	    }
	    System.out.println("\n\n\nWelcome to GoFish\n\n");
	    return;
	}
    }

    //starts the game
    public static void play(){
	boolean emptyHand = false;
	while (!emptyHand || deck.size() == 0){
	    if (totalDoubles() == 26){
		break;
	    }
	    //print();
	    //System.out.println("\n");
	    checkAllDoubles();
	    printAllDoubles();
	    //the user goes if he has cards
	    if (players[0].hand.size() > 0 ){
		((GoFishPlayer)players[0]).move();
		if (totalDoubles() == 26){
		    break;
		}
	    }
	    System.out.println("\nEND OF YOUR TURN\nBEGINNING OF AI TURNS\n");
	    //all the AIs turns
	    for (int i = 1; i < players.length; i++){
		((GoFishAI)players[i]).move();
		//System.out.println("Player " + i + ": " + players[i].hand);
		System.out.println("\n");
		printAllDoubles();
		System.out.println("\n");
		if (totalDoubles() == 26){
		    break;
		}
	    }
	    System.out.println("Everyone has moved once\n");
	    //When the deck is empty, so all remaining cards are in players hands
	    if (deck.size() == 0){
		emptyHand = true;
		for (Player player: players){
		    if (player.hand.size() != 0) {
			emptyHand = false;
			System.out.println("Player " + ((GoFishPlayer)player).player + " has cards remaining");
		    }
		}
	    }
	}
	System.out.println("All cards are paired up:\n\t\t The game is over\n\n\n");
	int mostDoubles =0;
	for (int i = 0; i <players.length; i++){
	    if (((GoFishPlayer)players[i]).doubles.size() > mostDoubles) {
		mostDoubles = ((GoFishPlayer)players[i]).doubles.size();
	    }
	}

	//prints out the winners
	String winnings = "";
	for (int i = 0; i <players.length; i++){
	    if (((GoFishPlayer)players[i]).doubles.size() == mostDoubles) {
		if (i ==0){
		    winners.add("User");
		} else {
		    winners.add("Player "+ ((GoFishPlayer)players[i]).player);
		}
	    }
	}
	System.out.println("These are the winners " + winners);
		    
    }

    //has all players check their hands for doubles
    public static void checkAllDoubles(){
	for (int i = 0; i < players.length; i++){
	    ((GoFishPlayer)players[i]).checkDoubles();
	}
	
    }

    //prints all players doubles
    public static void printAllDoubles(){
	for (int i = 0; i < players.length; i++){
	    ((GoFishPlayer)players[i]).printDoubles();
	}
	
    }

    public static int totalDoubles(){
	int rtn = 0;
	for (int i = 0; i < players.length; i++){
	    rtn += ((GoFishPlayer)players[i]).doubles.size();
	}
	return rtn;
    }

    //if the user wants to play again
    public static void playAgain(){
	System.out.println("Do You Want To Give It Another Go?   \n1. Yea, I'm Game \n2. Nah, Let's Try Something Else");
	System.out.print("Pick a Number: ");
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

    //resets game state to before setup
    public static void reset(){
	resetDecks();
	turns = 0;
	winners = new ArrayList<String>();
    }
}

