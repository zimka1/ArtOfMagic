package Cards;

import Cards.TypeOfCard.Card_Minion;
import Cards.TypeOfCard.Card_Weapon;
import GameBoard.Board;
import GameBoard.Graveyard;
import Players.Hand;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Represents a card in a game, encompassing all properties and behaviors including attack mechanics,
 * health points, mana cost, and interactions with weapons.
 */
public abstract class Card {
    private String name;
    private int manaCost;
    private int power;
    private String ID;

    private int alreadyAttacked = 0;
    private int Whose = 0;
    private int numberBoard = 1;
    private int hp = 0;
    private int nowHP = hp;
    private Card weapon = null;
    private int numberOfUses = 0;

    /**
     * Constructs a card with specified attributes.
     *
     * @param name The name of the card.
     * @param manaCost The mana cost required to play the card.
     * @param power The attack power of the card.
     * @param whose The player identifier this card belongs to.
     */
    public Card(String name, int manaCost, int power, int whose) {
        setName(name);
        setManaCost(manaCost);
        setPower(power);
        setWhose(whose);
        setID(generateSHA256Hash(name + manaCost + power + whose));
    }
    /**
     * Generates a SHA-256 hash of the given string.
     *
     * @param data String to hash.
     * @return A hexadecimal string representation of the hash.
     */
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
    public Card getWeapon(){return weapon;}
    public int getWhose(){return Whose;}
    public int getNowHP() {return nowHP;}
    public int getNumberBoard(){return numberBoard;}
    public int getAlreadyAttacked(){return alreadyAttacked;}



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
    public void setWeapon(Card weapon, Hand hand){
        this.weapon = weapon;
        hand.removeCard(weapon.getID());
    }
    public void setWhose(int whoTake) {
        this.Whose = whoTake;
    }
    public void setNowHP(){
        this.nowHP = this.hp;
    }
    public void setNumberBoard(int numberBoard){
        this.setNumberBoard(numberBoard);
    }
    public void removeWeapon(){
        this.weapon = null;
    }

    public void minusNumberOfUses(){ this.numberOfUses--;}
    public void setAlreadyAttacked(int flag){alreadyAttacked = flag;}


    /**
     * Handles the card receiving damage.
     *
     * @param damage The amount of damage the card takes.
     */
    public void takingDamage(int damage){
        this.nowHP -= damage;
    }

    /**
     * Handles the logic for this card attacking another card.
     *
     * @param opponents_card The card being attacked.
     * @param yourBoard The board of the attacking card.
     * @param opponents_Board The board of the defending card.
     */
    public void attackCard(Card opponents_card, Board yourBoard, Board opponents_Board){
        if (this.getWeapon() != null){
            System.out.println(this.getWeapon().getPower());
            opponents_card.takingDamage(this.getPower() + this.getWeapon().getPower());
            this.getWeapon().minusNumberOfUses();
            if (this.getWeapon().getNumberOfUses() <= 0){
                this.getWeapon().death(yourBoard);
                this.removeWeapon();
            }
        }
        else{
            opponents_card.takingDamage(this.getPower());
        }
        this.alreadyAttacked = 1;
        opponents_card.attackInResponse(this);
        if (nowHP <= 0){
            this.death(yourBoard);
        }
        if (opponents_card.nowHP <= 0){
            opponents_card.death(opponents_Board);
        }
    }
    /**
     * Handles the card's response to being attacked.
     *
     * @param opponents_card The attacking card.
     */
    public void attackInResponse(Card opponents_card){
        if (this.weapon != null){
            opponents_card.takingDamage(this.getPower() + this.weapon.getPower());
        }
        else{
            opponents_card.takingDamage(this.getPower());
        }
    }
    /**
     * Resets the card's health to its initial state.
     */
    public void restoringValues(){
        this.nowHP = this.getHp();
    }

    /**
     * Handles the card's death, removing it from the board and sending it to the graveyard.
     *
     * @param board The board from which the card will be removed.
     */
    public void death(Board board){
        board.removeCard(this.getID());
        this.restoringValues();
        board.getGraveyard().sendCardToGraveyard(this);
    }
    /**
     * Outputs the card's details to the system output.
     */
    public void getInfo(){
        System.out.println("Card name: " + this.name + "\nManacost: " + this.manaCost + "\nPower: " + this.power + "\nID" + this.ID + "\n");
    }
}
