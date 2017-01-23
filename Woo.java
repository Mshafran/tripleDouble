import java.util.ArrayList;
import cs1.Keyboard;

public class Woo {
    //static int money = 1000;
    public static void main(String[] args){
	
	
	System.out.println("ARE YOU READY...\n\t\tTO PLAY SOME CARDS?\n");
	System.out.println("What game do you want to play?");
	System.out.println("1. BlackJack \n2. War \n3. Crazy8s \n4. Chase the Ace \n5. GoFish");
	System.out.print("Pick a Number: ");
	int response = Keyboard.readInt();
	if (response == 1){
	    BlackJack.main(args);
	} else if (response == 2){
	    War.main(args);
	} else if (response == 3){
	    Crazy8s.main(args);
	}  else if (response == 4){
	    Chase.main(args);
	} else if (response == 5){
	    GoFish.main(args);
	} else {
	    System.out.println("That number is not recognized... SO I'LL ASK AGAIN\n");
	    main(args);
	}
    }
}
