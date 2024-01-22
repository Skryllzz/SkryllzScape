package io.ruin.model.map.object.actions.impl;

import io.ruin.model.inter.dialogue.MessageDialogue;
import io.ruin.model.inter.dialogue.NPCDialogue;
import io.ruin.model.map.object.actions.ObjectAction;
import io.ruin.model.stat.StatType;

public class Brimhaven {

    static  {
        /** Shilo Travel Cart **/
        ObjectAction.register(2230, 2777, 3211, 0, "board", (player, obj) -> {
           if (player.getStats().check(StatType.Agility,32) && player.getStats().check(StatType.Crafting, 20) && player.getStats().check(StatType.Mining, 40)) {
               if (player.getInventory().hasItem(995, 200)) {
                   player.startEvent(event -> {
                       player.lock();
                       player.getPacketSender().fadeOut();
                       event.delay(2);
                       player.getMovement().teleport(2830, 2954, 0);
                       event.delay(2);
                       player.getPacketSender().fadeIn();
                       player.unlock();
                       player.getInventory().remove(995, 200);
                   });
               } else {
                   player.dialogue(new MessageDialogue("You need 200 coins to travel on this cart."));
               }
           } else {
               player.dialogue(new NPCDialogue(5356, "I'm sorry, but you don't meet the requirements to travel on this cart."),
                       new MessageDialogue("You need 32 Agility, 20 Crafting, and 40 Mining to access Shilo VIllage."));
           }
        });
        ObjectAction.register(2230, 2777, 3211, 0, "pay-fare", (player, obj) -> {
            if (player.getStats().check(StatType.Agility,32) && player.getStats().check(StatType.Crafting, 20) && player.getStats().check(StatType.Mining, 40)) {
                if (player.getInventory().hasItem(995, 200)) {
                    player.startEvent(event -> {
                        player.lock();
                        player.getPacketSender().fadeOut();
                        event.delay(2);
                        player.getMovement().teleport(2830, 2954, 0);
                        event.delay(2);
                        player.getPacketSender().fadeIn();
                        player.unlock();
                        player.getInventory().remove(995, 200);
                    });
                } else {
                    player.dialogue(new MessageDialogue("You need 200 coins to travel on this cart."));
                }
            } else {
                player.dialogue(new NPCDialogue(5356, "I'm sorry, but you don't meet the requirements to travel on this cart."),
                        new MessageDialogue("You need 32 Agility, 20 Crafting, and 40 Mining to access Shilo VIllage."));
            }
        });
        /** Travel back to Brimhaven from Shilo Village **/
        ObjectAction.register(2265, 2831, 2952, 0, "board", (player, obj) -> {
                if (player.getInventory().hasItem(995, 200)) {
                    player.startEvent(event -> {
                        player.lock();
                        player.getPacketSender().fadeOut();
                        event.delay(2);
                        player.getMovement().teleport(2778, 3214, 0);
                        event.delay(2);
                        player.getPacketSender().fadeIn();
                        player.unlock();
                        player.getInventory().remove(995, 200);
                    });
                } else {
                    player.dialogue(new MessageDialogue("You need 200 coins to travel on this cart."));
                }
        });
        ObjectAction.register(2265, 2831, 2952, 0, "pay-fare", (player, obj) -> {
            if (player.getInventory().hasItem(995, 200)) {
                player.startEvent(event -> {
                    player.lock();
                    player.getPacketSender().fadeOut();
                    event.delay(2);
                    player.getMovement().teleport(2778, 3214, 0);
                    event.delay(2);
                    player.getPacketSender().fadeIn();
                    player.unlock();
                    player.getInventory().remove(995, 200);
                });
            } else {
                player.dialogue(new MessageDialogue("You need 200 coins to travel on this cart."));
            }
        });
    }
}
