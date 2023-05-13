// Helper entity class for: Deck | Player | Dealer
// A copy of ArrayLists
public class DynamicCardArray {
//Fields:
    private final Card[] cards;
    private int next;

//Constructor:
    public DynamicCardArray(){
        this.cards = new Card[1000];
        this.next = 0;
    }

//Getters:
    // Getting a card from a card array based on the index given:
    public Card getCard(int index){
        return this.cards[index];
    }

//Custom methods:
    // Adding a card to card array:
    public void add(Card card){
        this.cards[next] = card;
        this.next++;
    }
    //  Get the length of a card array. (Support method for the add() method):
    public int length(){
        return this.next;
    }
    // Swapping two cards in the Card array based on two given indexes:
    public void swapCards(int randomIndex, int lastIndex){
        Card temp = this.cards[randomIndex];
        this.cards[randomIndex] = this.cards[lastIndex];
        this.cards[lastIndex] = temp;
    }
}
