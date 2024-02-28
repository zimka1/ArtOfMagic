package Cards;

public class Card {
    private String name;
    private int manaCost;
    private int power;

    public Card(String name, int manaCost, int power) {
        this.name = name;
        this.manaCost = manaCost;
        this.power = power;
    }
    public String getName(){
        return this.name;
    }
    public int getManaCost(){return this.manaCost;}
    public int getPower(){return this.power;}

    public void getInfo(){
        System.out.println("Card name: " + this.name + "\nManacost: " + this.manaCost + "\nPower: " + this.power + "\n");
    }
}
