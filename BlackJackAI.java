public class BlackJackAI extends Player{

    boolean passed = false;
    int runCount = 0;
    int handSum = 0;
    int player;
    public BlackJackAI( int i ){
	player = i;
    }
    
     public int sumHand(){
	handSum =0;
	for (int i = 0; i < hand.size(); i++){
	    handSum += hand.get(i).BJval;
	}
	return handSum;
    }
    public void move(){
	passed = false;
        sumHand();
	if (handSum > 21){
	    BlackJack.players[player].passed = true;
	    return;
	} else if (handSum == 21){
	    checkWinner();
	    BlackJack.totalBlackJacks++;
	    BlackJack.players[player].passed = true;
	    return;
	}

	runCount = 0;
	for (int loop = 0; loop < BlackJack.pile.size(); loop++){
	    //System.out.println("hi");
	    if (BlackJack.pile.get(loop).BJval<= 6 && BlackJack.pile.get(loop).BJval>=2){
		runCount+=1;
	    }
	    if (BlackJack.pile.get(loop).BJval> 6 && BlackJack.pile.get(loop).BJval< 10){
		runCount+=1;
	    }
	    if (BlackJack.pile.get(loop).BJval>= 10 || BlackJack.pile.get(loop).BJval==1){
		runCount-=1;
	    }
	    //System.out.println("by");
	}
        
	//System.out.println(handSum);
	//System.out.println(runCount);

	//players stop hittin with a negative runcount and their handsum is over 30
	if ((runCount >= 0 && handSum < 21) || handSum < 14){
	    hitme();
	    //System.out.println("hit");
	} else {
	    BlackJack.players[player].passed = true;
	}
    }
    public void hitme(){
	BlackJack.pile.add(BlackJack.deck.get(0));
	hand.add(BlackJack.deck.remove(0));
	//System.out.println(runCount);
    }
    public void checkWinner(){
	if (handSum == 21){
	    BlackJack.winners.add(this);
	}
    }
	

}
