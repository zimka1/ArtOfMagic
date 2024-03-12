package Cards.TypeOfCard;

import Cards.Card;
import GameBoard.Board;
import GameBoard.Graveyard;
import Players.Hand;

public class Card_Spell extends Card {
    public Card_Spell(String name, int manaCost, int power, int whose){
        super(name, manaCost, power, whose);
    }


    public void attackCard(Card opponents_card, Board yourBoard, Board opponents_Board){
        opponents_card.takingDamage(this.getPower());
        if (opponents_card.getNowHP() <= 0){
            opponents_card.death(opponents_Board);
        }
        this.death(yourBoard);
    }
    public void death(Board board){
        this.restoringValues();

        board.getGraveyard().sendCardToGraveyard(this);
    }
    public void getInfo(){
        System.out.println("Card name: " + getName() + "\nManacost: " + getManaCost() + "\nPower: " + getPower() + "\nHP: " + "\nID: " + getID() + "\n");
    }
}
