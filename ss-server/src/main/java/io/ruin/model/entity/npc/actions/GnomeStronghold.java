package io.ruin.model.entity.npc.actions;

import io.ruin.model.entity.npc.NPCAction;
import io.ruin.model.shop.ShopManager;

public class GnomeStronghold {

    static {
        /** King Narnode **/
        NPCAction.register(8019, "talk-to", (player, npc) -> {
            ShopManager.openIfExists(player, "187125fd-8a23-4952-a059-f9c5127b6225");
        });
    }
}
