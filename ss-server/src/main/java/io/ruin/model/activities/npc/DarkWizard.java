package io.ruin.model.activities.npc;

import io.ruin.api.utils.Random;
import io.ruin.model.combat.AttackStyle;
import io.ruin.model.entity.npc.NPCCombat;
import io.ruin.model.map.Projectile;

public class DarkWizard extends NPCCombat {
    @Override
    public void init() {
    }

    @Override
    public void follow() {
        follow(8);
    }

    @Override
    public boolean attack() {
        if(!withinDistance(5))
            return false;
        projectileAttack(Projectile.WATER_STRIKE, info.attack_animation, AttackStyle.MAGIC, info.max_damage);
        if(Random.rollDie(4)) {
            target.graphics(27, 38, 41);
        }
        return true;
    }
}
