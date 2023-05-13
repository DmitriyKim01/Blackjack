import java.util.Random;
// Helper entity class for: BlackJack
// This class is responsible for all the deck related manipulations
public class Deck {
//Fields:
    private final DynamicCardArray deck;
    private int currentPosition;
    // Constructor:
    public Deck(){
        this.currentPosition = -1;
		deck = new DynamicCardArray();
        for (Suit suit : Suit.values()){
            for (Number number : Number.values()){
                    this.deck.add(new Card(suit, number));
            }
        }
    }

//Getter methods:
    public Card getCard(){
        return this.deck.getCard(getCurrentPosition());
    }
    // This method returns current position in the deck and shift it by 1 when called
    private int getCurrentPosition(){
        return ++this.currentPosition;
    }

//Custom methods:
    public String toString(){
        String result = "";
        for (int i = 0; i < this.deck.length(); i++){
            result += this.deck.getCard(i) + " ";
        }
        return result;
    }

    // This method shuffles all cards in the deck
    public void shuffle(){
        Random random = new Random();
        for (int i = 0; i < this.deck.length()-1; i++){
            int randomNumber = random.nextInt(this.deck.length()-i-1);
			deck.swapCards(randomNumber, (this.deck.length()-i-1));
        }
    }
}
