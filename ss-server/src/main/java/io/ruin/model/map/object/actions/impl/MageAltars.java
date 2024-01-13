package io.ruin.model.map.object.actions.impl;

import io.ruin.model.entity.player.Player;
import io.ruin.model.inter.dialogue.MessageDialogue;
import io.ruin.model.inter.dialogue.OptionsDialogue;
import io.ruin.model.inter.utils.Option;
import io.ruin.model.map.object.actions.ObjectAction;
import io.ruin.model.skills.magic.SpellBook;
import io.ruin.model.stat.StatType;

public class MageAltars {
    static {
        ObjectAction.register(6552, actions -> {
            actions[1] = (player, obj) -> player.dialogue(
                    new OptionsDialogue("Select which prayer book you'd like to switch to:",
                            new Option("Modern", () -> switchBook(player, SpellBook.MODERN, true)),
                            new Option("Ancient", () -> switchBook(player, SpellBook.ANCIENT, true))
                    )
            );
        });

        /** Pyramid ALtar Room Exit **/
        ObjectAction.register(6553, 3234, 9324, 0, "open", (player, obj) -> {
            player.addEvent(e -> {
                player.getPacketSender().fadeOut();
                player.getMovement().teleport(3233, 2887, 0);
                player.getPacketSender().fadeIn();
                player.sendMessage("You walk through the pyramid and end up outside.");
            });
        });

        /** Back door to Pyramid **/
        ObjectAction.register(6481, "enter", (player, obj) -> {
            player.dialogue(new MessageDialogue("You cannot enter here."));
        });

        /** Entrance doors to Pyramid **/
        ObjectAction.register(6545, 3233, 2899, 0, "open", (player, obj) -> {
            if (player.getStats().check(StatType.Thieving , 53) && (player.getStats().check(StatType.Magic, 50))
                && (player.getStats().check(StatType.Firemaking, 50)) && (player.getStats().check(StatType.Slayer, 10))) {
                if (player.isAt(3233, 2900) || player.isAt(3232, 2900)) {
                    player.getMovement().teleport(player.getAbsX(), player.getAbsY() - 2, 0);
                }
            } else {
                player.dialogue(new MessageDialogue("You need 53 Thieving, 50 Magic, 50 Firemaking, and 10 slayer to enter here."));
            }
        });

        ObjectAction.register(6547, 3232, 2899, 0, "open", (player, obj) -> {
            if (player.getStats().check(StatType.Thieving , 53) && (player.getStats().check(StatType.Magic, 50))
                    && (player.getStats().check(StatType.Firemaking, 50)) && (player.getStats().check(StatType.Slayer, 10))) {
            if (player.isAt(3233, 2900) || player.isAt(3232, 2900)) {
                player.getMovement().teleport(player.getAbsX(), player.getAbsY() - 2, 0);
            }
            } else {
                player.dialogue(new MessageDialogue("You need 53 Thieving, 50 Magic, 50 Firemaking, and 10 slayer to enter here."));
            }
        });
    }

    public static void switchBook(Player player, SpellBook book, boolean altar) {
        if(book.isActive(player) && altar) {
            player.dialogue(new MessageDialogue("You're already using this spellbook."));
            return;
        }
        book.setActive(player);
        if(altar) {
            player.animate(645);
            player.sendMessage("You are now using the " + book.name + " spellbook.");
        }
    }
}
