package Players;
import Cards.Card;
import Deck.Deck;
import GameBoard.Board;

public class Player {
    private Hand hand = new Hand();
    private int hp = 30;

    private int mana = 1;
    private int nowMana = 1;

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
