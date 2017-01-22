import java.util.ArrayList;
import cs1.Keyboard;

public class BlackJack extends CardGame{
    static int turns = 0;
    public static int totalBlackJacks = 0;
    public static ArrayList<Player> winners = new ArrayList<Player>();
    public static boolean allPassed;
    public static void main(String[] args){
	printRules();
	setup();
	//print();
	play();
	finish();
	//	System.out.println("You currently have $" + Woo.money + ".");
	playAgain();
	
	
     }
    public static void playAgain(){
	System.out.println("Do You Want To Give It Another Go?   \n1. Yea, I'm Game \n2. Nah, Let's Try Something Else");
	int response = Keyboard.readInt();
	if (response == 1) {
	    reset();
	    main(null);
	} else if (response == 2 ){
	    reset();
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
	allPassed = true;
    }
    public static void printAllBut1(){
	//System.out.println("DECK: "+deck);
	//System.out.println("PILE: "+pile);
	System.out.println("This be you "+ players[0].hand);
	for (int i =1; i < players.length; i++){
	    String knownHand = "[??";
	    for ( int cards = 1; cards < players[i].hand.size(); cards++){
		knownHand+=", "+ players[i].hand.get(cards);
	    }
	    knownHand += "]";
	    System.out.println("Player " + i +"    " + knownHand);
	}
    }
     public static void setup(){
	addCards();
	addCards();
	addCards();
	shuffleDeck();
	makePlayers();
	deal();
     }
    public static void makePlayers(){
	System.out.println("How many players?");
	int numPlayers = Keyboard.readInt();
	if (numPlayers > 40 || numPlayers < 1){
	    System.out.println("Try a reasonable amount");
	    makePlayers();
	    System.out.println("skrt");
	    return;
	} else {
	    players = new Player[numPlayers];
	    players[0] = new BlackjackUser();
	    for (int i = 1; i <players.length; i++){
		players[i] = new BlackJackAI(i);
	    }
	    System.out.println("\n\n\nTime to BlackJack\n\n");
	    return;
	}
    }
    
    //deals two cards to each player initally
    public static void deal(){
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
    public static void play(){
	while (deck.size()>0){
	    ((BlackjackUser)players[0]).move();
	    for (int i = 1; i < players.length; i++){
		((BlackJackAI)players[i]).move();
	    }
	    //print();
	    turns++;
	    if (turns > 10){
		System.out.println("10 turns have passed");
		//System.out.println(totalBlackJacks);
		//System.out.println(winners);
		return;
	    }
	    allPassed = true;
	    for (int i = 0; i < players.length; i ++){
		System.out.println(CardGame.players[i].passed);
		if (!CardGame.players[i].passed){
		    System.out.println("Player "+i+" hit"); 
		    allPassed = false;
		} else {
		    System.out.println("Player "+i+" passed");
		}
	    }
	    System.out.println("\n");
	    printAllBut1();
	    if (allPassed){
		System.out.println("Every Player passed this round, and thus the game is finished");
		return;
	    }
	   
	}

    }
    public static void printInstructions(){
	System.out.println("These are the rules");
	System.out.println("To hit, type 'hit'");
	System.out.println("To see the sum of your hand, type 'sum'");
	System.out.println("To end your turn, type 'end'");
	System.out.println("If you forget how to play, type 'help'");
    }
    public static void finish(){
	int biggestHand;
	if (((BlackjackUser)players[0]).sumHand() <= 21){
	    biggestHand =((BlackjackUser)players[0]).sumHand();
	} else {biggestHand = 0;}
	ArrayList<String> winners = new ArrayList<String>();
	for ( int i = 1; i < players.length; i ++){
	    if (((BlackJackAI)players[i]).sumHand() > biggestHand && ((BlackJackAI)players[i]).sumHand() <= 21){
		biggestHand = ((BlackJackAI)players[i]).sumHand();
	    }
	}
	if (((BlackjackUser)players[0]).sumHand() == biggestHand){
	    winners.add("You");
	}
	for ( int i = 1; i < players.length; i ++){
	    if (((BlackJackAI)players[i]).sumHand() == biggestHand){
		winners.add("Player " +((BlackJackAI)players[i]).player);
	    }
	}
	System.out.println("These are the winners " + winners);
	
    }

        public static void printRules() {
		System.out.println("In Blackjack, everyone is dealt out 2 cards, and the goal is to have your hand add up to 21. All number cards are worth their number value. Jacks, Queens, Kings are all worth 10. The Ace is worth 1. Every turn, you can either pass or hit. If you pass, that means you are satisfied with your hand, and it is as close to 21 as you think is possible. If you hit, you are dealt one more card. If your hand is over 21, you automatically lose. The people with the hand whose sum is the closest to 21 win.");
    }

}
