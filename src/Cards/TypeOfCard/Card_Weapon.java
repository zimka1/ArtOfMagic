package Cards.TypeOfCard;

import Cards.Card;

public class Card_Weapon extends Card {
    public Card_Weapon(String name, int manaCost, int power, int numberOfUses, int whose){
        super(name, manaCost, power, whose);
        setNumberOfUses(numberOfUses);
    }



    public void getInfo(){
        System.out.println("Card name: " + getName() + "\nManacost: " + getManaCost() + "\nPower: " + getPower() + "\nHP: " + getNumberOfUses() + "\nID: " + getID() +  "\n");
    }
}
