package Cards.TypeOfCard;

import Cards.Card;
import GameBoard.Board;
import GameBoard.Graveyard;

public class Card_Spell extends Card {
    public Card_Spell(String name, int manaCost, int power, int whose){
        super(name, manaCost, power, whose);
    }


    public void attackCard(Card_Minion opponents_card, Board board, Graveyard yourGraveyard, Graveyard opponents_Graveyard){
        opponents_card.takingDamage(this.getPower());
        if (opponents_card.getNowHP() <= 0){
            opponents_card.death(board);
        }
        this.death(board);
    }

    public void getInfo(){
        System.out.println("Card name: " + getName() + "\nManacost: " + getManaCost() + "\nPower: " + getPower() + "\nHP: " + "\nID: " + getID() + "\n");
    }
}
