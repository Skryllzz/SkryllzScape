package io.ruin.model.map.object.actions.impl.edgeville;

import io.ruin.api.utils.Random;
import io.ruin.discord.DiscordMessager;
import io.ruin.model.World;
import io.ruin.model.item.Item;
import io.ruin.model.map.object.actions.ObjectAction;
import io.ruin.utility.Broadcast;

import static io.ruin.cache.ItemID.*;

public class CrystalKeyChest {

    private static final Item[][] LOOTS = {
            /**
             * Spinach roll & coins
            */
            new Item[]{
                    new Item(SPINACH_ROLL, 1),
                    new Item(COINS_995, 5000)
            },
            /**
             * Raw swordfish & coins
            */
            new Item[]{
                    new Item(COINS_995, 10000),
                    new Item(RAW_SWORDFISH_NOTED, 60)
            },
            /**
             * Runes
            */
            new Item[]{
                    new Item(DEATH_RUNE, 500),
                    new Item(SOUL_RUNE, 450),
                    new Item(BLOOD_RUNE, 450)
            },
            /**
             * Coal ore
            */
            new Item[]{
                    new Item(COAL_NOTED, 600)
            },
            /**
             * Gems
            */
            new Item[]{
                    new Item(UNCUT_DRAGONSTONE_NOTED, 5)
            },
            /**
             * Tooth half of a key & coins
            */
            new Item[]{
                    new Item(COINS_995, 750000),
                    new Item(TOOTH_HALF_OF_KEY, 1)
            },
            /**
             * Runite bars
            */
            new Item[]{
                    new Item(RUNITE_BAR_NOTED, 20)
            },
            /**
             * Loop half of a key
            */
            new Item[]{
                    new Item(COINS_995, 20000),
                    new Item(LOOP_HALF_OF_KEY, 1)
            },
            /**
             * Iron ore
            */
            new Item[]{
                    new Item(IRON_ORE_NOTED, 150)
            },
            /**
             * Adamant sq
            */
            new Item[]{
                    new Item(ADAMANT_SQ_SHIELD, 3)
            },
            /**
             * Rune platelegs/plateskirt
            */
            new Item[]{
                    new Item(RUNE_PLATELEGS_NOTED, 3),
                    new Item(RUNE_PLATESKIRT_NOTED, 3)
            }
    };

    private static final Item[] RARE_LOOT = {
            new Item(DRAGONSTONE_PLATEBODY, 1),
            new Item(DRAGONSTONE_PLATELEGS, 1),
            new Item(DRAGONSTONE_FULL_HELM, 1)
    };


    static {
        ObjectAction.register(172, "open", (player, obj) -> {
            Item crystalKey = player.getInventory().findItem(CRYSTAL_KEY);
            if (crystalKey == null) {
                player.sendFilteredMessage("You need a crystal key to open this chest.");
                return;
            }

            player.startEvent(event -> {
                player.lock();
                player.sendFilteredMessage("You unlock the chest with your key.");
                crystalKey.remove();
                player.privateSound(51);
                player.animate(536);
                World.startEvent(e -> {
                    obj.setId(173);
                    e.delay(2);
                    obj.setId(obj.originalId);
                });
                crystalKey.setId(UNCUT_DRAGONSTONE);
                if(Random.get() <= 0.004) { //1/250
                    /**
                     * Rare loot
                     */
                    Item loot = RARE_LOOT[Random.get(RARE_LOOT.length - 1)];
                    player.getInventory().add(loot.getId(), loot.getAmount());
                    Broadcast.WORLD.sendNews(player.getName() + " just received " + loot.getDef().descriptiveName + " from the crystal chest!");
                    DiscordMessager.sendBroadcastMessage(player.getName() + " just received " + loot.getDef().descriptiveName + " from the crystal chest!");
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
