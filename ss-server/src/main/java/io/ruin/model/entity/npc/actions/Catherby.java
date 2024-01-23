package io.ruin.model.entity.npc.actions;

import io.ruin.cache.ItemID;
import io.ruin.cache.NpcID;
import io.ruin.model.entity.npc.NPCAction;
import io.ruin.model.inter.dialogue.ItemDialogue;
import io.ruin.model.inter.dialogue.NPCDialogue;
import io.ruin.model.inter.dialogue.OptionsDialogue;
import io.ruin.model.inter.dialogue.PlayerDialogue;
import io.ruin.model.inter.utils.Option;
import io.ruin.model.stat.StatType;

public class Catherby {

    static {
        /** Caleb **/
        NPCAction.register(NpcID.CALEB, "talk-to", (player, npc) -> {
            if (player.getInventory().hasAtLeastOneOf(ItemID.STEEL_GAUNTLETS)) {
                player.dialogue(new PlayerDialogue("Hello Caleb, I got a pair of steel guantlets can you change them for me?"),
                        new NPCDialogue(npc, "Certainly!"),
                        new ItemDialogue().one(ItemID.STEEL_GAUNTLETS, "You hand your steel gauntlets to Caleb"),
                        new ItemDialogue().one(ItemID.COOKING_GAUNTLETS, "Caleb hands you cooking guantlets"),
                        new NPCDialogue(npc, "You will find the rate you burn certain foods is much reduced whilst wearing these gauntlets."),
                        new PlayerDialogue("Thank you"));
                        player.getInventory().remove(ItemID.STEEL_GAUNTLETS,1);
                        player.getInventory().add(ItemID.COOKING_GAUNTLETS,1);
            }
        });
        /** Hickton **/
        NPCAction.register(NpcID.HICKTON, "talk-to", (player, npc) -> {
            if (player.getStats().check(StatType.Thieving, 99)) {
                player.dialogue(
                        new PlayerDialogue("Can you tell me about your skillcape?"),
                        new NPCDialogue(npc, " Certainly! Masters of the Fletching skill can wear this with pride to flaunt their achievement.").animate(607),
                        new PlayerDialogue("Can I buy a Skillcape of Fletching from you?.").animate(593),
                        new NPCDialogue(npc, "Certainly! Right when you give me 99000 coins").animate(570),
                        new OptionsDialogue(
                                new Option("Sorry, that's overpiced.", () -> {

                                }),
                                new Option("Okay than.", () -> {
                                    if (player.getInventory().hasItem(995, 99000)) {
                                        player.getInventory().remove(995, 99000);
                                        player.getInventory().add(ItemID.FLETCHING_CAPE, 1);
                                        if (player.getStats().total99s >= 2) {
                                            player.getInventory().add(ItemID.FLETCHING_CAPET, 1);
                                        } else {
                                            player.getInventory().add(ItemID.FLETCHING_HOOD, 1);
                                        }
                                    } else {
                                        player.dialogue( new PlayerDialogue("But, unfortunately, I don't have enough money with me"),
                                                new NPCDialogue(npc, "Well, come back and see me when you do."));
                                    }
                                })
                        )
                );
            } else {
                player.dialogue( new PlayerDialogue("Hello Hickton, how are you?.").animate(593),
                        new NPCDialogue(npc, "I've been doing good, thanks for asking.").animate(570));
            }
        });
    }
}
