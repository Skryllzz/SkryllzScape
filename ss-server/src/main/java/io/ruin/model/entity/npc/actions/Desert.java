package io.ruin.model.entity.npc.actions;

import io.ruin.cache.ItemID;
import io.ruin.model.entity.npc.NPCAction;
import io.ruin.model.inter.dialogue.ItemDialogue;
import io.ruin.model.inter.dialogue.NPCDialogue;
import io.ruin.model.inter.dialogue.OptionsDialogue;
import io.ruin.model.inter.dialogue.PlayerDialogue;
import io.ruin.model.inter.utils.Option;
import io.ruin.model.item.loot.LootItem;
import io.ruin.model.item.loot.LootTable;
import io.ruin.model.shop.ShopManager;

public class Desert {

    private static final LootTable ALIJUNK = new LootTable()
            .addTable(1,
                    new LootItem(ItemID.BRONZE_PICKAXE, 1, 3000, 3),          // Bronze Pick
                    new LootItem(ItemID.BRONZE_AXE, 1, 3000, 3),          // Bronze Axe
                    new LootItem(ItemID.KNIFE, 1, 3000, 3),          // Knife
                    new LootItem(ItemID.CHISEL, 1, 1000, 1)           // Chisel
            );

    static {
        /** AliMorrisane **/
        NPCAction.register(3533, "talk-to", (player, npc) -> {
            player.dialogue(
                    new NPCDialogue(npc, "Hello "+ player.getName()+ ", Would you like to buy some equipment?"),
                    new OptionsDialogue(
                            new Option("Yes", () -> {
                                ShopManager.openIfExists(player, "08a45727-e7a0-4c42-b4e6-b56178f170b3");
                            }),
                            new Option("Not right now", () -> {
                                /*if (player.getInventory().hasItem(995, 1)) {
                                    player.getInventory().remove(995, (int)(Math.random() - 2));
                                    player.sendMessage("Dispite your best efforts, Ali still sells you some junk.");
                                    ItemContainer container = new ItemContainer();
                                    container.init(player, 6, -1, 64161, 141, false);
                                    container.sendAll = true;
                                    Item item;
                                    item = ALIJUNK.rollItem();
                                        int amount = item.getAmount() / 6;
                                        item.setAmount(amount);
                                    container.add(item);
                                    //player.getInventory().add(item, 1);
                                }*/
                            })
                    ));

        });

        /** Karim **/
        NPCAction.register(2877, "talk-to", (player, npc) -> {
            player.dialogue(
                    new NPCDialogue(npc, "Hello "+ player.getName()+ ", Would you like to buy a nice kebab? Only one gold."),
                    new OptionsDialogue(
                            new Option("Yes please.", () -> {
                                if (player.getInventory().hasItem(995, 1)) {
                                    player.getInventory().remove(995, 1);
                                    player.getInventory().add(1971, 1);
                                } else {
                                            player.dialogue(
                                                    new PlayerDialogue("Oops, I forgot to bring any money with me."),
                                                    new NPCDialogue(npc, "Come back when you have some."));
                                }
                            }),
                            new Option("I think I'll give it a miss.", () -> {
                                player.dialogue(new NPCDialogue(npc, "Come back if you change your mind."));
                            })
                    ));

        });

        /** Silk Trader **/
        NPCAction.register(2873, "talk-to", (player, npc) -> {
            player.dialogue(
                    new NPCDialogue(npc, "Hello "+ player.getName()+ ", Do you want to buy any fine silks?"),
                    new OptionsDialogue(
                            new Option("How much are they?", () -> {
                                player.dialogue(
                                        new NPCDialogue(npc, "3 gp."),
                                        new OptionsDialogue(
                                                new Option("No. That's too much for me.", () -> {
                                                    player.dialogue(
                                                            new PlayerDialogue("No. That's too much for me."),
                                                            new NPCDialogue(npc, "2 gp and that's as low as I'll go."),
                                                            new NPCDialogue(npc, "I'm not selling it for any less. You'll probably go and sell it in Varrock for a profit, anyway."),
                                                                            new OptionsDialogue(
                                                                                    new Option("Ok", () -> {
                                                                                        if (player.getInventory().hasItem(995, 2)) {
                                                                                            player.getInventory().add(950, 1);
                                                                                            player.getInventory().remove(995, 2);
                                                                                            player.dialogue(new ItemDialogue().one(950, "You buy some silk for 2 gp."));
                                                                                        } else {
                                                                                            player.sendMessage("You don't have enough coins.");
                                                                                        }
                                                                                    }),
                                                                                    new Option("No Thank you", () -> {
                                                                                        player.dialogue(new NPCDialogue(npc, "Your loss."));
                                                                                    })
                                                                            ));
                                                }),
                                                    new Option("Okay, that sounds good.", () -> {
                                                        if (player.getInventory().hasItem(995, 3)) {
                                                            player.getInventory().add(950, 1);
                                                            player.getInventory().remove(995, 3);
                                                            player.dialogue(new ItemDialogue().one(950, "You buy some silk for 3 gp."));
                                                        } else {
                                                            player.sendMessage("You don't have enough coins.");
                                                        }

                                            })
                                ));
                            }),
                            new Option("No. Silk doesn't suit me.", () -> {
                                player.dialogue(new PlayerDialogue("No. Silk doesn't suit me.."));
                            })
                    ));

        });

    }
}
