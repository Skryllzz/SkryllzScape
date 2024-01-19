package io.ruin.model.item.actions.impl.chargable;

import io.ruin.cache.Color;
import io.ruin.cache.ItemDef;
import io.ruin.model.entity.player.Player;
import io.ruin.model.item.Item;
import io.ruin.model.item.actions.ItemAction;
import io.ruin.model.item.attributes.AttributeExtensions;
import io.ruin.model.item.attributes.AttributeTypes;

public class Ibans {

    private static final int IBANS = 1409;
    private static final int IBANSU = 12658;

    private static final int MAX_CHARGES = 120;
    private static final int MAX_CHARGESU = 2500;

    static {
        ItemAction.registerEquipment(IBANS, "check", Ibans::check);
        ItemAction.registerEquipment(IBANSU, "check", Ibans::checku);
        ItemAction.registerInventory(IBANS, "check", Ibans::check);;
        ItemAction.registerInventory(IBANSU, "check", Ibans::checku);
        ItemDef.get(IBANS).addPreTargetDefendListener((player, item, hit, target) -> {
            if (hit.attackStyle != null && hit.attackStyle.isMagic() && target.npc != null) {
                consumeCharge(player, item);
            }
        });
    }


    public static void addChargesIban(Player player, Item ibans) {
        int charges = AttributeExtensions.getCharges(ibans);
        if (charges >= 1) {
            player.sendMessage("You can't charge the staff");
            return;
        }
        AttributeExtensions.addCharges(ibans, 120);
        check(player, ibans);
    }

    public static void addChargesIbanU(Player player, Item ibansu) {
        int charges = AttributeExtensions.getCharges(ibansu);
        int allowedAmount = MAX_CHARGES - charges;
        if (allowedAmount == 0) {
            player.sendMessage("You can't charge the staff");
            return;
        }
        AttributeExtensions.addCharges(ibansu, 2500);
        check(player, ibansu);
    }

    private static void check(Player player, Item ibans) {
        int charges = AttributeExtensions.getCharges(ibans);
        player.sendMessage("Your staff currently holds " + (charges) + " charges" + (charges <= 1 ? "" : "s") + ".");
    }

    private static void checku(Player player, Item ibansu) {
        int charges = AttributeExtensions.getCharges(ibansu);
        player.sendMessage("Your staff currently holds " + (charges) + " charges" + (charges <= 1 ? "" : "s") + ".");
    }

    public static boolean consumeCharge(Player player, Item item) {
        int charges = AttributeExtensions.getCharges(item);
        AttributeExtensions.deincrementCharges(item, 1);
        if (charges <= 0) {
            item.clearAttribute(AttributeTypes.CHARGES);
            player.sendMessage(Color.RED.wrap("Your Ibans Staff ran out of charges!"));
            player.getCombat().updateWeapon(false);
        }
        return true;
    }

}
