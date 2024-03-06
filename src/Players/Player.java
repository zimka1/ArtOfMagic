package Players;
import Cards.Card;
import GameBoard.Deck;
import GameBoard.Board;

public class Player {
    private Hand hand = new Hand();
    private int hp = 30;
    private int whose = 0;
    private int mana = 100;
    private int nowMana = 100;

    public Player (int whose){
        this.whose = whose;
    }
    public Hand getHand(){
        return hand;
    }
    public int getWhose(){return whose;}
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
    public void restoringMana(){
        this.nowMana = this.mana;
    }
    public void putCardInHand(Deck deck) {
        this.hand.takeCard(deck);
    }


}
