package io.ruin.model.skills.cooking;

import io.ruin.cache.ItemDef;
import io.ruin.model.entity.player.Player;
import io.ruin.model.inter.dialogue.skill.SkillDialogue;
import io.ruin.model.inter.dialogue.skill.SkillItem;
import io.ruin.model.item.Item;
import io.ruin.model.item.actions.ItemItemAction;
import io.ruin.model.stat.StatType;

import static io.ruin.cache.ItemID.*;

public enum Flour {

    //Normal log
    BREAD(BREAD_DOUGH, 1, 1.0, new Item(BREAD_DOUGH, 1), "Bread Dough", ""),
    PASTRY(PASTRY_DOUGH, 1, 1.0, new Item(PASTRY_DOUGH, 1), "Pastry Dough", ""),
    PIZZA(PIZZA_BASE, 1, 1.0, new Item(PIZZA_BASE, 1), "Pizza Base", ""),
    PITTA(PITTA_DOUGH, 58, 1.0, new Item(PITTA_DOUGH, 1), "Pitta Dough", ""),;
    public final int dougeID, levelReq;
    public final double exp;
    public final Item item;
    public final String name, descriptionName;

    Flour(int dougeID, int levelReq, double exp, Item item, String name, String descriptionName) {
        this.dougeID = dougeID;
        this.levelReq = levelReq;
        this.exp = exp;
        this.item = item;
        this.name = name;
        this.descriptionName = descriptionName;
    }

    private static void makeBread(Player player, Flour id, int amount) {
        if(!player.getStats().check(StatType.Cooking, id.levelReq, id.descriptionName))
            return;
        player.startEvent(event -> {
            int made = 0;
            while (made++ < amount) {
                player.lock();
                Item dougetomake = player.getInventory().findItem(POT_OF_FLOUR);
                if(dougetomake == null) {
                    player.unlock();
                    break;
                }
                dougetomake.remove( 1);
                player.getInventory().remove(BUCKET_OF_WATER, 1);
                player.getInventory().add(id.item);
                player.sendFilteredMessage("You mix the flour and water and make a " + id.name + ".");
                player.getStats().addXp(StatType.Cooking, id.exp, true);
                event.delay(2);
                player.unlock();
            }
        });
        player.unlock();
    }

    static {
        /** Flour with Bucket of Water **/
        ItemItemAction.register(POT_OF_FLOUR, BUCKET_OF_WATER, (player, item, object) -> {
            SkillDialogue.make(player,
                    new SkillItem(BREAD.dougeID).name(ItemDef.get(BREAD.dougeID).name).addAction((p, amount, event) -> makeBread(p, BREAD, amount)),
                    new SkillItem(PASTRY.dougeID).name(ItemDef.get(PASTRY.dougeID).name).addAction((p, amount, event) -> makeBread(p, PASTRY, amount)),
                    new SkillItem(PIZZA.dougeID).name(ItemDef.get(PIZZA.dougeID).name).addAction((p, amount, event) -> makeBread(p, PIZZA, amount)),
                    new SkillItem(PITTA.dougeID).name(ItemDef.get(PITTA.dougeID).name).addAction((p, amount, event) -> makeBread(p, PITTA, amount)));
        });
    }
}
