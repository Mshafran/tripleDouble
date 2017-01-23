import java.util.ArrayList;
import cs1.Keyboard;

public class Chase extends CardGame {
    static int turns = 0;
    public static ArrayList<Player> winners = new ArrayList<Player>();
    public static ArrayList<ChaseAI> ais = new ArrayList<ChaseAI>();
    public static ChaseUser user;
    public static void main(String[] args){
	printRules();
	setup();
	//print();
	//System.out.println("setup");
	play();
	//System.out.println("play");
	finish();
	//	System.out.println("You currently have $" + Woo.money + ".");
	playAgain();
	
	
    }

    public static void playAgain(){
	System.out.println("Do You Want To Give It Another Go?   \n1. Yea, I'm Game \n2. Nah, Let's Try Something Else");
	int response = Keyboard.readInt();
	if (response == 1) {
	    turns = 0;
	    ais = new ArrayList<ChaseAI>();
	    winners = new ArrayList<Player>();
	    main(null);
	} else if (response == 2 ){
	    Woo.main(null);
	} else {
	    System.out.println("I don't understand, so I'll ask again");
	    playAgain();
	}
    }
     public static void setup(){
	addCards();
	shuffleDeck();
	makePlayers();
	deal();
     }
    public static void makePlayers(){
	System.out.println("How many players do you want? (Between 3 and 10, inclusive!)");
	int resp = Keyboard.readInt();
	if (resp < 3) {
	    System.out.println("Please choose at least 3 players");
	    makePlayers();
	}
	else if (resp > 10) {
	    System.out.println("Please choose at most 10 players");
	    makePlayers();
	}
	else {
	    System.out.println("How many lives do you want each player to have?");
	    int res = Keyboard.readInt();
	    if (res < 1) {
		System.out.println("You can't have less than 1 life!");
		makePlayers();
	    }
	    else {
	        user = new ChaseUser(res);
		for (int x = 0; x  < resp - 1; x++){
		    //System.out.println("asd;fja");
		    ais.add(new ChaseAI(x, res));
		}
	    }
	}
	System.out.println("\n\n\nThe Chase for the Ace Begins!\n\n");
	return;
    }

    
    //deals one cards to each player initally
    public static void deal(){
	user.hand.add(deck.remove(0));
	for (int x = 0; x < ais.size(); x++) {
	    (ais.get(x)).hand.add(deck.remove(0));
	}
    }
    
    public static void play(){
	boolean AllButOne = false;
	while (AllButOne == false) {
	    if (user.lives <= 0) {
		System.out.println("You have lost all your lives");
	    }		
	    if (user.lives > 0) {
		user.move();
		if ((user.trade == true) && ((ais.get(0)).hand.get(0).ChaseVal < 100)) {
			user.hand.add(ais.get(0).hand.remove(0));
			ais.get(0).hand.add(user.hand.remove(0));
			System.out.println("You have traded your " + ais.get(0).hand.get(0) + " for a " + user.hand.get(0));
		    }
		if ((user.trade == true) && (ais.get(0).hand.get(0).ChaseVal == 100)) {
		    System.out.println("You tried to trade but your opponent has an ACE! Tough luck");
		}
		if ( user.discard == true) {
		    System.out.println(" You have discarded your " + user.hand.get(0) + " and drew a " + deck.get(0));
		    deck.add(user.hand.remove(0));
		    user.hand.add(deck.remove(0));
		}
		user.trade = false;
		user.discard = false;
	    }
	    for (ChaseAI x: ais) {
		if (x.lives > 0) {
		    x.move();
		    if ((x.trade == true) && (x.player < ais.size() - 1) && ((ais.get((x.player)+1)).hand.get(0).ChaseVal < 100)) {
			x.hand.add(ais.get((x.player)+1).hand.remove(0));
			ais.get((x.player)+1).hand.add(x.hand.remove(0));
		    }
		    if ((x.trade == true) && (x.player == ais.size() - 1) && (user.hand.get(0).ChaseVal < 100)) {
			x.hand.add(user.hand.remove(0));
			user.hand.add(x.hand.remove(0));
		    }
		    if ( x.discard == true) {
		    deck.add(x.hand.remove(0));
		    x.hand.add(deck.remove(0));
		    }
		    x.trade = false;
		    x.discard = false;
		}
	    }
	    if (user.lives > 0) {System.out.println("Your card at the end of the round is " + user.hand.get(0));}
	    //System.out.println("asi.size() = " + ais.size());
	    for (int i = 0; i < ais.size(); i++){
		if (ais.get(i).lives > 0 ){
		    System.out.println("Player " + ais.get(i).player + "'s card is " +ais.get(i).hand.get(0));
		}
	    }
	    winners();
	    reDeal();
	    if (playersLeft() == 1) {
		AllButOne = true;
	    }
	}
    }

	

    public static void winners() {
	int maxCard = 0;
	int aiCard = 0;
	for (int x = 0; x < ais.size() ; x++) {
	    if (ais.get(x).lives > 0) {
		aiCard = ais.get(x).hand.get(0).ChaseVal;
		///System.out.println("AICARD = " + aiCard);
		if (aiCard > maxCard) {
		    maxCard = aiCard;
		    //System.out.println("maxCArd = "+ maxCard);
		}
	    }
	}
	//System.out.println(maxCard);
	if ((user.lives > 0) && (maxCard < user.hand.get(0).ChaseVal)) {
	    maxCard = user.hand.get(0).ChaseVal;
	}
	if (user.lives > 0) {
	    if (user.hand.get(0).ChaseVal < maxCard) {
		user.lives -= 1;
		System.out.println("You have lost this round");
	    }
	    else {System.out.println("You won this round with the " + user.hand.get(0));}
	}
	for (ChaseAI x: ais) {
	    if (x.lives > 0) {
		if (x.hand.get(0).ChaseVal < maxCard) {
		    x.lives -= 1;
		}
		else if (x.hand.get(0).ChaseVal == maxCard){
		    System.out.println("AI" + x.player + " won this round with the " + x.hand.get(0));
		}
	    }
	}

	System.out.println("\n\n\n\n\n\n\n");
    }

    public static void reDeal() {
	deck.add(user.hand.remove(0));
	for (int x = 0; x < ais.size(); x++) {
	    deck.add((ais.get(x)).hand.remove(0));
	}
	shuffleDeck();
	deal();
    }

    public static int playersLeft() {
	int players = 0;
	if (user.lives > 0) {
	    players += 1;
	}
	for (int x = 0; x < ais.size() ; x++) {
	    if (ais.get(x).lives > 0) {
		players += 1;
	    }
	}
	return players;
    }
	
    public static void printRules() {
		System.out.println("Chase the Ace is played with a deck of 52 playing cards and at least 3 people. To begin, the dealer will deal each player one card and one card only. The cards must remain face down, however the players can look at the cards. The object of the game is to not have the lowest card. Once the deal has finished, players will then look at their cards and choose whether they will pass their card to the player on their left or hold onto the card. The ranks go from 1 - A, which means if youre holding a card with the face value of 10 or higher, you will want to hold on to it. If youre holding a low ranked card, you would want to switch with the person to the left of you. The person to the left of the dealer starts and can either switch with the person to left of them or they can discard their card and draw a new one.  If a player has an Ace, that player gets to hold on to their card regardless of what the other players want to do. The people with the highest card at the end of the round win. In this game, you usually give the player x number of lives, since it is a very fast game to play and when someone loses all their lives, they are out");
    }
	

    public static void printInstructions(){
	System.out.println("To swap cards with the next player, type 'trade'");
	System.out.println("To discard your card, type 'discard'");
	System.out.println("To pass, type 'pass'");
	System.out.println("If you want to forfeit the game, type 'forfeit'");
	System.out.println("If you forget how to play, type 'help'");
    }
    public static void finish(){
	if (user.lives > 0) {
	    System.out.println("You have WON this race to Chase The Ace");
	}
	else { System.out.println("You have LOST the race to Chase The Ace");
	}
	for (ChaseAI x: ais) {
	    if (x.lives > 0) {
		System.out.println("AI" + x.player + " has won!");
	    }
	}
    }
}
	    
