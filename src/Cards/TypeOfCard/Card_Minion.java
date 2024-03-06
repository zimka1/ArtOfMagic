package Cards.TypeOfCard;

import Cards.Card;
import GameBoard.Board;
import GameBoard.Graveyard;
import Players.Hand;

public class Card_Minion extends Card {

    public Card_Minion(String name, int manaCost, int power, int hp, int whose){
        super(name, manaCost, power, whose);
        this.setHp(hp);
        this.setNowHP();
    }

    public void getInfo(){
        System.out.println("Card name: " + getName() + "\nManacost: " + getManaCost() + "\nPower: " + getPower() + "\nHP: " + getHp() + "\nID: " + getID() + "\n");
    }
}
