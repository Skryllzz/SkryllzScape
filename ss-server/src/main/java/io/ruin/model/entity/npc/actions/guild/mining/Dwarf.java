package io.ruin.model.entity.npc.actions.guild.mining;

import io.ruin.cache.ItemID;
import io.ruin.model.entity.npc.NPC;
import io.ruin.model.entity.npc.NPCAction;
import io.ruin.model.entity.player.Player;
import io.ruin.model.inter.dialogue.NPCDialogue;
import io.ruin.model.inter.dialogue.OptionsDialogue;
import io.ruin.model.inter.dialogue.PlayerDialogue;
import io.ruin.model.inter.utils.Option;
import io.ruin.model.stat.StatType;

public class Dwarf {

    private static void whatHaveYouGot(Player player, NPC npc) {
        int miningLevel = player.getStats().get(StatType.Mining).currentLevel;
        if (miningLevel < 60) {
            player.dialogue(
                    new PlayerDialogue("What have you got in the guild?"),
                    new NPCDialogue(npc, "Ooh, it's WONDERFUL! There are lots of coal rocks, and even a few mithril rocks in the guild, " +
                            "all exclusively for people with at least level 60 mining. There's no better mining site anywhere near here.").animate(570),
                    new PlayerDialogue("So you won't let me go in there?").animate(610),
                    new NPCDialogue(npc, "Sorry, but rules are rules. Do some more training first. Can I help you with anything else?").animate(611),
                    new OptionsDialogue(
                            new Option("What do you dwarves do with the ore you mine?", () -> whatDwarvesDo(player, npc)),
                            new Option("No thanks, I'm fine.", () -> noThanks(player))
                    ));
        } else {
            player.dialogue(
                    new PlayerDialogue("What have you got in the guild?"),
                    new NPCDialogue(npc, "Ooh, it's WONDERFUL! There are lots of coal rocks, and even a few mithril rocks in the guild, " +
                            "all exclusively for people with at least level 60 mining. There's no better mining site anywhere near here.").animate(570),
                    new PlayerDialogue("It's a good thing I have level " + miningLevel + " Mining.").animate(567),
                    new NPCDialogue(npc, "Yes, that's pretty good. Did you want anything else?").animate(567),
                    new OptionsDialogue(
                            new Option("What do you dwarves do with the ore you mine?", () -> whatDwarvesDo(player, npc)),
                            new Option("No thanks, I'm fine.", () -> noThanks(player))
                    ));
        }
    }

    private static void whatDwarvesDo(Player player, NPC npc) {
        player.dialogue(
                new PlayerDialogue("What do you dwarves do with the ore you mine?"),
                new NPCDialogue(npc, "What do you think? We smelt it into bars, smith the metal to make armour and weapons, " +
                        "then we exchange them for goods and services.").animate(607),
                new PlayerDialogue("I don't see many dwarves selling armour or weapons here.").animate(593),
                new NPCDialogue(npc, "No, this is only a mining outpost. We dwarves don't much like to settle in human cities. Most of the ore is carted off to Keldagrim, the great dwarven city. They've got a special blast furnace up there - it makes").animate(570),
                new NPCDialogue(npc, "smelting the ore so much easier. There are plenty of dwarven traders working in Keldagrim. Anyway, can I help you with anything else?").animate(569),
                new OptionsDialogue(
                        new Option("What have you got in the guild?", () -> whatHaveYouGot(player, npc)),
                        new Option("No thanks, I'm fine.", () -> noThanks(player))
                )
        );
    }

    private static void Skillcapes(Player player, NPC npc) {
        player.dialogue(
                new PlayerDialogue("Can you tell me about your skillcape?"),
                new NPCDialogue(npc, "Sure, this is a Skillcape of Mining. It shows my stature as a master miner! It has all sorts of uses including a skill boost to my Mining skill and a chance of mining extra ores. When you get to level 99 come and talk to.").animate(607),
                new NPCDialogue(npc, "me and I'll sell you one."),
                new PlayerDialogue("Can I buy a Skillcape of Mining from you?.").animate(593),
                new NPCDialogue(npc, "You believe right, miner. You have earned the right to wear one and when you do you'll have a small chance of finding an extra ore, but I'll need 99000 coins from you first.").animate(570),
                new OptionsDialogue(
                        new Option("Sorry, that's overpiced.", () -> whatHaveYouGot(player, npc)),
                        new Option("Okay than.", () -> {
                            if (player.getInventory().hasItem(995, 99000)) {
                                player.getInventory().remove(995, 99000);
                                player.getInventory().add(ItemID.MINING_HOOD, 1);
                                if (player.getStats().total99s >= 2) {
                                    player.getInventory().add(ItemID.MINING_CAPET, 1);
                                } else {
                                    player.getInventory().add(ItemID.MINING_CAPE, 1);
                                }
                            } else {
                                player.dialogue( new PlayerDialogue("But, unfortunately, I don't have enough money with me"),
                                                 new NPCDialogue(npc, "Well, come back and see me when you do."));
                            }
                        })
                )
        );
    }

    private static void noThanks(Player player) {
        player.dialogue(new PlayerDialogue("No thanks, I'm fine.").animate(562));
    }
    static final int[] DWARF_SET_ONE = {7712, 7713};
    static final int[] DWARF_SET_TWO = {7714, 7715};

    static {
        for (int id : DWARF_SET_ONE)
            NPCAction.register(id, "talk-to", (player, npc) -> player.dialogue(
                    new NPCDialogue(npc, "Welcome to the Mining Guild. Can I help you with anything?").animate(568),
                    new OptionsDialogue(
                            new Option("What have you got in the Guild?", () -> whatHaveYouGot(player, npc)),
                            new Option("What do you dwarves do with the ore you mine?", () -> whatDwarvesDo(player, npc)),
                            new Option("No thanks, I'm fine.", () -> noThanks(player))
                    )
            ));

        for (int id : DWARF_SET_TWO)
            NPCAction.register(id, "talk-to", (player, npc) -> player.dialogue(
                    new PlayerDialogue("What's through this door?"),
                    new NPCDialogue(npc, "This leads deeper into the guild.<br>Through here, you'll find even more rocks including runite and amethyst!"),
                    new PlayerDialogue("Can I go in?"),
                    new NPCDialogue(npc, "Of course. Go right ahead.")
            ));

        NPCAction.register(7721, "talk-to", (player, npc) -> player.dialogue(
                new NPCDialogue(npc, "Hello there. Do you need anything?"),
                new PlayerDialogue("What's through this cave?"),
                new NPCDialogue(npc, "This cave leads to the Mining Guild, home to the finest mining site around. Is there anything else I can help you with?"),
                new OptionsDialogue(
                        new Option("What have you got in the Guild?", () -> whatHaveYouGot(player, npc)),
                        new Option("What do you dwarves do with the ore you mine?", () -> whatDwarvesDo(player, npc)),
                        new Option("No thanks, I'm fine.", () -> noThanks(player))
                )
        ));

        NPCAction.register(7716, "talk-to", (player, npc) -> {
            if (player.getStats().check(StatType.Mining, 99)) {
                player.dialogue(
                        new NPCDialogue(npc, "Welcome to the Mining Guild. Can I help you with anything?").animate(568),
                        new OptionsDialogue(
                                new Option("What have you got in the Guild?", () -> whatHaveYouGot(player, npc)),
                                new Option("What do you dwarves do with the ore you mine?", () -> whatDwarvesDo(player, npc)),
                                new Option("Can you tell me about your skillcape?", () -> Skillcapes(player, npc)),
                                new Option("No thanks, I'm fine.", () -> noThanks(player))
                        )
                );
            } else {
                player.dialogue(
                        new NPCDialogue(npc, "Welcome to the Mining Guild. Can I help you with anything?").animate(568),
                        new OptionsDialogue(
                                new Option("What have you got in the Guild?", () -> whatHaveYouGot(player, npc)),
                                new Option("What do you dwarves do with the ore you mine?", () -> whatDwarvesDo(player, npc)),
                                new Option("No thanks, I'm fine.", () -> noThanks(player))
                        )
                );
            }
    });

    }
}
