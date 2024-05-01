package Cards.TypeOfCard;

import Cards.Card;

/**
 * Represents a weapon card in the game. Weapon cards can be equipped to characters or used directly,
 * providing additional power and limited usage before depletion.
 */
public class Card_Weapon extends Card {

    /**
     * Constructs a new weapon card with specified attributes.
     *
     * @param name The name of the weapon card.
     * @param manaCost The mana cost required to play the weapon card.
     * @param power The additional attack power provided by the weapon.
     * @param numberOfUses The number of times the weapon can be used before it is exhausted.
     * @param whose The player identifier to whom this card belongs.
     */
    public Card_Weapon(String name, int manaCost, int power, int numberOfUses, int whose) {
        super(name, manaCost, power, whose);
        setNumberOfUses(numberOfUses); // Set how many times this weapon can be used
    }

    /**
     * Outputs detailed information about this weapon card, including its name, mana cost, power,
     * number of uses left, and unique identifier. Useful for debugging or displaying card details
     * in the game interface.
     */
    @Override
    public void getInfo() {
        System.out.println("Card name: " + getName() +
                "\nManacost: " + getManaCost() +
                "\nPower: " + getPower() +
                "\nNumber of Uses: " + getNumberOfUses() +
                "\nID: " + getID() + "\n");
    }
}
