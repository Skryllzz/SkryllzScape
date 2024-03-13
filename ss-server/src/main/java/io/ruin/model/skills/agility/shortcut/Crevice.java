package io.ruin.model.skills.agility.shortcut;

import io.ruin.model.entity.player.Player;
import io.ruin.model.entity.shared.LockType;
import io.ruin.model.map.Direction;
import io.ruin.model.map.Position;
import io.ruin.model.map.object.GameObject;
import io.ruin.model.stat.StatType;

public class Crevice {

    public static void shortcut(Player player, GameObject object, int levelReq, Position position, Position destination) {
        if (!player.getStats().check(StatType.Agility, levelReq, "attempt this"))
            return;
        player.startEvent(e -> {
            e.path(player, position);
            player.lock(LockType.FULL_DELAY_DAMAGE);
            player.animate(749);
            e.delay(1);
            if (player.getAbsX() > object.x)
                player.getMovement().force(destination.getX() - player.getAbsX(), 0, 0, 0, 0, 50, Direction.WEST);
            else
                player.getMovement().force(destination.getX() - player.getAbsX(), 0, 0, 0, 0, 50, Direction.EAST);
            e.delay(2);
            player.unlock();
        });

    }

    public static void LittleCrack(Player player, GameObject object, int levelReq) {
        if (!player.getStats().check(StatType.Agility, levelReq, "attempt this"))
            return;
        player.startEvent(e -> {
            e.waitForMovement(player);
            player.lock(LockType.FULL_DELAY_DAMAGE);
            player.animate(749);
            if (player.getAbsX() == 2899) {
                player.getPacketSender().fadeOut();
                e.delay(2);
                player.getMovement().teleport(2904,3720,0);
            } else if(player.getAbsX() == 2904) {
                player.getPacketSender().fadeOut();
                e.delay(2);
                player.getMovement().teleport(2899,3713,0);
            }
            e.delay(2);
            player.getPacketSender().fadeIn();
            e.delay(2);
            player.unlock();
        });
    }
}
