package io.ruin.model.map.object.actions.impl;

import io.ruin.model.entity.player.Player;
import io.ruin.model.inter.dialogue.MessageDialogue;
import io.ruin.model.inter.handlers.OptionScroll;
import io.ruin.model.inter.utils.Option;
import io.ruin.model.map.object.actions.ObjectAction;
import io.ruin.model.skills.woodcutting.Hatchet;

import java.util.ArrayList;
import java.util.List;

import static io.ruin.data.impl.teleports.teleport;

public class CanoeStation {

    public static final int[] LOGSTATION = { 12144 };

    static {
        for (int LST : LOGSTATION) {
            ObjectAction.register(LST, 1, (player, obj) -> {
                Hatchet hatchet = Hatchet.find(player);
                if (hatchet != null) {
                    OptionScroll.open(player, "Canoe Network", getOptions(player));
                } else {
                    player.dialogue(new MessageDialogue("You need a axe to use the canoe network."));
                }
            });
        }
    }

    private static List<Option> getOptions(Player player) {
        List<Option> options = new ArrayList<>();
        options.add(new Option("Lumbridge", () -> teleport(player, 3243, 3236, 0)));
        options.add(new Option("Champion's Guild", () -> teleport(player, 3204, 3343, 0)));
        options.add(new Option("Barbarian Village", () -> teleport(player, 3112, 3409, 0)));
        options.add(new Option("Edgeville", () -> teleport(player, 3132, 3509, 0)));
        options.add(new Option("Wilderness", () -> teleport(player, 3145, 3800, 0)));
        return options;
    }

}
