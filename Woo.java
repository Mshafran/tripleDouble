import java.util.ArrayList;
import cs1.Keyboard;

public class Woo {
    static int money = 1000;
    public static void main(String[] args){
	System.out.println("Hank Hank it be Casino time");
	System.out.println("What game do you want to play?");
	System.out.println("1. BlackJack \n2. war \n3. CardGame (General)");
	if (Keyboard.readInt() == 1){
	    BlackJack.main(args);
	}else if (Keyboard.readInt() == 2){
	    War.main(args);
	} else {
	    CardGame.main(args);
	}
    }
}
