package io.ruin.model.entity.npc.actions;

import io.ruin.model.entity.npc.NPCAction;
import io.ruin.model.entity.player.Player;
import io.ruin.model.inter.dialogue.MessageDialogue;
import io.ruin.model.inter.dialogue.NPCDialogue;
import io.ruin.model.inter.handlers.OptionScroll;
import io.ruin.model.inter.utils.Option;
import io.ruin.model.stat.StatType;

import java.util.ArrayList;
import java.util.List;

import static io.ruin.cache.NpcID.*;
import static io.ruin.data.impl.teleports.teleport;

public class GnomeGlider {

    public static final int[] GNOMEGLIDERS = {CAPTAIN_DALBUR, CAPTAIN_SHORACKS, CAPTAIN_ERRDO_6091, CAPTAIN_KLEMFOODLE, CAPTAIN_BLEEMADGE, GNORMADIUM_AVLAFRIM_7517 };

    static {
        for (int GG : GNOMEGLIDERS) {
        NPCAction.register(GG, 1, (player, npc) -> {
            OptionScroll.open(player, "Glider Network", getOptions(player));
        });
    }

    }

    private static List<Option> getOptions(Player player) {
        List<Option> options = new ArrayList<>();
        options.add(new Option("Ta Quir Priw (The Grand Tree)", () -> teleport(player, 2465, 3501, 3)));
        options.add(new Option("Sindarpos (White Wolf Mountain)", () -> teleport(player, 2851, 3498, 0)));
        options.add(new Option("Lemanto Andra (Digsite)", () -> teleport(player, 3326, 3427, 0)));
        options.add(new Option("Kar-Hewo (Al Kharid)", () -> teleport(player, 3284, 3213, 0)));
        options.add(new Option("Lemantolly Undri (Feldip Hills)", () -> teleport(player, 2539, 2971, 0)));
        options.add(new Option("Ookookolly Undri (Ape Atoll)", () -> {
            if (player.getStats().check(StatType.Agility, 25) && (player.getStats().totalLevel >= 50)) {
                teleport(player, 2716, 2804, 0);
            } else {
                for (int GG : GNOMEGLIDERS) {
                player.dialogue(new NPCDialogue(GG, "Daero wouldn't be to happy with me taking you there."),
                        new MessageDialogue("You need at least 25 agility and 50 combat to travel to ape atoll."));
                }
            }
        }));
        options.add(new Option("Gandius (Ship Yard)", () -> teleport(player, 2970, 2969, 0)));
        return options;
    }


}
