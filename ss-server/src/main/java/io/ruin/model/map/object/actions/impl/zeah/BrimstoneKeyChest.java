package io.ruin.model.map.object.actions.impl.zeah;

import io.ruin.api.utils.Random;
import io.ruin.discord.DiscordMessager;
import io.ruin.model.item.Item;
import io.ruin.model.map.object.actions.ObjectAction;
import io.ruin.utility.Broadcast;

import static io.ruin.cache.ItemID.*;

public class BrimstoneKeyChest {

    private static final Item[][] LOOTS = {
            new Item[]{new Item(COAL_NOTED, 300)},
            new Item[]{new Item(COINS_995, 50000)},
            new Item[]{new Item(UNCUT_DIAMOND_NOTED, 30)},
            new Item[]{new Item(UNCUT_RUBY_NOTED, 30)},
            new Item[]{new Item(GOLD_ORE_NOTED, 200)},
            new Item[]{new Item(DRAGON_ARROWTIPS, 100)},
            new Item[]{new Item(IRON_ORE_NOTED, 400)},
            new Item[]{new Item(RUNE_FULL_HELM_NOTED, 2)},
            new Item[]{new Item(RUNE_PLATEBODY_NOTED, 2)},
            new Item[]{new Item(RUNE_PLATELEGS_NOTED, 2)},
            new Item[]{new Item(RUNITE_ORE_NOTED, 15)},
            new Item[]{new Item(STEEL_BAR_NOTED, 400)},
            new Item[]{new Item(MAGIC_LOGS_NOTED, 5)},
            new Item[]{new Item(DRAGON_DART_TIP, 160)},
            new Item[]{new Item(PALM_TREE_SEED, 4)},
            new Item[]{new Item(MAGIC_SEED, 2)},
            new Item[]{new Item(CELASTRUS_SEED, 2)},
            new Item[]{new Item(DRAGONFRUIT_TREE_SEED, 2)},
            new Item[]{new Item(REDWOOD_TREE_SEED, 1)},
            new Item[]{new Item(TORSTOL_SEED, 3)},
            new Item[]{new Item(SNAPDRAGON_SEED, 4)},
            new Item[]{new Item(RANARR_SEED, 5)},
            new Item[]{new Item(PURE_ESSENCE_NOTED, 6000)},
            new Item[]{new Item(RAW_LOBSTER_NOTED, 350)},
            new Item[]{new Item(RAW_TUNA_NOTED, 300)},
            new Item[]{new Item(RAW_SWORDFISH_NOTED, 250)},
            new Item[]{new Item(RAW_MONKFISH_NOTED, 200)},
            new Item[]{new Item(RAW_SHARK_NOTED, 150)},
            new Item[]{new Item(RAW_SEA_TURTLE_NOTED, 80)},
            new Item[]{new Item(RAW_MANTA_RAY_NOTED, 80)}
    };

    private static final Item[] RARE_LOOT = {
            /**
             * Dusk Mystic
            */
            new Item(MYSTIC_BOOTS_DUSK, 1),
            new Item(MYSTIC_HAT_DUSK, 1),
            new Item(MYSTIC_ROBE_BOTTOM_DUSK, 1),
            new Item(MYSTIC_ROBE_TOP_DUSK, 1),
            new Item(MYSTIC_GLOVES_DUSK, 1),
            /**
             * Broken Dragon Hasta
            */
            new Item(BROKEN_DRAGON_HASTA, 1),
    };


    static {
        ObjectAction.register(34662, 1, (player, obj) -> {
            Item brimstoneKey = player.getInventory().findItem(BRIMSTONE_KEY);
            if (brimstoneKey == null) {
                player.sendFilteredMessage("You need a brimstone key to open this chest.");
                return;
            }

            player.startEvent(event -> {
                player.lock();
                player.sendFilteredMessage("You unlock the chest with your key.");
                brimstoneKey.remove(1);
                player.privateSound(51);
                player.animate(536);
                if (Random.get() <= 0.004) { // 1/250
                    /**
                     * Rare loot
                     */
                    Item loot = RARE_LOOT[Random.get(RARE_LOOT.length - 1)];
                    player.getInventory().addOrDrop(loot.getId(), loot.getAmount());
                    Broadcast.WORLD.sendNews(player.getName() + " just received " + loot.getDef().descriptiveName + " from the brimstone chest!");
                    DiscordMessager.sendBroadcastMessage(player.getName() + " just received " + loot.getDef().descriptiveName + " from the brimstone chest!");
                } else {
                    /**
                     * Regular loot
                     */
                    Item[] loot = LOOTS[Random.get(LOOTS.length - 1)];
                    for(Item item : loot)
                        player.getInventory().addOrDrop(item.getId(), item.getAmount());
                }
                event.delay(1);
                player.unlock();
            });
        });
    }
}
