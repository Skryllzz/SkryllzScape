package io.ruin.model.map.object.actions.impl;

import io.ruin.model.entity.player.Player;
import io.ruin.model.inter.dialogue.MessageDialogue;
import io.ruin.model.inter.dialogue.OptionsDialogue;
import io.ruin.model.inter.utils.Option;
import io.ruin.model.map.object.actions.ObjectAction;
import io.ruin.model.skills.magic.SpellBook;
import io.ruin.model.stat.StatType;

public class Rellekka {

    static {
        registerPrayAction();

        /** Rellekka Slayer Cave**/
        ObjectAction.register(2141, 2809, 10001, 0, "Enter", (player, obj) -> {
            player.startEvent(event -> {
            player.lock();
            player.animate(2796);
            event.delay(2);
            player.resetAnimation();
            player.getMovement().teleport(2797, 3615, 0);
            player.unlock();
            });
        });
        ObjectAction.register(2123, 2797, 3614, 0, "Enter", (player, obj) -> {
            player.startEvent(event -> {
                player.lock();
                player.animate(2796);
                event.delay(2);
                player.resetAnimation();
                player.getMovement().teleport(2808, 10002, 0);
                player.unlock();
            });
        });
    }
    public static void registerPrayAction() {
        ObjectAction.register(34771, "pray", (player, obj) -> {
            if (player.getStats().check(StatType.Herblore, 5) && player.getStats().check(StatType.Crafting, 61)
                    && player.getStats().check(StatType.Defence, 60) && player.getStats().check(StatType.Firemaking, 49)
                    && player.getStats().check(StatType.Magic, 65) && player.getStats().check(StatType.Mining,60) && player.getStats().check(StatType.Woodcutting, 55))  {
                        player.dialogue(new OptionsDialogue("Select which prayer book you'd like to switch to:",
                                new Option("Modern", () -> switchBook(player, SpellBook.MODERN, false)),
                                new Option("Lunar", () -> switchBook(player, SpellBook.LUNAR, false))
                        ));
                    } else {
                        player.dialogue(new MessageDialogue("You need 5 Herblore, 61 Crafting, 60 Defence, 49 Firemaking, 65 Magic, 60 Mining, and 55 Woodcutting to pray."));
                }
        });
    }

    public static void switchBook(Player player, SpellBook book, boolean altar) {
        if (book.isActive(player) && altar) {
            player.dialogue(new MessageDialogue("You're already using this spellbook."));
            return;
        }
        book.setActive(player);
        if (altar) {
            player.animate(645);
            player.sendMessage("You are now using the " + book.name + " spellbook.");
        }
    }
}