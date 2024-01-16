package io.ruin.model.item.actions.impl;

import io.ruin.cache.ItemID;
import io.ruin.model.item.actions.ItemAction;

public class ArdyCloak {

    static {
        ItemAction.registerInventory(ItemID.ARDOUGNE_CLOAK_1, "Monastery Teleport", (player, item) -> {
            player.addEvent(event -> {
                player.lock();
                player.animate(1816);
                player.graphics(436);
                event.delay(2);
                player.getMovement().teleport(2606, 3221, 0);
                event.delay(1);
                player.animate(715);
                player.unlock();
            });
        });
        ItemAction.registerEquipment(ItemID.ARDOUGNE_CLOAK_1, "Kandarin Monastery", (player, item) -> {
            player.addEvent(event -> {
                player.lock();
                player.animate(1816);
                player.graphics(436);
                event.delay(2);
                player.getMovement().teleport(2606, 3221, 0);
                event.delay(1);
                player.animate(715);
                player.unlock();
            });
        });

    }

}
