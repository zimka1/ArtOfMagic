package Deck;

public class Card {
    private String name;
    private int manaCost;
    private int power;
    private int hp;

    public Card(String name, int manaCost, int power, int hp) {
        this.name = name;
        this.manaCost = manaCost;
        this.power = power;
        this.hp = hp;
    }
    public String getName(){
        return this.name;
    }
    public int getManaCost(){return this.manaCost;}
    public void getInfo(){
        System.out.println("Card name: " + name + "\nManacost: " + manaCost + "\nPower: " + power + "\nHP: " + hp + "\n");
    }


}
