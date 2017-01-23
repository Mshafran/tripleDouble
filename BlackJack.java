import java.util.ArrayList;
import cs1.Keyboard;

public class BlackJack extends CardGame{
    static int turns = 0;
    public static int totalBlackJacks = 0;
    public static ArrayList<Player> winners = new ArrayList<Player>();
    public static boolean allPassed;
    public static void main(String[] args){
	printRules(); // prints rules
	setup(); // sets up game
	//print();
	play(); // controls gameplay
	finish(); // finishes the game
	//	System.out.println("You currently have $" + Woo.money + ".");
	playAgain(); // play again?
	
	
     }


    //---------------------------Step 1 of main method--------------------------------------


        public static void printRules() {
		System.out.println("In Blackjack, everyone is dealt out 2 cards, and the goal is to have your hand add up to 21. All number cards are worth their number value. Jacks, Queens, Kings are all worth 10. The Ace is worth 1. Every turn, you can either pass or hit. If you pass, that means you are satisfied with your hand, and it is as close to 21 as you think is possible. If you hit, you are dealt one more card. If your hand is over 21, you automatically lose. The people with the hand whose sum is the closest to 21 win.");
    }

    //--------------------------------------------------------------------------------------


    
    //---------------------------Step 2 of main method--------------------------------------
    
    
    public static void setup(){
	 addCards(); // adds one deck
	 addCards();// adds one deck
	 addCards();// adds one deck
	 shuffleDeck(); // shuffles the 3 decks
	 makePlayers();// makes the players
	 deal();// deals out the cards
     }
    public static void makePlayers(){ // makes players
	System.out.println("How many players?");
	int numPlayers = Keyboard.readInt();
	if (numPlayers > 40 || numPlayers < 1){ // if not between 1 and 40 players
	    System.out.println("Try a reasonable amount");
	    makePlayers();
	    System.out.println("skrt");
	    return;
	} else { //if between 1 and 40 players
	    players = new Player[numPlayers];//array of players
	    players[0] = new BlackjackUser();// first player is the user
	    for (int i = 1; i <players.length; i++){// all others are AI
		players[i] = new BlackJackAI(i);
	    }
	    System.out.println("\n\n\nTime to BlackJack\n\n");
	    return;
	}
    }
    

    public static void deal(){    //deals two cards to each player initally
	int origDeckSize =  deck.size();
	int i = 0;
	//while (players[players.length-1].hand.size()<2){
	while (deck.size() > origDeckSize - (players.length*2) ){
	//players[deck.size()%players.length].hand.add(deck.remove(0));
	    players[i].hand.add(deck.remove(0));
	    i++;
	    i %= players.length;
	}
    }

    //--------------------------------------------------------------------------------------


    
    //---------------------------Step 3 of main method--------------------------------------
    
    public static void play(){// plays the game
	while (deck.size()>0){ // while the deck isnt empty
	    ((BlackjackUser)players[0]).move(); // user moves
	    for (int i = 1; i < players.length; i++){
		((BlackJackAI)players[i]).move(); // all AIs move
	    }
	    //print();
	    turns++; // turns are incremented
	    if (turns > 10){ // if more than 10 turns pass
		System.out.println("10 turns have passed");
		//System.out.println(totalBlackJacks);
		//System.out.println(winners);
		return;// game is over
	    }
	    allPassed = true;
	    for (int i = 0; i < players.length; i ++){ 
		System.out.println(CardGame.players[i].passed);
		if (!CardGame.players[i].passed){ // if a player didn't pass
		    System.out.println("Player "+i+" hit"); 
		    allPassed = false;
		} else {
		    System.out.println("Player "+i+" passed");
		}
	    }
	    System.out.println("\n");
	    printAllBut1(); // print everyone's known hand
	    if (allPassed){
		System.out.println("Every Player passed this round, and thus the game is finished");
		return;
	    }
	   
	}

    }

    public static void printAllBut1(){ // prints all the known cards of the opponents
	//System.out.println("DECK: "+deck);
	//System.out.println("PILE: "+pile);
	System.out.println("This be you "+ players[0].hand);//prints user's hand
	for (int i =1; i < players.length; i++){ // loop to print all but first cards of AIs
	    String knownHand = "[??";
	    for ( int cards = 1; cards < players[i].hand.size(); cards++){
		knownHand+=", "+ players[i].hand.get(cards);
	    }
	    knownHand += "]";
	    System.out.println("Player " + i +"    " + knownHand);
	}
    }

    //--------------------------------------------------------------------------------------


    
    //---------------------------Step 4 of main method--------------------------------------
        
    public static void finish(){// once the game is over
	int biggestHand; // the biggest hand
	if (((BlackjackUser)players[0]).sumHand() <= 21){ // if the user hasn't busted
	    biggestHand =((BlackjackUser)players[0]).sumHand(); // their hand is considered first
	} else {biggestHand = 0;} // if the user busted, then the biggest hand is still 0
	ArrayList<String> winners = new ArrayList<String>(); // resets winners
	for ( int i = 1; i < players.length; i ++){ // finds the biggest hand
	    if (((BlackJackAI)players[i]).sumHand() > biggestHand && ((BlackJackAI)players[i]).sumHand() <= 21){
		biggestHand = ((BlackJackAI)players[i]).sumHand();
	    }
	}
	if (((BlackjackUser)players[0]).sumHand() == biggestHand){ // if user has the biggest hand
	    winners.add("You"); // you are added to the winners list
	}
	for ( int i = 1; i < players.length; i ++){ // checks for AI's that won
	    if (((BlackJackAI)players[i]).sumHand() == biggestHand){
		winners.add("Player " +((BlackJackAI)players[i]).player);
	    }
	}
	System.out.println("These are the winners " + winners); // prints out winners
    }

    //--------------------------------------------------------------------------------------


    
    //---------------------------Step 5 of main method--------------------------------------

    public static void playAgain(){
	System.out.println("Do You Want To Give It Another Go?   \n1. Yea, I'm Game \n2. Nah, Let's Try Something Else");
	int response = Keyboard.readInt();
	if (response == 1) { // if user wants to play again
	    reset(); // resets game
	    main(null); // goes to main
	} else if (response == 2 ){ // if user does not want to play again
	    reset(); // resets
	    Woo.main(null); // goes back to Woo
	} else {
	    System.out.println("I don't understand, so I'll ask again");
	    playAgain();
	}
    }

    public static void reset(){ // reset decks
	resetDecks(); // empty deck
	turns = 0; // reset turns
	winners = new ArrayList<Player>(); // empties list of winners
	allPassed = true; // resets allPassed
    }

    //--------------------------------------------------------------------------------------


    public static void printInstructions(){ // prints instruction for user
	System.out.println("These are the rules");
	System.out.println("To hit, type 'hit'");
	System.out.println("To see the sum of your hand, type 'sum'");
	System.out.println("To end your turn, type 'pass'");
	System.out.println("If you forget how to play, type 'help'");
    }
    


}
