package Players;

import Cards.*;
import GameBoard.Deck;

import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;
public class Hand {
    private List<Card> cards;

    public Hand() {
        this.cards = new ArrayList<>();
    }

    public List<Card> getCards(){return cards;}
    // Метод для добавления карты в колоду
    public void takeCard(Deck deck) {
        cards.add(deck.giveCard());
    }
    public Card findCard(String ID){
        for (Card card : cards) {
            if (card.getID().equals(ID)) {
                return card;
            }
        }
        return null;
    }
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

    public void displayHand() {
        int k = 0;
        for (Card card : cards) {
            k++;
            System.out.println("Cards in hand: " + k + ":");
            card.getInfo();
        }
    }
}
