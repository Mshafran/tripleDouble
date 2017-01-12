public class BlackJackAI extends Player{

    boolean hitme = false;
    int handSum = 0;
    public void move(){
	for (int loop = 0; loop < BlackJack.pile.size(); loop++){
	    handSum = 0;
	   
	}
	for (int i = 0; i < hand.size(); i++){
	    handSum += hand.get(i).value;
	}
	//players stop hitting when their  handsum is over 30
	if (handSum < 30){
	    hitme();
	    System.out.println("hit");
	}
    }
    public void hitme(){
	BlackJack.pile.add(BlackJack.deck.get(0));
	hand.add(BlackJack.deck.remove(0));
    }
	

}
