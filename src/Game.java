import Cards.Minions.Card_Alleycat;
import Cards.Minions.Card_Bobr;
import Cards.Minions.Card_SadHumster;
import Cards.Spells.Card_Spell_Fireball;
import Cards.Weapons.Card_Weapon_Axe;
import GameBoard.*;
import Players.*;

public class Game {

    public static void main(String[] args) {

        Card_SadHumster SadHumster = new Card_SadHumster("Sad Humster", 3, 2, 10);
        Card_Bobr Bobr = new Card_Bobr("Bobr", 1, 1, 5);
        Card_Alleycat Alleycat = new Card_Alleycat("Alleycat", 1, 1, 1);
        Card_Spell_Fireball Fireball = new Card_Spell_Fireball("Fireball", 1, 2);
        Card_Weapon_Axe Axe = new Card_Weapon_Axe("Axe", 2, 1, 3);

        Board yourBoard = new Board();
        Player you = new Player();


        yourBoard.getDeck().addCard(SadHumster);
        yourBoard.getDeck().addCard(Bobr);
        yourBoard.getDeck().addCard(Alleycat);
        yourBoard.getDeck().addCard(Fireball);
        yourBoard.getDeck().addCard(Axe);

        you.putCardInHand(yourBoard.getDeck());
        you.putCardInHand(yourBoard.getDeck());
        you.putCardInHand(yourBoard.getDeck());

        you.putCardOnTable(Alleycat.getID(), yourBoard);
        
        Alleycat.setWeapon(Axe, you.getHand());

        you.getHand().displayHand();
        yourBoard.getDeck().displayDeck();
        yourBoard.DisplayBoard();


    }

}
