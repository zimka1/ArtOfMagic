package GameBoard;

import Cards.Card;
import Cards.TypeOfCard.Card_Minion;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
/**
 * Represents a deck of cards in a game, managing operations such as adding,
 * removing, and dealing cards.
 */

public class Deck {
    private List<Card> cards;

    /**
     * Constructs a new empty deck.
     */
    public Deck() {
        this.cards = new ArrayList<>();
    }

    /**
     * Adds a card to the deck.
     *
     * @param card The card to be added to the deck.
     */
    public void addCard(Card card) {
        cards.add(card);
    }

    /**
     * Retrieves the list of cards currently in the deck.
     *
     * @return A list of cards in the deck.
     */
    public List<Card> getCards(){
        return cards;
    }

    /**
     * Removes a specific card from the deck by its ID.
     *
     * @param ID The unique identifier of the card to be removed.
     */
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

    /**
     * Deals the last card from the deck, removing it in the process.
     *
     * @return The card that was dealt from the deck.
     */
    public Card giveCard(){
        Card HeadCard = cards.getLast();
        this.removeCard(HeadCard.getID());
        return HeadCard;
    }

}
