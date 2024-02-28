package Players;
import Deck.Card;
import Deck.Deck;
import GameBoard.Board;
import Players.Hand;
public class Player {
    private Hand hand;
    private int hp = 30;

    private int mana = 0;
    private int nowMana = 0;

    public Hand getHand(){
        return hand;
    }
    public void putCardOnTable(String name, Board board){
        Card findedCard = this.hand.findCard(name);
        if (findedCard.getManaCost() <= this.nowMana){
            nowMana -= findedCard.getManaCost();
            this.hand.removeCard(findedCard.getName());
            board.putCardOnTable(findedCard);
        }
        else{
            System.out.println("You don't have enough mana!");
        }
    }


    public void PutCardInHand(Deck deck) {
        this.hand.takeCard(deck);
    }


}
