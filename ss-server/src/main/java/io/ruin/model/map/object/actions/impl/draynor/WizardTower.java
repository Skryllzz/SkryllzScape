package io.ruin.model.map.object.actions.impl.draynor;

import io.ruin.model.inter.dialogue.OptionsDialogue;
import io.ruin.model.inter.utils.Option;
import io.ruin.model.map.object.actions.ObjectAction;

public class WizardTower {

    static {
        ObjectAction.register(12536, 3103, 3159, 0, "climb-up", (player, obj) -> player.getMovement().teleport(3105, 3160, 1));
        ObjectAction.register(12537, 3103, 3159, 1, "climb-up", (player, obj) -> player.getMovement().teleport(3104, 3161, 2));
        ObjectAction.register(12537, 3103, 3159, 1, "climb-down", (player, obj) -> player.getMovement().teleport(3105, 3160, 0));
        ObjectAction.register(12538, 3104, 3160, 2, "climb-down", (player, obj) -> player.getMovement().teleport(3105, 3160, 1));

        ObjectAction.register(12537, 3103, 3159, 1, "climb", (player, obj) -> {
            player.dialogue(
                    new OptionsDialogue(
                            new Option("Climb-Up.", () -> player.getMovement().teleport(3104, 3161, 2)),
                            new Option("Climb-Down.", () -> {
                                player.getMovement().teleport(3105, 3160, 0);
                            })
                    ));
        });
    }
}
