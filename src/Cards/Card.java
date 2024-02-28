package Cards;

import Cards.TypeOfCard.Card_Minion;
import GameBoard.Board;
import GameBoard.Graveyard;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
public class Card {
    private String name;
    private int manaCost;
    private int power;
    private String ID;

    public Card(String name, int manaCost, int power) {
        this.name = name;
        this.manaCost = manaCost;
        this.power = power;
        this.ID = generateSHA256Hash(name + manaCost + power);
    }
    private String generateSHA256Hash(String data) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(data.getBytes(java.nio.charset.StandardCharsets.UTF_8));
            StringBuilder hexString = new StringBuilder();
            for (byte b : hash) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }


    public String getName(){
        return this.name;
    }
    public int getManaCost(){return this.manaCost;}
    public int getPower(){return this.power;}
    public String getID(){return this.ID;}

    public void getInfo(){
        System.out.println("Card name: " + this.name + "\nManacost: " + this.manaCost + "\nPower: " + this.power + "\nID" + this.ID + "\n");
    }
}
