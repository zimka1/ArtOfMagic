package Cards;

import Cards.TypeOfCard.Card_Minion;
import Cards.TypeOfCard.Card_Weapon;
import GameBoard.Board;
import GameBoard.Graveyard;
import Players.Hand;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
public class Card {
    private String name;
    private int manaCost;
    private int power;
    private String ID;

    private int hp;
    private Card_Weapon weapon = null;
    private int numberOfUses;

    public Card(String name, int manaCost, int power) {
        setName(name);
        setManaCost(manaCost);
        setPower(power);
        setID(generateSHA256Hash(name + manaCost + power));
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
    public int getHp() {
        return hp;
    }
    public int getNumberOfUses() {
        return numberOfUses;
    }
    public Card_Weapon getWeapon(){return weapon;}

    public void setName(String name){ this.name = name;}
    public void setHp(int hp) {
        this.hp = hp;
    }
    public void setID(String ID) {
        this.ID = ID;
    }
    public void setManaCost(int manaCost){this.manaCost = manaCost;}
    public void setPower(int power){this.power = power;}
    public void setNumberOfUses(int numberOfUses){this.numberOfUses = numberOfUses;}
    public void setWeapon(Card_Weapon weapon, Hand hand){
        this.weapon = weapon;
        hand.removeCard(weapon.getID());
    }
    public void removeWeapon(){
        this.weapon = null;
    }

    public void minusNumberOfUses(){ this.numberOfUses--;}

    public void death(Board board, Graveyard graveyard){
        board.removeCard(this.getID());
        graveyard.sendCardToGraveyard(this);
    }
    public void getInfo(){
        System.out.println("Card name: " + this.name + "\nManacost: " + this.manaCost + "\nPower: " + this.power + "\nID" + this.ID + "\n");
    }
}
