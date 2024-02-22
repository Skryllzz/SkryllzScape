package io.ruin.model.skills.agility.shortcut;

import io.ruin.model.entity.player.Player;
import io.ruin.model.entity.shared.LockType;
import io.ruin.model.entity.shared.Renders;
import io.ruin.model.entity.shared.StepType;
import io.ruin.model.map.object.GameObject;
import io.ruin.model.stat.StatType;

public class LogBalance {

    public static void shortcut(Player p, GameObject log, int levelReq) {
        if (!p.getStats().check(StatType.Agility, levelReq, "attempt this"))
            return;
        p.startEvent(e -> {
            p.lock(LockType.FULL_DELAY_DAMAGE);
            p.getAppearance().setCustomRenders(Renders.AGILITY_BALANCE);
            if(p.getAbsX() >= log.x)
                p.stepAbs(p.getAbsX() - 4, p.getAbsY(), StepType.FORCE_WALK);
            else
                p.stepAbs(p.getAbsX() + 4, p.getAbsY(), StepType.FORCE_WALK);
            e.delay(4);
            p.getStats().addXp(StatType.Agility, 8, true);
            p.getAppearance().removeCustomRenders();
            p.unlock();
        });
    }
}
