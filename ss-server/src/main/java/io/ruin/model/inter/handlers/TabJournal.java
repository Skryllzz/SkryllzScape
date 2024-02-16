package io.ruin.model.inter.handlers;

import io.ruin.model.entity.player.Player;
import io.ruin.model.inter.Interface;
import io.ruin.model.inter.InterfaceHandler;
import io.ruin.model.inter.actions.SimpleAction;

public class TabJournal {

    static {
        InterfaceHandler.register(701, h -> {
            h.actions[48] = (SimpleAction) TabJournal::restore;
//                h.actions[49] = (SimpleAction) TabJournal::restore;
            for(int i = 9; i <= 47; i++) {
                int finalI = i;
                h.actions[i] = (SimpleAction) p -> p.journal.select(p, finalI - 8);
            }
        });
    }

   /** public static void swap(Player player, int interfaceId) {
        if(player.isFixedScreen())
            player.getPacketSender().sendInterface(interfaceId, 548, 68, 1);
        else if(player.getGameFrameId() == 164)
            player.getPacketSender().sendInterface(interfaceId, 164, 70, 1);
        else
            player.getPacketSender().sendInterface(interfaceId, 161, 70, 1);
    }**/

    public static void swap(Player player, int interfaceId) {
        player.getPacketSender().sendInterface(interfaceId, Interface.QUEST_TAB, 2, 1);
    }

    public static void restore(Player player) {
        swap(player, Interface.NOTICEBOARD);
        TabQuest.send(player);
        //player.journal.send(player);
    }

}