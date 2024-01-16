package io.ruin.model.entity.npc.actions;

import io.ruin.cache.ItemID;
import io.ruin.model.achievements.listeners.ardougne.ArdyEasy;
import io.ruin.model.entity.npc.NPCAction;
import io.ruin.model.inter.dialogue.ItemDialogue;
import io.ruin.model.inter.dialogue.NPCDialogue;
import io.ruin.model.inter.dialogue.OptionsDialogue;
import io.ruin.model.inter.dialogue.PlayerDialogue;
import io.ruin.model.inter.utils.Option;
import io.ruin.model.item.loot.LootItem;
import io.ruin.model.item.loot.LootTable;
import io.ruin.model.shop.ShopManager;

import static io.ruin.cache.ItemID.*;

public class ArdyNpcs {

    private static final LootTable TindelSwords = new LootTable()
            .addTable(2, //low level swords
                    new LootItem(ItemID.IRON_LONGSWORD, 1, 1, 3),
                    new LootItem(ItemID.IRON_SWORD, 1, 1, 3),
                    new LootItem(BRONZE_SWORD, 1, 1, 3),
                    new LootItem(BRONZE_LONGSWORD, 1, 1, 3),
                    new LootItem(STEEL_SWORD, 1, 1, 1),
                    new LootItem(STEEL_LONGSWORD, 1, 1, 1)
            )
            .addTable(1, //medium level swords
                    new LootItem(ItemID.BLACK_SWORD, 1, 1, 3),
                    new LootItem(ItemID.BLACK_LONGSWORD, 1, 1, 1)
            )
            .addTable(1, //high swords
                    new LootItem(ItemID.MITHRIL_SWORD, 1, 1, 5),
                    new LootItem(ItemID.MITHRIL_LONGSWORD, 1, 1, 5),
                    new LootItem(COINS_995, 2000, 2000, 1)
            );


    static {
        /** Silk Merchant **/
        NPCAction.register(3211, "talk-to", (player, npc) -> {
            if (player.getInventory().hasItem(ItemID.SILK, 1)) {
                player.dialogue(
                        new PlayerDialogue("Hello. I have some fine silk from far away to sell to you."),
                        new NPCDialogue(npc, "Ah I may be interested in that. What sort of price were you looking at per piece of silk?"),
                        new OptionsDialogue(
                                new Option("20 coins?", () -> {
                                    player.dialogue(
                                            new PlayerDialogue("20 coins."),
                                            new NPCDialogue(npc, "Ok suits me."));
                                    int silkAmount = player.getInventory().getAmount(950);
                                    player.getInventory().remove(950, silkAmount);
                                    player.getInventory().add(995, silkAmount * 20);
                                }),
                                new Option("120 coins.", () -> {
                                    player.dialogue(
                                            new PlayerDialogue("120 coins."),
                                            new NPCDialogue(npc, "You'll never get that much for it. I'll be generous and give you 50 for it."),
                                            new PlayerDialogue("I'll give it to you for 60."),
                                            new NPCDialogue(npc, "You drive a hard bargain, but I guess that will have to do."));
                                    int silkAmount = player.getInventory().getAmount(950);
                                    player.getInventory().remove(ItemID.SILK, silkAmount);
                                    player.getInventory().add(ItemID.COINS_995, silkAmount * 60);
                                    if (!ArdyEasy.isTaskCompleted(player, 3)) {
                                        ArdyEasy.completeTask(player, 3);
                                        player.sendMessage("<col=800000>Well done! You have completed an easy task in the Ardougne area. Your Achievement Diary has been updated.</col>");
                                    }
                                })
                        ));
            } else {
                player.dialogue(new NPCDialogue(npc, "I buy silk. If you ever want to sell any silk, bring it here."));
            }

        });

        /** Tindel Merchant **/
        NPCAction.register(1358, "give-sword", (player, npc) -> {
            if (player.getInventory().hasItem(686, 1)) {
                if (player.getInventory().hasItem(995, 100)) {
                    player.getInventory().remove(995, 100);
                    player.getInventory().remove(686, 1);
                    TindelSwords.rollItems(true).forEach(lootItem -> {
                        player.getInventory().add(lootItem.getId(), lootItem.getAmount());
                        player.dialogue(new ItemDialogue().two(686, 995,"You hand Tindel 100 coins plus the rusty sword."),
                                new NPCDialogue(npc, "There you go my friend, it turned out to be a " + lootItem.getDef().name + "."));
                    });
                    if (!ArdyEasy.isTaskCompleted(player, 6)) {
                        ArdyEasy.completeTask(player, 6);
                        player.sendMessage("<col=800000>Well done! You have completed an easy task in the Ardougne area. Your Achievement Diary has been updated.</col>");
                    }
                } else {
                    player.dialogue(new NPCDialogue(npc, "Sorry my friend, but you don't seem to have enough money to cover my fee!"));
                }
            } else {
                player.dialogue(new NPCDialogue(npc, "Sorry my friend, but you don't seem to have any swords that need to be identified."));
            }
        });

        /** Aleck **/
        NPCAction.register(1501, "trade", (player, npc) -> {
            if (!ArdyEasy.isTaskCompleted(player, 8)) {
                ArdyEasy.completeTask(player, 8);
                player.sendMessage("<col=800000>Well done! You have completed an easy task in the Ardougne area. Your Achievement Diary has been updated.</col>");
            }
            ShopManager.openIfExists(player, "96929497-d504-450e-b3cd-476a03c8798b");
        });

        /** Two-pints **/
        NPCAction.register(5519, "talk-to", (player, npc) -> {
            if (player.ArdyEasyComplete && !player.ArdyEasyClaimed) {
                player.dialogue(new PlayerDialogue("I've completed all of the easy tasks in my Ardougne achievement diary!"),
                        new NPCDialogue(npc, "I can see that, well done! You'll be wanting your reward then!"),
                        new PlayerDialogue("Yes please!"),
                        new NPCDialogue(npc, "This cloak is a symbol of your exploration of Ardougne. It will allow you to teleport to Ardougne Monastery at anytime."),
                        new PlayerDialogue("Thanks!"));
                player.getInventory().add(ARDOUGNE_CLOAK_1, 1);
                player.getInventory().add(ANTIQUE_LAMP, 1);
                player.ArdyEasyClaimed = true;
            } else {
            player.dialogue(new NPCDialogue(npc, "I have more chat options coming soon."));
            }
        });
    }
}
