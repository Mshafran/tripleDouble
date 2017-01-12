import java.util.ArrayList;
import cs1.Keyboard;

public class BlackJack extends CardGame{
    static int turns = 0;
    public static void main(String[] args){
	setup();
	print();
	play();
	
	
     }
     public static void setup(){
	addCards();
	shuffleDeck();
	makePlayers();
	deal();
    }
     public static void makePlayers(){
	System.out.println("How many players?");
	players = new Player[Keyboard.readInt()];
	for (int i = 0; i <players.length; i++){
	    players[i] = new BlackJackAI();
	}
    }
    
    public static void deal(){
	int i = 0;
	while (deck.size() > 52 - (players.length*2) ){
	    players[i].hand.add(deck.remove(0));
	    i++;
	    i %= players.length;
	}
    }
    public static void play(){
	while (deck.size()>0){
	    for (int i = 0; i < players.length; i++){
		((BlackJackAI)players[i]).move();
	    }
	    print();
	    turns++;
	    if (turns > 10){return;}
	}
    }
    

}
