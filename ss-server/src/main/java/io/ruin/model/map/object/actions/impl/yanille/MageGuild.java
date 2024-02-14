package io.ruin.model.map.object.actions.impl.yanille;

import io.ruin.model.achievements.listeners.ardougne.ArdyEasy;
import io.ruin.model.achievements.listeners.lumbridgedraynor.LumbEasy;
import io.ruin.model.entity.npc.NPCAction;
import io.ruin.model.entity.shared.StepType;
import io.ruin.model.inter.dialogue.MessageDialogue;
import io.ruin.model.map.object.actions.ObjectAction;
import io.ruin.model.shop.ShopManager;
import io.ruin.model.stat.StatType;

public class MageGuild {

    public static int Aubury = 0;
    public static int Distentor = 0;

    public static int Cromperty = 0;

    public static int Sedridor = 0;

    public static final int[] MAGEDOOR = {1732, 1733 };
    public static final int[] ESSNPC = {3248, 2886 };

    static {
        /**
         * East Door
         */
        for (int MDOOR : MAGEDOOR) {
        ObjectAction.register(MDOOR, 1, (player, obj) -> {
            if (player.getStats().check(StatType.Magic, 66)) {
                if (player.getAbsX() == 2597) {
                    player.step(player.getAbsX() == obj.x ? -1 : 1, 0, StepType.FORCE_WALK);
                } else if (player.getAbsX() == 2596) {
                    player.step(player.getAbsX() > obj.x ? +1 : 1, 0, StepType.FORCE_WALK);
                } else if (player.getAbsX() == 2585) {
                    player.step(player.getAbsX() > obj.x ? -1 : 1, 0, StepType.FORCE_WALK);
                } else if (player.getAbsX() == 2584) {
                    player.step(player.getAbsX() > obj.x ? +1 : 1, 0, StepType.FORCE_WALK);
                }
            } else {
                player.dialogue(new MessageDialogue("You need a magic level of 66 to enter."));
            }
          });
      }
        /**
         * Staircase
         */
        //GoingUp
        ObjectAction.register(15645, 2590, 3089, 0, "climb-up", (player, obj) -> player.getMovement().teleport(2591, 3092, 1));
        ObjectAction.register(15645, 2590, 3084, 1, "climb-up", (player, obj) -> player.getMovement().teleport(2591, 3087, 2));
        //GoingDown
        ObjectAction.register(15648, 2590, 3090, 1, "climb-down", (player, obj) -> player.getMovement().teleport(2591, 3088, 0));
        ObjectAction.register(15648, 2590, 3085, 2, "climb-down", (player, obj) -> player.getMovement().teleport(2590, 3083, 1));

        /** Wizard Sinina **/
        NPCAction.register(3249, "Trade", (player, npc) -> {
            if (player.getStats().check(StatType.Magic, 99)) {
                ShopManager.openIfExists(player, "fd8195f7-2eed-478f-8a6b-127e36ffd699");
            } else {
                ShopManager.openIfExists(player, "fd8195f7-2eed-478f-8a6b-127e36ffd659");
            }
        });
            /** Wizard Distentor **/
            NPCAction.register(3248, "Teleport", (player, npc) -> {
                npc.addEvent(e -> {
                        npc.face(player);
                        //player.setMageGuild(false);
                        Distentor = 1;
                        npc.forceText("Senventior Disthine Molenko");
                        npc.animate(1818);
                        npc.graphics(343);
                        player.graphics(342);
                        player.animate(1816);
                        e.delay(2);
                        npc.faceNone(true);
                        player.getMovement().teleport(2926, 4844, 0);
                        e.delay(2);
                        player.animate(715);
                });
            });

            /** ESS Mine Portals **/
            ObjectAction.register(34774, "use", (player, obj) -> { //2885, 4850, 0,
                if (obj.x <= 2940 && obj.x >= 2880) {
                    if (Aubury > 0) {
                        player.getMovement().teleport(3253, 3400, 0);
                        Aubury = 0;
                    }
                    if (Distentor > 0) {
                        player.getMovement().teleport(2590, 3086, 0);
                        Distentor = 0;
                    }
                    if (Cromperty > 0) {
                        player.getMovement().teleport(2681, 3325, 0);
                        Cromperty = 0;
                    }
                    if (Sedridor > 0) {
                        player.getMovement().teleport(3105, 9572, 0);
                        Sedridor = 0;
                    }
            }
                    });

            /** Aubury **/
            NPCAction.register(2886, "Teleport", (player, npc) -> {
                npc.addEvent(e -> {
                        npc.face(player);
                        //player.setAubury(false);
                        Aubury = 1;
                        npc.forceText("Senventior Disthine Molenko");
                        npc.animate(1818);
                        npc.graphics(343);
                        player.graphics(342);
                        player.animate(1816);
                        e.delay(2);
                        npc.faceNone(true);
                        player.getMovement().teleport(2910, 4831, 0);
                        e.delay(2);
                        player.animate(715);
                });
            });

        /** Sedridor **/
        NPCAction.register(5034, "Teleport", (player, npc) -> {
            npc.addEvent(e -> {
                if (!LumbEasy.isTaskCompleted(player, 3)) {
                    // Mark Task 1 as completed and notify ArdyMed
                    LumbEasy.completeTask(player, 3);
                    player.sendMessage("<col=800000>Well done! You have completed an easy task in the Lumbridge & Draynor area. Your Achievement Diary has been updated.</col>");
                }
                npc.face(player);
                Sedridor = 1;
                npc.forceText("Senventior Disthine Molenko");
                npc.animate(1818);
                npc.graphics(343);
                player.graphics(342);
                player.animate(1816);
                e.delay(2);
                npc.faceNone(true);
                player.getMovement().teleport(2910, 4831, 0);
                e.delay(2);
                player.animate(715);
            });
        });

        /** Wizard Cromperty **/
        NPCAction.register(8480, "Teleport", (player, npc) -> {
            npc.addEvent(e -> {
                if (!ArdyEasy.isTaskCompleted(player, 1)) {
                    // Mark Task 1 as completed and notify ArdyMed
                    ArdyEasy.completeTask(player, 1);
                    player.sendMessage("<col=800000>Well done! You have completed an easy task in the Ardougne area. Your Achievement Diary has been updated.</col>");
                }
                npc.face(player);
                //player.setAubury(false);
                Cromperty = 1;
                npc.forceText("Senventior Disthine Molenko");
                npc.animate(1818);
                npc.graphics(343);
                player.graphics(342);
                player.animate(1816);
                e.delay(2);
                npc.faceNone(true);
                player.getMovement().teleport(2910, 4831, 0);
                e.delay(2);
                player.animate(715);
            });
        });

    }
}
