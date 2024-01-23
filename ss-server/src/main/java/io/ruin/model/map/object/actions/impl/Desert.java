package io.ruin.model.map.object.actions.impl;

import io.ruin.cache.ItemID;
import io.ruin.cache.NpcID;
import io.ruin.model.entity.npc.NPCAction;
import io.ruin.model.inter.dialogue.ItemDialogue;
import io.ruin.model.inter.dialogue.NPCDialogue;
import io.ruin.model.inter.dialogue.PlayerDialogue;
import io.ruin.model.map.object.actions.ObjectAction;

public class Desert {

    static {
        /** Rope out of dungeon **/
        ObjectAction.register(6439, 3205, 9379, 0, "climb-up", (player, obj) -> {
            player.startEvent(event -> {
                player.lock();
                player.animate(828);
                player.getPacketSender().fadeOut();
                event.delay(2);
                player.resetAnimation();
                player.getMovement().teleport(2797, 3615, 0);
                player.getPacketSender().fadeIn();
                player.unlock();
            });
        });
        /** Smokey Well **/
        ObjectAction.register(6279, 3310, 2962, 0, "climb-down", (player, obj) -> {
            player.startEvent(event -> {
                player.lock();
                player.animate(807);
                player.getPacketSender().fadeOut();
                event.delay(2);
                player.resetAnimation();
                player.getMovement().teleport(3204, 9379, 0);
                player.getPacketSender().fadeIn();
                player.unlock();
            });
        });
        /** Avan **/
        NPCAction.register(NpcID.AVAN, "talk-to", (player, npc) -> {
            if (player.getInventory().hasAtLeastOneOf(ItemID.STEEL_GAUNTLETS)) {
                player.dialogue(new PlayerDialogue("Hello Avan, I got a pair of steel guantlets can you change them for me?"),
                        new NPCDialogue(npc, "Certainly!"),
                        new ItemDialogue().one(ItemID.STEEL_GAUNTLETS, "You hand your steel gauntlets to Caleb"),
                        new ItemDialogue().one(ItemID.GOLDSMITH_GAUNTLETS, "Avan hands you goldsmith guantlets"),
                        new NPCDialogue(npc, "You will find the rate you gain experience when smelting gold much improved whilst wearing these gauntlets.."),
                        new PlayerDialogue("Thank you"));
                player.getInventory().remove(ItemID.STEEL_GAUNTLETS,1);
                player.getInventory().add(ItemID.GOLDSMITH_GAUNTLETS,1);
            }
        });
    }
}
