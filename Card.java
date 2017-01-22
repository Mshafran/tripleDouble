public class Card{ // card Class
    public int value; // value
    public int suit; // suit
    public int BJval;
    public int ChaseVal;
    //Spades = 3, Hearts = 2 ...
    //hearts/diamonds/clubs/spades -- 3/4/5/6

    public Card(int Val, int Suit){
	value = Val; // value is Val
	suit = Suit; // suit is Suit
	if (Val == 1) { // if card is an Ace
	    ChaseVal = 100;
	}
	if (Val == 11){ // if card is a Jack
	    BJval = 10;
	    ChaseVal = 20;
	} else if (Val == 12) {// if card is a Queen
	    BJval = 10;
	    ChaseVal = 40;
	} else if (Val == 13) { // if card is a King
	    BJval = 10;
	    ChaseVal = 50;
	} else {// if card is a number card
	    BJval = Val;
	    ChaseVal = Val;
	}
    }

    public String toString(){
	char val = 'X';
	char symbol = 'X';
	if (suit == 0){ // diamonds
	    //symbol = (char)(suit+4);
	    symbol = '\u2666';
	}
	if (suit == 1){//clubs
	    // symbol = (char)(suit+4);
	    symbol = '\u2663';
	}
	if (suit == 2){//hearts
	    //symbol = (char)(suit+1);
	    symbol = '\u2764';
	}
	if (suit == 3){//spades
	    // symbol = (char)(suit+3);
	    symbol = '\u2660';
	}
	if (value == 1){ // ace
	    val = 'A';
	} else if (value == 10){// 10
	    return ""+10+symbol;
	} else if (value == 11){// Jack
	    val = 'J';
	} else if (value == 12){//queen
	    val = 'Q';
	} else if (value == 13){//king
	    val = 'K';
	} else {val = (char)(48+value);} //number card
	
	return "" + val + symbol; // return the value and the suit
    }

}
