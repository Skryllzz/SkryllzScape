package io.ruin.model.skills.magic.spells;

import io.ruin.model.World;
import io.ruin.model.entity.player.Player;
import io.ruin.model.map.Position;
import io.ruin.model.skills.magic.Spell;
import io.ruin.model.skills.magic.spells.modern.ModernTeleport;

import java.util.LinkedList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;

public class HomeTeleport extends Spell {

    //public static final HomeTeleport MODERN = new HomeTeleport(p -> ModernTeleport.teleport(p, World.HOME));
    public static final HomeTeleport MODERN = new HomeTeleport(p -> p.getMovement().teleport(World.HOME));

    public static final HomeTeleport ANCIENT = new HomeTeleport(p -> ModernTeleport.teleport(p, World.HOME));

    public static final HomeTeleport LUNAR = new HomeTeleport(p -> ModernTeleport.teleport(p, World.HOME));

    public static final HomeTeleport ARCEUUS = new HomeTeleport(p -> ModernTeleport.teleport(p, World.HOME));

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
            Position override = getHomeTeleportOverride(p);
            if (override != null) {
                ModernTeleport.teleport(p, override.getX(), override.getY(), override.getZ());
            } else {
                p.addEvent(event -> {
                        event.delay(2);
                        p.lock();
                        p.animate(4847);
                        p.graphics(800);
                        p.privateSound(193);
                        event.delay(5);
                        p.animate(4850);
                        p.privateSound(196);
                        event.delay(3);
                        p.animate(4853);
                        p.graphics(803);
                        p.privateSound(194);
                        event.delay(5);
                        p.animate(4857);
                        p.graphics(804);
                        p.privateSound(195);
                        event.delay(2);
                        consumer.accept(p);
                        p.resetAnimation();
                        p.unlock();
                });
            }
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