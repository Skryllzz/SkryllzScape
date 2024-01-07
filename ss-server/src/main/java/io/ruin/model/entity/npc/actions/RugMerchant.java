package io.ruin.model.entity.npc.actions;

import io.ruin.model.entity.npc.NPCAction;
import io.ruin.model.inter.dialogue.NPCDialogue;
import io.ruin.model.inter.dialogue.OptionsDialogue;
import io.ruin.model.inter.utils.Option;

public class RugMerchant {
    private static final int RUG_MERCHANT = 17;

    static {
        /*NPCAction.register(RUG_MERCHANT, "talk-to", (player, npc) -> {
            player.dialogue(new NPCDialogue(npc, "This is no place to talk! Meet me at the Varrock Chaos Temple!").animate(557));
        });*/
        /** Shantay pass rug merchant **/
        NPCAction.register(RUG_MERCHANT, "travel", (player, npc) -> {
            player.dialogue(
                    new NPCDialogue(npc, "Hello stranger, where would you like to travel?"),
                    new OptionsDialogue(
                            new Option("Pollnivneach", () -> {
                                if (player.getInventory().hasItem(995, 200)) {
                                    player.getInventory().remove(995, 200);
                                    player.getMovement().teleport(3349, 3004, 0);
                                } else {
                                    player.dialogue(
                                    new NPCDialogue(17, "You need 200 coins to travel to Pollnivneach."));
                                }
                            }),
                            new Option("Bedabin Camp", () -> {
                                if (player.getInventory().hasItem(995, 200)) {
                                    player.getInventory().remove(995, 200);
                                    player.getMovement().teleport(3180, 3043, 0);
                                } else {
                                    player.dialogue(
                                            new NPCDialogue(17, "You need 200 coins to travel to Bedabin Camp."));
                                }
                            }),
                            new Option("Uzer", () -> {
                                if (player.getInventory().hasItem(995, 200)) {
                                    player.getInventory().remove(995, 200);
                                    player.getMovement().teleport(3470, 3113, 0);
                                } else {
                                    player.dialogue(
                                            new NPCDialogue(17, "You need 200 coins to travel to Uzer."));
                                }
                            })
                    ));
        });

        /** Uzer Rug Merchant **/
        NPCAction.register(20, "travel", (player, npc) -> {
                player.getMovement().teleport(3308, 3108, 0);
                player.sendMessage("You travel back to Shantay Pass.");
        });

        /** Pollnivneach and Bedabin Camp Rug Merchant **/
        NPCAction.register(22, "travel", (player, npc) -> {
            player.getMovement().teleport(3308, 3108, 0);
            player.sendMessage("You travel back to Shantay Pass.");
        });
    }
}
