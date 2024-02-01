package io.ruin.model.map.object.actions.impl.dungeons;

import io.ruin.api.utils.Random;
import io.ruin.model.World;
import io.ruin.model.entity.player.Player;
import io.ruin.model.entity.shared.StepType;
import io.ruin.model.inter.dialogue.MessageDialogue;
import io.ruin.model.inter.utils.Config;
import io.ruin.model.item.loot.LootItem;
import io.ruin.model.item.loot.LootTable;
import io.ruin.model.map.object.GameObject;
import io.ruin.model.map.object.actions.ObjectAction;
import io.ruin.model.stat.StatType;

import static io.ruin.cache.ItemID.*;

public class HamHideout {

    static {
        /**
         * Trapdoor
         */
        ObjectAction.register(5492, 1, (player, obj) -> {
            if (Config.HAM_HIDEOUT_ENTRANCE.get(player) == 1) {
                player.sendFilteredMessage("You climb down through the trapdoor...");
                player.sendFilteredMessage("... and enter a dimly lit cavern area.");
                player.getMovement().teleport(3149, 9652, 0);
            } else {
                player.sendFilteredMessage("This trapdoor seems totally locked.");
            }
        });
        ObjectAction.register(5492, 5, (player, obj) -> player.startEvent(event -> {
            player.animate(537);
            player.sendFilteredMessage("You attempt to pick the lock on the trapdoor.");
            event.delay(5);
            player.animate(537);
            player.sendFilteredMessage("You attempt to pick the lock on the trapdoor.");
            event.delay(5);
            if (Random.rollPercent(70)) {
                player.sendFilteredMessage("You pick the lock on the trapdoor.");
                World.startEvent(e -> {
                    Config.HAM_HIDEOUT_ENTRANCE.set(player, 1);
                    e.delay(100);
                    Config.HAM_HIDEOUT_ENTRANCE.set(player, 0);
                });
            } else {
                player.sendFilteredMessage("You fail to pick the lock - your fingers get numb from fumbling with the lock.");
            }
        }));
        ObjectAction.register(5492, 2, (player, obj) -> {
            Config.HAM_HIDEOUT_ENTRANCE.set(player, 0);
            player.sendFilteredMessage("You close the trapdoor.");
        });
        /**
         * Ladder
         */
        ObjectAction.register(5493, 3149, 9653, 0, "climb-up", (player, obj) -> {
            player.getMovement().teleport(3166, 3251, 0);
            player.sendFilteredMessage("You leave the HAM Fanatics' Camp.");
        });

        /** Storeroom **/
        ObjectAction.register(10042, 3166, 9622, 0, "climb-down", (player, obj) -> {
            if (player.getStats().check(StatType.Mining,17) && player.getStats().check(StatType.Agility, 23) && player.getStats().check(StatType.Thieving, 23)) {
                player.animate(827);
                player.getMovement().teleport(2568, 5185, 0);
            } else {
                player.dialogue(new MessageDialogue("You need a mining level of 17, an agility level of 23, and a thieving level of 23 to enter the storeroom."));
            }
        });
        ObjectAction.register(15747, 2567, 5185, 0, "climb-up", (player, obj) -> {
            player.getMovement().teleport(3166, 9623, 0);
        });

        ObjectAction.register(15759, "pick-lock", (player, obj) -> {
                player.startEvent(event -> {
                    player.animate(537);
                    player.sendFilteredMessage("You attempt to pick the lock on the door.");
                    event.delay(5);
                    if (player.isAt(2577, 5198)) {
                        player.stepAbs(2576, 5198, StepType.FORCE_WALK);
                    }
                    if (player.isAt(2577, 5192)) {
                        player.stepAbs(2576, 5192, StepType.FORCE_WALK);
                    }
                    if (player.isAt(2566, 5192)) {
                        player.stepAbs(2567, 5192, StepType.FORCE_WALK);
                    }
                    if (player.isAt(2566, 5198)) {
                        player.stepAbs(2567, 5198, StepType.FORCE_WALK);
                    }
                });
        });

        ObjectAction.register(15759, "open", (player, obj) -> {
            if (player.isAt(2576, 5198)) {
                player.stepAbs(2577, 5198, StepType.FORCE_WALK);
            } else if (player.isAt(2576, 5192)) {
                player.stepAbs(2577, 5192, StepType.FORCE_WALK);
            } else if (player.isAt(2567, 5192)) {
                player.stepAbs(2566, 5192, StepType.FORCE_WALK);
            } else if (player.isAt(2567, 5198)) {
                player.stepAbs(2566, 5198, StepType.FORCE_WALK);
            }
        });

        /** Chests **/
        //Bronze Chest
        ObjectAction.register(15723, "open", HamHideout::bchestopen);
        //Iron Chest
        ObjectAction.register(15726, "open", HamHideout::ichestopen);
        //Silver Chest
        ObjectAction.register(15724, "open", HamHideout::silverchestopen);
        //Steel Chest
        ObjectAction.register(15722, "open", HamHideout::steelchestopen);

    }
//Item loot = new LarranChestLoot().rollItem();
private static void bchestopen(Player player, GameObject item) {
    if (player.getInventory().hasItem(BRONZE_KEY_8867, 1)) {
        player.startEvent(event -> {
            player.lock();
            player.sendFilteredMessage("You unlock the chest with your key.");
            player.getInventory().remove(BRONZE_KEY_8867, 1);
            player.privateSound(51);
            player.animate(536);
            player.getInventory().add(bronzechest.rollItem());
            event.delay(1);
            player.unlock();
        });
    } else {
        player.sendFilteredMessage("You need a bronze key to open this chest.");
    }
}

    private static void ichestopen(Player player, GameObject item) {
        if (player.getInventory().hasItem(IRON_KEY_8869, 1)) {
            player.startEvent(event -> {
                player.lock();
                player.sendFilteredMessage("You unlock the chest with your key.");
                player.getInventory().remove(IRON_KEY_8869, 1);
                player.privateSound(51);
                player.animate(536);
                player.getInventory().add(ironchest.rollItem());
                event.delay(1);
                player.unlock();
            });
        } else {
            player.sendFilteredMessage("You need a iron key to open this chest.");
        }
    }

    private static void silverchestopen(Player player, GameObject item) {
        if (player.getInventory().hasItem(SILVER_KEY, 1)) {
            player.startEvent(event -> {
                player.lock();
                player.sendFilteredMessage("You unlock the chest with your key.");
                player.getInventory().remove(SILVER_KEY, 1);
                player.privateSound(51);
                player.animate(536);
                player.getInventory().add(silverchest.rollItem());
                event.delay(1);
                player.unlock();
            });
        } else {
            player.sendFilteredMessage("You need a silver key to open this chest.");
        }
    }

    private static void steelchestopen(Player player, GameObject item) {
        if (player.getInventory().hasItem(STEEL_KEY, 1)) {
            player.startEvent(event -> {
                player.lock();
                player.sendFilteredMessage("You unlock the chest with your key.");
                player.getInventory().remove(STEEL_KEY, 1);
                player.privateSound(51);
                player.animate(536);
                player.getInventory().add(steelchest.rollItem());
                event.delay(1);
                player.unlock();
            });
        } else {
            player.sendFilteredMessage("You need a steel key to open this chest.");
        }
    }
    private static final LootTable bronzechest = new LootTable().addTable(1,
            new LootItem(COINS_995, 61, 5),
            new LootItem(GOLD_RING, 1, 5),
            new LootItem(GOLD_NECKLACE, 1, 5),
            new LootItem(GOLD_AMULET, 1, 5),
            new LootItem(SAPPHIRE_RING, 1, 1),
            new LootItem(SAPPHIRE_NECKLACE, 1, 1)
    );

    private static final LootTable ironchest = new LootTable().addTable(1,
            new LootItem(COINS_995, 100, 3),
            new LootItem(SAPPHIRE_NECKLACE, 1, 7),
            new LootItem(SAPPHIRE_RING, 1, 7),
            new LootItem(SAPPHIRE_AMULET, 1, 7),
            new LootItem(EMERALD_RING, 1, 3),
            new LootItem(EMERALD_AMULET, 1, 3),
            new LootItem(EMERALD_NECKLACE, 1, 3)
    );

    private static final LootTable silverchest = new LootTable().addTable(1,
            new LootItem(COINS_995, 150, 6),
            new LootItem(SAPPHIRE_NECKLACE, 1, 6),
            new LootItem(SAPPHIRE_RING, 1, 6),
            new LootItem(EMERALD_RING, 1, 7),
            new LootItem(EMERALD_NECKLACE, 1, 7),
            new LootItem(EMERALD_AMULET, 1, 7),
            new LootItem(RUBY_AMULET, 1, 5),
            new LootItem(RUBY_RING, 1, 5),
            new LootItem(RUBY_NECKLACE, 1, 5),
            new LootItem(DIAMOND_RING, 1, 2)
    );

    private static final LootTable steelchest = new LootTable().addTable(1,
            new LootItem(COINS_995, 200, 3),
            new LootItem(EMERALD_NECKLACE, 1, 3),
            new LootItem(EMERALD_AMULET, 1, 3),
            new LootItem(EMERALD_RING, 1, 3),
            new LootItem(RUBY_RING, 1, 4),
            new LootItem(RUBY_NECKLACE, 1, 4),
            new LootItem(RUBY_AMULET, 1, 5),
            new LootItem(DIAMOND_NECKLACE, 1, 5),
            new LootItem(DIAMOND_AMULET, 1, 5),
            new LootItem(DIAMOND_RING, 1, 5)
    );

}
