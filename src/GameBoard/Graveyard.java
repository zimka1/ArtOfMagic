package GameBoard;

import Cards.Card;

import java.util.ArrayList;
import java.util.List;

public class Graveyard {
    private List<Card> atTheGraveyard;
    public Graveyard(){
        this.atTheGraveyard = new ArrayList<>();
    }
    public List<Card> getCards(){return atTheGraveyard;}
    public void sendCardToGraveyard(Card card){
        atTheGraveyard.add(card);
    }
    public void DisplayGraveyard(){
        for (Card card : atTheGraveyard){
            System.out.println("Cards at the cemetery: " + card.getName() + " ");
        }
        System.out.println("\n");
    }

}
