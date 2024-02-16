package io.ruin.model.map.object.actions.impl;

import io.ruin.model.World;
import io.ruin.model.entity.npc.NPC;
import io.ruin.model.entity.npc.NPCAction;
import io.ruin.model.entity.player.Player;
import io.ruin.model.inter.dialogue.ItemDialogue;
import io.ruin.model.inter.dialogue.MessageDialogue;
import io.ruin.model.inter.dialogue.NPCDialogue;
import io.ruin.model.inter.dialogue.OptionsDialogue;
import io.ruin.model.inter.utils.Option;
import io.ruin.model.item.actions.ItemAction;
import io.ruin.model.item.actions.ItemObjectAction;
import io.ruin.model.map.Bounds;
import io.ruin.model.map.object.GameObject;
import io.ruin.model.map.object.actions.ObjectAction;

import static io.ruin.cache.ItemID.*;
import static io.ruin.cache.NpcID.NATURE_SPIRIT;

public class Morytania {
    private static final Bounds MORTLOGAREA = new Bounds(3422, 3331, 3444, 3358, 0);

    public enum Mortlog {
        ROTTENLOG(3508, 3509, 5);

        public final int originalId, replacementID, respawnTime;

        Mortlog(int originalId, int replacementID, int respawnTime) {
            this.originalId = originalId;
            this.replacementID = replacementID;
            this.respawnTime = respawnTime;
        }
    }

    static {
        ItemAction.registerInventory(SILVER_SICKLE_B, "Cast Bloom", (player, item) -> {
            player.dialogue(new MessageDialogue("Use the sickle on the log you want to grow"));
        });
        ItemObjectAction.register(SILVER_SICKLE_B, "rotting log", (player, item, obj) -> updatelog(player, obj));
        ObjectAction.register(3509, "pick", Morytania::pick);
        ObjectAction.register(3516, 3440, 3337, 0, "enter", (player, obj) -> player.getMovement().teleport(3442, 9734, 1));
        ObjectAction.register(3526, 3442, 9733, 1, "exit", (player, obj) -> player.getMovement().teleport(3440, 3337, 0));
        NPCAction.register(NATURE_SPIRIT, "talk-to", (player, npc) -> {natureSpirit(player);});
    }

    private static void replacelog(GameObject log) {
        World.startEvent(event -> {
            log.setId(3509);
            event.delay(30);
            log.setId(log.originalId);
        });
    }

    private static void updatelog(Player player, GameObject log) {
        player.startEvent(event -> {
            player.sendMessage("You cast bloom and fungi grows on the log");
            player.animate(1100);
            player.graphics(263);
            replacelog(log);
            player.unlock();
        });
    }

    private static void pick(Player player, GameObject flax) {
        if(player.getInventory().isFull()) {
            player.sendMessage("You can't carry any more fungi.");
            return;
        }
        player.startEvent(event -> {
            player.lock();
            player.animate(827);
            event.delay(1);
            player.getInventory().add(2970, 1);
            player.sendMessage("You pick some mort myre fungi.");
            player.unlock();
        });
    }

    public static void bless(Player player) {
        NPC npc = new NPC(NATURE_SPIRIT);
        if (player.getInventory().hasItem(SILVER_SICKLE,1)) {
        if (player.getInventory().hasItem(COINS_995, 50000)) {
        player.startEvent(event -> {
            player.lock();
            player.dialogue(new NPCDialogue(NATURE_SPIRIT, "Certainly!."),
                    new ItemDialogue().one(SILVER_SICKLE, "You hand your sickle to the spirit."));
            event.delay(2);
            player.getInventory().remove(SILVER_SICKLE,1 );
            player.dialogue(new ItemDialogue().one(SILVER_SICKLE_B, "Spirit hands you the blessed sickle."));
            event.delay(2);
            player.getInventory().add(SILVER_SICKLE_B);
            player.unlock();
            });
        } else {
            player.dialogue(new NPCDialogue(NATURE_SPIRIT, "I'm afraid you don't have enough coins."));
        }
        } else {
            player.dialogue(new MessageDialogue( "You don't have any sickles on you."));
        }
    }

    public static void natureSpirit(Player player) {
        player.dialogue(new NPCDialogue(NATURE_SPIRIT, "Hello " + player.getName() + ", how may I help you?"),
                        new OptionsDialogue("Choose an Option:",
                        new Option("Can you bless my sickle? (50k coins)", () -> bless(player)),
                        new Option("Nothing at the moment.")));

    }
}