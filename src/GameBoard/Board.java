package GameBoard;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import Cards.Card;

/**
 * Represents the game board in a card game, managing cards that are currently played on the board,
 * as well as the deck and graveyard associated with a player.
 */
public class Board {

    private Deck deck = new Deck();
    private Graveyard graveyard = new Graveyard();
    private List<Card> onTheBoard;
    private int whose;

    /**
     * Constructs a Board for a specific player identified by 'whose'.
     *
     * @param whose Identifier for the player to whom this board belongs.
     */
    public Board(int whose){
        this.onTheBoard = new ArrayList<>();
        this.whose = whose;
    }

    /**
     * Returns the list of cards currently on the board.
     *
     * @return List of cards on the board.
     */
    public List<Card> getBoard(){ return onTheBoard; }

    /**
     * Returns the deck associated with this board.
     *
     * @return The deck of cards.
     */
    public Deck getDeck(){ return deck; }

    /**
     * Returns the graveyard associated with this board.
     *
     * @return The graveyard.
     */
    public Graveyard getGraveyard(){ return graveyard; }

    /**
     * Gets the player identifier to whom this board belongs.
     *
     * @return The player identifier.
     */
    public int getWhose(){ return whose; }

    /**
     * Adds a card to the board.
     *
     * @param card The card to place on the board.
     */
    public void putCardOnTable(Card card){
        onTheBoard.add(card);
    }


    /**
     * Removes a card from the board by its ID.
     *
     * @param ID The unique identifier of the card to remove.
     */
    public void removeCard(String ID) {
        for (Iterator<Card> iterator = onTheBoard.iterator(); iterator.hasNext(); ) {
            Card card = iterator.next();
            if (card.getID().equals(ID)) {
                iterator.remove();
                System.out.println(card.getName() + " has been removed from the board.");
                return;
            }
        }
        System.out.println("Card not found.");
    }
}
