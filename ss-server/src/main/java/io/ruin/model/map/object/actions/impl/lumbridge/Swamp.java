package io.ruin.model.map.object.actions.impl.lumbridge;

import io.ruin.cache.ItemID;
import io.ruin.model.inter.dialogue.OptionsDialogue;
import io.ruin.model.inter.utils.Option;
import io.ruin.model.item.actions.ItemAction;
import io.ruin.model.item.actions.ItemItemAction;
import io.ruin.model.map.object.actions.ObjectAction;

import static io.ruin.model.skills.Tool.TINDER_BOX;

public class Swamp {

    public static final int[] LIGHTSOURCE = {33, 32, 594, 7053 };

    static {
        /** Dark Hole **/
        ObjectAction.register(5947, 3169, 3172, 0, "climb-down", (player, obj) -> {
            for (int LS : LIGHTSOURCE) {
            if (player.getInventory().hasItem(LS, 1)) {
                player.animate(827);
                player.getMovement().teleport(3169, 9571, 0);
                } else {
                    player.sendMessage("Best to have a light source before going down.");
                }
            }
        });

        /** Enter Coffin to get back to deaths office **/
        ObjectAction.register(2145, 3249, 3192, 0, "open", (player, obj) -> {
            player.getMovement().teleport(1867, 4243, 0);
        });

        /** Tools in Shed **/
        ObjectAction.register(10375, 3203, 3170, 0, "take", (player, obj) -> {
            player.dialogue(new OptionsDialogue("Which tool would you like to take?",
                    new Option("Rake", () -> player.getInventory().add(ItemID.RAKE, 1)),
                    new Option("Spade", () -> player.getInventory().add(ItemID.SPADE, 1)),
                    new Option("Trowel", () -> player.getInventory().add(ItemID.TROWEL, 1)),
                    new Option("Seed Dibber", () -> player.getInventory().add(ItemID.SEED_DIBBER, 1)),
                    new Option("Watering Can", () -> player.getInventory().add(ItemID.WATERING_CAN, 1))));
        });

        /** Climb Up from Swamp Dungeon **/
        ObjectAction.register(5946, 3169, 9572, 0, "climb", (player, obj) -> {
                    player.animate(828);
                    player.getMovement().teleport(3169, 3173, 0);
        });

        /*** Light sources ***/
        /** Candle **/
        ItemItemAction.register(36, TINDER_BOX, (player, primary, secondary) -> {
                    player.getInventory().remove(36,1);
                    player.getInventory().add(33, 1);
                });
        /** Extingwish Candle **/
        ItemAction.registerInventory(33, "Extinguish", (player, item) -> {
            player.getInventory().remove(33,1);
            player.getInventory().add(36, 1);
            player.sendMessage("You extinguish the candle");
                });
        /** Torch **/
        ItemItemAction.register(596, TINDER_BOX, (player, primary, secondary) -> {
            player.getInventory().remove(596,1);
            player.getInventory().add(594, 1);
        });
        /** Extingwish Torch **/
        ItemAction.registerInventory(594, "Extinguish", (player, item) -> {
            player.getInventory().remove(594,1);
            player.getInventory().add(596, 1);
            player.sendMessage("You extinguish the torch");
        });
        /** Black Candle **/
        ItemItemAction.register(38, TINDER_BOX, (player, primary, secondary) -> {
            player.getInventory().remove(38,1);
            player.getInventory().add(32, 1);
        });
        /** Extingwish Black Candle **/
        ItemAction.registerInventory(32, "Extinguish", (player, item) -> {
            player.getInventory().remove(32,1);
            player.getInventory().add(38, 1);
            player.sendMessage("You extinguish the black candle");
        });


    }

}
