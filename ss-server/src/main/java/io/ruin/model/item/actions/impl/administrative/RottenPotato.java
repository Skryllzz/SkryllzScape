package io.ruin.model.item.actions.impl.administrative;

import io.ruin.Server;
import io.ruin.cache.Color;
import io.ruin.cache.ItemDef;
import io.ruin.cache.Varp;
import io.ruin.cache.Varpbit;
import io.ruin.data.impl.teleports;
import io.ruin.model.World;
import io.ruin.model.combat.Hit;
import io.ruin.model.entity.Entity;
import io.ruin.model.entity.npc.NPC;
import io.ruin.model.entity.player.Player;
import io.ruin.model.inter.dialogue.OptionsDialogue;
import io.ruin.model.inter.handlers.TabQuest;
import io.ruin.model.inter.utils.Config;
import io.ruin.model.inter.utils.Option;
import io.ruin.model.item.Item;
import io.ruin.model.item.actions.ItemAction;
import io.ruin.model.map.object.GameObject;
import io.ruin.model.stat.Stat;
import io.ruin.model.stat.StatType;
import io.ruin.utility.DebugMessage;

import java.util.Arrays;

import static io.ruin.cache.ItemID.ROTTEN_POTATO;
import static io.ruin.model.skills.slayer.Slayer.potatoreset;

public class RottenPotato {

    public static void eat(Player player, Item item) {
        if (!player.getInventory().hasItem(item.getId(),1)) return;
        player.sendMessage("Why would you make me eat something rotten...");
        Stat prayer = player.getStats().get(StatType.Prayer);
        Stat hitpoints = player.getStats().get(StatType.Hitpoints);
        prayer.restore();
        hitpoints.restore();
        player.getMovement().restoreEnergy(100);
        player.getCombat().restoreSpecial(100);
        player.cureVenom(0);
    }

    static void slice(Player player, Item item) {
        player.dialogue(new OptionsDialogue(
                new Option("Bestiary", () -> TabQuest.sendBestiary(player)),
                new Option("Set Slayer Points", () -> player.integerInput("Enter an amount of points", points -> {
                    Config.SLAYER_POINTS.set(player, points);
                    player.sendMessage("You now have a total of " + Config.SLAYER_POINTS.get(player) + " Slayer Points.");
                }))
                /**new Option("Set Loyalty Points", () -> player.integerInput("Enter an amount of points", points -> {
                    player.loyaltyPoints = Long.valueOf(points);
                    player.sendMessage("You now have a total of " + player.getLoyaltyPoints() + " Loyalty Points.");
                })),
                new Option("Set Voting Points", () -> player.integerInput("Enter an amount of points", points -> {
                    player.votePoints = points;
                    player.sendMessage("You now have a total of " + player.getVotePoints() + " voting Points.");
                })),
                new Option("Set S&R Points", () -> player.integerInput("Enter an amount of points", points -> {
                    player.searchAndRescuePoints = points;
                    player.sendMessage("You now have a total of " + player.getSearchAndRescuePoints() + " S&R Points.");
                }))**/
        ));
    }

    static void peel(Player player, Item item) {
        player.dialogue(new OptionsDialogue(
                new Option("Open Bank", () -> player.getBank().open()),
                new Option("Teleport Menu", () -> teleports.open(player)),
                new Option("Reset Slayer Task", () -> potatoreset(player))
        ));
    }

    static void mash(Player player, Item item) {
        if (!player.getInventory().hasItem(item.getId(),1)) return;
        player.dialogue(new OptionsDialogue(
                new Option("Max Stats", () -> {
                    int xp = Stat.xpForLevel(99);
                    for (int i = 0; i < StatType.values().length; i ++) {
                        Stat stat = player.getStats().get(i);
                        stat.currentLevel = stat.fixedLevel = 99;
                        stat.experience = xp;
                        stat.updated = true;
                    }
                    player.getCombat().updateLevel();
                    player.getAppearance().update();
                }),
                new Option("Wipe Inventory", () -> {
                    player.getInventory().clear();
                    player.getInventory().add(ROTTEN_POTATO);
                }),
                new Option("Spawn Item", () ->
                        player.itemSearch("", true, (integer) -> {
                            if (integer < 0) return;
                            player.integerInput("Enter the amount to spawn", amount -> {
                                System.out.printf("%s is spawning item %s (%d) x %d%n", player.getName(), ItemDef.get(integer).name, integer, amount);
                                player.getInventory().add(integer, amount);
                            });
                        })
                ),
                new Option("Teleport to a Player", () ->
                        player.nameInput("Who would you like to teleport to:", name -> {
                            name = name.replaceAll("[^a-zA-Z0-9\\s]", "");
                            name = name.substring(0, Math.min(name.length(), 12));
                            if(name.isEmpty()) {
                                player.retryStringInput("Invalid name, try again:");
                                return;
                            }
                            Player target = World.getPlayer(name);
                            if (target == null) return;
                            player.getMovement().teleport(target.getPosition());
                            player.sendMessage("You've teleport to " + target.getName() + "'s position.");
                            target.sendMessage(player.getName() + " has just teleported to your location.");
                        })
                ),
                new Option("Varp-Check", () -> player.integerInput("Enter the ID of the VarpBit", varpbitId -> printVarpData(player, varpbitId)))
        ));
    }

    private static void printVarpData(Player player, int varpbitId) {
        Varpbit varpbit = Varpbit.get(varpbitId);
        if (varpbit == null) return;
        DebugMessage debugVarpbit = new DebugMessage()
                .add("id", varpbit.id)
                .add("varpId", varpbit.varpId)
                .add("leastSigBit", varpbit.leastSigBit)
                .add("mostSigBit", varpbit.mostSigBit);
        player.sendMessage("[PotatoDebugVarpbit] " + debugVarpbit.toString());
        Varp varp = Varp.get(varpbit.varpId);
        if (varp == null || varp.bits == null) return;
        Server.log("Varp: " + varpbit.varpId);
        for (Varpbit bit : varp.bits)
            Server.log("\tbit: " + bit.id + "  shift: " + bit.leastSigBit);
    }

    public static void debugObject(Player player, GameObject object) {
        player.dialogue(new OptionsDialogue(
                new Option("Object Details", () -> {
                    if(object == null) return;
                    DebugMessage debug = new DebugMessage()
                            .add("id", object.id)
                            .add("name", object.getDef().name)
                            .add("position", object.getPosition().toString())
                            .add("type", object.type)
                            .add("direction", object.direction)
                            .add("options", Arrays.toString(object.getDef().options))
                            .add("Idle Anim:", object.getDef().unknownOpcode24)
                            .add("Unknown:", object.getDef().unknownOpcode75)
                            .add("varpbitId", object.getDef().varpBitId)
                            .add("varpId", object.getDef().varpId);
                    player.sendMessage("[PotatoDebug] " + debug.toString());
                    printVarpData(player, object.getDef().varpBitId);
                }),
                new Option("Remove Object", (Runnable) object::remove),
                new Option("Change Object", () -> player.integerInput("Enter the ID of the Object", object::setId)),
                new Option("Restore Object", () -> object.setId(object.originalId)),
                new Option("Set Animation", () -> player.integerInput("Animation ID", object::animate))
        ));
    }

    public static void onMob(Player player, Entity target) {
        if (target == null) return;
        if (target.isPlayer()) onPlayer(player, target.asPlayer());
        if (target.isNpc()) onNPC(player, target.asNPC());
    }

    static void onPlayer(Player player, Player target) {
        player.sendMessage(String.format("Name: %s | IP: %s | MAC: %s | PrimaryGroup: %s",
                target.getName(),
                target.getIp(),
                target.getMACAddress(),
                target.getPrimaryGroup() == null ? "null" : target.getPrimaryGroup().name()));
    }

    static void onNPC(Player player, NPC target) {
        if (target.getCombat() != null) {
            Hit potatoHit = new Hit().fixedDamage(target.getHp());
            target.getCombat().startDeath(potatoHit);
            player.sendMessage(Color.RED.wrap("And another one bytes the dust..."));
        } else player.sendMessage(Color.RED.wrap("You can't just go around killing any NPC you want..."));
        // TODO; A way to edit the NPC's variables maybe via some interface and maybe a way to save it to the db repo
    }

    static {
        ItemAction.registerInventory(ROTTEN_POTATO,"eat", RottenPotato::eat);
        ItemAction.registerInventory(ROTTEN_POTATO, 2, RottenPotato::slice);
        ItemAction.registerInventory(ROTTEN_POTATO, "peel", RottenPotato::peel);
        ItemAction.registerInventory(ROTTEN_POTATO, 4, RottenPotato::mash);
    }
}
