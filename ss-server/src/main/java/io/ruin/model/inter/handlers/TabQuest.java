package io.ruin.model.inter.handlers;

import io.ruin.Server;
import io.ruin.api.utils.TimeUtils;
import io.ruin.cache.Color;
import io.ruin.model.World;
import io.ruin.model.activities.wilderness.Wilderness;
import io.ruin.model.entity.player.Player;
import io.ruin.model.entity.shared.listeners.LoginListener;
import io.ruin.model.inter.Interface;
import io.ruin.model.inter.InterfaceAction;
import io.ruin.model.inter.InterfaceHandler;
import io.ruin.model.inter.InterfaceType;
import io.ruin.model.inter.actions.SimpleAction;
import io.ruin.model.inter.journal.Journal;
import lombok.Getter;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

import static io.ruin.Server.currentTick;

/**
 *
 * Handles the notification board in the quest tab.
 *
 * @author ReverendDread on 5/17/2020
 * https://www.rune-server.ee/members/reverenddread/
 * @project Kronos
 */
public class TabQuest {

    @Getter
    private enum NoticeboardComponent {

        //COMPONENT_8(8, player -> "Players Online: " + Color.GREEN.wrap(String.valueOf(World.players.count()))),
        COMPONENT_8(8, player -> "Players Online: " + Color.GREEN.wrap(String.valueOf(getOnlineCount())), (SimpleAction) TabQuest::sendOnlinePlayers),
        COMPONENT_9(9, player -> "Online Staff: " + Color.GREEN.wrap(String.valueOf(getStaffOnlineCount())), (SimpleAction) TabQuest::sendStaffOnline),
        COMPONENT_10(10, player -> "Players in Wild: " + Color.GREEN.wrap(String.valueOf(Wilderness.players.size()))),
        //COMPONENT_11(11, player -> "Players in Tournament: " + Color.GREEN.wrap(String.valueOf(PVPInstance.players.size()))),
        COMPONENT_11(11, player -> "Server Uptime: " + Color.GREEN.wrap(TimeUtils.fromMs(currentTick() * Server.tickMs(), false))),
        COMPONENT_12(12, player -> "Time Played: " + Color.GREEN.wrap(TimeUtils.fromMs(player.playTime * Server.tickMs(), false))),
        //COMPONENT_43(43, player -> "XP Bonus: " + Color.GREEN.wrap(String.valueOf(World.xpMultiplier))),
        //COMPONENT(43, player -> "Online Players List", (SimpleAction) TabQuest::sendOnlinePlayers),
        COMPONENT_43(43, player -> "-------------------------------------------------------------", null),
        COMPONENT_44(44, player -> "", null),
        COMPONENT_45(45, player -> "", null),
        COMPONENT_46(46, player -> "", null),
        COMPONENT_47(47, player -> "", null),
        COMPONENT_13(13, player -> "", null),
        COMPONENT_14(14, player -> "", null),
        COMPONENT_15(15, player -> "", null),
        COMPONENT_16(16, player -> "", null),
        COMPONENT_17(17, player -> "", null),
        COMPONENT_18(18, player -> "", null),
        COMPONENT_49(49, player -> "", null),
        //COMPONENT_44(44, player -> "Double Drops: " + Color.GREEN.wrap(getDoubleDrops())),
        //COMPONENT_45(45, player -> "Double PK Points: " + Color.GREEN.wrap(getDoublePkp())),
        //COMPONENT_46(46, player -> "Double Slayer Points: " + Color.GREEN.wrap(getDoubleSlayerPoints())),
        //COMPONENT_47(47, player -> "Double Pest Control: " + Color.GREEN.wrap(getDoublePcPoints())),
        //COMPONENT_14(14, player -> {
           // boolean hasTwoFactor = player.tfa;
           // String text = "Two-factor authentication";
           // return hasTwoFactor ? Color.GREEN.wrap(text) : Color.RED.wrap(text);
        //}, (SimpleAction) player -> player.openUrl("https://community.kronos.rip/index.php?account/security")), //need to hookup tfa to the website somehow
        //COMPONENT_16(16, player -> "Total Spent: " + Color.GREEN.wrap( "$" + player.storeAmountSpent)),
        //COMPONENT_17(17, player -> "Base XP: " + Color.GREEN.wrap(String.valueOf(getXpBonus(player)))),
        //COMPONENT_18(18, player -> "Double Drop Chance: " + Color.GREEN.wrap(DoubleDrops.getChance(player) + "%")),
        //COMPONENT_49(49, player -> "PVM Points: " + Color.GREEN.wrap(Integer.toString(player.PvmPoints))),

        COMPONENT_50(50, player -> "Achievements", (SimpleAction) player -> sendAchievements(player)),
        COMPONENT_51(51, player -> "Drop Tables", (SimpleAction) player -> sendBestiary(player)),
        COMPONENT_52(52, player -> "Settings", (SimpleAction) player -> sendToggles(player)),

        COMPONENT_19(19, player -> "Website", (SimpleAction) player -> player.openUrl("https://discord.com/invite/VgJjr7nEmz/")),
        COMPONENT_20(20, player -> "Community", (SimpleAction) player -> player.openUrl("https://discord.com/invite/VgJjr7nEmz")),
        COMPONENT_21(21, player -> "Discord", (SimpleAction) player -> player.openUrl("https://discord.com/invite/VgJjr7nEmz")),
        COMPONENT_22(22, player -> "Store", (SimpleAction) player -> player.openUrl("https://skryllzscape.everythingrs.com/services/store"));

        final int componentId;
        final TextField text;
        final InterfaceAction action;

        static String getTime() {
            DateFormat dateFormat = new SimpleDateFormat("HH:mm");
            Date date = new Date();
            return dateFormat.format(date);
        }

        NoticeboardComponent(int componentId) {
            this(componentId, player -> "", null);
        }

        NoticeboardComponent(int componentId, TextField text) {
            this(componentId, text, null);
        }

        NoticeboardComponent(int componentId, TextField text, InterfaceAction action) {
            this.componentId = componentId;
            this.text = text;
            this.action = action;
        }
    }

    @Getter
    private enum AchievementComponent {
        COMPONENT_8(8, player -> "Players Online: " + Color.GREEN.wrap(String.valueOf(getOnlineCount())), (SimpleAction) TabQuest::sendOnlinePlayers);
        @Getter
        final int componentId;
        final TextField text;
        final InterfaceAction action;

        static String getTime() {
            DateFormat dateFormat = new SimpleDateFormat("HH:mm");
            Date date = new Date();
            return dateFormat.format(date);
        }

        AchievementComponent(int componentId) {
            this(componentId, player -> "", null);
        }

        AchievementComponent(int componentId, TextField text) {
            this(componentId, text, null);
        }

        AchievementComponent(int componentId, TextField text, InterfaceAction action) {
            this.componentId = componentId;
            this.text = text;
            this.action = action;
        }

        public int getComopnentId() {
            return componentId;
        }
    }

    /**
     * Sends all of the basic text for the noticeboard components
     * @param player    The player.
     */
    public static void send(Player player) {
        Arrays.stream(NoticeboardComponent.values()).forEach(component -> player.getPacketSender().sendString(720, component.getComponentId(), component.getText().send(player)));
    }
    /**
     * Sends an interface filtered with staff that are currently online.
     * @param player    The player.
     */
    private static void sendStaffOnline(Player player) {
        int interId = 116;
        player.openInterface(InterfaceType.MAIN, interId);
        player.getPacketSender().sendString(interId, 4, "Online Staff");
        List<Player> staffList = World.getPlayerStream().filter(Player::isStaff).collect(Collectors.toList());
        int component = 6;
        for (Player p1 : staffList) {
            player.getPacketSender().sendString(interId, component++, p1.getPrimaryGroup().tag() + " " + p1.getName());
        }
    }

    private static void sendOnlinePlayers(Player player) {
        int interId = 116;
        player.openInterface(InterfaceType.MAIN, interId);
        player.getPacketSender().sendString(interId, 4, "Players Online");
        List<Player> onlineList = World.getPlayerStream().filter(Player::isOnline).collect(Collectors.toList());
        int component = 6;
        for (Player p1 : onlineList) {
            player.getPacketSender().sendString(interId, component++, p1.getPrimaryGroup().tag() + " " + p1.getName());
        }
    }
    public static void sendBestiary(Player player) {
        Journal.BESTIARY.send(player);
    }

    private static void sendAchievements(Player player) {
        Journal.ACHIEVEMENTS.send(player);
    }
    private static void sendToggles(Player player) {
        Journal.TOGGLES.send(player);
    }

    private static String getDoubleDrops() {
        if (World.doubleDrops) {
            return Color.GREEN.wrap("Enabled!");
        } else {
            return Color.RED.wrap("Disabled");
        }
    }
    private static String getDoublePkp() {
        if (World.doublePkp) {
            return Color.GREEN.wrap("Enabled!");
        } else {
            return Color.RED.wrap("Disabled");
        }
    }
    private static String getDoubleSlayerPoints() {
        if (World.doubleSlayer) {
            return Color.GREEN.wrap("Enabled!");
        } else {
            return Color.RED.wrap("Disabled");
        }
    }
    private static String getDoublePcPoints() {
        if (World.doublePest) {
            return Color.GREEN.wrap("Enabled!");
        } else {
            return Color.RED.wrap("Disabled");
        }
    }

    private static int getStaffOnlineCount() {
        List<Player> staffList = World.getPlayerStream().filter(Player::isStaff).collect(Collectors.toList());
        return staffList.size();
    }

    private static int getOnlineCount() {
        List<Player> onlineList = World.getPlayerStream().filter(Player::isOnline).collect(Collectors.toList());
        return onlineList.size();
    }

    private static double getXpBonus(Player player) {
        double xp = 1;

        if (World.xpMultiplier > 0)
            xp += World.xpMultiplier - 1;

        if(player.expBonus.isDelayed()) //50% xp scrolls
            xp += 0.5;

        if (player.wildernessLevel > 1) //wilderness bonus
            xp += .25;

        if (World.weekendExpBoost)
            xp += .25;

        return xp;
    }

   /** static void swapTabInnerInterface(Player, int newId) {
        player.getPacketSender().sendInterface(newId, Interface.QUEST_TAB, 2, 1);
    }**/

   static void swapTabInnerInterface(Player player, int newId) {
       player.getPacketSender().sendInterface(newId, Interface.QUEST_TAB, 2, 1);
   }

    static {
        InterfaceHandler.register(Interface.QUEST_TAB, (h) -> {
            h.actions[3] = (SimpleAction) p -> swapTabInnerInterface(p, Interface.NOTICEBOARD);
            h.actions[4] = (SimpleAction) TabQuest::sendAchievements;
            h.actions[5] = (SimpleAction) p -> swapTabInnerInterface(p, Interface.MINIGAMES);
            h.actions[6] = (SimpleAction) p -> swapTabInnerInterface(p, Interface.FAVOUR);
        });
        AtomicReference<Short> actionPointer = new AtomicReference<>((short) 1);
        /**InterfaceHandler.register(Interface.NOTICEBOARD, (h) -> {
            for (NoticeboardComponent component : NoticeboardComponent.values()) {
                h.actions[component.getComponentId()] = component.getAction();
            }
        });**/
        InterfaceHandler.register(Interface.NOTICEBOARD, (h) -> Arrays.stream(NoticeboardComponent.values()).forEach(component -> h.actions[component.getComponentId()] = component.getAction()));
        //InterfaceHandler.register(Interface.ACHIEVEMENT, (h) -> Arrays.stream(AchievementComponent.values()).forEach(component -> h.actions[component.getComopnentId()] = component.getAction()));

        LoginListener.register(player -> {
            send(player);
            player.addEvent(event -> {
                while(true) {
                    send(player);
                    event.delay(1);
                    if (player.isVisibleInterface(Interface.QUEST_TAB) && currentTick() == 0) send(player);
                }
            });
        });
    }

    private interface TextField {
        String send(Player player);
    }

}
