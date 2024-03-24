package io.ruin.model.skills.slayer;

import io.ruin.model.map.Bounds;

import java.util.HashMap;
import java.util.Map;

public class KonarLocations {
    public enum Area {
        SLAYER_TOWER_1st_FLOOR,
        SLAYER_TOWER_2nd_FLOOR,
        SLAYER_TOWER_3rd_FLOOR,
        CATACOMBS_OF_KOUREND,
        STRONGHOLD_SLAYER_CAVE,
        ABYSS,
        GOD_WARS_DUNGEON,
        TAVERLEY_DUNGEON,
        BRIMHAVEN_DUNGEON,
        CHASM_OF_FIRE_1st_FLOOR,
        CHASM_OF_FIRE_2nd_FLOOR,
        CHASM_OF_FIRE_3rd_FLOOR,
        KRAKEN_COVE,
        KARUULM_SLAYER_DUNGEON,
        TROLL_STRONGHOLD,
        FORTHOS_DUNGEON,

        KALPHITE_LAIR,
        ASGARNIAN_ICE_DUNGEON,

        STRONGHOLD_SECURITY_DUNGEON,
        FREMENNIK_SLAYER_DUNGEON,
        ANCIENT_CAVERN_BOTTOM_FLOOR,
        ANCIENT_CAVERN_TOP_FLOOR,
        SMOKE_DUNGEON,
        LITHKREN_LABORATORY,
        NONE
        // Add more area enums as needed
    }

    public static final Map<String, Area> slayerMonsterAreas = new HashMap<>();

    static {
        slayerMonsterAreas.put("Aberrant spectre", Area.SLAYER_TOWER_2nd_FLOOR);
        slayerMonsterAreas.put("Cave Kraken", Area.KRAKEN_COVE);
        slayerMonsterAreas.put("Fire Giants", Area.STRONGHOLD_SLAYER_CAVE);
        slayerMonsterAreas.put("Trolls", Area.STRONGHOLD_SLAYER_CAVE);
        slayerMonsterAreas.put("Blue Dragons", Area.TAVERLEY_DUNGEON);
        slayerMonsterAreas.put("Red Dragons", Area.FORTHOS_DUNGEON);
        slayerMonsterAreas.put("Black Dragons", Area.CATACOMBS_OF_KOUREND);
        slayerMonsterAreas.put("Greater Demons", Area.CHASM_OF_FIRE_2nd_FLOOR);
        slayerMonsterAreas.put("Black Demons", Area.CHASM_OF_FIRE_1st_FLOOR);
        slayerMonsterAreas.put("Hellhounds", Area.TAVERLEY_DUNGEON);
        slayerMonsterAreas.put("Dagannoth", Area.CATACOMBS_OF_KOUREND);
        slayerMonsterAreas.put("Turoth", Area.FREMENNIK_SLAYER_DUNGEON);
        slayerMonsterAreas.put("Abyssal Demons", Area.SLAYER_TOWER_3rd_FLOOR);
        slayerMonsterAreas.put("Basilisks", Area.FREMENNIK_SLAYER_DUNGEON);
        slayerMonsterAreas.put("Kurask", Area.FREMENNIK_SLAYER_DUNGEON);
        slayerMonsterAreas.put("Gargoyles", Area.SLAYER_TOWER_2nd_FLOOR);
        slayerMonsterAreas.put("Bloodveld", Area.STRONGHOLD_SLAYER_CAVE);
        slayerMonsterAreas.put("Dust Devils", Area.CATACOMBS_OF_KOUREND);
        slayerMonsterAreas.put("Jellies", Area.FREMENNIK_SLAYER_DUNGEON);
        slayerMonsterAreas.put("Nechryael", Area.SLAYER_TOWER_3rd_FLOOR);
        slayerMonsterAreas.put("Kalphite", Area.KALPHITE_LAIR);
        slayerMonsterAreas.put("Bronze Dragons", Area.BRIMHAVEN_DUNGEON);
        slayerMonsterAreas.put("Iron Dragons", Area.BRIMHAVEN_DUNGEON);
        slayerMonsterAreas.put("Steel Dragons", Area.BRIMHAVEN_DUNGEON);
        slayerMonsterAreas.put("Dark Beasts", Area.CATACOMBS_OF_KOUREND);
        slayerMonsterAreas.put("Skeletal Wyverns", Area.ASGARNIAN_ICE_DUNGEON);
        slayerMonsterAreas.put("Ankou", Area.STRONGHOLD_SECURITY_DUNGEON);
        slayerMonsterAreas.put("Waterfiends", Area.ANCIENT_CAVERN_BOTTOM_FLOOR);
        slayerMonsterAreas.put("Mithril Dragons", Area.ANCIENT_CAVERN_TOP_FLOOR);
        slayerMonsterAreas.put("Aviansies", Area.GOD_WARS_DUNGEON);
        slayerMonsterAreas.put("Smoke Devils", Area.SMOKE_DUNGEON);
        slayerMonsterAreas.put("Adamant Dragons", Area.LITHKREN_LABORATORY);
        slayerMonsterAreas.put("Rune Dragons", Area.LITHKREN_LABORATORY);
        slayerMonsterAreas.put("Wyrms", Area.KARUULM_SLAYER_DUNGEON);
        slayerMonsterAreas.put("Drakes", Area.KARUULM_SLAYER_DUNGEON);
        slayerMonsterAreas.put("Hydras", Area.KARUULM_SLAYER_DUNGEON);
    }

    public static String GetAreaName(Area area) {
        if (area == null) {
            return "Unknown Area";
        }

        switch (area) {
            case SLAYER_TOWER_1st_FLOOR:
            case SLAYER_TOWER_2nd_FLOOR:
            case SLAYER_TOWER_3rd_FLOOR:
                return "Slayer Tower";
            case CATACOMBS_OF_KOUREND:
                return "Catacombs of Kourend";
            case STRONGHOLD_SLAYER_CAVE:
                return "Stronghold Slayer Cave";
            case ABYSS:
                return "Abyss";
            case GOD_WARS_DUNGEON:
                return "God Wars Dungeon";
            case TAVERLEY_DUNGEON:
                return "Taverley Dungeon";
            case BRIMHAVEN_DUNGEON:
                return "Brimhaven Dungeon";
            case KRAKEN_COVE:
                return "Kraken Cove";
            case TROLL_STRONGHOLD:
                return "Troll Stronghold";
            case FORTHOS_DUNGEON:
                return "Forthos Dungeon";
            case CHASM_OF_FIRE_1st_FLOOR:
            case CHASM_OF_FIRE_2nd_FLOOR:
            case CHASM_OF_FIRE_3rd_FLOOR:
                return "Chasm of Fire";
            case KARUULM_SLAYER_DUNGEON:
                return "Karuulm Slayer Dungeon";
            case FREMENNIK_SLAYER_DUNGEON:
                return "Fremennik Slayer Dungeon";
            case KALPHITE_LAIR:
                return "Kalphite Lair";
            case ASGARNIAN_ICE_DUNGEON:
                return "Asgarnian Ice Dungeon";
            case STRONGHOLD_SECURITY_DUNGEON:
                return "Stronghold Security Dungeon";
            case ANCIENT_CAVERN_BOTTOM_FLOOR:
            case ANCIENT_CAVERN_TOP_FLOOR:
                return "Ancient Cavern";
            case SMOKE_DUNGEON:
                return "Smoke Dungeon";
            case LITHKREN_LABORATORY:
                return "Lithkren Laboratory";
            default:
                return "Unknown Area";
        }
    }
    public static Bounds getBoundsForMonster(String monsterName) {
        Area area = slayerMonsterAreas.getOrDefault(monsterName, Area.NONE);
        switch (area) {
            case SLAYER_TOWER_1st_FLOOR:
                return new Bounds(3404, 3531, 3454, 3579, 0);
            case SLAYER_TOWER_2nd_FLOOR:
                return new Bounds(3404, 3531, 3454, 3579, 1);
            case SLAYER_TOWER_3rd_FLOOR:
                return new Bounds(3404, 3531, 3454, 3579, 2);
            case KRAKEN_COVE:
                return new Bounds(2245, 9989, 2303, 10046, 0);
            case STRONGHOLD_SLAYER_CAVE:
                return new Bounds(2378, 9769, 2496, 9838, 0);
            case TROLL_STRONGHOLD:
                return new Bounds(2840, 3577, 2882, 3604, 0);
            case CATACOMBS_OF_KOUREND:
                return new Bounds(1598, 9986, 1726, 10105, 0);
            case ABYSS:
                return new Bounds(3012, 4803, 3068, 4927, 0);
            case GOD_WARS_DUNGEON:
                return new Bounds(2829, 5250, 2938, 5355, 2);
            case TAVERLEY_DUNGEON:
                return new Bounds(2835, 9729, 2940, 9850, 0);
            case BRIMHAVEN_DUNGEON:
                return new Bounds(2630, 9416, 2719, 9594, 0);
            case KARUULM_SLAYER_DUNGEON:
                return new Bounds(1281, 10179, 1386, 10287, 0);
            case FORTHOS_DUNGEON:
                return new Bounds(1795, 9924, 1855, 9977, 0);
            case FREMENNIK_SLAYER_DUNGEON:
                return new Bounds(2692, 9989, 2813, 10038, 0);
            case CHASM_OF_FIRE_1st_FLOOR:
                return new Bounds(1410, 10055, 1465, 10106, 1);
            case CHASM_OF_FIRE_2nd_FLOOR:
                return new Bounds(1410, 10055, 1465, 10106, 2);
            case CHASM_OF_FIRE_3rd_FLOOR:
                return new Bounds(1410, 10055, 1465, 10106, 3);
            case KALPHITE_LAIR:
                return new Bounds(3461, 9475, 3517, 9530, 2);
            case ASGARNIAN_ICE_DUNGEON:
                return new Bounds(3027, 9540, 3068, 9592, 0);
            case STRONGHOLD_SECURITY_DUNGEON:
                return new Bounds(2306, 5185, 2365, 5247, 0);
            case ANCIENT_CAVERN_BOTTOM_FLOOR:
                return new Bounds(1729, 5318, 1779, 5368, 0);
            case ANCIENT_CAVERN_TOP_FLOOR:
                return new Bounds(1739, 5280, 1790, 5365, 1);
            case SMOKE_DUNGEON:
                return new Bounds(2379, 9417, 2428, 9466, 0);
            case LITHKREN_LABORATORY:
                return new Bounds(1539, 5059, 1597, 5111, 0);
                        // Add cases for more areas as needed
            default:
                return null; // Return null if area not found
        }
    }
}