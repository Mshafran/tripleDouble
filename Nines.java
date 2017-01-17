public class Crazy8s extends CardGame{
    public static void main( String[] args){
	setup();
    }
    public static void setup(){
	addCards();
	shuffleDeck();
	makePlayers();
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
	    players = new Player[numPlayers];
	    players[0] = new BlackjackUser();
	    for (int i = 1; i <players.length; i++){
		players[i] = new BlackJackAI(i);
	    }
	    System.out.println("\n\n\nWelcome to Crazy 8s\n\n");
	    return;
	}
    }

}
