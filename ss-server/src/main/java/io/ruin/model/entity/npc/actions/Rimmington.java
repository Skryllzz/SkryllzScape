package io.ruin.model.entity.npc.actions;

import io.ruin.cache.ItemDef;
import io.ruin.cache.ItemID;
import io.ruin.model.entity.npc.NPC;
import io.ruin.model.entity.npc.NPCAction;
import io.ruin.model.entity.player.Player;
import io.ruin.model.inter.dialogue.ItemDialogue;
import io.ruin.model.inter.dialogue.NPCDialogue;
import io.ruin.model.inter.dialogue.OptionsDialogue;
import io.ruin.model.inter.dialogue.PlayerDialogue;
import io.ruin.model.inter.utils.Option;
import io.ruin.model.item.Item;
import io.ruin.model.item.actions.ItemNPCAction;

import static io.ruin.cache.ItemID.COINS_995;
import static io.ruin.cache.NpcID.PHIALS;

public class Rimmington {

    public enum NotedItems {
        REGULAR_BONES(ItemID.BONES),
        BURNT_BONES(ItemID.BURNT_BONES),
        BAT_BONES(ItemID.BAT_BONES),
        WOLF_BONES(ItemID.WOLF_BONES),
        BIG_BONES(ItemID.BIG_BONES),
        LONG_BONE(ItemID.LONG_BONE),
        CURVED_BONE(ItemID.CURVED_BONE),
        JOGRE_BONE(ItemID.JOGRE_BONE),
        BABYDRAGON_BONES(ItemID.BABYDRAGON_BONES),
        DRAGON_BONES(ItemID.DRAGON_BONES),
        ZOGRE_BONES(ItemID.ZOGRE_BONES),
        OURG_BONES(ItemID.OURG_BONES),
        WYVERN_BONES(ItemID.WYVERN_BONES),
        DAGANNOTH_BONES(ItemID.DAGANNOTH_BONES),
        LAVA_DRAGON_BONES(ItemID.LAVA_DRAGON_BONES),
        SUPERIOR_DRAGON_BONES(ItemID.SUPERIOR_DRAGON_BONES),
        WYRM_BONES(ItemID.WYRM_BONES),
        DRAKE_BONES(ItemID.DRAKE_BONES),
        HYDRA_BONES(ItemID.HYDRA_BONES),
        PLANK(ItemID.PLANK),
        OAKPLANK(ItemID.OAK_PLANK),
        TEAKPLANK(ItemID.TEAK_PLANK),
        MAHOGANYPLANK(ItemID.MAHOGANY_PLANK);

        public final int id, notedId;
        NotedItems(int id) {
            this.id = id;
            this.notedId = ItemDef.get(id).notedId;;
        }
    }

    private static final int COST = 5;

    static {
        /** Phials **/
        NPCAction.register(PHIALS, "talk-to", (player, npc) -> player.dialogue(new NPCDialogue(npc, "Hello, " + player.getName() + " I can exchange your noted items for items for 5gp each."),
                new NPCDialogue(npc, "If you would like to do this, just use any noted items on me.")
        ));

        NPCAction.register(PHIALS, "talk-to", (player, npc) -> player.dialogue(
                new NPCDialogue(npc, "Hello. Do you wish me to exchange banknotes for you? I charge only 5 coins for each banknote."),
                new OptionsDialogue(
                        new Option("Yes please.", () -> player.dialogue(
                                new PlayerDialogue("Yes please."),
                                new NPCDialogue(npc, "Hand me the banknotes you wish to exchange."))),
                        new Option("No thanks.", () -> player.dialogue(new PlayerDialogue("No thanks.")))
                )
        ));
        for(NotedItems i : NotedItems.values()) {
            ItemNPCAction.register(i.notedId, PHIALS, Rimmington::promptForAmount);
        }

    }

    private static void promptForAmount(Player player, Item item, NPC npc) {
        Item coins = player.getInventory().findItem(COINS_995);
        if(coins == null || coins.getAmount() < COST) {
            player.dialogue(new NPCDialogue(npc, "I charge " + COST + " coins for exchanging each banknote."));
            return;
        }

        player.dialogue(new OptionsDialogue(
                new Option("Exchange '" + item.getDef().name + "': " + COST + " coins", () -> exchange(player, item, 1)),
                new Option("Exchange All: " + (item.getAmount() * COST), () -> exchange(player, item, Integer.MAX_VALUE)),
                new Option("Exchange X", () -> player.integerInput("How many would you like to exchange?", amt -> exchange(player, item, amt)))
        ));
    }

    private static void exchange(Player player, Item item, int amt) {
        if(amt > item.getAmount())
            amt = item.getAmount();
        while(amt-- > 0) {
            Item coins = player.getInventory().findItem(COINS_995);
            if(coins == null || coins.getAmount() < COST)
                break;
            if(player.getInventory().isFull())
                break;
            item.remove(1);
            coins.remove(250);
            player.getInventory().add(item.getDef().fromNote().id, 1);
        }
        player.dialogue(new ItemDialogue().one(item.getDef().fromNote().id, "Phials converts your banknote."));
    }
}
