package io.ruin.model.map.object.actions.impl.lumbridge;

/*
 * @project Kronos
 * @author Patrity - https://github.com/Patrity
 * Created on - 3/2/2020
 */

import io.ruin.model.entity.player.Player;
import io.ruin.model.inter.dialogue.MessageDialogue;
import io.ruin.model.map.object.actions.ObjectAction;
import io.ruin.model.shop.ShopManager;

public class CulimancersChest {
    static {
        ObjectAction.register(12309, actions -> {
            actions[1] = (player, obj) -> openbank(player);
           //actions[2] = (player, obj) -> switchBook(player, SpellBook.ANCIENT, true);
            actions[4] = (player, obj) -> foodshop(player);
            actions[5] = (player, obj) -> itemshop(player);
        });
    }

    public static void foodshop(Player player) {
        ShopManager.openIfExists(player, "50c155bf-d1a2-4713-948b-87be3dced4eg");
    }

    public static void itemshop (Player player) {
        if (player.getStats().totalLevel >= 1500) {
            ShopManager.openIfExists(player, "50c155bf-d1a2-4713-948b-87be3dced4ef");
        } else if (player.getStats().totalLevel >= 1350 && player.getStats().totalLevel <=1499) {
            ShopManager.openIfExists(player, "50c155bf-d1a2-4713-948b-87be3dced4e9");
        } else if (player.getStats().totalLevel >= 1200 && player.getStats().totalLevel <=1349) {
            ShopManager.openIfExists(player, "50c155bf-d1a2-4713-948b-87be3dced4e8");
        } else if (player.getStats().totalLevel >= 1050 && player.getStats().totalLevel <=1199) {
            ShopManager.openIfExists(player, "50c155bf-d1a2-4713-948b-87be3dced4e7");
        } else if (player.getStats().totalLevel >= 900 && player.getStats().totalLevel <=1049) {
            ShopManager.openIfExists(player, "50c155bf-d1a2-4713-948b-87be3dced4e6");
        } else if (player.getStats().totalLevel >= 750 && player.getStats().totalLevel <=899) {
            ShopManager.openIfExists(player, "50c155bf-d1a2-4713-948b-87be3dced4e5");
        } else if (player.getStats().totalLevel >= 600 && player.getStats().totalLevel <=749) {
            ShopManager.openIfExists(player, "50c155bf-d1a2-4713-948b-87be3dced4e4");
        } else if (player.getStats().totalLevel >= 450 && player.getStats().totalLevel <=599) {
            ShopManager.openIfExists(player, "50c155bf-d1a2-4713-948b-87be3dced4e3");
        } else if (player.getStats().totalLevel >= 300 && player.getStats().totalLevel<=449) {
            ShopManager.openIfExists(player, "50c155bf-d1a2-4713-948b-87be3dced4e2");
        } else if (player.getStats().totalLevel >= 150 && player.getStats().totalLevel <=299) {
            ShopManager.openIfExists(player, "50c155bf-d1a2-4713-948b-87be3dced4e1");
        } else if (player.getStats().totalLevel < 150) {
            player.dialogue(new MessageDialogue("You need at least 150 total level to access this shop."));
        }
    }

    public static void openbank(Player player) {
        player.getBank().open();
    }
}
