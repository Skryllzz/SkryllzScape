package io.ruin.model.map.object.actions.impl.gnome_stronghold;

import io.ruin.model.entity.npc.NPCAction;
import io.ruin.model.inter.dialogue.ItemDialogue;
import io.ruin.model.inter.dialogue.MessageDialogue;
import io.ruin.model.inter.dialogue.NPCDialogue;
import io.ruin.model.inter.dialogue.PlayerDialogue;
import io.ruin.model.map.object.actions.ObjectAction;
import io.ruin.model.stat.StatType;

public class MonkeyMadness {

    static {
        /** Daero **/
        NPCAction.register(1444, "talk-to", (player, npc) -> {
            if (player.getStats().check(StatType.Agility, 25) && player.getCombat().getLevel() >= 50) {
                player.dialogue(
                        new PlayerDialogue("I need to return to Crash Island."),
                        new NPCDialogue(npc, "You know the routine..."),
                        new ItemDialogue().one(1025, "You wear the blindfold Daero hands you.")
                );
                player.startEvent(event -> {
                    event.delay(4);
                    player.lock();
                    player.getPacketSender().fadeOut();
                    event.delay(2);
                    player.getMovement().teleport(2893, 2726, 0);
                    //player.getMovement().teleport(2803, 2706, 0); //Ape Atoll
                    event.delay(2);
                    player.getPacketSender().fadeIn();
                    player.unlock();
                });
            } else {
                player.dialogue(new MessageDialogue("Daero doesn't speak to anyone under 25 agility and 50 combat."));
            }
        });

        /** Lumdo **/
        NPCAction.register(1453, "talk-to", (player, npc) -> {
            if (player.getAbsX() >= 2850) {
                player.dialogue(
                        new PlayerDialogue("Can you take me to Ape Atoll please."),
                        new NPCDialogue(npc, "Sure, no problem."));
                player.startEvent(event -> {
                    event.delay(4);
                    player.lock();
                    player.getPacketSender().fadeOut();
                    event.delay(2);
                    player.getMovement().teleport(2803, 2706, 0); //Ape Atoll
                    event.delay(2);
                    player.getPacketSender().fadeIn();
                    player.unlock();
                });
            } else if (player.getAbsX() <= 2849) {
                player.dialogue(
                        new PlayerDialogue("Can you take me to Crash Island please."),
                        new NPCDialogue(npc, "Sure, no problem."));
                        player.startEvent(event -> {
                            event.delay(4);
                            player.lock();
                            player.getPacketSender().fadeOut();
                            event.delay(2);
                            player.getMovement().teleport(2893, 2726, 0);
                            event.delay(2);
                            player.getPacketSender().fadeIn();
                            player.unlock();
                        });
            }
        });

        /**
         * Cave
         */
        ObjectAction.register(28686, "enter", (player, obj) -> player.startEvent(event -> {
            player.lock();
            player.getPacketSender().fadeOut();
            event.delay(2);
            player.sendFilteredMessage("You enter the cavern beneath the crash site.");
            player.sendFilteredMessage("Why would you want to go in there?");
            player.getMovement().teleport(2129, 5646);
            player.getPacketSender().fadeIn();
            event.delay(1);
            player.unlock();
        }));
        ObjectAction.register(28687, "climb-up", (player, obj) -> player.startEvent(event -> {
            player.lock();
            player.animate(828);
            player.getPacketSender().fadeOut();
            event.delay(2);
            player.getMovement().teleport(2026, 5611);
            player.getPacketSender().fadeIn();
            event.delay(1);
            player.unlock();
        }));

        /**
         * Entrance
         */
        ObjectAction.register(28656, 1, (player, obj) -> {
            if (player.getAbsY() >= 5568) {
                player.startEvent(event -> {
                    player.lock();
                    player.getPacketSender().fadeOut();
                    event.delay(2);
                    player.getMovement().teleport(2435, 3519);
                    player.getPacketSender().fadeIn();
                    event.delay(1);
                    player.unlock();
                });
            } else {
                player.startEvent(event -> {
                    player.lock();
                    player.getPacketSender().fadeOut();
                    event.delay(2);
                    player.getMovement().teleport(1987, 5568);
                    player.getPacketSender().fadeIn();
                    event.delay(1);
                    player.unlock();
                });
            }
        });
    }
}
