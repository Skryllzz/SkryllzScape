package io.ruin.model.map.object.actions.impl;

import io.ruin.model.World;
import io.ruin.model.entity.player.Player;
import io.ruin.model.entity.shared.StepType;
import io.ruin.model.item.containers.Equipment;
import io.ruin.model.item.containers.Inventory;
import io.ruin.model.map.object.GameObject;
import io.ruin.model.map.object.actions.ObjectAction;
import io.ruin.model.skills.woodcutting.Hatchet;
import io.ruin.model.stat.StatType;

import static io.ruin.cache.ItemID.*;

public class Vine {

    private static void chopDown(Player player, GameObject vines) {
        Hatchet axe = Hatchet.find(player);
        if(axe == null) {
            player.sendMessage("You need an axe to chop down these vines.");
            player.sendMessage("You do not have an axe which you have the Woodcutting level to use.");
            return;
        }
        if(player.getStats().get(StatType.Woodcutting).currentLevel < 10) {
            player.sendMessage("You need a Woodcutting level of at least 10 to chop down these vines.");
            return;
        }

        player.startEvent(event -> {
            Equipment equipment = player.getEquipment();
            Inventory inventory = player.getInventory();
            if (equipment.hasId(DRAGON_AXE) || inventory.hasId(DRAGON_AXE)) {
                player.animate(Hatchet.DRAGON.animationId);
            } else if (equipment.hasId(RUNE_AXE) || inventory.hasId(RUNE_AXE)) {
                player.animate(Hatchet.RUNE.animationId);
            } else if (equipment.hasId(ADAMANT_AXE) || inventory.hasId(ADAMANT_AXE)) {
                player.animate(Hatchet.ADAMANT.animationId);
            } else if (equipment.hasId(MITHRIL_AXE) || inventory.hasId(MITHRIL_AXE)) {
                player.animate(Hatchet.MITHRIL.animationId);
            } else if (equipment.hasId(STEEL_AXE) || inventory.hasId(STEEL_AXE)) {
                player.animate(Hatchet.STEEL.animationId);
            } else if (equipment.hasId(BLACK_AXE) || inventory.hasId(BLACK_AXE)) {
                player.animate(Hatchet.BLACK.animationId);
            } else if (equipment.hasId(IRON_AXE) || inventory.hasId(IRON_AXE)) {
                player.animate(Hatchet.IRON.animationId);
            } else if (equipment.hasId(BRONZE_AXE) || inventory.hasId(BRONZE_AXE)) {
                player.animate(Hatchet.BRONZE.animationId);
            }
            event.delay(3);
            if(vines.id != -1) {
                World.startEvent(e -> {
                    vines.remove();
                    event.delay(4);
                    vines.restore();
                });
            }
            int diffX = vines.x - player.getAbsX();
            int diffY = vines.y - player.getAbsY();
            if(Math.abs(diffX) > 1 || Math.abs(diffY) > 1 || (diffX + diffY) > 1)
                return;
            if(vines.direction == 1 || vines.direction == 3) {
                if(diffX == -1)
                    player.step(-2, 0, StepType.FORCE_WALK);
                else if(diffX == 1)
                    player.step(2, 0, StepType.FORCE_WALK);
            } else {
                if(diffY == -1)
                    player.step(0, -2, StepType.FORCE_WALK);
                else if(diffY == 1)
                    player.step(0, 2, StepType.FORCE_WALK);
            }

            player.animate(-1);
        });
    }

    static {
        ObjectAction.register(21731, "chop-down", Vine::chopDown);
        ObjectAction.register(21732, "chop-down", Vine::chopDown);
        ObjectAction.register(21733, "chop-down", Vine::chopDown);
        ObjectAction.register(21734, "chop-down", Vine::chopDown);
        ObjectAction.register(21735, "chop-down", Vine::chopDown);
    }
}
