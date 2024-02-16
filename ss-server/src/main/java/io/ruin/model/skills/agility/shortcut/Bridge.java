package io.ruin.model.skills.agility.shortcut;

import io.ruin.model.entity.player.Player;
import io.ruin.model.entity.shared.LockType;
import io.ruin.model.map.Direction;
import io.ruin.model.map.object.GameObject;
import io.ruin.model.stat.StatType;

public class Bridge {

    public static void shortcut(Player p, GameObject bridge) {
        p.startEvent(e -> {
            p.lock(LockType.FULL_DELAY_DAMAGE);
            p.animate(2750);
            if(p.getAbsY() < bridge.y)
                p.getMovement().force(0, 4, 0, 0, 0, 60, Direction.NORTH);
            else
                p.getMovement().force(0, -4, 0, 0, 0, 60, Direction.SOUTH);
            e.delay(2);
            p.getStats().addXp(StatType.Agility, 1, true);
            p.unlock();
        });
    }
}
