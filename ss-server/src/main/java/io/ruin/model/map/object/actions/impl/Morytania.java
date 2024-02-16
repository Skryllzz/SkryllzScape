package io.ruin.model.map.object.actions.impl;

import io.ruin.model.World;
import io.ruin.model.entity.player.Player;
import io.ruin.model.inter.dialogue.MessageDialogue;
import io.ruin.model.item.actions.ItemAction;
import io.ruin.model.item.actions.ItemObjectAction;
import io.ruin.model.map.Bounds;
import io.ruin.model.map.object.GameObject;
import io.ruin.model.map.object.actions.ObjectAction;

import static io.ruin.cache.ItemID.SILVER_SICKLE_B;

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
}