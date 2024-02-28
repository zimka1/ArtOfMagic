package Cards.TypeOfCard;

import Cards.Card;

public class Card_Spell extends Card {
    public Card_Spell(String name, int manaCost, int power){
        super(name, manaCost, power);
    }
    public void getInfo(){
        System.out.println("Card name: " + getName() + "\nManacost: " + getManaCost() + "\nPower: " + getPower() + "\nHP: " + "\n");
    }
}
