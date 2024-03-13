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
                player.getStats().addXp(StatType.Agility, 1,true);
                player.animate(-1);
                player.unlock();
            });
        } else {
            player.dialogue(new MessageDialogue("You need at least 10 Agility to climb this rock."));
        }
    }

    public static void diagclimb(Player player, GameObject object, int levelReq) {
        if (!player.getStats().check(StatType.Agility, levelReq, "attempt this"))
            return;
            player.startEvent(e -> {
                e.waitForMovement(player);
                player.lock(LockType.FULL_DELAY_DAMAGE);
                player.animate(839);
                if (player.getAbsX() < object.x)
                    player.getMovement().force(2, 0, 0, 0, 0, 60, Direction.NORTH_EAST);
                else
                    player.getMovement().force(-2, 0, 0, 0, 0, 60, Direction.SOUTH_WEST);
                e.delay(2);
                player.getStats().addXp(StatType.Agility, 1,true);
                player.animate(-1);
                player.unlock();
            });
        }

    public static void ldiagClimb(Player player, GameObject object, int levelReq) {
        if (!player.getStats().check(StatType.Agility, levelReq, "attempt this"))
            return;
        player.startEvent(e -> {
            e.waitForMovement(player);
            player.lock(LockType.FULL_DELAY_DAMAGE);
            player.animate(839);
            if (player.getAbsX() < object.x)
                player.getMovement().force(2, 2, 0, 0, 0, 60, Direction.NORTH_EAST);
            else
                player.getMovement().force(-2, -2, 0, 0, 0, 60, Direction.SOUTH_WEST);
            e.delay(2);
            player.getStats().addXp(StatType.Agility, 1,true);
            player.animate(-1);
            player.unlock();
        });
    }

    public static void EWClimb(Player player, GameObject object, int levelReq) {
        if (!player.getStats().check(StatType.Agility, levelReq, "attempt this"))
            return;
        player.startEvent(e -> {
            e.waitForMovement(player);
            player.lock(LockType.FULL_DELAY_DAMAGE);
            player.animate(839);
            if (player.getAbsX() < object.x)
                player.getMovement().force(2, 0, 0, 0, 0, 60, Direction.EAST);
            else
                player.getMovement().force(-2, 0, 0, 0, 0, 60, Direction.WEST);
            e.delay(2);
            player.getStats().addXp(StatType.Agility, 1,true);
            player.animate(-1);
            player.unlock();
        });
    }

    public static void LEWClimb(Player player, GameObject object, int levelReq) {
        if (!player.getStats().check(StatType.Agility, levelReq, "attempt this"))
            return;
        player.startEvent(e -> {
            e.waitForMovement(player);
            player.lock(LockType.FULL_DELAY_DAMAGE);
            player.animate(839);
            if (player.getAbsX() < object.x)
                player.getMovement().force(3, 0, 0, 0, 0, 60, Direction.EAST);
            else
                player.getMovement().force(-3, 0, 0, 0, 0, 60, Direction.WEST);
            e.delay(2);
            player.getStats().addXp(StatType.Agility, 1, true);
            player.animate(-1);
            player.unlock();
        });
    }

        public static void LNSClimb(Player player, GameObject object, int levelReq) {
            if (!player.getStats().check(StatType.Agility, levelReq, "attempt this"))
                return;
            player.startEvent(e -> {
                e.waitForMovement(player);
                player.lock(LockType.FULL_DELAY_DAMAGE);
                player.animate(839);
                if (player.getAbsY() < object.y)
                    player.getMovement().force(0, 3, 0, 0, 0, 60, Direction.NORTH);
                else
                    player.getMovement().force(0, -3, 0, 0, 0, 60, Direction.SOUTH);
                e.delay(2);
                player.getStats().addXp(StatType.Agility, 1, true);
                player.animate(-1);
                player.unlock();
            });
        }

        public static void Boulder(Player player, GameObject object, int levelReq) {
            if (!player.getStats().check(StatType.Strength, levelReq, "attempt this"))
                return;
            player.startEvent(e -> {
                e.waitForMovement(player);
                player.lock(LockType.FULL_DELAY_DAMAGE);
                player.animate(4343);
                if (player.getAbsY() < object.y) {
                    player.getPacketSender().fadeOut();
                    e.delay(2);
                    player.sendMessage("You push with all your strength passed the bolder.");
                    player.getMovement().force(0, 4, 0, 0, 0, 60, Direction.NORTH);
                } else {
                    player.getPacketSender().fadeOut();
                    e.delay(2);
                    player.sendMessage("You push with all your strength passed the bolder.");
                    player.getMovement().force(0, -4, 0, 0, 0, 60, Direction.SOUTH);
                }
                e.delay(2);
                player.getPacketSender().fadeIn();
                player.getStats().addXp(StatType.Agility, 1, true);
                player.animate(-1);
                player.unlock();
            });
        }


}
