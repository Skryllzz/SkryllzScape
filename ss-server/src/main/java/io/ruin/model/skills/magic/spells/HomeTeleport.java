package io.ruin.model.skills.magic.spells;

import io.ruin.model.entity.player.Player;
import io.ruin.model.map.Position;
import io.ruin.model.map.object.actions.impl.PortalNetwork;
import io.ruin.model.skills.magic.Spell;

import java.util.LinkedList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;

public class HomeTeleport extends Spell {

    public static final HomeTeleport PORTAL = new HomeTeleport(PortalNetwork::open);
    private static final List<HomeTeleportOverride> OVERRIDES = new LinkedList<>();

    private HomeTeleport(Consumer<Player> consumer) {
        clickAction = (p, i) -> {
            if(p.wildernessLevel > 0) {
                if(p.getCombat().isDefending(16)) {
                    p.sendMessage("You can't cast this spell while in combat in the wilderness.");
                    return;
                }
            } else if(p.pvpAttackZone) {
                if(p.getCombat().isDefending(16)) {
                    p.sendMessage("You can't cast this spell while in combat in a pvp zone.");
                    return;
                }
            }
            if (p.getCombat().isDefending(10)) {
                p.sendMessage("You can't cast this spell while in combat.");
                return;
            }
            consumer.accept(p);
        };
    }

    private static class HomeTeleportOverride {
        Predicate<Player> condition;
        Position destination;

        public HomeTeleportOverride(Predicate<Player> condition, Position destination) {
            this.condition = condition;
            this.destination = destination;
        }
    }

    public static void registerHomeTeleportOverride(Predicate<Player> condition, Position destination) {
        OVERRIDES.add(new HomeTeleportOverride(condition, destination));
    }

    public static Position getHomeTeleportOverride(Player player) {
        for (HomeTeleportOverride teleportOverride : OVERRIDES) {
            if (teleportOverride.condition.test(player))
                return teleportOverride.destination;
        }
        return null;
    }
}