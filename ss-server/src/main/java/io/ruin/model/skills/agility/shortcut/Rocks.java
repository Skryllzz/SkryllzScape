package io.ruin.model.skills.agility.shortcut;

import io.ruin.model.entity.player.Player;
import io.ruin.model.entity.shared.LockType;
import io.ruin.model.inter.dialogue.MessageDialogue;
import io.ruin.model.map.Direction;
import io.ruin.model.map.object.GameObject;
import io.ruin.model.stat.StatType;

public class Rocks {

    public static void climb(Player player, GameObject object) {
        if (player.getStats().check(StatType.Agility, 10)) {
            player.startEvent(e -> {
                e.waitForMovement(player);
                player.lock(LockType.FULL_DELAY_DAMAGE);
                player.animate(839);
                if (player.getAbsY() < object.y)
                    player.getMovement().force(0, 2, 0, 0, 0, 60, Direction.NORTH);
                else
                    player.getMovement().force(0, -2, 0, 0, 0, 60, Direction.SOUTH);
                e.delay(2);
                player.animate(-1);
                player.unlock();
            });
        } else {
            player.dialogue(new MessageDialogue("You need at least 10 Agility to climb this rock."));
        }
    }

}
