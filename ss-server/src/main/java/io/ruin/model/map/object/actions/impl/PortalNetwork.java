package io.ruin.model.map.object.actions.impl;

import io.ruin.cache.Color;
import io.ruin.model.entity.player.Player;
import io.ruin.model.entity.shared.LockType;
import io.ruin.model.inter.dialogue.MessageDialogue;
import io.ruin.model.inter.handlers.OptionScroll;
import io.ruin.model.inter.utils.Option;
import io.ruin.model.map.object.actions.ObjectAction;

import java.util.ArrayList;
import java.util.List;

public class PortalNetwork {

    static {
        ObjectAction.register(36240, "Travel", (player, obj) -> {
            OptionScroll.open(player, "Portal Network. Charges: " + player.PNCredit + ".", getOptions(player));
        });
    }

    public static void teleport(Player player, int x, int y, int z, int cred, int total) {
        player.resetActions(true, true, true);
        if(player.wildernessLevel >= 21) {
            if(player.getCombat().isDefending(16)) {
                player.sendMessage("You can't cast this spell while in combat in the wilderness.");
                return;
            }
            player.sendMessage("You can't cast this spell while above level 20 wilderness.");
            return;
        }
        if(player.getCombat().isDefending(16)) {
            player.sendMessage("You can't cast this spell while in combat.");
            return;
        }
        if (player.getStats().totalLevel >= total) {
            if (player.PNCredit >= cred) {
                player.lock(LockType.FULL_NULLIFY_DAMAGE); //keep lock outside of event!
                player.startEvent(event -> {
                        event.delay(2);
                        player.animate(4847);
                        player.graphics(800);
                        player.privateSound(193);
                        event.delay(5);
                        player.animate(4850);
                        player.privateSound(196);
                        event.delay(3);
                        player.animate(4853);
                        player.graphics(803);
                        player.privateSound(194);
                        event.delay(5);
                        player.animate(4857);
                        player.graphics(804);
                        player.privateSound(195);
                        event.delay(2);
                        player.getPacketSender().fadeOut();
                        event.delay(4);
                        player.getMovement().teleport(x, y, z);
                        player.PNCredit -= cred;
                        player.sendMessage(Color.DARK_RED.tag() + "You have " + player.PNCredit + " PN Charges remaining.");
                        event.delay(2);
                        player.getPacketSender().clearFade();
                        player.getPacketSender().fadeIn();
                        player.resetAnimation();
                        player.unlock();
                });
            } else {
                player.dialogue(new MessageDialogue("You need at least " + cred + " PN Credits to travel to this location. If you would like to purchase more you can visit Surok Magis in Varrock Castle"));
            }
        } else {
            player.dialogue(new MessageDialogue("You need a total level of at least " + total + " to travel to this location."));
        }
    }

    private void TeleportAction(Player player) {
            if(player.wildernessLevel > 0) {
                if(player.getCombat().isDefending(16)) {
                    player.sendMessage("You can't cast this spell while in combat in the wilderness.");
                    return;
                }
            } else if(player.pvpAttackZone) {
                if(player.getCombat().isDefending(16)) {
                    player.sendMessage("You can't cast this spell while in combat in a pvp zone.");
                    return;
                }
            }
    }
    private static List<Option> getOptions(Player player) {
        List<Option> options = new ArrayList<>();
        options.add(new Option("Lumbridge - Free - Total: 1", () -> teleport(player, 3232, 3223, 0, 0, 1)));
        options.add(new Option("Al Kharid - 1 Charges - Total: 50", () -> teleport(player, 3297, 3175, 0, 1, 50)));
        options.add(new Option("Varrock - 1 Charges - Total: 100", () -> teleport(player, 3215, 3379, 0, 1, 100)));
        options.add(new Option("Draynor Village - 2 Charges - Total: 150", () -> teleport(player, 3090, 3260, 0, 2, 150)));
        options.add(new Option("Edgeville - 3 Charges - Total: 200", () -> teleport(player, 3074, 3510, 0, 3, 200)));
        options.add(new Option("Falador - 4 Charges - Total: 250", () -> teleport(player, 3058, 3349, 0, 4, 250)));
        options.add(new Option("Port Sarim - 5 Charges - Total: 300", () -> teleport(player, 3009, 3221, 0, 5, 300)));
        options.add(new Option("Burthrope - 6 Charges - Total: 350", () -> teleport(player, 3215, 3379, 0, 6, 350)));
        options.add(new Option("Ardougne - 7 Charges - Total: 400", () -> teleport(player, 2639, 3360, 0, 7, 400)));
        options.add(new Option("Canifis - 8 Charges - Total: 450", () -> teleport(player, 3506, 3471, 0, 8, 450)));
        options.add(new Option("Yanille - 9 Charges - Total: 500", () -> teleport(player, 2618, 3094, 0, 9, 500)));
        options.add(new Option("Hosidius - 10 Charges - Total: 550", () -> teleport(player, 1770, 3594, 0, 10, 550)));
        options.add(new Option("Arceuus - 11 Charges - Total: 600", () -> teleport(player, 1652, 3763, 0, 11, 600)));
        return options;
    }
}
