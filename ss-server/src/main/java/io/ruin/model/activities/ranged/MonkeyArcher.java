package io.ruin.model.activities.ranged;

import io.ruin.api.utils.Random;
import io.ruin.model.combat.AttackStyle;
import io.ruin.model.entity.npc.NPCCombat;
import io.ruin.model.map.Projectile;

public class MonkeyArcher extends NPCCombat {

    @Override
    public void init() {
    }

    @Override
    public void follow() {
        follow(8);
    }

    @Override
    public boolean attack() {
        if(!withinDistance(1))
            return false;
        projectileAttack(Projectile.BOLT, info.attack_animation, AttackStyle.RANGED, info.max_damage);
        if(Random.rollDie(4)) {
            target.graphics(27, 38, 41);
        }
        return true;
    }

}
