package io.ruin.model.map.object.actions.impl;

import io.ruin.model.entity.shared.StepType;
import io.ruin.model.map.object.actions.ObjectAction;

public class ShantayPass {

    static {
        ObjectAction.register(4031, 1, (player, obj) -> {
            if (player.getAbsY() == 3117) {
                if (player.getInventory().hasItem(1854, 1)) {
                    player.getInventory().remove(1854, 1);
                    player.step(0, player.getAbsY() > obj.y ? -2 : 2, StepType.FORCE_WALK);
                } else {
                    player.sendMessage("You need a shantay pass to enter.");
                }
            } else if (player.getAbsY() == 3115) {
                    player.step(0, player.getAbsY() > obj.y ? -2 : 2, StepType.FORCE_WALK);
            }
        });
    }

}
