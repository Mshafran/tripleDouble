public class Card{
    public int value;
    public int suit;
    public int BJval;
    //Spades = 3, Hearts = 2 ...
    //hearts/diamonds/clubs/spades -- 3/4/5/6

    public Card(int Val, int Suit){
	value = Val;
	suit = Suit;
	if (Val == 11){
	    BJval = 10;
	} else if (Val == 12) {
	    BJval = 10;
	} else if (Val == 13) {
	    BJval = 10;
	} else {
	    BJval = Val;
	}
    }

    public String toString(){
	char val = 'X';
	char symbol = 'X';
	if (suit == 0){
	    //symbol = (char)(suit+4);
	    symbol = '\u2666';
	}
	if (suit == 1){
	    // symbol = (char)(suit+4);
	    symbol = '\u2663';
	}
	if (suit == 2){
	    //symbol = (char)(suit+1);
	    symbol = '\u2764';
	}
	if (suit == 3){
	    // symbol = (char)(suit+3);
	    symbol = '\u2660';
	}
	if (value == 1){
	    val = 'A';
	} else if (value == 10){
	    return ""+10+symbol;
	} else if (value == 11){
	    val = 'J';
	} else if (value == 12){
	    val = 'Q';
	} else if (value == 13){
	    val = 'K';
	} else {val = (char)(48+value);}
	
	return "" + val + symbol;
    }

}
