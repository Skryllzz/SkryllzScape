package io.ruin.model.entity.npc.actions.misc;

import io.ruin.model.combat.Hit;
import io.ruin.model.entity.npc.NPCCombat;
import io.ruin.model.entity.shared.listeners.HitListener;

public class HighwayMan extends NPCCombat {

    @Override
    public void init() {
        npc.hitListener = new HitListener().preDefend(this::preDefend);
    }

    @Override
    public void follow() {
    if(withinDistance(4)) {
        follow(1);
        }
    }

    private void preDefend(Hit hit) {
        npc.forceText("Stand and Deliver!");
        npc.hitListener = null; // Remove the HitListener after it has been triggered
    }

    @Override
    public boolean attack() {
        if (!withinDistance(1))
            return false;
        basicAttack();
        return true;
    }

    public void startDeath(Hit killHit) {
        super.startDeath(killHit);
        npc.hitListener = new HitListener().preDefend(this::preDefend);
    }

}
