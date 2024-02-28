package GameBoard;
import java.util.ArrayList;
import java.util.List;

import Cards.Card;

public class Board {
    private List<Card> onTheBoard;
    public Board(){
        this.onTheBoard = new ArrayList<>();
    }
    public void putCardOnTable(Card card){
        onTheBoard.add(card);
    }
    public void DisplayBoard(){
        for (Card card : onTheBoard){
            System.out.print("Cards on the table: " + card.getName() + " ");
        }
        System.out.println("\n");
    }

}
