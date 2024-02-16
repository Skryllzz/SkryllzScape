package io.ruin.model.entity.npc.actions;

import io.ruin.Server;
import io.ruin.api.utils.TimeUtils;
import io.ruin.cache.ItemID;
import io.ruin.cache.NpcID;
import io.ruin.model.achievements.listeners.lumbridgedraynor.LumbEasy;
import io.ruin.model.entity.npc.NPCAction;
import io.ruin.model.inter.dialogue.*;
import io.ruin.model.inter.utils.Option;

import static io.ruin.cache.ItemID.*;

public class Lumbridge {


    static {
        /** HANS **/
            NPCAction.register(3077, "talk-to", (player, npc) -> {
                player.dialogue(
                        new NPCDialogue(npc, "Hello. What are you doing here?"),
                        new OptionsDialogue(
                                new Option("I'm looking for whoever is in charge of this place.", () -> {
                                    player.dialogue(
                                            new PlayerDialogue("I'm looking for whoever is in charge of this place."),
                                            new NPCDialogue(npc, "Who, the Duke? He's in his study, on the first floor.")
                                    );

                                }),
                                new Option("Can you tell me how long I've been here?", () -> {
                                    player.dialogue(
                                            new PlayerDialogue("Can you tell me how long I've been here?"),
                                            new NPCDialogue(npc, "Ahh, I see all the newcomers arriving in Lumbridge, fresh-faced and eager for adventure. I remember you..."),
                                            new NPCDialogue(npc, "You've spent "+ (TimeUtils.fromMs(player.playTime * Server.tickMs(), false)) +"ins in the world since you arrived.")
                                    );
                                    if (!LumbEasy.isTaskCompleted(player, 5)) {
                                        LumbEasy.completeTask(player, 5);
                                        player.sendMessage("<col=800000>Well done! You have completed an easy task in the Lumbridge & Draynor area. Your Achievement Diary has been updated.</col>");
                                    }
                                })
                        ));

            });

            NPCAction.register(3077, "Age", ((player, npc) -> {
                player.dialogue(
                        new PlayerDialogue("Can you tell me how long I've been here?"),
                        new NPCDialogue(npc, "Ahh, I see all the newcomers arriving in Lumbridge, fresh-faced and eager for adventure. I remember you..."),
                        new NPCDialogue(npc, "You've spent "+ (TimeUtils.fromMs(player.playTime * Server.tickMs(), false)) +"ins in the world since you arrived.")
                );
                if (!LumbEasy.isTaskCompleted(player, 5)) {
                    LumbEasy.completeTask(player, 5);
                    player.sendMessage("<col=800000>Well done! You have completed an easy task in the Lumbridge & Draynor area. Your Achievement Diary has been updated.</col>");
                }
            }));

        /** Duke **/
        NPCAction.register(815, "talk-to", (player, npc) -> player.dialogue(new NPCDialogue(npc, "Hello, " + player.getName() + " how may I help you??"),
                new OptionsDialogue(
                        new Option("I seek a shield dragon fire shield", () -> player.getInventory().add(ItemID.ANTIDRAGON_SHIELD, 1)),
                        new Option("Nothing right now thanks.", () -> {
                            return;
                        }))
        ));

        /** Kazgar **/
        NPCAction.register(7301, "Mines", (player, npc) -> {
            player.startEvent(event -> {
                player.lock();
                player.sendMessage("Kazgar leads you to the mines.");
                player.getPacketSender().fadeOut();
                event.delay(2);
                player.getMovement().teleport(3316, 9612, 0);
                event.delay(2);
                player.getPacketSender().fadeIn();
                player.unlock();
                player.dialogue(new NPCDialogue(npc, "Here we are."));
            });
        });

        /** Kazgar **/
        NPCAction.register(7301, "Watermill", (player, npc) -> {
            player.startEvent(event -> {
                player.lock();
                player.sendMessage("Kazgar leads you to the Cellar.");
                player.getPacketSender().fadeOut();
                event.delay(2);
                player.getMovement().teleport(3230, 9609, 0);
                event.delay(2);
                player.getPacketSender().fadeIn();
                player.unlock();
                player.dialogue(new NPCDialogue(npc, "Here we are."));
            });
        });

        /** Father Urhney **/
        NPCAction.register(923, "talk-to", (player, npc) -> player.dialogue(new NPCDialogue(npc, "Go away! I'm meditating!"),
                new OptionsDialogue(
                        new Option("I've lost the Amulet of Ghostspeak.", () -> {
                            if (player.getInventory().hasItem(552, 1) || player.getEquipment().contains(552)) {
                                player.dialogue(new NPCDialogue(npc, "What are you talking about? I can see you've got it with you!"));
                            } else if (player.getBank().hasItem(552, 1)) {
                                player.dialogue(new NPCDialogue(npc, "You come here wasting my time... Has it even occurred to you that you've got it stored somewhere? Now GO AWAY!"));
                            } else {
                                player.dialogue(
                                        new MessageDialogue("Father Urhney signs."),
                                        new NPCDialogue(npc, "How careless can you get? Those things aren't easy to come by you know! It's a good job I've got a spare."),
                                        new ItemDialogue().one(552, "Father Urnhey hands you an amulet."),
                                        new NPCDialogue(npc, "Be more careful this time."),
                                        new PlayerDialogue("Okay, I'll try to be.")
                                );
                                player.getInventory().add(552, 1);
                            }
                        }),
                        new Option("Sorry to have wasted your time.", () -> {
                            return;
                        }))
        ));

        /** Candle Seller **/
        NPCAction.register(1896, "talk-to", (player, npc) -> player.dialogue(new NPCDialogue(npc, "Do you want a lit candle for 1000 gold?"),
                new OptionsDialogue(
                        new Option("Yes please.", () -> {
                            if (player.getInventory().hasItem(995, 1000)){
                                player.dialogue(
                                        new NPCDialogue(npc, "Here you go then."),
                                        new NPCDialogue(npc, "I should warn you, though, it can be dangerous to take a naked flame down there. You'd be better off making a lantern."),
                                        new ItemDialogue().one(36, "You are handed a candle as you pay the seller.")
                                );
                                player.getInventory().remove(995,1000);
                                player.getInventory().add(36, 1);
                            } else {
                                player.dialogue(
                                        new PlayerDialogue("But I don't have that kind of money on me."),
                                        new NPCDialogue(npc, "Well then, no candle for you!")
                                );
                            }
                        }),
                        new Option("No thanks, I'd rather curse the darkness.", () -> {
                            player.dialogue(new PlayerDialogue("No thanks, I'd rather curse the darkness."));
                        }))
        ));

        /** Count Check **/
        NPCAction.register(7414, "talk-to", (player, npc) -> player.dialogue(new NPCDialogue(npc, "Ahahahaha! I am Count Check, the renowned security expert. Would you like some security advice?"),
                new OptionsDialogue(
                        new Option("Yes I would love some advice.", () -> {
                            if (player.countcheck) {
                                player.dialogue(
                                        new PlayerDialogue("Where can I learn more about security?"),
                                        new NPCDialogue(npc, "The Stronghold of Security, in Barbarian Village. I see you have not visited it. Would you like to? I can send you straight there - but only once."),
                                        new OptionsDialogue(
                                                new Option("Yes", () -> {
                                                    npc.addEvent(e -> {
                                                        player.countcheck = false;
                                                        npc.face(player);
                                                        npc.animate(1818);
                                                        npc.graphics(343);
                                                        player.graphics(342);
                                                        player.animate(1816);
                                                        e.delay(2);
                                                        npc.faceNone(true);
                                                        player.getMovement().teleport(3080, 3421, 0);
                                                        e.delay(2);
                                                        player.animate(715);
                                                    });
                                                }),
                                                new Option("Not Right Now", () -> {
                                                    player.dialogue(new NPCDialogue(npc, "Ahahahaha!"));
                                                }))
                                );
                            } else {
                                player.dialogue(
                                        new PlayerDialogue("Where can I learn more about security?"),
                                        new NPCDialogue(npc, "The Stronghold of Security, in Barbarian Village."));
                            }
                        }),
                        new Option("Not right now thank you", () -> {
                            player.dialogue(new NPCDialogue(npc, "Ahahahaha!"));
                        }))
        ));

        /** Hatius Cosaintus **/
        NPCAction.register(NpcID.HATIUS_COSAINTUS, "talk-to", (player, npc) -> {
            if (player.LumbEasyComplete && !player.LumbEasyClaimed) {
                player.dialogue(new PlayerDialogue("I've completed all of the easy tasks in my Lumbridge achievement diary!"),
                        new NPCDialogue(npc, "Quite... You'll be wanting a reward then!"),
                        new PlayerDialogue("Yes please!"),
                        new ItemDialogue().one(EXPLORERS_RING_1, "You are handed an explorer's ring."),
                        new NPCDialogue(npc, "This ring is a symbol of your exploration of Lumbridge and Draynor. It can recharge half of your run energy twice per day and cast low level alchemy without runes 30 times per day."),
                        new PlayerDialogue("Thanks!"));
                player.getInventory().add(EXPLORERS_RING_1, 1);
                player.LumbEasyClaimed = true;
            } else {
                player.dialogue(new NPCDialogue(npc, "I have more chat options coming soon."));
            }
        });
    }
}
