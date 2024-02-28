import Deck.*;
import GameBoard.*;
import Players.*;

public class Game {

    public static void main(String[] args) {

        Card_SadHumster SadHumster = new Card_SadHumster("Sad Humster", 3, 2, 10);
        Card_Bobr Bobr = new Card_Bobr("Bobr", 1, 1, 5);


        Deck Yourdeck = new Deck();
        Board YourBoard = new Board();
        Player you = new Player();

        // Создание и добавление карт в колоду
        Yourdeck.addCard(SadHumster);
        Yourdeck.addCard(Bobr);

        System.out.println(SadHumster.getName());
        // Отображение информации о колоде
        Yourdeck.displayDeck();

//        YourBoard.DisplayBoard();


    }

}
