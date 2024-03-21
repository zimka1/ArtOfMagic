package Cards;

import Cards.TypeOfCard.Card_Minion;
import Cards.TypeOfCard.Card_Spell;
import Cards.TypeOfCard.Card_Weapon;

public class CardLibrary {
    // Массивы карт для игрока
    public static final Card_Minion[] playerMinions = {
            new Card_Minion("Guardian Of The Forest", 1, 2, 3, 1),
            new Card_Minion("Mystic Owl", 3, 2, 4, 1),
            new Card_Minion("Thunder Lizard", 4, 5, 6, 1),
            new Card_Minion("Ice Sorceress", 6, 4, 7, 1),
            new Card_Minion("Fire Imp", 2, 3, 2, 1),
            new Card_Minion("Water Elemental", 4, 3, 6, 1),
            new Card_Minion("Earth Giant", 8, 8, 8, 1),
            new Card_Minion("Wind Sprinter", 3, 4, 3, 1),
            new Card_Minion("Shadow Assassin", 5, 5, 5, 1),
            new Card_Minion("Lightwarden", 2, 1, 1, 1)
    };

    public static final Card_Spell[] playerSpells = {
            new Card_Spell("Fireball", 3, 2, 1),
            new Card_Spell("Lightning Bolt", 1, 3, 1),
            new Card_Spell("Frost Nova", 2, 2, 1),
            new Card_Spell("Shadow Fury", 4, 4, 1),
            new Card_Spell("Earthquake", 5, 5, 1),
            new Card_Spell("Arcane Blast", 2, 2, 1),
            new Card_Spell("Solar Beam", 3, 3, 1),
            new Card_Spell("Void Terror", 4, 4, 1),
            new Card_Spell("Ice Arrow", 1, 1, 1),
            new Card_Spell("Thunder Strike", 2, 2, 1)
    };

    public static final Card_Weapon[] playerWeapons = {
            new Card_Weapon("Battle Axe", 3, 2, 2, 1),
            new Card_Weapon("Crystal Sword", 4, 3, 3, 1),
            new Card_Weapon("Shadow Dagger", 2, 1, 4, 1),
            new Card_Weapon("Frost Mace", 3, 4, 1, 1),
            new Card_Weapon("Flame Scythe", 5, 2, 2, 1),
            new Card_Weapon("Thunder Hammer", 4, 3, 3, 1),
            new Card_Weapon("Wind Saber", 2, 3, 2, 1),
            new Card_Weapon("Earth Staff", 3, 2, 4, 1),
            new Card_Weapon("Light Bow", 2, 2, 3, 1),
            new Card_Weapon("Dark Lance", 4, 4, 1, 1)
    };

    // Массивы карт для противника
    public static final Card_Minion[] opponentMinions = {
            new Card_Minion("Guardian Of The Forest", 1, 2, 3, 2),
            new Card_Minion("Mystic Owl", 3, 2, 4, 2),
            new Card_Minion("Thunder Lizard", 4, 5, 6, 2),
            new Card_Minion("Ice Sorceress", 6, 4, 7, 2),
            new Card_Minion("Fire Imp", 2, 3, 2, 2),
            new Card_Minion("Water Elemental", 4, 3, 6, 2),
            new Card_Minion("Earth Giant", 8, 8, 8, 2),
            new Card_Minion("Wind Sprinter", 3, 4, 3, 2),
            new Card_Minion("Shadow Assassin", 5, 5, 5, 2),
            new Card_Minion("Lightwarden", 2, 1, 1, 2)
    };

    public static final Card_Spell[] opponentSpells = {
            new Card_Spell("Fireball", 3, 2, 2),
            new Card_Spell("Lightning Bolt", 1, 3, 2),
            new Card_Spell("Frost Nova", 2, 2, 2),
            new Card_Spell("Shadow Fury", 4, 4, 2),
            new Card_Spell("Earthquake", 5, 5, 2),
            new Card_Spell("Arcane Blast", 2, 2, 2),
            new Card_Spell("Solar Beam", 3, 3, 2),
            new Card_Spell("Void Terror", 4, 4, 2),
            new Card_Spell("Ice Arrow", 1, 1, 2),
            new Card_Spell("Thunder Strike", 2, 2, 2)
    };

    public static final Card_Weapon[] opponentWeapons = {
            new Card_Weapon("Battle Axe", 3, 2, 2, 2),
            new Card_Weapon("Crystal Sword", 4, 3, 3, 2),
            new Card_Weapon("Shadow Dagger", 2, 1, 4, 2),
            new Card_Weapon("Frost Mace", 3, 4, 1, 2),
            new Card_Weapon("Flame Scythe", 5, 2, 2, 2),
            new Card_Weapon("Thunder Hammer", 4, 3, 3, 2),
            new Card_Weapon("Wind Saber", 2, 3, 2, 2),
            new Card_Weapon("Earth Staff", 3, 2, 4, 2),
            new Card_Weapon("Light Bow", 2, 2, 3, 2),
            new Card_Weapon("Dark Lance", 4, 4, 1, 2)
    };
}
