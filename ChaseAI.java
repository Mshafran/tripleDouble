import java.util.ArrayList;
public class ChaseAI extends Player{
    public int player;
    public int lives;
    public boolean trade = false;
    public boolean discard = false;

    public ChaseAI(int play, int liv) {
	this.player = play;
	this.lives = liv;
    }

    public int getLives() {
	return this.lives;
    }
    
    public void move() {
	boolean turn = true;
	while ((turn == true) && (lives > 0)) {
	    if (this.hand.get(0).ChaseVal < 6) {
	        this.trade = true;
		turn = false;
		}
	    
	    else if (this.hand.get(0).ChaseVal > 10) {
		turn = false;
	    }
	    else {
	        this.discard = true;
		turn = false;
	    }
	}
    }

    /*public static void main(String[] args) {
	move();
	}*/
		

    
    
}
