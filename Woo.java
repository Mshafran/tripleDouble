import java.util.ArrayList;
import cs1.Keyboard;

public class Woo {

    public static void main(String[] args){
	System.out.println("What game do you want to play?");
	System.out.println("1. BlackJack \n2. CardGame (General)");
	if (Keyboard.readInt() == 1){
	    BlackJack.main(args);
	} else {
	    CardGame.main(args);
	}
    }
}
