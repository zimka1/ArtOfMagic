package GameBoard;

import Cards.Card;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
public class Deck {
    private List<Card> cards;

    public Deck() {
        this.cards = new ArrayList<>();
    }

    public void addCard(Card card) {
        cards.add(card);
    }

    public void removeCard(String ID){
        for (Iterator<Card> iterator = cards.iterator(); iterator.hasNext(); ) {
            Card card = iterator.next();
            if (card.getID().equals(ID)) {
                iterator.remove();
                System.out.println(card.getName() + " has been removed from the deck.");
                return;
            }
        }
        System.out.println("Card not found.");
    }
    public Card giveCard(){
        Card HeadCard = cards.getLast();
        this.removeCard(HeadCard.getID());
        return HeadCard;
    }

    public void displayDeck() {
        int k = 0;
        for (Card card : cards) {
            k++;
            System.out.println("Cards in deck: " + k + ":");
            card.getInfo();
        }
    }
}
