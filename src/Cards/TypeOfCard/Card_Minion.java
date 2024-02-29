package Cards.TypeOfCard;

import Cards.Card;
import GameBoard.Board;
import GameBoard.Graveyard;
import Players.Hand;

public class Card_Minion extends Card {

    private int nowHP;


    public Card_Minion(String name, int manaCost, int power){
        super(name, manaCost, power);
    }
    public Card_Minion(String name, int manaCost, int power, int hp){
        super(name, manaCost, power);
        this.setHp(hp);
    }

    public int getNowHP() {return nowHP;}



    public void takingDamage(int damage){
        this.nowHP -= damage;
    }
    public void attackCard(Card_Minion opponents_card, Board board, Graveyard yourGraveyard, Graveyard opponents_Graveyard){
        if (this.getWeapon() != null){
            opponents_card.takingDamage(this.getPower() + this.getWeapon().getPower());
            this.getWeapon().minusNumberOfUses();
            if (this.getWeapon().getNumberOfUses() <= 0){
                this.getWeapon().death(board, yourGraveyard);
                this.removeWeapon();
            }
        }
        else{
            opponents_card.takingDamage(this.getPower());
        }
        opponents_card.attackInResponse(this);
        if (nowHP <= 0){
            this.death(board, yourGraveyard);
        }
        if (opponents_card.nowHP <= 0){
            opponents_card.death(board, yourGraveyard);
        }
    }
    public void attackInResponse(Card_Minion opponents_card){
        opponents_card.takingDamage(this.getPower());
    }
    public void restoringValues(){
        this.nowHP = this.getHp();
    }

    public void getInfo(){
        System.out.println("Card name: " + getName() + "\nManacost: " + getManaCost() + "\nPower: " + getPower() + "\nHP: " + getHp() + "\nID: " + getID() + "\n");
    }
}
