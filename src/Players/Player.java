package Players;
import Cards.Card;
import GameBoard.Deck;
import GameBoard.Board;

public class Player {
    private Hand hand = new Hand();
    private int hp = 30;
    private int mana = 1;
    private int nowMana = 1;

    public Hand getHand(){
        return hand;
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
    public void restoringMana(){
        this.nowMana = this.mana;
    }
    public void putCardInHand(Deck deck) {
        this.hand.takeCard(deck);
    }


}
