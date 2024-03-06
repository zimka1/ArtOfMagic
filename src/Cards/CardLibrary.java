package Cards;


import Cards.TypeOfCard.Card_Minion;
import Cards.TypeOfCard.Card_Spell;
import Cards.TypeOfCard.Card_Weapon;

public class CardLibrary {
    // Minions
    public static final Card_Minion players_GuardianOfTheForest = new Card_Minion("Guardian Of The Forest", 1, 2, 3, 1);
    public static final Card_Minion players_MysticOwl = new Card_Minion("Mystic Owl", 3, 2, 4, 1);
    public static final Card_Minion players_ThunderLizard = new Card_Minion("Thunder Lizard", 4, 5, 6, 1);
    public static final Card_Minion players_IceSorceress = new Card_Minion("Ice Sorceress", 6, 4, 7, 1);
    public static final Card_Minion players_FireImp = new Card_Minion("Fire Imp", 2, 3, 2, 1);
    public static final Card_Minion players_WaterElemental = new Card_Minion("Water Elemental", 4, 3, 6, 1);
    public static final Card_Minion players_EarthGiant = new Card_Minion("Earth Giant", 8, 8, 8, 1);
    public static final Card_Minion players_WindSprinter = new Card_Minion("Wind Sprinter", 3, 4, 3, 1);
    public static final Card_Minion players_ShadowAssassin = new Card_Minion("Shadow Assassin", 5, 5, 5, 1);
    public static final Card_Minion players_Lightwarden = new Card_Minion("Lightwarden", 2, 1, 1, 1);

    // Spells
    public static final Card_Spell players_Fireball = new Card_Spell("Fireball", 3, 2, 1);
    public static final Card_Spell players_LightningBolt = new Card_Spell("Lightning Bolt", 1, 3, 1);
    public static final Card_Spell players_FrostNova = new Card_Spell("Frost Nova", 2, 2, 1);
    public static final Card_Spell players_ShadowFury = new Card_Spell("Shadow Fury", 4, 4, 1);
    public static final Card_Spell players_Earthquake = new Card_Spell("Earthquake", 5, 5, 1);
    public static final Card_Spell players_ArcaneBlast = new Card_Spell("Arcane Blast", 2, 2, 1);
    public static final Card_Spell players_SolarBeam = new Card_Spell("Solar Beam", 3, 3, 1);
    public static final Card_Spell players_VoidTerror = new Card_Spell("Void Terror", 4, 4, 1);
    public static final Card_Spell players_IceArrow = new Card_Spell("Ice Arrow", 1, 1, 1);
    public static final Card_Spell players_ThunderStrike = new Card_Spell("Thunder Strike", 2, 2, 1);

    // Weapons
    public static final Card_Weapon players_BattleAxe = new Card_Weapon("Battle Axe", 3, 2, 2, 1);
    public static final Card_Weapon players_CrystalSword = new Card_Weapon("Crystal Sword", 4, 3, 3, 1);
    public static final Card_Weapon players_ShadowDagger = new Card_Weapon("Shadow Dagger", 2, 1, 4, 1);
    public static final Card_Weapon players_FrostMace = new Card_Weapon("Frost Mace", 3, 4, 1, 1);
    public static final Card_Weapon players_FlameScythe = new Card_Weapon("Flame Scythe", 5, 2, 2, 1);
    public static final Card_Weapon players_ThunderHammer = new Card_Weapon("Thunder Hammer", 4, 3, 3, 1);
    public static final Card_Weapon players_WindSaber = new Card_Weapon("Wind Saber", 2, 3, 2, 1);
    public static final Card_Weapon players_EarthStaff = new Card_Weapon("Earth Staff", 3, 2, 4, 1);
    public static final Card_Weapon players_LightBow = new Card_Weapon("Light Bow", 2, 2, 3, 1);
    public static final Card_Weapon players_DarkLance = new Card_Weapon("Dark Lance", 4, 4, 1, 1);



    public static final Card_Minion opponents_GuardianOfTheForest = new Card_Minion("Guardian Of The Forest", 1, 2, 3, 2);
    public static final Card_Minion opponents_MysticOwl = new Card_Minion("Mystic Owl", 3, 2, 4, 2);
    public static final Card_Minion opponents_ThunderLizard = new Card_Minion("Thunder Lizard", 4, 5, 6, 2);
    public static final Card_Minion opponents_IceSorceress = new Card_Minion("Ice Sorceress", 6, 4, 7, 2);
    public static final Card_Minion opponents_FireImp = new Card_Minion("Fire Imp", 2, 3, 2, 2);
    public static final Card_Minion opponents_WaterElemental = new Card_Minion("Water Elemental", 4, 3, 6, 2);
    public static final Card_Minion opponents_EarthGiant = new Card_Minion("Earth Giant", 8, 8, 8, 2);
    public static final Card_Minion opponents_WindSprinter = new Card_Minion("Wind Sprinter", 3, 4, 3, 2);
    public static final Card_Minion opponents_ShadowAssassin = new Card_Minion("Shadow Assassin", 5, 5, 5, 2);
    public static final Card_Minion opponents_Lightwarden = new Card_Minion("Lightwarden", 2, 1, 1, 2);

    // Spells
    public static final Card_Spell opponents_Fireball = new Card_Spell("Fireball", 3, 2, 2);
    public static final Card_Spell opponents_LightningBolt = new Card_Spell("Lightning Bolt", 1, 3, 2);
    public static final Card_Spell opponents_FrostNova = new Card_Spell("Frost Nova", 2, 2, 2);
    public static final Card_Spell opponents_ShadowFury = new Card_Spell("Shadow Fury", 4, 4, 2);
    public static final Card_Spell opponents_Earthquake = new Card_Spell("Earthquake", 5, 5, 2);
    public static final Card_Spell opponents_ArcaneBlast = new Card_Spell("Arcane Blast", 2, 2, 2);
    public static final Card_Spell opponents_SolarBeam = new Card_Spell("Solar Beam", 3, 3, 2);
    public static final Card_Spell opponents_VoidTerror = new Card_Spell("Void Terror", 4, 4, 2);
    public static final Card_Spell opponents_IceArrow = new Card_Spell("Ice Arrow", 1, 1, 2);
    public static final Card_Spell opponents_ThunderStrike = new Card_Spell("Thunder Strike", 2, 2, 2);

    // Weapons
    public static final Card_Weapon opponents_BattleAxe = new Card_Weapon("Battle Axe", 3, 2, 2, 2);
    public static final Card_Weapon opponents_CrystalSword = new Card_Weapon("Crystal Sword", 4, 3, 3, 2);
    public static final Card_Weapon opponents_ShadowDagger = new Card_Weapon("Shadow Dagger", 2, 1, 4, 2);
    public static final Card_Weapon opponents_FrostMace = new Card_Weapon("Frost Mace", 3, 4, 1, 2);
    public static final Card_Weapon opponents_FlameScythe = new Card_Weapon("Flame Scythe", 5, 2, 2, 2);
    public static final Card_Weapon opponents_ThunderHammer = new Card_Weapon("Thunder Hammer", 4, 3, 3, 2);
    public static final Card_Weapon opponents_WindSaber = new Card_Weapon("Wind Saber", 2, 3, 2, 2);
    public static final Card_Weapon opponents_EarthStaff = new Card_Weapon("Earth Staff", 3, 2, 4, 2);
    public static final Card_Weapon opponents_LightBow = new Card_Weapon("Light Bow", 2, 2, 3, 2);
    public static final Card_Weapon opponents_DarkLance = new Card_Weapon("Dark Lance", 4, 4, 1, 2);

}
