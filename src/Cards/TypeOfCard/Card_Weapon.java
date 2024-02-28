package Cards.TypeOfCard;

import Cards.Card;

public class Card_Weapon extends Card {
    private int numberOfUses;
    public Card_Weapon(String name, int manaCost, int power, int numberOfUses){
        super(name, manaCost, power);
        this.numberOfUses = numberOfUses;
    }

    public int getNumberOfUses() {
        return numberOfUses;
    }

    public void getInfo(){
        System.out.println("Card name: " + getName() + "\nManacost: " + getManaCost() + "\nPower: " + getPower() + "\nHP: " + this.numberOfUses + "\n");
    }
}
