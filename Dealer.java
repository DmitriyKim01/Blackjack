// Helper entity class for: BlackJack
// This class is responsible for all the dealer's related manipulations
public class Dealer {
//Fields:
    DynamicCardArray hand;
//Constructor:
    public Dealer(){
        this.hand = new DynamicCardArray();
    }

//Getters methods:
    public DynamicCardArray getHand(){
        return this.hand;
    }

    public Card getCard(int index){
        return this.hand.getCard(index);
    }

// Boolean methods:
    // Checks if dealer has an Ace card in hand
    public boolean haveAce(){
        for (int i = 0; i < this.hand.length(); i++){
            if (this.hand.getCard(i).getScore() == 11){
                return true;
            }
        }
        return false;
    }
    // Checks if dealer is able to make a move during the playing stage
    public boolean isAlive(){
        return calculateScore() <= 21;
    }

//Custom methods:
    // Calculates dealer score:
    public int calculateScore(){
        int score = 0;
        for (int i = 0; i < hand.length(); i++){
            score += hand.getCard(i).getScore();
        }
        if (score > 21 && haveAce()){
            score -= 10;
        }
        return score;
    }
    // Calculates score only for the dealer's first card
    public int calculateScoreFirstCard(){
        return this.hand.getCard(0).getScore();
    }
    // Dealer takes the top card from the deck
    public void takeCard(Deck deck){
        this.hand.add(deck.getCard());
    }

}
