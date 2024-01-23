package io.ruin.model.map.object.actions.impl;

import io.ruin.cache.ItemID;
import io.ruin.model.entity.npc.NPC;
import io.ruin.model.entity.npc.NPCAction;
import io.ruin.model.entity.player.Player;
import io.ruin.model.inter.dialogue.MessageDialogue;
import io.ruin.model.inter.dialogue.NPCDialogue;
import io.ruin.model.inter.dialogue.OptionsDialogue;
import io.ruin.model.inter.dialogue.PlayerDialogue;
import io.ruin.model.inter.utils.Option;
import io.ruin.model.map.object.actions.ObjectAction;
import io.ruin.model.stat.StatType;

public class Kandarin {

    static {
        /** Door **/
        ObjectAction.register(11665, 2658, 3438, 0, "open", (player, obj) -> {
            if (player.getStats().check(StatType.Ranged, 40)) {
                if (player.isAt(2658, 3439) || player.isAt(2657, 3438)) {
                    player.getMovement().teleport(2659, 3437, 0);
                }
            } else {
                player.dialogue((new MessageDialogue("You need a ranged level of 40 to enter.")));
            }
        });
        /** Armour Salesman **/
        NPCAction.register(7716, "talk-to", (player, npc) -> {
            if (player.getStats().check(StatType.Ranged, 99)) {
                player.dialogue(
                        new NPCDialogue(npc, "Welcome to the Ranging Guild. Can I help you with anything?").animate(568),
                        new OptionsDialogue(
                                new Option("Can you tell me about your skillcape?", () -> Skillcapes(player, npc)),
                                new Option("No thanks, I'm fine.", () -> noThanks(player))
                        )
                );
            } else {
                player.dialogue(
                        new NPCDialogue(npc, "Welcome to the Ranging Guild. Enjoy your stay.").animate(568),
                        new PlayerDialogue("Thank you!"));
            }
        });
    }

    private static void Skillcapes(Player player, NPC npc) {
        player.dialogue(
                new PlayerDialogue("Can you tell me about your skillcape?"),
                new NPCDialogue(npc, " Certainly! Skillcapes are a symbol of achievement. Only people who have mastered a skill and reached level 99 can get their hands on them and gain the benefits they carry.").animate(607),
                new NPCDialogue(npc, "The Cape of Ranging has been modified by Ava to function as one of her accumulator devices. Is there anything else I can help you with?").animate(607),
                new PlayerDialogue("Can I buy a Skillcape of Mining from you?.").animate(593),
                new NPCDialogue(npc, "Certainly! Right when you give me 99000 coins").animate(570),
                new OptionsDialogue(
                        new Option("Sorry, that's overpiced.", () -> {

                        }),
                        new Option("Okay than.", () -> {
                            if (player.getInventory().hasItem(995, 99000)) {
                                player.getInventory().remove(995, 99000);
                                player.getInventory().add(ItemID.RANGING_CAPE, 1);
                                if (player.getStats().total99s >= 2) {
                                    player.getInventory().add(ItemID.RANGING_CAPET, 1);
                                } else {
                                    player.getInventory().add(ItemID.RANGING_HOOD, 1);
                                }
                            } else {
                                player.dialogue( new PlayerDialogue("But, unfortunately, I don't have enough money with me"),
                                        new NPCDialogue(npc, "Well, come back and see me when you do."));
                            }
                        })
                )
        );
    }

    private static void noThanks(Player player) {
        player.dialogue(new PlayerDialogue("No thanks, I'm fine.").animate(562));
    }
}
