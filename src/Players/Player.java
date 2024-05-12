package Players;
import Cards.Card;
import GameBoard.Deck;
import GameBoard.Board;

/**
 * Represents a player in the game, managing their hand, health, mana, and other player-specific properties.
 */
public class Player {
    private Hand hand = new Hand();
    private int hp = 10;
    private int whose = 0;
    private int mana = 5;
    private int nowMana = 5;

    /**
     * Constructs a new player with an identifier.
     *
     * @param whose Unique identifier for the player.
     */
    public Player(int whose) {
        this.whose = whose;
    }

    /**
     * Returns the hand containing the player's cards.
     *
     * @return The player's hand.
     */
    public Hand getHand() {
        return hand;
    }

    /**
     * Returns the player's identifier.
     *
     * @return The unique identifier of the player.
     */
    public int getWhose() {
        return whose;
    }

    /**
     * Returns the current health points of the player.
     *
     * @return Current health points.
     */
    public int getNowHP() {
        return hp;
    }

    /**
     * Returns the current mana points of the player available for this turn.
     *
     * @return Current available mana points.
     */
    public int getNowMana() {
        return nowMana;
    }

    /**
     * Returns the total mana capacity of the player.
     *
     * @return Total mana points.
     */
    public int getMana() {
        return mana;
    }

    /**
     * Sets the current mana to the total mana at the start of a turn.
     */
    public void setNowMana() {
        this.nowMana = mana;
    }

    /**
     * Increases the player's mana capacity by one point.
     */
    public void plusMana() {
        this.mana += 1;
    }

    /**
     * Decreases the player's available mana by the specified amount.
     *
     * @param mana Amount of mana to deduct.
     */
    public void minusMana(int mana) {
        this.nowMana -= mana;
    }

    /**
     * Moves a card from the player's hand to the game board if sufficient mana is available.
     *
     * @param ID    The identifier of the card to play.
     * @param board The board where the card will be placed.
     */
    public void putCardOnTable(String ID, Board board) {
        Card findedCard = this.hand.findCard(ID);
        if (findedCard != null && findedCard.getManaCost() <= this.nowMana) {
            nowMana -= findedCard.getManaCost();
            this.hand.removeCard(findedCard.getID());
            board.putCardOnTable(findedCard);
        } else {
            System.out.println("You don't have enough mana!");
        }
    }

    /**
     * Reduces the player's health by the specified amount of damage.
     *
     * @param damage Amount of damage to inflict on the player.
     */
    public void takingDamage(int damage) {
        this.hp -= damage;
    }

    /**
     * Adds a card from the deck to the player's hand.
     *
     * @param deck The deck from which a card is drawn.
     */
    public void putCardInHand(Deck deck) {
        this.hand.takeCard(deck);
    }
}
