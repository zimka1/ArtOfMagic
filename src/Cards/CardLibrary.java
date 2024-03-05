package Cards;


import Cards.TypeOfCard.Card_Minion;
import Cards.TypeOfCard.Card_Spell;
import Cards.TypeOfCard.Card_Weapon;

public class CardLibrary {
    // Minions
    public static final Card_Minion GuardianOfTheForest = new Card_Minion("Guardian Of The Forest", 1, 2, 3);
    public static final Card_Minion MysticOwl = new Card_Minion("Mystic Owl", 3, 2, 4);
    public static final Card_Minion ThunderLizard = new Card_Minion("Thunder Lizard", 4, 5, 6);
    public static final Card_Minion IceSorceress = new Card_Minion("Ice Sorceress", 6, 4, 7);
    public static final Card_Minion FireImp = new Card_Minion("Fire Imp", 2, 3, 2);
    public static final Card_Minion WaterElemental = new Card_Minion("Water Elemental", 4, 3, 6);
    public static final Card_Minion EarthGiant = new Card_Minion("Earth Giant", 8, 8, 8);
    public static final Card_Minion WindSprinter = new Card_Minion("Wind Sprinter", 3, 4, 3);
    public static final Card_Minion ShadowAssassin = new Card_Minion("Shadow Assassin", 5, 5, 5);
    public static final Card_Minion Lightwarden = new Card_Minion("Lightwarden", 2, 1, 1);

    // Spells
    public static final Card_Spell Fireball = new Card_Spell("Fireball", 3, 2);
    public static final Card_Spell LightningBolt = new Card_Spell("Lightning Bolt", 1, 3);
    public static final Card_Spell FrostNova = new Card_Spell("Frost Nova", 2, 2);
    public static final Card_Spell ShadowFury = new Card_Spell("Shadow Fury", 4, 4);
    public static final Card_Spell Earthquake = new Card_Spell("Earthquake", 5, 5);
    public static final Card_Spell ArcaneBlast = new Card_Spell("Arcane Blast", 2, 2);
    public static final Card_Spell SolarBeam = new Card_Spell("Solar Beam", 3, 3);
    public static final Card_Spell VoidTerror = new Card_Spell("Void Terror", 4, 4);
    public static final Card_Spell IceArrow = new Card_Spell("Ice Arrow", 1, 1);
    public static final Card_Spell ThunderStrike = new Card_Spell("Thunder Strike", 2, 2);

    // Weapons
    public static final Card_Weapon BattleAxe = new Card_Weapon("Battle Axe", 3, 2, 2);
    public static final Card_Weapon CrystalSword = new Card_Weapon("Crystal Sword", 4, 3, 3);
    public static final Card_Weapon ShadowDagger = new Card_Weapon("Shadow Dagger", 2, 1, 4);
    public static final Card_Weapon FrostMace = new Card_Weapon("Frost Mace", 3, 4, 1);
    public static final Card_Weapon FlameScythe = new Card_Weapon("Flame Scythe", 5, 2, 2);
    public static final Card_Weapon ThunderHammer = new Card_Weapon("Thunder Hammer", 4, 3, 3);
    public static final Card_Weapon WindSaber = new Card_Weapon("Wind Saber", 2, 3, 2);
    public static final Card_Weapon EarthStaff = new Card_Weapon("Earth Staff", 3, 2, 4);
    public static final Card_Weapon LightBow = new Card_Weapon("Light Bow", 2, 2, 3);
    public static final Card_Weapon DarkLance = new Card_Weapon("Dark Lance", 4, 4, 1);
}
