public class BlackJackAI extends Player{

    boolean passed = false;
    int runCount = 0;
    int handSum = 0;
    int player;
    public BlackJackAI( int i ){ // constructor
	player = i; // sets player number
    }
    
    public void move(){ // moves
	passed = false;// pass boolean
        sumHand(); // sums the hand
	if (handSum > 21){ // if the AI busted
	    BlackJack.players[player].passed = true; // the AI passes
	    return;
	} else if (handSum == 21){ // if the AI has exactly 21
	    checkWinner(); // double checks
	    BlackJack.totalBlackJacks++; // increments totalBlackJacks in Blackjack
	    BlackJack.players[player].passed = true; // passes
	    return;
	}

	runCount = 0;
	for (int loop = 0; loop < BlackJack.pile.size(); loop++){ // card counting
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
	if ((runCount >= 0 && handSum < 21) || handSum < 14){ // if less than 14 and hasnt busted
	    hitme(); // hit
	    //System.out.println("hit");
	} else {
	    BlackJack.players[player].passed = true; // otherwise pass
	}
    }

    public int sumHand(){
	 handSum =0; // handsum initially 0
	 for (int i = 0; i < hand.size(); i++){// loops thru cards in hand
	     handSum += hand.get(i).BJval;// gets the sum of each card
	}
	 return handSum; // returns the sum of the hand
    }

    
    public void hitme(){ // hit method
	BlackJack.pile.add(BlackJack.deck.get(0)); // takes first card from deck and adds it to the pile
	hand.add(BlackJack.deck.remove(0));// removes first card from deck and puts it in the AI's hand
	//System.out.println(runCount);
    }

    
    public void checkWinner(){ // checks for winner
	if (handSum == 21){ // if the sum of the hand is 21
	    BlackJack.winners.add(this); // adds to ArrayList of winners
	}
    }
	

}
