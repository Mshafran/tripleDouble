import java.util.ArrayList;
import cs1.Keyboard;

public class GoFishPlayer extends Player{
    int player = 0;
    ArrayList<Card[]> doubles = new ArrayList<Card[]>();

    //default for the user
    public GoFishPlayer(){
	player = 0;
    }
    //default for the AIs
    public GoFishPlayer(int i){
	player = i;
    }
    public void move(){
	checkDoubles();
	System.out.println("This is your hand: " + hand);
	System.out.print(  "                   ");
	for (int i = 0; i < hand.size(); i++){
	    if (hand.get(i).value == 10) {
		System.out.print("  " + i+ "  ");
	    } else {
		System.out.print(" " + i+ "  ");
	    }
	}
	System.out.println();
	//Finds out which player to ask and which card to ask for
	System.out.println("Which Player would you like to ask for a card? (input an integer) \nIf you wish to forfeit, type in '-1' ");
	int askPlayer = Keyboard.readInt();
	if (askPlayer == -1) {GoFish.reset(); Woo.main(null);}
	if (askPlayer == 0) {
	    System.out.println("You cannot ask yourself for a card!");
	    move();
	}
	System.out.println("Which card of yours would you like to ask for? (input the integer corresponding to the card in your hand)\nIf you wish to forfeit, type in '-1'");
	int cardIndex = Keyboard.readInt();
	if (cardIndex == -1) {GoFish.reset(); Woo.main(null);}
	askForCard(askPlayer, cardIndex);
	checkDoubles();
        restockCards();
    }

    //Asks the player at the indec askPlayer of the players array if they have a card with a value equals to that of the card at cardIndex
    public void askForCard(int askPlayer, int cardIndex){
	if ((askPlayer >= GoFish.players.length || askPlayer < 0) || (cardIndex < 0 || cardIndex >= hand.size())){
	    System.out.println("\nThat player or card index does not exist, try again please\n");
	    move();
	} else {
	    ((GoFishPlayer)GoFish.players[askPlayer]).askedAboutCard(hand.get(cardIndex), (GoFishPlayer)GoFish.players[player]);
	}
    }

    //How a player responds when they are asked if they have a car
    public void askedAboutCard( Card c, GoFishPlayer turnPlayer ){
	for (int i = 0; i < hand.size(); i++){
	    if (hand.get(i).value==c.value){
		turnPlayer.hand.add(hand.remove(i));
		if (turnPlayer.player == 0){
		    System.out.println("\nYou had a "+c+" and got a card from Player "+player);
		} else {
		    System.out.println("\nPlayer " + turnPlayer.player +" had a "+c+" and got a card from Player "+player);
		}
		return;
	    }
	}
	System.out.println("Player " + turnPlayer.player +" asked for a "+c.value+" and did not get it from Player "+player);
	System.out.println("GO FISH!!!");
	turnPlayer.goFish();	
    }

    //if possible draw a card
    public void goFish(){
	if (GoFish.deck.size() > 0) {
	    hand.add(GoFish.deck.remove(0));
	    if (player == 0){
		System.out.println("You drew a card");
	    } else {
		System.out.println("Player " + player + " drew a card");
	    }
	} else {
	    System.out.println("There are no cards left so Player " + player + " did not draw");
	}
    }

    //puts any doubles in the players hand into the doubles arraylist
    public void checkDoubles(){
	Card c;
	for (int i = 0; i < hand.size() ; i++){
	    c = hand.remove(i);
	    int dubSize = doubles.size();
	    for (int j = 0; j < hand.size() ; j++) {
		if (hand.get(j).value == c.value) {
		    //System.out.println("hi");
		    Card[] addThis = new Card[2];
		    addThis[0] = hand.remove(j);
		    addThis[1] = c;
		    doubles.add( addThis );
		    //System.out.println(addThis[0].toString() + addThis[1].toString());
		    //System.out.println("hi");
		    checkDoubles();
		    restockCards();
		    return;
		}
	    }
	    if (doubles.size() == dubSize) {
		hand.add(0,c);
	    }
	}
    }

    //prints the arraylist containing all the doubles for a player
    public String printDoubles(){
	String rtn;
	if (player == 0){
	    rtn = "User's doubles:      [  ";
	} else {
	    rtn = "Player " + player + "'s  doubles: [  ";
	}
	for (int i = 0 ; i < doubles.size(); i++){
	    rtn += "[ " + doubles.get(i)[0].toString() + doubles.get(i)[1].toString() + " ]";
	}
	rtn += "  ]";
	rtn += "\t# of doubles: " + doubles.size();
	System.out.println(rtn);
	return rtn;
    }

    //if a player has no cards, draw up to 7 if possible
    public void restockCards(){
	if (hand.size() == 0){
	    while (GoFish.deck.size() > 0 && hand.size() < 7){
		hand.add(GoFish.deck.remove(0));
	    }
	    if (player == 0){
		System.out.println("User drew cards if possible");
	    } else {
		System.out.println("Player " + player + " drew cards if possible");
	    }
	    checkDoubles();
	}
    }

   
    

    
}
