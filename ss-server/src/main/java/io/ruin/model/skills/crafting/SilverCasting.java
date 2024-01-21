package io.ruin.model.skills.crafting;

import io.ruin.model.entity.player.Player;
import io.ruin.model.inter.Interface;
import io.ruin.model.inter.InterfaceHandler;
import io.ruin.model.inter.InterfaceType;
import io.ruin.model.inter.actions.DefaultAction;
import io.ruin.model.item.Item;
import io.ruin.model.item.actions.ItemObjectAction;
import io.ruin.model.stat.StatType;

public enum SilverCasting {

    /** TOPAZ **/
    TOPAZ_AMULET(1595, 21105, 1613, 45, 80.0, 17),
    TOPAZ_BRACELET(11065, 21123, 1613, 38, 75.0, 21),
    TOPAZ_NECKLACE(1597, 21096, 1613, 32, 70.0, 13),
    TOPAZ_RING(1592, 21087, 1613, 16, 35.0, 9),

    /** OPAL **/
    OPAL_AMULET(1595, 21108, 1609, 27, 55.0, 15),
    OPAL_BRACELET(11065, 21117, 1609, 22, 45.0, 19),
    OPAL_NECKLACE(1597, 21090, 1609, 16, 35.0, 11),
    OPAL_RING(1592, 21081, 1609, 1, 10.0, 7),

    /** JADE **/
    JADE_AMULET(1595, 21111, 1611, 34, 70.0, 16),
    JADE_BRACELET(11065, 21120, 1611, 29, 60.0, 20),
    JADE_NECKLACE(1597, 21093, 1611, 25, 54.0, 12),
    JADE_RING(1592, 21084, 1611, 13, 32.0, 8),

    /** SILVER **/
    HOLY_SYMBOL(1599, 1718, -1, 32, 70.0, 23),
    SILVER_SICKLE(2976, 2961, -1, 18, 50.0, 25),
    SILVER_BOLT(9434, 9382, -1, 21, 50.0, 27),
    UNHOLY_SYMBOL(1594, 1720, -1, 16, 50.0, 24),
    SILVER_TIARA(5523, 5525, 1615, 23, 52.5, 28),
    DEMONIC_SIGIL(6747, 6748, -1, 30, 50.0, 30);

    public final int mould, jewellery, gem, levelReq, child;
    public final double exp;

    SilverCasting(int mould, int jewellery, int gem, int levelReq, double exp, int child) {
        this.mould = mould;
        this.jewellery = jewellery;
        this.gem = gem;
        this.levelReq = levelReq;
        this.exp = exp;
        this.child = child;
    }

    private static final int SILVER_BAR = 2355;

    private static void craft(Player player, SilverCasting mould, int amount) {
        player.closeInterfaces();
        if(!player.getStats().check(StatType.Crafting, mould.levelReq, "make this"))
            return;
        player.startEvent(event -> {
            int amt = amount;
            while(amt --> 0) {
                Item gem = player.getInventory().findItem(mould.gem);
                if(mould.gem != -1 && gem == null)
                    return;
                Item silverBar = player.getInventory().findItem(SILVER_BAR);
                if(silverBar == null)
                    return;
                Item mouldItem = player.getInventory().findItem(mould.mould);
                if(mouldItem == null)
                    return;
                player.animate(899);
                event.delay(3);
                if(gem != null)
                    gem.remove();
                silverBar.remove();
                player.getInventory().add(mould.jewellery, 1);
                player.getStats().addXp(StatType.Crafting, mould.exp, true);
                event.delay(1);
            }
        });
    }

    private static void multiplecraft(Player player, SilverCasting mould, int amount) {
        player.closeInterfaces();
        if(!player.getStats().check(StatType.Crafting, mould.levelReq, "make this"))
            return;
        player.startEvent(event -> {
            int amt = amount;
            while(amt --> 0) {
                Item gem = player.getInventory().findItem(mould.gem);
                if(mould.gem != -1 && gem == null)
                    return;
                Item silverBar = player.getInventory().findItem(SILVER_BAR);
                if(silverBar == null)
                    return;
                Item mouldItem = player.getInventory().findItem(mould.mould);
                if(mouldItem == null)
                    return;
                player.animate(899);
                event.delay(3);
                if(gem != null)
                    gem.remove();
                silverBar.remove();
                player.getInventory().add(mould.jewellery, 10);
                player.getStats().addXp(StatType.Crafting, mould.exp, true);
                event.delay(1);
            }
        });
    }

    private static void option(Player player, SilverCasting mould) {
        craft(player, mould, Integer.MAX_VALUE);
        //TODO:: Come back later when need to fix it
        //player.integerInput("Enter amount:", amt -> craft(player, mould, amt));
    }

    private static void multioption(Player player, SilverCasting mould) {
        multiplecraft(player, mould, Integer.MAX_VALUE);
        //TODO:: Come back later when need to fix it
        //player.integerInput("Enter amount:", amt -> craft(player, mould, amt));
    }


    static {
        ItemObjectAction.register(SILVER_BAR, "furnace", (player, item, object) -> {
            player.openInterface(InterfaceType.MAIN, Interface.SILVER_CASTING);
            player.getPacketSender().sendAccessMask(Interface.SILVER_CASTING, 4, 0, 11, 62);
            player.getPacketSender().sendAccessMask(Interface.SILVER_CASTING, 6, 0, 7, 62);
        });

        InterfaceHandler.register(Interface.SILVER_CASTING, h -> {
            /*
             * test
             */
            h.actions[TOPAZ_AMULET.child] = (DefaultAction) (p, option, slot, itemId) -> option(p, TOPAZ_AMULET);
            h.actions[TOPAZ_BRACELET.child] = (DefaultAction) (p, option, slot, itemId) -> option(p, TOPAZ_BRACELET);
            h.actions[TOPAZ_NECKLACE.child] = (DefaultAction) (p, option, slot, itemId) -> option(p, TOPAZ_NECKLACE);
            h.actions[TOPAZ_RING.child] = (DefaultAction) (p, option, slot, itemId) -> option(p, TOPAZ_RING);
            h.actions[JADE_AMULET.child] = (DefaultAction) (p, option, slot, itemId) -> option(p, JADE_AMULET);
            h.actions[JADE_BRACELET.child] = (DefaultAction) (p, option, slot, itemId) -> option(p, JADE_BRACELET);
            h.actions[JADE_NECKLACE.child] = (DefaultAction) (p, option, slot, itemId) -> option(p, JADE_NECKLACE);
            h.actions[JADE_RING.child] = (DefaultAction) (p, option, slot, itemId) -> option(p, JADE_RING);
            h.actions[OPAL_AMULET.child] = (DefaultAction) (p, option, slot, itemId) -> option(p, OPAL_AMULET);
            h.actions[OPAL_BRACELET.child] = (DefaultAction) (p, option, slot, itemId) -> option(p, OPAL_BRACELET);
            h.actions[OPAL_NECKLACE.child] = (DefaultAction) (p, option, slot, itemId) -> option(p, OPAL_NECKLACE);
            h.actions[OPAL_RING.child] = (DefaultAction) (p, option, slot, itemId) -> option(p, OPAL_RING);
            h.actions[UNHOLY_SYMBOL.child] = (DefaultAction) (p, option, slot, itemId) -> option(p, UNHOLY_SYMBOL);
            h.actions[SILVER_TIARA.child] = (DefaultAction) (p, option, slot, itemId) -> option(p, SILVER_TIARA);
            h.actions[SILVER_SICKLE.child] = (DefaultAction) (p, option, slot, itemId) -> option(p, SILVER_SICKLE);
            h.actions[HOLY_SYMBOL.child] = (DefaultAction) (p, option, slot, itemId) -> option(p, HOLY_SYMBOL);
            h.actions[DEMONIC_SIGIL.child] = (DefaultAction) (p, option, slot, itemId) -> option(p, DEMONIC_SIGIL);
            h.actions[SILVER_BOLT.child] = (DefaultAction) (p, option, slot, itemId) -> multioption(p, SILVER_BOLT);
        });

    }
}
