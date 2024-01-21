package io.ruin.model.entity.npc.actions;

import io.ruin.model.achievements.listeners.ardougne.ArdyEasy;
import io.ruin.model.entity.npc.NPCAction;
import io.ruin.model.inter.dialogue.*;
import io.ruin.model.inter.utils.Option;
import io.ruin.model.item.Item;
import io.ruin.model.item.loot.LootItem;
import io.ruin.model.item.loot.LootTable;
import io.ruin.model.shop.ShopManager;

import static io.ruin.cache.ItemID.*;
import static io.ruin.model.item.actions.impl.chargable.Ibans.addChargesIban;
import static io.ruin.model.item.actions.impl.chargable.Ibans.addChargesIbanU;

public class ArdyNpcs {

    private static final LootTable TindelSwords = new LootTable()
            .addTable(2, //low level swords
                    new LootItem(IRON_LONGSWORD, 1, 1, 3),
                    new LootItem(IRON_SWORD, 1, 1, 3),
                    new LootItem(BRONZE_SWORD, 1, 1, 3),
                    new LootItem(BRONZE_LONGSWORD, 1, 1, 3),
                    new LootItem(STEEL_SWORD, 1, 1, 1),
                    new LootItem(STEEL_LONGSWORD, 1, 1, 1)
            )
            .addTable(1, //medium level swords
                    new LootItem(BLACK_SWORD, 1, 1, 3),
                    new LootItem(BLACK_LONGSWORD, 1, 1, 1)
            )
            .addTable(1, //high swords
                    new LootItem(MITHRIL_SWORD, 1, 1, 5),
                    new LootItem(MITHRIL_LONGSWORD, 1, 1, 5),
                    new LootItem(COINS_995, 2000, 2000, 1)
            );


    static {
        /** Silk Merchant **/
        NPCAction.register(3211, "talk-to", (player, npc) -> {
            if (player.getInventory().hasItem(SILK, 1)) {
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
                                    player.getInventory().remove(SILK, silkAmount);
                                    player.getInventory().add(COINS_995, silkAmount * 60);
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

        /** Koftik **/
        NPCAction.register(3413, "talk-to", (player, npc) -> {
            if (player.getCombat().getLevel() >= 60) {
                player.dialogue(new PlayerDialogue("Hello Koftik, I understand you have the Iban's staff."),
                                new NPCDialogue(npc, "Hello, yes I have do, however it broke during my last battle"),
                                new PlayerDialogue("Oh no! Can it be fixed?."),
                                new NPCDialogue(npc, "I know the Dark Mage east from here can but I couldn't afford his fee"),
                                new NPCDialogue(npc,"If you want to give it a shot you can have it."),
                                new OptionsDialogue(
                                        new Option("Yes", () -> {
                                            player.getInventory().add(1410, 1);
                                            player.dialogue(new ItemDialogue().one(1410, "Koftik gives you the broken staff"),
                                                    new PlayerDialogue("Thank you!"),
                                                    new NPCDialogue(npc, "Good Luck!"));
                                    }),
                                        new Option("No", () -> {
                                            player.dialogue(new PlayerDialogue("Not, right now"),
                                                    new NPCDialogue(npc, "Let me know if you change your mind."));
                                    })));
        } else {
                player.dialogue(new PlayerDialogue("Hello Koftik, I understand you have the Iban's Staff."),
                                new NPCDialogue(npc, "Yes what's it to you?"),
                                new MessageDialogue("Koftik won't help you until you have at least 60 combat level."));
            }
        });

        /** Dark Mage **/
        NPCAction.register(7752, "talk-to", (player, npc) -> {
            if (player.getInventory().hasItem(IBANS_STAFF_1410, 1)) {
                player.dialogue(new NPCDialogue(npc, "Can I help you with something, traveller? I am experimenting with dark magic; it is a dangerous craft, and I prefer not to be disturbed."),
                        new PlayerDialogue("Can you repair my broken Staff of Iban?"),
                        new ItemDialogue().one(1410, "You show the mage your broken staff."),
                        new NPCDialogue(npc, "The Staff of Iban holds dangerous magic, traveller. I can fix it, but it will cost you - the process could kill me!"),
                        new PlayerDialogue("How much?"),
                        new NPCDialogue(npc, "200,000 coins. That'll give it 120 charges, enough to cast Iban's magic 120 times"),
                        new OptionsDialogue(
                                new Option("Repair the Staff", () -> {
                                    if (player.getInventory().hasItem(995,200000)) {
                                        player.getInventory().remove(995, 200000);
                                        player.getInventory().remove(IBANS_STAFF_1410,1);
                                        player.getInventory().add(IBANS_STAFF,1);
                                        player.dialogue(new ItemDialogue().one(IBANS_STAFF, "The Mage repairs the staff and hands you it."));
                                    } else {
                                        player.dialogue(new PlayerDialogue("I seem to not have enough money on me"),
                                                new NPCDialogue(npc, "You'll have to come back when you do."));
                                    }
                                }),
                                new Option("No thanks", () -> {
                                    player.dialogue(new PlayerDialogue("Not, right now"),
                                            new NPCDialogue(npc, "Let me know if you change your mind."));
                                })));
            } else if (player.getInventory().hasItem(IBANS_STAFF, 1)) {
                player.dialogue(new OptionsDialogue(
                        new Option("Talk about Staff Upgrade", () -> {
                            player.dialogue(new NPCDialogue(npc, "Can I help you with something, traveller? I am experimenting with dark magic; it is a dangerous craft, and I prefer not to be disturbed."),
                                    new PlayerDialogue("Can you make it hold more than 120 charges?"),
                                    new NPCDialogue(npc, "Hah! Yes, I can upgrade it to hold 2500 charges, enough to cast Iban's magic 2500 times. That'll cost you 400,000 coins."),
                                    new OptionsDialogue(
                                            new Option("Upgrade the Staff", () -> {
                                                if (player.getInventory().hasItem(995, 400000)) {
                                                    player.getInventory().remove(995, 400000);
                                                    player.getInventory().remove(IBANS_STAFF, 1);
                                                    player.getInventory().add(IBANS_STAFF_U, 1);
                                                    player.dialogue(new ItemDialogue().one(IBANS_STAFF_U, "The Mage upgrades the staff and hands you it."));
                                                } else {
                                                    player.dialogue(new PlayerDialogue("I seem to not have enough money on me"),
                                                            new NPCDialogue(npc, "You'll have to come back when you do."));
                                                }
                                            }),
                                            new Option("No thanks", () -> {
                                                player.dialogue(new PlayerDialogue("Not, right now"),
                                                        new NPCDialogue(npc, "Let me know if you change your mind."));
                                            })
                                    )
                            );
                        }),
                        new Option("Talk about recharging the staff", () -> {
                            player.dialogue(new PlayerDialogue("Can you recharge my Staff of Iban?"),
                                    new NPCDialogue(npc, "I'll want 100,000 coins to recharge the Staff of Iban."),
                                    new OptionsDialogue(
                                            new Option("Recharge the Staff", () -> {
                                                if (player.getInventory().hasItem(995, 100000)) {
                                                    player.getInventory().remove(995, 100000);
                                                    Item ibans = player.getInventory().findItem(IBANS_STAFF); // Replace IBANS_STAFF with the actual item ID of the ibans staff
                                                    if (ibans != null) {
                                                        addChargesIban(player, ibans);
                                                    }
                                                } else {
                                                    player.dialogue(new PlayerDialogue("I seem to not have enough money on me"),
                                                            new NPCDialogue(npc, "You'll have to come back when you do."));
                                                }
                                            }),
                                            new Option("No thanks", () -> {
                                                player.dialogue(new PlayerDialogue("Not, right now"),
                                                        new NPCDialogue(npc, "Let me know if you change your mind."));
                                            })
                                    )
                            );
                        })
                ));
            } else if (player.getInventory().hasItem(IBANS_STAFF_U,1)) {
                player.dialogue(new PlayerDialogue("Can you recharge my Staff of Iban?"),
                        new NPCDialogue(npc, "I'll want 100,000 coins to recharge the Staff of Iban."),
                        new OptionsDialogue(
                                new Option("Recharge the Staff", () -> {
                                    if (player.getInventory().hasItem(995, 100000)) {
                                        player.getInventory().remove(995, 100000);
                                        Item ibans = player.getInventory().findItem(IBANS_STAFF_U); // Replace IBANS_STAFF with the actual item ID of the ibans staff
                                        if (ibans != null) {
                                            addChargesIbanU(player, ibans);
                                        }
                                    } else {
                                        player.dialogue(new PlayerDialogue("I seem to not have enough money on me"),
                                                new NPCDialogue(npc, "You'll have to come back when you do."));
                                    }
                                }),
                                new Option("No thanks", () -> {
                                    player.dialogue(new PlayerDialogue("Not, right now"),
                                            new NPCDialogue(npc, "Let me know if you change your mind."));
                                })
                        )
                );
            } else {
                player.dialogue(new NPCDialogue(npc, "Don't bother me I am experimenting with dark magic."));
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
