import java.util.ArrayList;
import cs1.Keyboard;

public class BlackJack extends CardGame{
    static int turns = 0;
    public static int totalBlackJacks = 0;
    public static ArrayList<Player> winners = new ArrayList<Player>();
    public static void main(String[] args){
	setup();
	//deal();
	print();
	play();
	System.out.println("You currently have $" + Woo.money + ".");
	
	
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
		players[i] = new BlackJackAI();
	    }
	    System.out.println("SKRT");
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
	printInstructions();
	while (deck.size()>0){
	    ((BlackjackUser)players[0]).move();
	    for (int i = 1; i < players.length; i++){
		((BlackJackAI)players[i]).move();
	    }
	    print();
	    turns++;
	    if (turns > 10){
		System.out.println(totalBlackJacks);
		System.out.println(winners);
		return;
	    }
	}
	//System.out.println(totalBlackJacks);
    }
    public static void printInstructions(){
	System.out.println("To hit, type 'hit'");
	System.out.println("To see the sum of your hand, type 'sum'");
	System.out.println("To end your turn, type 'end'");
	System.out.println("If you forget how to play, type 'help'");
    }
    

}
