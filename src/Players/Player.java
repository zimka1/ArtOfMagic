package Players;
import Cards.Card;
import GameBoard.Deck;
import GameBoard.Board;

public class Player {
    private Hand hand = new Hand();
    private int hp = 30;
    private int whose = 0;
    private int mana = 10;
    private int nowMana = 100;

    public Player (int whose){
        this.whose = whose;
    }
    public Hand getHand(){
        return hand;
    }
    public int getWhose(){return whose;}
    public int getNowHP() {
        return hp;
    }
    public int getNowMana() {
        return nowMana;
    }
    public int getMana(){
        return mana;
    }

    public void setNowMana(){
        this.nowMana = mana;
    }

    public void plusMana(){
        this.mana += 1;
    }

    public void putCardOnTable(String ID, Board board){
        Card findedCard = this.hand.findCard(ID);
        if (findedCard.getManaCost() <= this.nowMana){
            nowMana -= findedCard.getManaCost();
            this.hand.removeCard(findedCard.getID());
            board.putCardOnTable(findedCard);
        }
        else{
            System.out.println("You don't have enough mana!");
        }
    }
    public void takingDamage(int damage){
        this.hp -= damage;
    }
    public void restoringMana(){
        this.nowMana = this.mana;
    }
    public void putCardInHand(Deck deck) {
        this.hand.takeCard(deck);
    }


}
