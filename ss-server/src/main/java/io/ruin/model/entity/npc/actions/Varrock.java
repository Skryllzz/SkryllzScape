package io.ruin.model.entity.npc.actions;

import io.ruin.cache.ItemID;
import io.ruin.cache.NpcID;
import io.ruin.model.entity.npc.NPCAction;
import io.ruin.model.entity.shared.listeners.SpawnListener;
import io.ruin.model.inter.dialogue.NPCDialogue;
import io.ruin.model.inter.dialogue.OptionsDialogue;
import io.ruin.model.inter.dialogue.PlayerDialogue;
import io.ruin.model.inter.utils.Option;
import io.ruin.model.stat.StatType;

import static io.ruin.cache.ItemID.COINS_995;

public class Varrock {

    static {
        SpawnListener.register(5422, npc -> npc.skipReachCheck = p -> p.equals(1264, 3500));
        SpawnListener.register(5422, npc -> npc.skipReachCheck = p -> p.equals(3302, 3491));

        /** Dimintheis **/
        NPCAction.register(NpcID.DIMINTHEIS, "talk-to", (player, npc) -> {
            if (player.getStats().check(StatType.Mining, 40) && player.getStats().check(StatType.Smithing,40)
                && player.getStats().check(StatType.Magic, 59) && player.getStats().check(StatType.Crafting, 40)) {
                player.dialogue(new PlayerDialogue("Hello Dimintheis."),
                        new NPCDialogue(npc, "I will not forget what you did for my family [player name]. Those 500,000 coins have allowed us to put food on the table."),
                        new PlayerDialogue("I was wondering if I could get a pair of steel gauntlets?"),
                        new NPCDialogue(npc, "Sure, but that is going to cost you 25,000 coins"),
                        new OptionsDialogue(
                                new Option("Sorry, that's overpiced.", () -> {
                                    player.dialogue(new NPCDialogue(npc, "I have to make a living, come back if you change your mind."));
                                }),
                                new Option("Okay than.", () -> {
                                    if (player.getInventory().hasItem(995, 25000)) {
                                        player.getInventory().remove(995, 25000);
                                        player.getInventory().add(ItemID.STEEL_GAUNTLETS, 1);
                                    } else {
                                        player.dialogue( new PlayerDialogue("But, unfortunately, I don't have enough money with me"),
                                                new NPCDialogue(npc, "Well, come back and see me when you do."));
                                    }
                                })
                        ));
            }
        });

        /** SUROK MAGIS **/
        NPCAction.register(NpcID.SUROK_MAGIS, "talk-to", (player, npc) -> {
            player.dialogue(new NPCDialogue(npc, "Hello " + player.getName() + " what do you need?."),
                    new OptionsDialogue(
                            new Option("Would like to purchase some PN Credits.", () -> {
                                player.dialogue(new PlayerDialogue("I would like to purchase some PN Credits."),
                                                new NPCDialogue(npc, "That is going to cost you 10,000 gp per credit, would you like to continue?"),
                                                new OptionsDialogue(
                                                        new Option("Yes", () -> {
                                                            player.integerInput("How many credits would you like?", amt -> {
                                                               if (player.getInventory().hasItem(COINS_995, 10000 * amt)) {
                                                                   npc.startEvent(event -> {
                                                                       npc.face(player);
                                                                       npc.forceText("hocus-pocus....");
                                                                       npc.animate(724);
                                                                       npc.graphics(343);
                                                                       player.getInventory().remove(COINS_995, 10000 * amt);
                                                                       player.PNCredit += amt;
                                                                       event.delay(2);
                                                                       player.dialogue(new NPCDialogue(npc, "I have charged your soul with " + amt + " PN Credits."),
                                                                               new PlayerDialogue("Thanks!"));
                                                                       npc.faceNone(true);
                                                                   });
                                                               } else {
                                                                   player.dialogue(new PlayerDialogue("I'm sorry, but you don't have enough money."));
                                                               }
                                                            });
                                                        }),
                                                        new Option("No", () -> {
                                                            player.dialogue(new NPCDialogue(npc, "Ok, come back when you change your mind."));
                                                        })
                                                ));
                            }),
                            new Option("Nothing at the moment", () -> {
                               player.closeDialogue();
                            })
                    ));
        });
    }
}
