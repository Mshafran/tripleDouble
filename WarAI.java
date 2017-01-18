import java.util.ArrayList;
public class WarAI extends Player{
    public ArrayList<Card> table = new ArrayList<Card>();

    
    public void putCard(){
	this.table.add(this.hand.remove(0));
    }

public void tie() {
    putCard();
    putCard();
}

    
    public void move() {
	putCard();
	turn = false;
	}

    /*public static void main(String[] args) {
	move();
	}*/
		

    
    
}
