package io.ruin.model.entity.npc.actions;

import io.ruin.model.entity.npc.NPCAction;
import io.ruin.model.inter.dialogue.MessageDialogue;
import io.ruin.model.inter.dialogue.NPCDialogue;
import io.ruin.model.inter.dialogue.OptionsDialogue;
import io.ruin.model.inter.utils.Option;
import io.ruin.model.shop.ShopManager;

public class Rellekka {

    static {
        NPCAction.register(3935, "talk-to", ((player, npc) -> {
            if (player.getCombat().getLevel() >= 40) {
                player.dialogue(
                        new NPCDialogue(npc, "Hello again Jiklah. Come to see what's for sale?"),
                        new OptionsDialogue(
                                new Option("Yes Please", () -> {
                                    ShopManager.openIfExists(player, "b6a18dd0-d4c8-4eb7-bd3f-d875cae4cff3");
                                }),
                                new Option("Not right now", () -> {

                                })
                        ));
        } else {
                player.dialogue(
                        new NPCDialogue(npc, "You are far to weak to speak to me"),
                        new MessageDialogue("Skulgrimen doesn't speak to anyone lower than 40 combat."));
            }
        }));
    }
}
