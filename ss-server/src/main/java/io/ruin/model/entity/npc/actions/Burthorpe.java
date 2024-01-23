package io.ruin.model.entity.npc.actions;

import io.ruin.cache.ItemID;
import io.ruin.cache.NpcID;
import io.ruin.model.entity.npc.NPC;
import io.ruin.model.entity.npc.NPCAction;
import io.ruin.model.entity.player.Player;
import io.ruin.model.inter.dialogue.NPCDialogue;
import io.ruin.model.inter.dialogue.OptionsDialogue;
import io.ruin.model.inter.dialogue.PlayerDialogue;
import io.ruin.model.inter.utils.Option;
import io.ruin.model.shop.ShopManager;
import io.ruin.model.stat.StatType;

public class Burthorpe {

    static {
        /** Martin Thwait **/
        NPCAction.register(NpcID.MARTIN_THWAIT, "talk-to", (player, npc) -> {
                player.dialogue(
                        new NPCDialogue(npc, "You know it's sometimes funny how things work out, I lose some gold but find an item, or I lose an item and find some gold... no-one ever knows what's gone where ya know").animate(568),
                        new OptionsDialogue(
                                new Option("Yeah I know what you mean, found anything recently?", () -> {
                                    if (player.getStats().check(StatType.Thieving, 50) && player.getStats().check(StatType.Agility,50)) {
                                        ShopManager.openIfExists(player, "400cc8bc-b896-4797-a23e-4ec97fd87536");
                                    } else {
                                        player.dialogue((new NPCDialogue(npc, "Sorry, mate. Train up your Thieving and Agility skills to at least 50 and I might be able to help you out.!")));
                                    }
                                }),
                                new Option("Can you tell me about your skillcape?", () -> {
                                    if (player.getStats().check(StatType.Thieving,99)) {
                                        Skillcapes(player, npc);
                                    } else {
                                        player.dialogue((new NPCDialogue(npc, "Sorry, mate. Train up your Thieving skill to at least 99 and I might be able to help you out.!")));
                                    }
                                })
                        )
                );
        });
        NPCAction.register(NpcID.MARTIN_THWAIT, "trade", (player, npc) -> {
                                if (player.getStats().check(StatType.Thieving, 50) && player.getStats().check(StatType.Agility,50)) {
                                    ShopManager.openIfExists(player, "400cc8bc-b896-4797-a23e-4ec97fd87536");
                                } else {
                                    player.dialogue((new NPCDialogue(npc, "Sorry, mate. Train up your Thieving and Agility skills to at least 50 and I might be able to help you out.!")));
                                }
        });
    }

    private static void Skillcapes(Player player, NPC npc) {
        player.dialogue(
                new PlayerDialogue("Can you tell me about your skillcape?"),
                new NPCDialogue(npc, " Certainly! Skillcapes are a symbol of achievement. Only people who have mastered a skill and reached level 99 can get their hands on them and gain the benefits they carry.").animate(607),
                new NPCDialogue(npc, "The Cape of Ranging has been modified by Ava to function as one of her accumulator devices. Is there anything else I can help you with?").animate(607),
                new PlayerDialogue("Can I buy a Skillcape of Mining from you?.").animate(593),
                new NPCDialogue(npc, "Certainly! Right when you give me 99000 coins").animate(570),
                new OptionsDialogue(
                        new Option("Sorry, that's overpiced.", () -> {

                        }),
                        new Option("Okay than.", () -> {
                            if (player.getInventory().hasItem(995, 99000)) {
                                player.getInventory().remove(995, 99000);
                                player.getInventory().add(ItemID.RANGING_CAPE, 1);
                                if (player.getStats().total99s >= 2) {
                                    player.getInventory().add(ItemID.RANGING_CAPET, 1);
                                } else {
                                    player.getInventory().add(ItemID.RANGING_HOOD, 1);
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
}
