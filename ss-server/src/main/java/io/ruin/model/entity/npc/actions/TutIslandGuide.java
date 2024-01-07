package io.ruin.model.entity.npc.actions;

import io.ruin.model.World;
import io.ruin.model.entity.npc.NPC;
import io.ruin.model.entity.npc.NPCAction;
import io.ruin.model.entity.player.GameMode;
import io.ruin.model.entity.player.Player;
import io.ruin.model.inter.dialogue.MessageDialogue;
import io.ruin.model.inter.dialogue.NPCDialogue;
import io.ruin.model.inter.dialogue.OptionsDialogue;
import io.ruin.model.inter.utils.Option;
import io.ruin.utility.Broadcast;
import io.ruin.cache.Color;

import static io.ruin.model.entity.npc.actions.edgeville.StarterGuide.giveEcoStarter;

public class TutIslandGuide {

    static NPC GIELINOR_GUIDE = new NPC(3308);

    static {
        NPCAction.register(3308, "Talk-to", (player, npc) -> {
            boolean actuallyNew = player.newPlayer;
            player.inTutorial = true;
            player.countcheck = true;
            player.startEvent(event -> {
                //boolean startTutorial = false;
                if (actuallyNew) {
                    player.dialogue(
                            new NPCDialogue(GIELINOR_GUIDE, "Welcome to SkryllzScape, let's talk about your game mode."),
                            new NPCDialogue(GIELINOR_GUIDE, "SkryllzScape is a Runescape Remake that mixes multiple era of RS."),
                            new NPCDialogue(GIELINOR_GUIDE, "Do you want to see the options for Iron Man modes?"),
                            new OptionsDialogue("View Iron Man options?",
                                    new Option("Yes", () -> {
                                        GameMode.openSelection(player);
                                        player.unsafeDialogue(new MessageDialogue("Close the interface once you're happy with your selection." +
                                                "<br><br><col=ff0000>WARNING:</col> This is the ONLY chance to choose your Iron Man mode.").hideContinue());
                                    }), new Option("No", player::closeDialogue)));
                    event.waitForDialogue(player);
                    String text = "You want to be a part of the economy, then? Great!";
                    if (player.getGameMode() == GameMode.IRONMAN) {
                        text = "Iron Man, huh? Self-sufficiency is quite a challenge, good luck!";
                    } else if (player.getGameMode() == GameMode.HARDCORE_IRONMAN) {
                        text = "Hardcore?! You only live once... make it count!";
                    } else if (player.getGameMode() == GameMode.ULTIMATE_IRONMAN) {
                        text = "Ultimate Iron Man... Up for quite the challenge, aren't you?";
                    }
                    if (player.getGameMode().isIronMan()) {
                        player.dialogue(new NPCDialogue(GIELINOR_GUIDE, text),
                                new NPCDialogue(GIELINOR_GUIDE, "I'll give you a few items to help get you started..."),
                                new NPCDialogue(GIELINOR_GUIDE, "There you go, some basic stuff. If you need anything else, remember to check the shops.") {
                                    @Override
                                    public void open(Player player) {
                                        giveEcoStarter(player);
                                        player.newPlayer = false;
                                        super.open(player);
                                    }
                                });
                    } else {
                        player.dialogue(new NPCDialogue(GIELINOR_GUIDE, "Excellent.. I'll give you a few items to help get you started"),
                                new NPCDialogue(GIELINOR_GUIDE, "There you go, some basic stuff. If you need anything else, remember to chat with others in the discord.") {
                                    @Override
                                    public void open(Player player) {
                                        giveEcoStarter(player);
                                        player.newPlayer = false;
                                        super.open(player);
                                    }
                                });
                    }
                    event.waitForDialogue(player);
                    player.sendMessage(Color.DARK_RED.wrap("<img=18>When you are ready to leave speak to the guide again."));
                }
            });
            if (player.inTutorial) {
                player.dialogue(new NPCDialogue(3308,
                                "Greetings, " + player.getName() +
                                        " Would you like to go to the mainland?"),
                        new OptionsDialogue("Go to Mainland?", new Option("Yes!", () -> {
                            player.closeDialogue();
                            player.getMovement().teleport(World.HOME);
                            player.sendMessage("You have been sent to the Mainland");
                            player.sendMessage("Reach out in the discord or in the clan chat using / to other players.");
                            player.sendMessage("Enjoy playing SkryllzScape!");
                            Broadcast.WORLD.sendNews(player.getName() + " has just joined " + World.type.getWorldName() + "!");
                           // LootAlert alert = new LootAlert(("SSBroadcastDiscord"), "New Player Joined");
                            //alert.setItemName("test");
                            //alert.setDescription("test");
                           // new RuneAlertClient().withToken(("runiverse_web_access_token")).sendAlert(alert);
                            player.inTutorial = false;
                            player.logoutListener = null;
                            player.setTutorialStage(0);
                            player.unlock();
                        }), new Option("Not just yet.", () -> {
                            player.closeDialogue();
                            player.inTutorial = false;
                            player.logoutListener = null;
                            player.unlock();
                        })));
            }
        });
    }
}