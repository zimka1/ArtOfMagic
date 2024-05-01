package Cards.TypeOfCard;

import Cards.Card;
import GameBoard.Board;
import GameBoard.Graveyard;
import Players.Hand;

/**
 * Represents a spell card in the game. Spell cards typically have effects that are applied immediately
 * and do not remain on the board like minion cards.
 */
public class Card_Spell extends Card {

    /**
     * Constructs a new spell card with specified attributes.
     *
     * @param name The name of the spell card.
     * @param manaCost The mana cost required to play the spell card.
     * @param power The effect power of the spell card, often representing damage or other impact strength.
     * @param whose The player identifier to whom this card belongs.
     */
    public Card_Spell(String name, int manaCost, int power, int whose) {
        super(name, manaCost, power, whose);
    }

    /**
     * Executes the attack logic specific to spell cards, applying damage directly to an opponent's card
     * and then removing itself to the graveyard.
     *
     * @param opponents_card The opponent's card that is targeted by the spell.
     * @param yourBoard The board of the player who is casting the spell.
     * @param opponents_Board The board of the opponent receiving the spell's effect.
     */
    @Override
    public void attackCard(Card opponents_card, Board yourBoard, Board opponents_Board) {
        opponents_card.takingDamage(this.getPower());
        if (opponents_card.getNowHP() <= 0) {
            opponents_card.death(opponents_Board);
        }
        this.death(yourBoard); // Spell cards are typically used once and then removed
    }

    /**
     * Handles the 'death' of the spell card, ensuring it is moved from the board to the graveyard after use.
     *
     * @param board The board from which the card will be removed.
     */
    @Override
    public void death(Board board) {
        this.restoringValues(); // Reset values if needed, typically redundant for spells unless they have state
        board.getGraveyard().sendCardToGraveyard(this);
    }

    /**
     * Outputs detailed information about this spell card, including its name, mana cost, and power.
     */
    @Override
    public void getInfo() {
        System.out.println("Card name: " + getName() +
                "\nManacost: " + getManaCost() +
                "\nPower: " + getPower() +
                "\nID: " + getID() + "\n");
    }
}
