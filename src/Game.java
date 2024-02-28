import Cards.Minions.Card_Alleycat;
import Cards.Minions.Card_Bobr;
import Cards.Minions.Card_SadHumster;
import GameBoard.*;
import Players.*;

public class Game {

    public static void main(String[] args) {

        Card_SadHumster SadHumster = new Card_SadHumster("Sad Humster", 3, 2, 10);
        Card_Bobr Bobr = new Card_Bobr("Bobr", 1, 1, 5);
        Card_Alleycat Alleycat = new Card_Alleycat("Alleycat", 1, 1, 1);

        Deck yourdeck = new Deck();
        Board yourBoard = new Board();
        Player you = new Player();

        // Создание и добавление карт в колоду
        yourdeck.addCard(SadHumster);
        yourdeck.addCard(Bobr);
        yourdeck.addCard(Alleycat);

        you.putCardInHand(yourdeck);
        you.putCardInHand(yourdeck);

        you.putCardOnTable(Alleycat.getID(), yourBoard);

        you.getHand().displayHand();
        yourdeck.displayDeck();
        yourBoard.DisplayBoard();


    }

}
