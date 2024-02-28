package Cards.TypeOfCard;

import Cards.Card;

public class Card_Minion extends Card {
    private int hp;
    public Card_Minion(String name, int manaCost, int power, int hp){
        super(name, manaCost, power);
        this.hp = hp;
    }

    public int getHp() {
        return hp;
    }

    public void getInfo(){
        System.out.println("Card name: " + getName() + "\nManacost: " + getManaCost() + "\nPower: " + getPower() + "\nHP: " + this.hp + "\nID: " + getID() + "\n");
    }
}
