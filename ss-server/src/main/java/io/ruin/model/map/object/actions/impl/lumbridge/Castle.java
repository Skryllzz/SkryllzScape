package io.ruin.model.map.object.actions.impl.lumbridge;

import io.ruin.model.inter.dialogue.OptionsDialogue;
import io.ruin.model.inter.utils.Option;
import io.ruin.model.map.object.actions.ObjectAction;

public class Castle {

    public static final int[] LIGHTSOURCE = {33, 32, 594, 7053 };

    static {
        /**
         * Castle Staircase
         */
        //South
        ObjectAction.register(16671, 3204, 3207, 0, "climb-up", (player, obj) -> player.getMovement().teleport(3205, 3209, 1));
        ObjectAction.register(16673, 3205, 3208, 2, "climb-down", (player, obj) -> player.getMovement().teleport(3205, 3209, 1));

        ObjectAction.register(16672, 3204, 3207, 1, "climb", (player, obj) -> {
                player.dialogue(
                        new OptionsDialogue(
                                new Option("Climb-Up.", () -> player.getMovement().teleport(3205, 3209, 2)),
                                new Option("Climb-Down.", () -> {
                                    player.getMovement().teleport(3205, 3209, 0);
                                })
                        ));
        });

        //North
        ObjectAction.register(16671, 3204, 3229, 0, "climb-up", (player, obj) -> player.getMovement().teleport(3205, 3228, 1));
        ObjectAction.register(16673, 3205, 3229, 2, "climb-down", (player, obj) -> player.getMovement().teleport(3205, 3228, 1));

        ObjectAction.register(16672, 3204, 3229, 1, "climb", (player, obj) -> {
            player.dialogue(
                    new OptionsDialogue(
                            new Option("Climb-Up.", () -> player.getMovement().teleport(3206, 3228, 2)),
                            new Option("Climb-Down.", () -> {
                                player.getMovement().teleport(3205, 3228, 0);
                            })
                    ));
        });

        //Alkharid Gate
            ObjectAction.register(2882, 3268, 3227, 0, "open", (player, obj) -> {
                if (player.isAt(3267, 3227) || player.isAt(3267, 3228)) {
                    player.getMovement().teleport(player.getAbsX() + 1, player.getAbsY(), 0);
                    player.sendMessage("The guards let you pass for free as you are a friend of Al-Kharid.");
                }
                if (player.isAt(3268, 3227) || player.isAt(3268, 3228)) {
                    player.getMovement().teleport(player.getAbsX() -1, player.getAbsY(), 0);
                    player.sendMessage("The guards let you pass for free as you are a friend of Al-Kharid.");
                }
            });

        ObjectAction.register(2882, 3268, 3227, 0, "pay-toll(10gp)", (player, obj) -> {
            if (player.isAt(3267, 3227) || player.isAt(3267, 3228)) {
                player.getMovement().teleport(player.getAbsX() + 1, player.getAbsY(), 0);
                player.sendMessage("The guards let you pass for free as you are a friend of Al-Kharid.");
            }
            if (player.isAt(3268, 3227) || player.isAt(3268, 3228)) {
                player.getMovement().teleport(player.getAbsX() -1, player.getAbsY(), 0);
                player.sendMessage("The guards let you pass for free as you are a friend of Al-Kharid.");
            }
        });

        ObjectAction.register(2883, 3268, 3228, 0, "open", (player, obj) -> {
            if (player.isAt(3267, 3227) || player.isAt(3267, 3228)) {
                player.getMovement().teleport(player.getAbsX() + 1, player.getAbsY(), 0);
                player.sendMessage("The guards let you pass for free as you are a friend of Al-Kharid.");
            }
            if (player.isAt(3268, 3227) || player.isAt(3268, 3228)) {
                player.getMovement().teleport(player.getAbsX() -1, player.getAbsY(), 0);
                player.sendMessage("The guards let you pass for free as you are a friend of Al-Kharid.");
            }
        });
        ObjectAction.register(2883, 3268, 3228, 0, "pay-toll(10gp)", (player, obj) -> {
            if (player.isAt(3267, 3227) || player.isAt(3267, 3228)) {
                player.getMovement().teleport(player.getAbsX() + 1, player.getAbsY(), 0);
                player.sendMessage("The guards let you pass for free as you are a friend of Al-Kharid.");
            }
            if (player.isAt(3268, 3227) || player.isAt(3268, 3228)) {
                player.getMovement().teleport(player.getAbsX() -1, player.getAbsY(), 0);
                player.sendMessage("The guards let you pass for free as you are a friend of Al-Kharid.");
            }
        });

        /** Lumbridge Cellar Entrance **/
        //Tile.getObject(6905, 3221, 9618, 0).skipReachCheck = p -> p.equals(3221, 9618) || p.equals(3220, 9618) || p.equals(3219, 9618);


       /** ObjectAction.register(6905, 1, (player, obj) -> {
            for (int LS : LIGHTSOURCE) {
                if (player.getAbsX()== 3221) {
                    player.animate(746);
                    player.getMovement().teleport(3219, 9618, 0);
                } else if (player.getAbsX()== 3219) {
                    if (player.getInventory().hasItem(LS, 1)) {
                        player.animate(746);
                        player.getMovement().teleport(3221, 9618, 0);
                    } else {
                        player.sendMessage("Best to have a light source before entering.");
                    }
                }
            }
        });**/
        /**ObjectAction.register(6905, 3219, 9618, 0, "Squeeze-through", (player, obj) -> {
            for (int LS : LIGHTSOURCE) {
                if (player.getInventory().hasItem(LS, 1)) {
                    player.animate(746);
                    player.getMovement().teleport(3221, 9618, 0);
                } else {
                    player.sendMessage("Best to have a light source before entering.");
                }
            }
        });

        ObjectAction.register(6905, 3221, 9618, 0, "squeeze-through", (player, obj) -> {
                    player.animate(746);
                    player.getMovement().teleport(3219, 9618, 0);
        });**/

        /** Cellar Cave into The Lumbridge Swamp **/
        ObjectAction.register(6912, 3224, 9601, 0, "squeeze-through", (player, obj) -> {
                    if (player.getAbsY() == 9604) {
                        player.animate(746);
                        player.getMovement().teleport(3224, 9600, 0);
                    } else if (player.getAbsY() == 9600) {
                        player.animate(746);
                        player.getMovement().teleport(3224, 9604, 0);
                    }
        });
    }
}
