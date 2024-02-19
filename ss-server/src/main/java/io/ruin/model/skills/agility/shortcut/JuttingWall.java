package io.ruin.model.skills.agility.shortcut;

import io.ruin.model.entity.player.Player;
import io.ruin.model.entity.shared.LockType;
import io.ruin.model.entity.shared.Renders;
import io.ruin.model.entity.shared.StepType;
import io.ruin.model.map.object.GameObject;
import io.ruin.model.stat.StatType;

public class JuttingWall {

    public static void shortcut(Player p, GameObject wall, int levelReq) {
        if (!p.getStats().check(StatType.Agility, levelReq, "attempt this"))
            return;
        p.startEvent(e -> {
            p.lock(LockType.FULL_DELAY_DAMAGE);
            p.animate(753);
            p.getAppearance().setCustomRenders(Renders.AGILITY_WALL);
            if(p.getAbsY() > wall.y)
                p.stepAbs(p.getAbsX(), p.getAbsY() - 2, StepType.FORCE_WALK);
            else
                p.stepAbs(p.getAbsX(), p.getAbsY() + 2, StepType.FORCE_WALK);
                //p.getMovement().force(0, 2, 0, 0, 0, 60, Direction.NORTH);
            e.delay(2);
            p.getStats().addXp(StatType.Agility, 10, true);
            p.getAppearance().removeCustomRenders();
            p.unlock();
        });
    }
}
