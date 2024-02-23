package io.ruin.model.content;

import io.ruin.model.entity.player.Player;
import io.ruin.model.inter.dialogue.MessageDialogue;
import io.ruin.model.stat.StatType;

public class QuestReqs {

    public static boolean HerosQuest(Player player) {
        if (player.getStats().check(StatType.Cooking, 53) &&
                player.getStats().check(StatType.Fishing, 53) &&
                player.getStats().check(StatType.Mining, 50) &&
                player.getStats().check(StatType.Herblore, 25) &&
                player.getStats().check(StatType.Crafting, 31) &&
                player.getStats().check(StatType.Woodcutting, 36) &&
                player.getStats().check(StatType.Magic, 33) &&
                player.getStats().check(StatType.Prayer, 37) &&
                player.getCombat().getLevel() >= 60) {
            return true; // All requirements are met
        } else {
            String requirements = "Cooking 53, Fishing 53, Mining 50, Herblore 25, Crafting 31, Woodcutting 36, Magic 33, Prayer 37, Combat Level 60";
            player.dialogue(new MessageDialogue("You need to have the following Heros quest requirements to enter: " + requirements));
            return false; // Not all requirements are met
        }
    }
}
