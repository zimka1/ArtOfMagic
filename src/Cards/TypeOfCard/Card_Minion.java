package Cards.TypeOfCard;

import Cards.Card;
import GameBoard.Board;
import GameBoard.Graveyard;
import Players.Hand;

/**
 * Represents a minion card in the game. Minions are a type of card that can be played on the board,
 * usually having health points and able to engage in battles with other minions.
 */
public class Card_Minion extends Card {

    /**
     * Constructs a new minion card with specified attributes.
     *
     * @param name The name of the minion card.
     * @param manaCost The mana cost required to play the minion card.
     * @param power The attack power of the minion card.
     * @param hp The health points of the minion card.
     * @param whose The player identifier to whom this card belongs.
     */
    public Card_Minion(String name, int manaCost, int power, int hp, int whose) {
        super(name, manaCost, power, whose);
        this.setHp(hp);
        this.setNowHP();  // Set the current health to the max health at instantiation
    }

    /**
     * Outputs detailed information about this minion card, including its name, mana cost, power,
     * health points, and ID. Useful for debugging or displaying card details in the game interface.
     */
    @Override
    public void getInfo() {
        System.out.println("Card name: " + getName() +
                "\nManacost: " + getManaCost() +
                "\nPower: " + getPower() +
                "\nHP: " + getHp() +
                "\nID: " + getID() + "\n");
    }
}
