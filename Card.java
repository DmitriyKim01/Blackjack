// Helper entity class for: Deck
// This class is responsible for a single card manipulations
public class Card{

//Fields:
    private final Number number;
    private final Suit suit;

//Constructor:
    public Card(Suit suit, Number number){
        this.suit = suit;
        this.number = number;
    }

//Getters methods:
    // Get the card score based on its number value:
    public int getScore(){
        return this.number.getValue();
    }
    // Get the card suit:
    public String getSuit(){
        return this.suit.toString();
    }
    public String getNumber(){
        return this.number.toString();
    }

//Custom methods:
    // Printing number and suit of a card:
    public String toString(){
        return String.valueOf(number) + suit;
    }
}