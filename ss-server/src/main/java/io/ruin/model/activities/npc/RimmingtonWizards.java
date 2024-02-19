package io.ruin.model.activities.npc;

import io.ruin.api.utils.Random;
import io.ruin.model.combat.AttackStyle;
import io.ruin.model.entity.npc.NPCCombat;
import io.ruin.model.map.Projectile;

import static io.ruin.cache.NpcID.*;

public class RimmingtonWizards extends NPCCombat {
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
            if (npc.getId() == WATER_WIZARD) {
                projectileAttack(Projectile.WATER_STRIKE, info.attack_animation, AttackStyle.MAGIC, info.max_damage);
                if(Random.rollDie(4)) {
                    target.graphics(27, 38, 41);
                }
                return true;
            }
            if (npc.getId() == FIRE_WIZARD) {
                projectileAttack(Projectile.FIRE_STRIKE, info.attack_animation, AttackStyle.MAGIC, info.max_damage);
                if(Random.rollDie(4)) {
                    target.graphics(27, 38, 41);
                }
                return true;
            }
            if (npc.getId() == AIR_WIZARD) {
                projectileAttack(Projectile.AIR_STRIKE, info.attack_animation, AttackStyle.MAGIC, info.max_damage);
                if(Random.rollDie(4)) {
                    target.graphics(27, 38, 41);
                }
                return true;
            }
            if (npc.getId() == EARTH_WIZARD) {
                projectileAttack(Projectile.EARTH_STRIKE, info.attack_animation, AttackStyle.MAGIC, info.max_damage);
                if(Random.rollDie(4)) {
                    target.graphics(27, 38, 41);
                }
                return true;
            }
            return true;
        }
    }
