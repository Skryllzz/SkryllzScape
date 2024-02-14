package io.ruin.model.skills.cooking;

import io.ruin.model.entity.player.Player;
import io.ruin.model.item.Item;
import io.ruin.model.item.actions.ItemAction;
import io.ruin.model.item.actions.ItemItemAction;
import io.ruin.model.stat.StatType;

import static io.ruin.cache.ItemID.*;

public enum Pie {

    //Normal log
    REDBERRY(REDBERRIES, PIE_SHELL,10, 1.0, new Item(UNCOOKED_BERRY_PIE, 1), "Uncooked RedBerry Pie", ""),
    MEAT(COOKED_MEAT, PIE_SHELL,20, 1.0, new Item(UNCOOKED_MEAT_PIE, 1), "Uncooked Meat Pie", ""),
    MUD(PART_MUD_PIE_7166, CLAY,  29, 1.0, new Item(RAW_MUD_PIE, 1), "Raw Mud Pie", ""),
    APPLE(COOKING_APPLE, PIE_SHELL,30, 1.0, new Item(UNCOOKED_APPLE_PIE, 1), "Uncooked Apple Pie", ""),
    GARDEN(PART_GARDEN_PIE_7174, CABBAGE, 34, 1.0, new Item(RAW_GARDEN_PIE, 1), "Raw Garden Pie", ""),
    FISH(PART_FISH_PIE_7184, POTATO,  47, 1.0, new Item(RAW_FISH_PIE, 1), "Raw Fish Pie", ""),
    BOTANICAL(GOLOVANOVA_FRUIT_TOP, PIE_SHELL, 52, 1.0, new Item(UNCOOKED_BOTANICAL_PIE, 1), "Uncooked Botanical Pie", ""),
    MUSHROOM(SULLIUSCEP_CAP, PIE_SHELL,  60, 1.0, new Item(UNCOOKED_MUSHROOM_PIE, 1), "Uncooked Mushroom Pie", ""),
    ADMIRAL(PART_ADMIRAL_PIE_7194, POTATO,70, 1.0, new Item(RAW_ADMIRAL_PIE, 1), "Raw Admiral Pie", ""),
    DRAGON(DRAGONFRUIT, PIE_SHELL, 73, 1.0, new Item(UNCOOKED_DRAGONFRUIT_PIE, 1), "Uncooked Dragonfruit Pie", ""),
    WILD(PART_WILD_PIE_7204, RAW_RABBIT, 85, 1.0, new Item(RAW_WILD_PIE, 1), "Raw Wild Pie", ""),
    SUMMER(PART_SUMMER_PIE_7214, COOKING_APPLE, 90, 1.0, new Item(RAW_SUMMER_PIE, 1), "Raw Summer Pie", ""),
    ;
    public final int fill, ing, levelReq;
    public final double exp;
    public final Item item;
    public final String name, descriptionName;

    Pie(int fill, int ing, int levelReq, double exp, Item item, String name, String descriptionName) {
        this.fill = fill;
        this.ing = ing;
        this.levelReq = levelReq;
        this.exp = exp;
        this.item = item;
        this.name = name;
        this.descriptionName = descriptionName;
    }

    private static void makePie(Player player, Pie id, int amount) {
        if(!player.getStats().check(StatType.Cooking, id.levelReq, id.descriptionName))
            return;
        player.startEvent(event -> {
            int made = 0;
            while (made++ < amount) {
                player.lock();
                Item piefill = player.getInventory().findItem(id.fill);
                Item pieing = player.getInventory().findItem(id.ing);
                if(piefill == null) {
                    player.unlock();
                    break;
                }
                piefill.remove( 1);
                pieing.remove( 1);
                player.getInventory().add(id.item);
                player.sendFilteredMessage("You mix your ingredients and make a " + id.name + ".");
                player.getStats().addXp(StatType.Cooking, id.exp, true);
                event.delay(2);
                player.unlock();
            }
        });
        player.unlock();
    }

    private static void makePieShell(Player player) {
                player.getInventory().remove(PASTRY_DOUGH, 1);
                player.getInventory().remove(PIE_DISH, 1);
                player.getInventory().add(PIE_SHELL);
    }

    private static void MudPiePart1(Player player) {
        player.getInventory().remove(PIE_SHELL, 1);
        player.getInventory().remove(COMPOST, 1);
        player.getInventory().add(PART_MUD_PIE);
    }

    private static void MudPiePart2(Player player) {
        player.getInventory().remove(PART_MUD_PIE, 1);
        player.getInventory().remove(BUCKET_OF_WATER, 1);
        player.getInventory().add(PART_MUD_PIE_7166);
    }

    private static void FishPiePart1(Player player) {
        player.getInventory().remove(PIE_SHELL, 1);
        player.getInventory().remove(TROUT, 1);
        player.getInventory().add(PART_FISH_PIE);
    }

    private static void FishPiePart2(Player player) {
        player.getInventory().remove(PART_FISH_PIE, 1);
        player.getInventory().remove(COD, 1);
        player.getInventory().add(PART_FISH_PIE_7184);
    }

    private static void GardenPiePart1(Player player) {
        player.getInventory().remove(PIE_SHELL, 1);
        player.getInventory().remove(TOMATO, 1);
        player.getInventory().add(PART_GARDEN_PIE);
    }

    private static void GardenPiePart2(Player player) {
        player.getInventory().remove(PART_GARDEN_PIE, 1);
        player.getInventory().remove(ONION, 1);
        player.getInventory().add(PART_GARDEN_PIE_7174);
    }

    private static void AdmiralPiePart1(Player player) {
        player.getInventory().remove(PIE_SHELL, 1);
        player.getInventory().remove(SALMON, 1);
        player.getInventory().add(PART_ADMIRAL_PIE);
    }

    private static void AdmiralPiePart2(Player player) {
        player.getInventory().remove(PART_ADMIRAL_PIE, 1);
        player.getInventory().remove(TUNA, 1);
        player.getInventory().add(PART_ADMIRAL_PIE_7194);
    }

    private static void WildPiePart1(Player player) {
        player.getInventory().remove(PIE_SHELL, 1);
        player.getInventory().remove(RAW_BEAR_MEAT, 1);
        player.getInventory().add(PART_WILD_PIE);
    }

    private static void WildPiePart2(Player player) {
        player.getInventory().remove(PART_WILD_PIE, 1);
        player.getInventory().remove(RAW_CHOMPY, 1);
        player.getInventory().add(PART_WILD_PIE_7204);
    }

    private static void SummerPiePart1(Player player) {
        player.getInventory().remove(PIE_SHELL, 1);
        player.getInventory().remove(STRAWBERRY, 1);
        player.getInventory().add(PART_SUMMER_PIE);
    }

    private static void SummerPiePart2(Player player) {
        player.getInventory().remove(PART_SUMMER_PIE, 1);
        player.getInventory().remove(WATERMELON, 1);
        player.getInventory().add(PART_SUMMER_PIE_7214);
    }

    static {
        ItemAction.registerInventory(BURNT_PIE, 1, (player, item) -> {
            player.getInventory().remove(BURNT_PIE,1);
            player.getInventory().add(PIE_DISH, 1);
            player.sendMessage("You dump out the burnt food.");
        });
        ItemItemAction.register(PIE_DISH, PASTRY_DOUGH, (player, item, object) -> {
            makePieShell(player);
        });
        ItemItemAction.register(PASTRY_DOUGH, PIE_DISH, (player, item, object) -> {
            makePieShell(player);
        });
        ItemItemAction.register(PIE_SHELL, REDBERRIES, (player, item, object) -> {
            makePie(player, REDBERRY, 1);
        });
        ItemItemAction.register(PIE_SHELL, COOKED_MEAT, (player, item, object) -> {
            makePie(player, MEAT, 1);
        });

        ItemItemAction.register(PIE_SHELL, COMPOST, (player, item, object) -> {
            MudPiePart1(player);
        });
        ItemItemAction.register(PART_MUD_PIE, BUCKET_OF_WATER, (player, item, object) -> {
            MudPiePart2(player);
        });
        ItemItemAction.register(PART_MUD_PIE_7166, CLAY, (player, item, object) -> {
            makePie(player, MUD, 1);
        });
        ItemItemAction.register(PIE_SHELL, COOKING_APPLE, (player, item, object) -> {
            makePie(player, APPLE, 1);
        });
        ItemItemAction.register(PIE_SHELL, TROUT, (player, item, object) -> {
            FishPiePart1(player);
        });
        ItemItemAction.register(PART_FISH_PIE, COD, (player, item, object) -> {
            FishPiePart2(player);
        });
        ItemItemAction.register(PART_FISH_PIE_7184, POTATO, (player, item, object) -> {
            makePie(player, FISH, 1);
        });
        ItemItemAction.register(PIE_SHELL, TOMATO, (player, item, object) -> {
            GardenPiePart1(player);
        });
        ItemItemAction.register(PART_GARDEN_PIE, ONION, (player, item, object) -> {
            GardenPiePart2(player);
        });
        ItemItemAction.register(PART_GARDEN_PIE_7174, CABBAGE, (player, item, object) -> {
            makePie(player, GARDEN, 1);
        });
        ItemItemAction.register(PIE_SHELL, GOLOVANOVA_FRUIT_TOP, (player, item, object) -> {
            makePie(player, BOTANICAL, 1);
        });
        ItemItemAction.register(PIE_SHELL, SULLIUSCEP_CAP, (player, item, object) -> {
            makePie(player, MUSHROOM, 1);
        });
        ItemItemAction.register(PIE_SHELL, SALMON, (player, item, object) -> {
            AdmiralPiePart1(player);
        });
        ItemItemAction.register(PART_ADMIRAL_PIE, TUNA, (player, item, object) -> {
            AdmiralPiePart2(player);
        });
        ItemItemAction.register(PART_ADMIRAL_PIE_7194, POTATO, (player, item, object) -> {
            makePie(player, ADMIRAL, 1);
        });
        ItemItemAction.register(PIE_SHELL, DRAGONFRUIT, (player, item, object) -> {
            makePie(player, DRAGON, 1);
        });
        ItemItemAction.register(PIE_SHELL, RAW_BEAR_MEAT, (player, item, object) -> {
            WildPiePart1(player);
        });
        ItemItemAction.register(PART_WILD_PIE, RAW_CHOMPY, (player, item, object) -> {
            WildPiePart2(player);
        });
        ItemItemAction.register(PART_WILD_PIE_7204, RAW_RABBIT, (player, item, object) -> {
            makePie(player, WILD, 1);
        });
        ItemItemAction.register(PIE_SHELL, STRAWBERRY, (player, item, object) -> {
            SummerPiePart1(player);
        });
        ItemItemAction.register(PART_SUMMER_PIE, WATERMELON, (player, item, object) -> {
            SummerPiePart2(player);
        });
        ItemItemAction.register(PART_SUMMER_PIE_7214, COOKING_APPLE, (player, item, object) -> {
            makePie(player, SUMMER, 1);
        });
    }
}
