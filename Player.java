// Helper entity class for: BlackJack
// This class is responsible for a single player's manipulations.
public class Player {
// Fields:
    private final String name;
    private int balance;
    private int bet;
    private DynamicCardArray hand;
    private String move;

// Constructor:
    public Player(String name){
        this.name = name;
        this.balance = 5000;
        this.hand = new DynamicCardArray();
        this.move = "hit";
    }

// Getter methods:
    public String getName(){
        return this.name;
    }
    public int getBalance(){
        return this.balance;
    }
    public int getBet(){
        return this.bet;
    }
    public String getMove(){
        return this.move;
    }
    public DynamicCardArray getHand(){
        return this.hand;
    }

// Setter methods:
    // Reset the new balance based on the bet placed
    public void setBalance(int bet){
        this.balance -= bet;
    }
    // checks if player's bet is less than player's balance
    public void setBet(int index, int bet){
        this.bet = bet;
    }
    public void setMove(String move){
        this.move = move;
    }

// Boolean methods:
    // Checks if the move is stand
    public boolean isAbleToMove(){
        return (!this.move.equalsIgnoreCase("stand") && isAlive());
    }
    // Checks if User is allowed to make next move
    public boolean isAlive(){
        return calculateScore() <= 21;
    }
    // Checks if user's balance is not below 4 (which means he lost)
    public boolean isEligibleToPlay(){
        return this.balance > 4;
    }
    // Checks if user have an Ace on hands
    private boolean haveAce(){
        for (int i = 0; i < this.hand.length(); i++){
            if (this.hand.getCard(i).getScore() == 11){
                return true;
            }
        }
        return false;
    }

// Custom methods:
    // toString method
    public String toString(){
        return this.name;
    }
    // Calculates users score
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
    // Player takes top card from the deck
    public void takeCard(Deck deck){
        this.hand.add(deck.getCard());
    }

    // Calculates player's balance based on the bet placed: code 1 mean double, code -1 means normal win
    public void calculateBalance(int code){
        if (code == 1){
            this.balance += this.bet * 2;
        }
        else if (code == -1){
            this.balance += this.bet;
        }
    }
    // Resets player's hand
    public void refreshCards(){
        this.hand = new DynamicCardArray();
    }
}
