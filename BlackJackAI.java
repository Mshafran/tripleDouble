public class BlackJackAI extends BlackjackPlayer{

    boolean over = false;
    int runCount = 0;
    int handSum = 0;
    public void move(){
	for (int i = 0; i < hand.size(); i++){
	    handSum += hand.get(i).value;
	}
	
	if (handSum > 21){
	    over = true;
	    return;
	} else if (handSum == 21){
	    checkWinner();
	    BlackJack.totalBlackJacks++;
	    return;
	}
	for (int loop = 0; loop < BlackJack.pile.size(); loop++){

	    //System.out.println("hi");
	    runCount = 0;
	    handSum = 0;
	    if (BlackJack.pile.get(loop).value<= 6 && BlackJack.pile.get(loop).value>=2){
		runCount+=1;
	    }
	    if (BlackJack.pile.get(loop).value> 6 && BlackJack.pile.get(loop).value< 10){
		runCount+=1;
	    }
	    if (BlackJack.pile.get(loop).value>= 10 || BlackJack.pile.get(loop).value==1){
		runCount-=1;
	    }
	    //System.out.println("by");
	}
        
	//System.out.println(handSum);
	//System.out.println(runCount);

	//players stop hittin with a negative runcount and their handsum is over 30
	if ((runCount >= 0 && handSum < 21) || handSum < 14){
	    hitme();
	    System.out.println("hit");
	}
    }
    public void hitme(){
	BlackJack.pile.add(BlackJack.deck.get(0));
	hand.add(BlackJack.deck.remove(0));
	System.out.println(runCount);
    }
    public void checkWinner(){
	if (handSum == 21){
	    BlackJack.winners.add(this);
	}
    }
	

}
