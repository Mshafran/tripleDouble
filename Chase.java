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

    //---------------------------Step 1 of main method--------------------------------------
    
    public static void printRules() {
		System.out.println("Chase the Ace is played with a deck of 52 playing cards and at least 3 people. To begin, the dealer will deal each player one card and one card only. The cards must remain face down, however the players can look at the cards. The object of the game is to not have the lowest card. Once the deal has finished, players will then look at their cards and choose whether they will pass their card to the player on their left or hold onto the card. The ranks go from 1 - A, which means if youre holding a card with the face value of 10 or higher, you will want to hold on to it. If youre holding a low ranked card, you would want to switch with the person to the left of you. The person to the left of the dealer starts and can either switch with the person to left of them or they can discard their card and draw a new one.  If a player has an Ace, that player gets to hold on to their card regardless of what the other players want to do. The people with the highest card at the end of the round win. In this game, you usually give the player x number of lives, since it is a very fast game to play and when someone loses all their lives, they are out");
    }

    //--------------------------------------------------------------------------------------

    

    //---------------------------Step 2 of main method--------------------------------------

    public static void setup(){ // sets up game
	addCards(); // adds deck
	shuffleDeck(); // shuffles deck
	makePlayers(); // makes players
	deal(); // deals cards
     }
    public static void makePlayers(){
	System.out.println("How many players do you want? (Between 3 and 10, inclusive!)"); // how many players?
	int resp = Keyboard.readInt();
	if (resp < 3) { // if too few
	    System.out.println("Please choose at least 3 players");
	    makePlayers();
	}
	else if (resp > 10) { // if too many
	    System.out.println("Please choose at most 10 players");
	    makePlayers();
	}
	else { // if between 3 and 10
	    System.out.println("How many lives do you want each player to have?"); // how many lives
	    int res = Keyboard.readInt();
	    if (res < 1) { // if less than 1 life
		System.out.println("You can't have less than 1 life!");
		makePlayers();
	    }
	    else {
	        user = new ChaseUser(res); // initializes user
		for (int x = 0; x  < resp - 1; x++){
		    //System.out.println("asd;fja");
		    ais.add(new ChaseAI(x, res)); // initializes x amount of AIs
		}
	    }
	}
	System.out.println("\n\n\nThe Chase for the Ace Begins!\n\n");
	return;
    }

    
    //deals one cards to each player initally
    public static void deal(){ // deals cards
	user.hand.add(deck.remove(0)); // deals one card to user
	for (int x = 0; x < ais.size(); x++) {
	    (ais.get(x)).hand.add(deck.remove(0)); // deals one card to each AI
	}
    }
    
    //--------------------------------------------------------------------------------------

    

    //---------------------------Step 3 of main method--------------------------------------

    public static void play(){
	boolean AllButOne = false; 
	while (AllButOne == false) {// while there is more than one player with more than 0 lives
	    if (user.lives <= 0) { // if the user doesnt have any lives left
		System.out.println("You have lost all your lives");
	    }		
	    else { // if the user still has lives
		user.move(); // user moves
		if ((user.trade == true) && ((ais.get(0)).hand.get(0).ChaseVal < 100)) { // if the user wants to trade and AI does not have an Ace
		    user.hand.add(ais.get(0).hand.remove(0)); // puts ai's card in users hand
		    ais.get(0).hand.add(user.hand.remove(0)); // puts user's initial card in ai's hand
		    System.out.println("You have traded your " + ais.get(0).hand.get(0) + " for a " + user.hand.get(0)); // prints out the cards traded
		    }
		if ((user.trade == true) && (ais.get(0).hand.get(0).ChaseVal == 100)) { // if user wants to trade but AI has an Ace
		    System.out.println("You tried to trade but your opponent has an ACE! Tough luck"); // does nothing
		}
		if ( user.discard == true) { // if the user wants to discard
		    System.out.println(" You have discarded your " + user.hand.get(0) + " and drew a " + deck.get(0)); // tells the user what they got
		    deck.add(user.hand.remove(0)); // moves the user's card to the back of the deck
		    user.hand.add(deck.remove(0)); // moves the first card in the deck to the user's hand
		}
		user.trade = false; 
		user.discard = false;
	    }
	    for (ChaseAI x: ais) { // for each AI
		if (x.lives > 0) { // if they have lives
		    x.move(); // move
		    if ((x.trade == true) && (x.player < ais.size() - 1) && ((ais.get((x.player)+1)).hand.get(0).ChaseVal < 100)) { // if they want to trade and the following player does not have an ace
			x.hand.add(ais.get((x.player)+1).hand.remove(0)); // puts second AI's card in first AI's hand
			ais.get((x.player)+1).hand.add(x.hand.remove(0));// puts first AI's original card in second AI's hand
		    }
		    if ((x.trade == true) && (x.player == ais.size() - 1) && (user.hand.get(0).ChaseVal < 100)) { // if the AI is the last one in the ArrayList
			x.hand.add(user.hand.remove(0)); // moves the user's card to the AI's hand
			user.hand.add(x.hand.remove(0)); // moves the AI's original card to the user's hand
		    }
		    if ( x.discard == true) { // if the AI wants to discard
			deck.add(x.hand.remove(0)); // moves AI's card to the back of the deck
			x.hand.add(deck.remove(0));// moves the first card in the deck to the AI's hand
		    }
		    x.trade = false;
		    x.discard = false;
		}
	    }
	    if (user.lives > 0) {System.out.println("Your card at the end of the round is " + user.hand.get(0));} // tells the user their card
	    //System.out.println("asi.size() = " + ais.size());
	    for (int i = 0; i < ais.size(); i++){ // prints out the cards of all the players
		if (ais.get(i).lives > 0 ){
		    System.out.println("Player " + ais.get(i).player + "'s card is " +ais.get(i).hand.get(0));
		}
	    }
	    winners(); // reveals the winners
	    reDeal(); // redeals cards
	    if (playersLeft() == 1) { // if there is only one player left
		AllButOne = true;// ends loop
	    }
	}
    }

	

    public static void winners() {
	int maxCard = 0;
	int aiCard = 0;
	for (int x = 0; x < ais.size() ; x++) { // checks to see max Card
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
	if ((user.lives > 0) && (maxCard < user.hand.get(0).ChaseVal)) { // checks if user has max card
	    maxCard = user.hand.get(0).ChaseVal;
	}
	if (user.lives > 0) {
	    if (user.hand.get(0).ChaseVal < maxCard) { // if user is not the winner
		user.lives -= 1; // subtracts a life
		System.out.println("You have lost this round");
	    }
	    else {System.out.println("You won this round with the " + user.hand.get(0));} // user won
	}
	for (ChaseAI x: ais) {
	    if (x.lives > 0) {
		if (x.hand.get(0).ChaseVal < maxCard) { // if AI is not the winner
		    x.lives -= 1; // subtracts a life
		}
		else if (x.hand.get(0).ChaseVal == maxCard){
		    System.out.println("AI" + x.player + " won this round with the " + x.hand.get(0));
		}
	    }
	}

	System.out.println("\n\n\n\n\n\n\n");
    }

    public static void reDeal() { //redeals cards
	deck.add(user.hand.remove(0)); // empties user's hand
	for (int x = 0; x < ais.size(); x++) {
	    deck.add((ais.get(x)).hand.remove(0)); // empties AI's hand
	}
	shuffleDeck(); // shuffles deck
	deal(); // deals
    }

    public static int playersLeft() { // checks to see how many players still have more than 0 lives
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

    //--------------------------------------------------------------------------------------



    //---------------------------Step 4 of main method--------------------------------------

    public static void finish(){ 
	if (user.lives > 0) { // if the user still has lives, they won
	    System.out.println("You have WON this race to Chase The Ace");
	}
	else { System.out.println("You have LOST the race to Chase The Ace");
	}
	for (ChaseAI x: ais) {
	    if (x.lives > 0) {// if the AI still has lives, it won
		System.out.println("AI" + x.player + " has won!");
	    }
	}
    }

    //--------------------------------------------------------------------------------------



    //---------------------------Step 5 of main method--------------------------------------
    
    
    public static void playAgain(){
	System.out.println("Do You Want To Give It Another Go?   \n1. Yea, I'm Game \n2. Nah, Let's Try Something Else");
	int response = Keyboard.readInt();
	if (response == 1) {
	    reset(); // resets decks
	    main(null);
	} else if (response == 2 ){
	    reset();
	    Woo.main(null);
	} else {
	    System.out.println("I don't understand, so I'll ask again");
	    playAgain();
	}
    }

    public static void reset() {
	turns = 0;
	ais = new ArrayList<ChaseAI>(); // resets ArrayList of ais
        winners = new ArrayList<Player>(); // resets winners
	resetDecks(); // resets decks
    }
	
    //--------------------------------------------------------------------------------------

    public static void printInstructions(){
	System.out.println("To swap cards with the next player, type 'trade'");
	System.out.println("To discard your card, type 'discard'");
	System.out.println("To pass, type 'pass'");
	System.out.println("If you want to forfeit the game, type 'forfeit'");
	System.out.println("If you forget how to play, type 'help'");
    }

}
	    
