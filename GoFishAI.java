import java.util.ArrayList;
import java.lang.Math;

public class GoFishAI extends GoFishPlayer{

    public GoFishAI(int i) {
	player = i;
    }
    //Moves very similar to the user
    public void move(){
	checkDoubles();
	int askPlayer = randomPlayer();
	//set cardIndex to random card that the AI has
	int cardIndex = (int)(Math.random()*hand.size());
	if (hand.size() > 0) {
	    askForCard(askPlayer, cardIndex);
	}
	checkDoubles();
	restockCards();
	
    }
    //determines the randomPlayer to ask for a card from
    public int randomPlayer(){
	int rtn = 0;
	rtn = (int)(Math.random()*GoFish.players.length);
	if (rtn == player) {
	    return randomPlayer();  
	} else if (GoFish.players[rtn].hand.size() == 0){
	    return randomPlayer();
	} else {
	    return rtn;
	}	
    }













}
