package io.ruin.model.map.object.actions.impl.draynor;

import io.ruin.model.map.object.actions.ObjectAction;

public class Draynor {

    static {
        /** Draynor Sewer Trapdoor **/
        ObjectAction.register(6434, 3084, 3272, 0, "open", (player, obj) -> {
            player.startEvent(event -> {
                player.lock();
                player.animate(827);
                event.delay(2);
                player.resetAnimation();
                player.getMovement().teleport(3085, 9672, 0);
                player.unlock();
            });
        });
        ObjectAction.register(17385, 3084, 9672, 0, "climb-up", (player, obj) -> {
            player.startEvent(event -> {
                player.lock();
                player.animate(828);
                event.delay(2);
                player.resetAnimation();
                player.getMovement().teleport(3084, 3273, 0);
                player.unlock();
            });
        });
    }
}
