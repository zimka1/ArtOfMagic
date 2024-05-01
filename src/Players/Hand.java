package Players;

import Cards.*;
import GameBoard.Deck;

import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;

/**
 * Represents a player's hand in a card game, holding and managing the cards that a player can use during their turn.
 */
public class Hand {
    private List<Card> cards;

    /**
     * Constructs a new Hand object.
     */
    public Hand() {
        this.cards = new ArrayList<>();
    }

    /**
     * Returns the list of cards currently held in the hand.
     *
     * @return the list of cards.
     */
    public List<Card> getCards() {
        return cards;
    }

    /**
     * Adds a card from the deck to the hand.
     *
     * @param deck The deck from which a card is drawn and added to the hand.
     */
    public void takeCard(Deck deck) {
        cards.add(deck.giveCard());
    }

    /**
     * Finds a card in the hand based on its ID.
     *
     * @param ID The unique identifier for the card to find.
     * @return The card if found, or null if no such card exists in the hand.
     */
    public Card findCard(String ID) {
        for (Card card : cards) {
            if (card.getID().equals(ID)) {
                return card;
            }
        }
        return null;
    }

    /**
     * Removes a card from the hand based on its ID.
     *
     * @param ID The unique identifier for the card to remove.
     */
    public void removeCard(String ID) {
        for (Iterator<Card> iterator = cards.iterator(); iterator.hasNext(); ) {
            Card card = iterator.next();
            if (card.getID().equals(ID)) {
                iterator.remove();
                System.out.println(card.getName() + " has been removed from the hand.");
                return;
            }
        }
        System.out.println("Card not found.");
    }

    /**
     * Displays all cards in the hand to the console. Each card's information is printed.
     */
    public void displayHand() {
        int k = 0;
        for (Card card : cards) {
            k++;
            System.out.println("Card " + k + ":");
            card.getInfo();
        }
    }
}
