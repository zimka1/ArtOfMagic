import Cards.CardLibrary;
import GameBoard.*;
import Players.*;

import static javafx.application.Application.launch;

public class Game {

    public static void main(String[] args) {

        Board your_Board = new Board();
        Player you = new Player();

        Board opponents_Board = new Board();
        Player opponent = new Player();


        your_Board.getDeck().addCard(CardLibrary.ArcaneBlast);
        your_Board.getDeck().addCard(CardLibrary.CrystalSword);
        your_Board.getDeck().addCard(CardLibrary.BattleAxe);
        your_Board.getDeck().addCard(CardLibrary.DarkLance);
        your_Board.getDeck().addCard(CardLibrary.GuardianOfTheForest);

        opponents_Board.getDeck().addCard(CardLibrary.ArcaneBlast);
        opponents_Board.getDeck().addCard(CardLibrary.CrystalSword);
        opponents_Board.getDeck().addCard(CardLibrary.BattleAxe);
        opponents_Board.getDeck().addCard(CardLibrary.DarkLance);
        opponents_Board.getDeck().addCard(CardLibrary.GuardianOfTheForest);

        you.putCardInHand(your_Board.getDeck());
        you.putCardInHand(your_Board.getDeck());
        you.putCardInHand(your_Board.getDeck());

        opponent.putCardInHand(opponents_Board.getDeck());
        opponent.putCardInHand(opponents_Board.getDeck());
        opponent.putCardInHand(opponents_Board.getDeck());

        you.putCardOnTable(CardLibrary.GuardianOfTheForest.getID(), your_Board);

        CardLibrary.GuardianOfTheForest.setWeapon(CardLibrary.BattleAxe, you.getHand());

        you.getHand().displayHand();
        your_Board.DisplayBoard();
        your_Board.getDeck().displayDeck();


    }
}
