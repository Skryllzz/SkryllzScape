package io.ruin.model.item.actions.impl;

import io.ruin.data.impl.npcs.npc_combat;
import io.ruin.model.entity.npc.NPC;
import io.ruin.model.entity.player.Player;
import io.ruin.model.inter.dialogue.MessageDialogue;
import io.ruin.model.item.Item;
import io.ruin.model.item.actions.ItemAction;
import io.ruin.model.item.containers.Equipment;
import io.ruin.model.map.Bounds;

public enum MonkeyGreeGree {

    KARAMJAN(3186, 4031, 1469),

    SMALL_NINJA(3179, 4024, 1462),
    MEDIUM_NINJA(3180, 4025, 1463),

    SMALL_ZOMBIE(3183, 4029, 1467),
    LARGE_ZOMBIE(3185, 4030, 1468),

    GORILLA(3181, 4026, 1464),
    BEARDED_GORILLA(3182, 4027, 1465),
    ANCIENT_GORILLA(4034, 4028, 1466),

    KRUK(19523, 19525, 5257);

    public final int remainsId;
    public final int greegreeId;
    public final int npcId;

    protected NPC npc;

    protected npc_combat.Info info;

    public final int monkey = 5274;

    MonkeyGreeGree(int remainsId, int greegreeId, int npcId) {
        this.remainsId = remainsId;
        this.greegreeId = greegreeId;
        this.npcId = npcId;
    }

    private void hold(Player player, Item greegree) {
        if(player.getAppearance().getNpcId() != -1 && !player.getPosition().inBounds(ZOO_BOUNDS)) {
            player.sendMessage("You attempt to use the Monkey Greegree but nothing happens.");
            return;
        }
        if(player.getAppearance().getNpcId() != -1 && !player.getPosition().inBounds(APE_ATOLL)) {
            player.sendMessage("You attempt to use the Monkey Greegree but nothing happens.");
            return;
        }
        if(player.getAppearance().getNpcId() != -1 && !player.getPosition().inBounds(APE_ATOLL_DUNG)) {
            player.sendMessage("You attempt to use the Monkey Greegree but nothing happens.");
            return;
        }
        player.getEquipment().equip(greegree);
        if(player.getEquipment().getId(Equipment.SLOT_WEAPON) == greegreeId) {
            player.privateSound(1677); //rs probably has unique sounds per greegree..
            player.graphics(160, 124, 0);
            player.getAppearance().setNpcId(npcId);
            player.getCombat().canAttack(npc, false);
            player.addEvent(e -> {
                while(true) {
                    Item item = player.getEquipment().get(Equipment.SLOT_WEAPON);
                    if(item == null || item.getId() != greegreeId)
                        break;
                    if(!player.getPosition().inBounds(ZOO_BOUNDS) && (!player.getPosition().inBounds(APE_ATOLL) && (!player.getPosition().inBounds(APE_ATOLL_DUNG)))) {
                        item.remove();
                        player.getInventory().addOrDrop(item.getId(), 1);
                        player.dialogue(new MessageDialogue("The Monkey Greegree wrenches itself from your hand as its power begins to fade...").lineHeight(24));
                        break;
                    }
                    e.delay(1);
                }
                player.privateSound(1681); //rs probably has unique sounds per greegree..
                player.graphics(160, 124, 0);
                player.getAppearance().setNpcId(-1);
            });
        }
    }

    static {
        for(MonkeyGreeGree monkeyGreeGree : values())
            ItemAction.registerInventory(monkeyGreeGree.greegreeId, "hold", monkeyGreeGree::hold);
    }

    private Bounds ZOO_BOUNDS = new Bounds(2592, 3264, 2639, 3287, 0);
    private Bounds APE_ATOLL = new Bounds(2600,2650, 2900, 2900, 0);

    private Bounds APE_ATOLL_DUNG = new Bounds(2692,9089, 2809, 9148, 0);

}
