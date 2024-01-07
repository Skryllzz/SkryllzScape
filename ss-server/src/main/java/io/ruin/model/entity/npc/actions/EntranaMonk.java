package io.ruin.model.entity.npc.actions;

import io.ruin.cache.ItemDef;
import io.ruin.model.entity.npc.NPCAction;
import io.ruin.model.inter.dialogue.MessageDialogue;
import io.ruin.model.inter.dialogue.NPCDialogue;
import io.ruin.model.inter.dialogue.OptionsDialogue;
import io.ruin.model.inter.utils.Option;

public class EntranaMonk {

    public static final int[] MONKS = {1165, 1166, 1167 };
    public static final int[] ENTMONKS = {1168, 1169, 1170 };

    public static final boolean[] ITEMS = {ItemDef.namee.contains("Dragon")};

    static {
        /** Monk of Entrana Port Sarim **/
        for(int MONK : MONKS) {
        NPCAction.register(MONK, "talk-to", (player, npc) -> {
            player.dialogue(
                    new NPCDialogue(npc, "Hello " + player.getName() + ", Would you like to go to entrana?"),
                    new OptionsDialogue(
                            new Option("Yes", () -> {
                                    //if (!player.getInventory().contains(ITEMS)) {
                                player.startEvent(event -> {
                                    player.lock();
                                    player.getPacketSender().fadeOut();
                                    event.delay(2);
                                    player.getMovement().teleport(2834, 3335, 0);
                                    event.delay(2);
                                    player.getPacketSender().fadeIn();
                                    player.unlock();
                                    player.dialogue(new MessageDialogue("You hop on the boat and travel to Entrana."));
                                });
                            }),
                            new Option("Not right now", () -> {

                            })
                    ));

        });

            NPCAction.register(MONK, "take-boat", (player, npc) -> {
                player.startEvent(event -> {
                    player.lock();
                    player.getPacketSender().fadeOut();
                    event.delay(2);
                    player.getMovement().teleport(2834, 3335, 0);
                    event.delay(2);
                    player.getPacketSender().fadeIn();
                    player.unlock();
                    player.dialogue(new MessageDialogue("You hop on the boat and travel to Entrana."));
                });
            });

    }
        /** Monk of Entrana on Island **/
        for(int MONK : ENTMONKS) {
            NPCAction.register(MONK, "talk-to", (player, npc) -> {
                player.dialogue(
                        new NPCDialogue(npc, "Hello " + player.getName() + ", Would you like to go to Port Sarim?"),
                        new OptionsDialogue(
                                new Option("Yes", () -> {
                                    //if (!player.getInventory().contains(ITEMS)) {
                                    player.startEvent(event -> {
                                        player.lock();
                                        player.getPacketSender().fadeOut();
                                        event.delay(2);
                                        player.getMovement().teleport(3048, 3234, 0);
                                        event.delay(2);
                                        player.getPacketSender().fadeIn();
                                        player.unlock();
                                        player.dialogue(new MessageDialogue("You hop on the boat and travel to Port Sarim."));
                                    });
                                }),
                                new Option("Not right now", () -> {

                                })
                        ));

            });

            NPCAction.register(MONK, "take-boat", (player, npc) -> {
                player.startEvent(event -> {
                    player.lock();
                    player.getPacketSender().fadeOut();
                    event.delay(2);
                    player.getMovement().teleport(3048, 3234, 0);
                    event.delay(2);
                    player.getPacketSender().fadeIn();
                    player.unlock();
                    player.dialogue(new MessageDialogue("You hop on the boat and travel to Port Sarim."));
                });
            });

        }
    }
}
