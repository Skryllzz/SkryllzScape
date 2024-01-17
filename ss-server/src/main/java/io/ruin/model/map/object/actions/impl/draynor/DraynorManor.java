package io.ruin.model.map.object.actions.impl.draynor;

import io.ruin.cache.NpcID;
import io.ruin.model.entity.npc.NPCAction;
import io.ruin.model.entity.player.Player;
import io.ruin.model.inter.dialogue.ItemDialogue;
import io.ruin.model.inter.dialogue.MessageDialogue;
import io.ruin.model.inter.dialogue.NPCDialogue;
import io.ruin.model.inter.dialogue.OptionsDialogue;
import io.ruin.model.inter.utils.Option;
import io.ruin.model.map.object.actions.ObjectAction;
import io.ruin.model.shop.ShopManager;
import io.ruin.model.stat.StatType;

import static io.ruin.cache.ItemID.AVAS_ACCUMULATOR;
import static io.ruin.cache.ItemID.AVAS_ATTRACTOR;

public class DraynorManor {

    public static void teleportPlayer(Player player, int x, int y) {
        player.startEvent(event -> {
            player.lock();
            event.delay(1);
            player.getMovement().teleport(x, y);
            event.delay(1);
            player.unlock();
        });
    }

    static {
        //Manor Doors
        ObjectAction.register(134, 3108, 3353, 0, "open", (player, obj) -> {
            if (player.isAt(3108, 3353) || player.isAt(3109, 3353)) {
                player.getMovement().teleport(player.getAbsX(), player.getAbsY() + 1, 0);
            } else {
                player.sendMessage("The door is locked.");
            }
        });
        ObjectAction.register(135, 3109, 3353, 0, "open", (player, obj) -> {
            if (player.isAt(3108, 3353) || player.isAt(3109, 3353)) {
                player.getMovement().teleport(player.getAbsX(), player.getAbsY() + 1, 0);
            } else {
                player.sendMessage("The door is locked.");
            }
        });
        ObjectAction.register(136, 3123, 3361, 0, "open", (player, obj) -> {
            if (player.isAt(3123, 3360)) {
                player.getMovement().teleport(player.getAbsX(), player.getAbsY() + 1, 0);
            } else {
                player.sendMessage("The door is locked.");
            }
        });

        /** Manor Bookcase / Lever **/
        ObjectAction.register(156, 3097, 3359, 0, "search", (player, obj) -> {
                player.getMovement().teleport(3096, 3358, 0);
        });

        ObjectAction.register(155, 3097, 3358, 0, "search", (player, obj) -> {
            player.getMovement().teleport(3096, 3358, 0);
        });

        ObjectAction.register(160, 3096, 3357, 0, "pull", (player, obj) -> {
            player.getMovement().teleport(3098, 3357, 0);
        });

        /** AVA **/
        NPCAction.register(4407, "talk-to", (player, npc) -> {
            player.dialogue(new NPCDialogue(NpcID.AVA, "Welcome to the draynor manor! Would you like to see my shop?"),
                            new OptionsDialogue("Look at Ava's shop?",
                                    new Option("Yes", () -> ShopManager.openIfExists(player, "aa5ecc9c-1d4f-4f66-a874-cefeebdd2df8")),
                                    new Option("No", () -> player.dialogue(new NPCDialogue(NpcID.AVA, "Alright then.")))));
        });
        NPCAction.register(4407, "Trade", (player, npc) -> {
            ShopManager.openIfExists(player, "aa5ecc9c-1d4f-4f66-a874-cefeebdd2df8");
        });
        NPCAction.register(4407, "Devices", (player, npc) -> {
            if (player.getStats().check(StatType.Slayer, 18) && player.getStats().check(StatType.Crafting, 19)
            && player.getStats().check(StatType.Ranged, 30) && player.getStats().check(StatType.Woodcutting, 35)) {
                if (player.getStats().check(StatType.Ranged, 50)) {
                    if (player.getInventory().hasItem(995, 999)) {
                        player.getInventory().remove(995, 999);
                        player.getInventory().add(AVAS_ACCUMULATOR, 1);
                        player.dialogue(new ItemDialogue().one(AVAS_ACCUMULATOR, "You purchase an ava's accumulator"));
                    } else {
                        player.dialogue(new NPCDialogue(NpcID.AVA, "The materials cost 999 coins to make an ava's accumulator."));
                    }
                } else {
                    if (player.getInventory().hasItem(995, 999)) {
                        player.getInventory().remove(995, 999);
                        player.getInventory().add(AVAS_ATTRACTOR, 1);
                        player.dialogue(new ItemDialogue().one(AVAS_ATTRACTOR, "You purchase an ava's attractor"));
                    } else {
                        player.dialogue(new NPCDialogue(NpcID.AVA, "The materials cost 999 coins to make an ava's attractor."));
                    }
                }
            } else {
                player.dialogue(new MessageDialogue("You need at least 18 in slayer, 19 in crafting, 30 in ranged, and 35 in woodcutting to purchase ava's devices."));
            }
        });


        /**
        //Door1
        ObjectAction.register(11470, 3109, 3358, 0, "open", (player, obj) -> {
            if (player.isAt(3109, 3357)) {
                player.getMovement().teleport(player.getAbsX(), player.getAbsY() + 1, 0);
            }
            if (player.isAt(3109, 3358)) {
                player.getMovement().teleport(player.getAbsX(), player.getAbsY() - 1, 0);
            }
        });

        //Door2
        ObjectAction.register(11470, 3106, 3368, 0, "open", (player, obj) -> {
            if (player.isAt(3109, 3357)) {
                player.getMovement().teleport(player.getAbsX(), player.getAbsY() + 1, 0);
            }
            if (player.isAt(3109, 3358)) {
                player.getMovement().teleport(player.getAbsX(), player.getAbsY() - 1, 0);
            }
        });

        /**
         * Double doors
         */
        final int[] DOORS = {11470};
        for (int door : DOORS) {
            ObjectAction.register(door, "open", (player, obj) -> {
                boolean atObjX = obj.x == player.getAbsX();
                boolean atObjY = obj.y == player.getAbsY();

                if (obj.direction == 0 && atObjX)
                    teleportPlayer(player, player.getAbsX() - 1, player.getAbsY());
                else if (obj.direction == 1 && atObjY)
                    teleportPlayer(player, obj.x, obj.y + 1);
                else if (obj.direction == 2 && atObjX)
                    teleportPlayer(player, obj.x + 1, obj.y);
                else if (obj.direction == 3 && atObjY)
                    teleportPlayer(player, obj.x, obj.y - 1);
                else
                    teleportPlayer(player, obj.x, obj.y);
            });
        }
    }
}
