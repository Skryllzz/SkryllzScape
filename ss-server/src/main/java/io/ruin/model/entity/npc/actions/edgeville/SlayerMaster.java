package io.ruin.model.entity.npc.actions.edgeville;

import io.ruin.api.utils.Random;
import io.ruin.model.entity.npc.NPC;
import io.ruin.model.entity.npc.NPCAction;
import io.ruin.model.entity.player.Player;
import io.ruin.model.inter.dialogue.ActionDialogue;
import io.ruin.model.inter.dialogue.NPCDialogue;
import io.ruin.model.inter.dialogue.OptionsDialogue;
import io.ruin.model.inter.dialogue.PlayerDialogue;
import io.ruin.model.inter.utils.Option;
import io.ruin.model.shop.ShopManager;
import io.ruin.model.skills.slayer.KonarLocations;
import io.ruin.model.skills.slayer.Slayer;
import io.ruin.model.skills.slayer.SlayerTask;
import io.ruin.model.skills.slayer.SlayerUnlock;

import static io.ruin.cache.NpcID.*;
import static io.ruin.model.skills.slayer.Slayer.reset;
import static io.ruin.model.skills.slayer.Slayer.set;

public class SlayerMaster {

    public static final int[] IDS = {NIEVE, KRYSTILIA, TURAEL, KONAR_QUO_MATEN, MAZCHNA, VANNAKA, CHAELDAR, DURADEL };

    static {
        for(int ID : IDS) {
            NPCAction.register(ID, "Talk-to", (player, npc) -> player.dialogue(
                    new NPCDialogue(npc, "Yeah? What do you want?"),
                    new OptionsDialogue(
                            player.slayerTask == null ?
                                    new Option("Speak about assignment.", () -> {
                                        player.dialogue(
                                                new PlayerDialogue("I want a Slayer assignment."),
                                                new ActionDialogue(() -> assignment(player, npc))
                                        );
                                    })
                                    :
                                    new Option("Tell me about my Slayer assignment.", () -> {
                                        player.dialogue(
                                                new PlayerDialogue("Tell me about my Slayer assignment."),
                                                new ActionDialogue(() -> assignment(player, npc))
                                        );
                                    }),
                            new Option("Do you have anything to trade?", () -> {
                                player.dialogue(
                                        new PlayerDialogue("Do you have anything to trade?"),
                                        new NPCDialogue(npc, "I have a wide variety of Slayer equipment for sale! Have a look..").lineHeight(28),
                                        new ActionDialogue(() -> ShopManager.openIfExists(player, "44f369bf-6369-48c5-9952-b9b50011e89b"))
                                );
                            }),
                            new Option("Have you any rewards for me?", () -> {
                                player.dialogue(
                                        new PlayerDialogue("Have you any rewards for me?"),
                                        new NPCDialogue(npc, "I have quite a few rewards you can earn!<br>Take a look..").lineHeight(28),
                                        new ActionDialogue(() -> rewards(player))
                                );
                            }),
                            new Option("Er... Nothing...", () -> player.dialogue(new PlayerDialogue("Er... Nothing...").animate(588)))
                    )
            ));
            NPCAction.register(ID, "Assignment", SlayerMaster::assignment);
            NPCAction.register(ID, "Trade", (player, npc) -> ShopManager.openIfExists(player, "06bf1d5a-e4d8-49f5-9aac-6229a965b98a"));
            NPCAction.register(ID, "Rewards", (player, npc) -> rewards(player));
        }
    }

    /**
     * Assignment
     */

    private static void assignment(Player player, NPC npc) {
        if (npc.getId() == TURAEL) {
            if (Slayer.getTask(player) != null) {
                player.dialogue(new NPCDialogue(npc, "Would you like me to reset your current task? I can give you another task but this will reset your task streak."),
                        new OptionsDialogue(
                                new Option("Yes please.", () -> player.dialogue(
                                        new PlayerDialogue("Yes please."),
                                        new ActionDialogue(() -> {
                                            reset(player);
                                            set(player, SlayerTask.Type.TURAEL, false);
                                            player.dialogue(new NPCDialogue(npc, "Your new task is to kill " + player.slayerTaskRemaining + " " + player.slayerTaskName + ".<br>Good luck!").lineHeight(28));
                                        }))),
                                new Option("No, thanks.")
                        )
                );
                return;
            }
        }
        if (player.slayerTaskRemaining == -1) {
            requestAmount(player, npc);
            return;
        }
        if(Slayer.getTask(player) == null) {
            if (npc.getId() == TURAEL) {
                player.dialogue(new PlayerDialogue("I need another assignment"),
                        new ActionDialogue(() -> {
                            reset(player);
                            set(player, SlayerTask.Type.TURAEL, false);
                            player.dialogue(new NPCDialogue(npc, "Excellent, you're doing great. Your new task is to kill " + player.slayerTaskRemaining + " " + player.slayerTaskName).lineHeight(28));
                        }));
            }
            if (npc.getId() == MAZCHNA) {
                player.dialogue(new PlayerDialogue("I need another assignment"),
                        new ActionDialogue(() -> {
                            reset(player);
                            set(player, SlayerTask.Type.MAZCHNA, false);
                            player.dialogue(new NPCDialogue(npc, "Excellent, you're doing great. Your new task is to kill " + player.slayerTaskRemaining + " " + player.slayerTaskName).lineHeight(28));
                        }));
            }
            if (npc.getId() == VANNAKA) {
                player.dialogue(new PlayerDialogue("I need another assignment"),
                        new ActionDialogue(() -> {
                            reset(player);
                            set(player, SlayerTask.Type.VANNAKA, false);
                            player.dialogue(new NPCDialogue(npc, "Excellent, you're doing great. Your new task is to kill " + player.slayerTaskRemaining + " " + player.slayerTaskName).lineHeight(28));
                        }));
            }
            if (npc.getId() == CHAELDAR) {
                player.dialogue(new PlayerDialogue("I need another assignment"),
                        new ActionDialogue(() -> {
                            reset(player);
                            set(player, SlayerTask.Type.CHAELDAR, false);
                            player.dialogue(new NPCDialogue(npc, "Excellent, you're doing great. Your new task is to kill " + player.slayerTaskRemaining + " " + player.slayerTaskName).lineHeight(28));
                        }));
            }
            if (npc.getId() == NIEVE) {
                player.dialogue(new PlayerDialogue("I need another assignment"),
                        new ActionDialogue(() -> {
                            reset(player);
                            set(player, SlayerTask.Type.NIEVE, false);
                            player.dialogue(new NPCDialogue(npc, "Excellent, you're doing great. Your new task is to kill " + player.slayerTaskRemaining + " " + player.slayerTaskName).lineHeight(28));
                        }));
            }
            if (npc.getId() == DURADEL) {
                player.dialogue(new NPCDialogue(npc, "'Ello, and what are you after then?"),
                        new OptionsDialogue(
                                new Option("I need another assignment.", () -> player.dialogue(
                                        new PlayerDialogue("Yes please."),
                                        new ActionDialogue(() -> {
                                            reset(player);
                                            set(player, SlayerTask.Type.DURADEL, false);
                                            player.dialogue(new NPCDialogue(npc, "Excellent, you're doing great. Your new task is to kill " + player.slayerTaskRemaining + " " + player.slayerTaskName).lineHeight(28));
                                        }))),
                                new Option("I would love a boss assignment.", () -> {
                                    if (player.getCombat().getLevel() >= 100) {
                                        player.dialogue(
                                                new PlayerDialogue("I would love a boss assignment."),
                                                new ActionDialogue(() -> {
                                                    reset(player);
                                                    set(player, SlayerTask.Type.BOSS, false);
                                                    player.dialogue(new NPCDialogue(npc, "Excellent, you're doing great. Your new task is to kill " + player.slayerTaskRemaining + " " + player.slayerTaskName).lineHeight(28));
                                                }));
                                    } else {
                                        player.dialogue(new PlayerDialogue("I'm sorry, but you need to be level 100 combat to get a boss task.").animate(588));

                                    }
                                })));
            }
            if (npc.getId() == KONAR_QUO_MATEN) {
                player.dialogue(new NPCDialogue(npc, "'Ello, and what are you after then?"),
                        new OptionsDialogue(
                                new Option("I need another assignment.", () -> player.dialogue(
                                        new PlayerDialogue("Yes please."),
                                        new ActionDialogue(() -> {
                                            reset(player);
                                            set(player, SlayerTask.Type.KONAR, false);
                                            KonarLocations.Area slayerTaskArea = KonarLocations.slayerMonsterAreas.get(player.slayerTaskName);
                                            String areaName = slayerTaskArea != null ? KonarLocations.GetAreaName(slayerTaskArea) : "Unknown Area";
                                            player.dialogue(new NPCDialogue(npc, "You are to bring balance to " + player.slayerTaskRemaining + " " + player.slayerTaskName + " in " + areaName).lineHeight(28));
                                        }))),
                                new Option("Er... Nothing....", () -> {
                                    player.dialogue(new PlayerDialogue("Er... Nothing...").animate(588));
                                })));
            }
            if (npc.getId() == KRYSTILIA) {
                player.dialogue(new PlayerDialogue("I need another assignment"),
                        new ActionDialogue(() -> {
                            reset(player);
                            set(player, SlayerTask.Type.KRYSTILIA, false);
                            player.dialogue(new NPCDialogue(npc, "Excellent, you're doing great. Your new task is to kill " + player.slayerTaskRemaining + " " + player.slayerTaskName).lineHeight(28));
                        }));
            }
            return;
        }
        player.dialogue(new NPCDialogue(npc, "You're currently assigned to kill " + player.slayerTaskName + "; only " + player.slayerTaskRemaining + " more to go.")/* Your reward point tally is " + tally + ".")*/.lineHeight(28));
    }

    private static void assign(Player player, NPC npc, SlayerTask.Type type) {
        if(player.getCombat().getLevel() < type.minimumCombatLevel) {
            player.dialogue(
                    new NPCDialogue(npc, "You must have combat level of " + type.minimumCombatLevel + " or higher for this type of task.").lineHeight(28)
            );
        } else {
            player.dialogue(
                    new NPCDialogue(npc, "Do you prefer monsters that can be found in the wilderness?").lineHeight(28),
                    new OptionsDialogue(
                            assign("Yes, the more dangerous the better!", player, npc, type, true),
                            assign("No, I'd rather stay out of the wilderness.", player, npc, type, false),
                            assign("It doesn't matter, surprise me!", player, npc, type, !Random.rollDie(2, 1))
                    )
            );
        }
    }

    private static Option assign(String message, Player player, NPC npc, SlayerTask.Type type, Boolean preferWilderness) {
        return new Option(message, () -> {
           set(player, type, preferWilderness);
           if(player.slayerTaskRemaining == -1) {
               player.dialogue(
                       new PlayerDialogue(message),
                       new ActionDialogue(() -> requestAmount(player, npc))
               );
           } else {
               player.dialogue(
                       new PlayerDialogue(message),
                       new NPCDialogue(npc, "Your new task is to kill " + player.slayerTaskRemaining + " " + player.slayerTaskName + ".<br>Good luck!").lineHeight(28)
               );
           }
        });
    }

    private static void requestAmount(Player player, NPC npc) {
        SlayerTask task = Slayer.getTask(player);
        if(task == null) { //literally should NEVER happen lol.
            reset(player);
            player.dialogue(new NPCDialogue(npc, "Oh my!"));
        } else {
            player.dialogue(
                    new NPCDialogue(npc, "Your new task is to kill " + player.slayerTaskName + ". How many would you like to slay?").lineHeight(28),
                    new ActionDialogue(() -> {
                        player.closeDialogue();
                        player.integerInput("Enter amount: (" + task.min + "-" + task.max + ")", amt -> {
                            if(amt < task.min || amt > task.max) {
                                player.retryIntegerInput("Invalid amount, try again: (" + task.min + "-" + task.max + ")");
                                return;
                            }
                            player.slayerTaskRemaining = amt;
                            player.dialogue(new NPCDialogue(npc, "Your new task is to kill " + player.slayerTaskRemaining + " " + player.slayerTaskName + ".<br>Good luck!").lineHeight(28));
                        });
                    })
            );
        }
    }

    /**
     * Rewards
     */

    private static void rewards(Player player) {
        SlayerUnlock.openRewards(player);
    }

}