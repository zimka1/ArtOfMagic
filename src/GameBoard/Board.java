package GameBoard;
import java.util.ArrayList;
import java.util.Iterator;
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
            System.out.print("Cards on the table:\n" + card.getName() + " ");
            if (card.getWeapon() != null){
                System.out.print("and has " + card.getWeapon().getName() + "\n");
            }
            else{
                System.out.print("\n");
            }
        }
        System.out.println("\n");
    }

    public Card findCard(String ID){
        for (Card card : onTheBoard) {
            if (card.getID().equals(ID)) {
                return card;
            }
        }
        return null;
    }
    public void removeCard(String ID) {
        for (Iterator<Card> iterator = onTheBoard.iterator(); iterator.hasNext(); ) {
            Card card = iterator.next();
            if (card.getID().equals(ID)) {
                iterator.remove();
                System.out.println(card.getName() + " has been removed from the hand.");
                return;
            }
        }
        System.out.println("Card not found.");
    }

}
