package io.ruin.model.entity.npc.actions;

import io.ruin.model.entity.npc.NPCAction;
import io.ruin.model.entity.player.Player;
import io.ruin.model.inter.dialogue.MessageDialogue;
import io.ruin.model.inter.dialogue.NPCDialogue;
import io.ruin.model.inter.dialogue.OptionsDialogue;
import io.ruin.model.inter.handlers.OptionScroll;
import io.ruin.model.inter.utils.Option;

import java.util.ArrayList;
import java.util.List;

import static io.ruin.data.impl.teleports.chartteleport;

public class CharterShip {

    public static final int[] PSSEAMAN = {3644, 3645, 3646 };

    public static final int[] CHARTERNPC = {1331, 1333, 1334, 1328 };

    static {
/** SEAMEN Travel to Musa Point **/
        for (int PSSEA : PSSEAMAN) {
            NPCAction.register(PSSEA, "talk-to", (player, npc) -> {
                player.dialogue(
                        new NPCDialogue(npc, "Hello " + player.getName() + ", Would you like to travel to Musa Point?"),
                        new OptionsDialogue(
                                new Option("Yes", () -> {
                                    //if (!player.getInventory().contains(ITEMS)) {
                                    if (player.getInventory().contains(995, 30)) {
                                        player.startEvent(event -> {
                                            player.lock();
                                            player.getPacketSender().fadeOut();
                                            event.delay(2);
                                            player.getMovement().teleport(2956, 3146, 0);
                                            event.delay(2);
                                            player.getPacketSender().fadeIn();
                                            player.unlock();
                                            player.getInventory().remove(995, 30);
                                            player.dialogue(new MessageDialogue("You pay 30 coins and ride the boat to Musa Point."));
                                        });
                                    } else {
                                        player.dialogue(new NPCDialogue(npc, "You need 30 coins to travel on this ship."));
                                    }
                                }),
                                new Option("Not right now", () -> {

                                })
                        ));

            });

            NPCAction.register(PSSEA, "pay-fare", (player, npc) -> {
                if (player.getInventory().contains(995, 30)) {
                    player.startEvent(event -> {
                        player.lock();
                        player.getPacketSender().fadeOut();
                        event.delay(2);
                        player.getMovement().teleport(2956, 3146, 0);
                        event.delay(2);
                        player.getPacketSender().fadeIn();
                        player.unlock();
                        player.getInventory().remove(995, 30);
                        player.dialogue(new MessageDialogue("You pay 30 coins and ride the boat to Musa Point."));
                    });
                } else {
                    player.dialogue(new NPCDialogue(npc, "You need 30 coins to travel on this ship."));
                }
            });

        }

        /** SEAMEN Travel to Musa Point **/
        NPCAction.register(3648, "talk-to", (player, npc) -> {
            player.dialogue(
                    new NPCDialogue(npc, "Hello " + player.getName() + ", Would you like to travel to Port Sarim?"),
                    new OptionsDialogue(
                            new Option("Yes", () -> {
                                //if (!player.getInventory().contains(ITEMS)) {
                                if (player.getInventory().contains(995, 30)) {
                                    player.startEvent(event -> {
                                        player.lock();
                                        player.getPacketSender().fadeOut();
                                        event.delay(2);
                                        player.getMovement().teleport(3029, 3217, 0);
                                        event.delay(2);
                                        player.getPacketSender().fadeIn();
                                        player.unlock();
                                        player.getInventory().remove(995, 30);
                                        player.dialogue(new MessageDialogue("You pay 30 coins and ride the boat to Port Sarim."));
                                    });
                                } else {
                                    player.dialogue(new NPCDialogue(npc, "You need 30 coins to travel on this ship."));
                                }
                            }),
                            new Option("Not right now", () -> {

                            })
                    ));

        });

        NPCAction.register(3648, "pay-fare", (player, npc) -> {
            if (player.getInventory().contains(995, 30)) {
                player.startEvent(event -> {
                    player.lock();
                    player.getPacketSender().fadeOut();
                    event.delay(2);
                    player.getMovement().teleport(3029, 3217, 0);
                    event.delay(2);
                    player.getPacketSender().fadeIn();
                    player.unlock();
                    player.getInventory().remove(995, 30);
                    player.dialogue(new MessageDialogue("You pay 30 coins and ride the boat to Port Sarim."));
                });
            } else {
                player.dialogue(new NPCDialogue(npc, "You need 30 coins to travel on this ship."));
            }
        });

        /** Captain Barnaby on Ardy Dock **/
        NPCAction.register(9250, "talk-to", (player, npc) -> {
            player.dialogue(
                    new NPCDialogue(npc, "Hello " + player.getName() + ", Would you like to travel to Brimhaven?"),
                    new OptionsDialogue(
                            new Option("Yes", () -> {
                                //if (!player.getInventory().contains(ITEMS)) {
                                if (player.getInventory().contains(995, 30)) {
                                    player.startEvent(event -> {
                                        player.lock();
                                        player.getPacketSender().fadeOut();
                                        event.delay(2);
                                        player.getMovement().teleport(2772, 3234, 0);
                                        event.delay(2);
                                        player.getPacketSender().fadeIn();
                                        player.unlock();
                                        player.getInventory().remove(995, 30);
                                        player.dialogue(new MessageDialogue("You pay 30 coins and ride the boat to Brimhaven."));
                                    });
                                } else {
                                    player.dialogue(new NPCDialogue(npc, "You need 30 coins to travel on this ship."));
                                }
                            }),
                            new Option("Not right now", () -> {

                            })
                    ));

        });

        NPCAction.register(9250, "Brimhaven", (player, npc) -> {
            if (player.getInventory().contains(995, 30)) {
                player.startEvent(event -> {
                    player.lock();
                    player.getPacketSender().fadeOut();
                    event.delay(2);
                    player.getMovement().teleport(2772, 3234, 0);
                    event.delay(2);
                    player.getPacketSender().fadeIn();
                    player.unlock();
                    player.getInventory().remove(995, 30);
                    player.dialogue(new MessageDialogue("You pay 30 coins and ride the boat to Brimhaven."));
                });
            } else {
                player.dialogue(new NPCDialogue(npc, "You need 30 coins to travel on this ship."));
            }
        });

        NPCAction.register(9250, "Rimmington", (player, npc) -> {
            if (player.getInventory().contains(995, 30)) {
                player.startEvent(event -> {
                    player.lock();
                    player.getPacketSender().fadeOut();
                    event.delay(2);
                    player.getMovement().teleport(2909, 3226, 0);
                    event.delay(2);
                    player.getPacketSender().fadeIn();
                    player.unlock();
                    player.getInventory().remove(995, 30);
                    player.dialogue(new MessageDialogue("You pay 30 coins and ride the boat to Rimmington."));
                });
            } else {
                player.dialogue(new NPCDialogue(npc, "You need 30 coins to travel on this ship."));
            }
        });

        /** Captain Barnaby on Brimhaven**/
        NPCAction.register(8764, "talk-to", (player, npc) -> {
            player.dialogue(
                    new NPCDialogue(npc, "Hello " + player.getName() + ", Would you like to travel to Ardougne?"),
                    new OptionsDialogue(
                            new Option("Yes", () -> {
                                //if (!player.getInventory().contains(ITEMS)) {
                                if (player.getInventory().contains(995, 30)) {
                                    player.startEvent(event -> {
                                        player.lock();
                                        player.getPacketSender().fadeOut();
                                        event.delay(2);
                                        player.getMovement().teleport(2683, 3271, 0);
                                        event.delay(2);
                                        player.getPacketSender().fadeIn();
                                        player.unlock();
                                        player.getInventory().remove(995, 30);
                                        player.dialogue(new MessageDialogue("You pay 30 coins and ride the boat to Brimhaven."));
                                    });
                                } else {
                                    player.dialogue(new NPCDialogue(npc, "You need 30 coins to travel on this ship."));
                                }
                            }),
                            new Option("Not right now", () -> {

                            })
                    ));

        });

        NPCAction.register(8764, "Ardougne", (player, npc) -> {
            if (player.getInventory().contains(995, 30)) {
                player.startEvent(event -> {
                    player.lock();
                    player.getPacketSender().fadeOut();
                    event.delay(2);
                    player.getMovement().teleport(2683, 3271, 0);
                    event.delay(2);
                    player.getPacketSender().fadeIn();
                    player.unlock();
                    player.getInventory().remove(995, 30);
                    player.dialogue(new MessageDialogue("You pay 30 coins and ride the boat to Ardougne."));
                });
            } else {
                player.dialogue(new NPCDialogue(npc, "You need 30 coins to travel on this ship."));
            }
        });

        NPCAction.register(8764, "Rimmington", (player, npc) -> {
            if (player.getInventory().contains(995, 30)) {
                player.startEvent(event -> {
                    player.lock();
                    player.getPacketSender().fadeOut();
                    event.delay(2);
                    player.getMovement().teleport(2909, 3226, 0);
                    event.delay(2);
                    player.getPacketSender().fadeIn();
                    player.unlock();
                    player.getInventory().remove(995, 30);
                    player.dialogue(new MessageDialogue("You pay 30 coins and ride the boat to Rimmington."));
                });
            } else {
                player.dialogue(new NPCDialogue(npc, "You need 30 coins to travel on this ship."));
            }
        });

        /** Captain Barnaby on Rimmington Dock**/
        NPCAction.register(8763, "talk-to", (player, npc) -> {
            player.dialogue(
                    new NPCDialogue(npc, "Hello " + player.getName() + ", Would were would you like to travel?"),
                    new OptionsDialogue(
                            new Option("Ardougne", () -> {
                                //if (!player.getInventory().contains(ITEMS)) {
                                if (player.getInventory().contains(995, 30)) {
                                    player.startEvent(event -> {
                                        player.lock();
                                        player.getPacketSender().fadeOut();
                                        event.delay(2);
                                        player.getMovement().teleport(2683, 3271, 0);
                                        event.delay(2);
                                        player.getPacketSender().fadeIn();
                                        player.unlock();
                                        player.getInventory().remove(995, 30);
                                        player.dialogue(new MessageDialogue("You pay 30 coins and ride the boat to Ardougne."));
                                    });
                                } else {
                                    player.dialogue(new NPCDialogue(npc, "You need 30 coins to travel on this ship."));
                                }
                            }),
                            new Option("Brimhaven", () -> {
                                if (player.getInventory().contains(995, 30)) {
                                    player.startEvent(event -> {
                                        player.lock();
                                        player.getPacketSender().fadeOut();
                                        event.delay(2);
                                        player.getMovement().teleport(3029, 3217, 0);
                                        event.delay(2);
                                        player.getPacketSender().fadeIn();
                                        player.unlock();
                                        player.getInventory().remove(995, 30);
                                        player.dialogue(new MessageDialogue("You pay 30 coins and ride the boat to Brimhaven."));
                                    });
                                } else {
                                    player.dialogue(new NPCDialogue(npc, "You need 30 coins to travel on this ship."));
                                }
                            })
                    ));

        });

        NPCAction.register(8763, "Ardougne", (player, npc) -> {
            if (player.getInventory().contains(995, 30)) {
                player.startEvent(event -> {
                    player.lock();
                    player.getPacketSender().fadeOut();
                    event.delay(2);
                    player.getMovement().teleport(2683, 3271, 0);
                    event.delay(2);
                    player.getPacketSender().fadeIn();
                    player.unlock();
                    player.getInventory().remove(995, 30);
                    player.dialogue(new MessageDialogue("You pay 30 coins and ride the boat to Ardougne."));
                });
            } else {
                player.dialogue(new NPCDialogue(npc, "You need 30 coins to travel on this ship."));
            }
        });

        NPCAction.register(8763, "Brimhaven", (player, npc) -> {
            if (player.getInventory().contains(995, 30)) {
                player.startEvent(event -> {
                    player.lock();
                    player.getPacketSender().fadeOut();
                    event.delay(2);
                    player.getMovement().teleport(2772, 3234, 0);
                    event.delay(2);
                    player.getPacketSender().fadeIn();
                    player.unlock();
                    player.getInventory().remove(995, 30);
                    player.dialogue(new MessageDialogue("You pay 30 coins and ride the boat to Brimhaven."));
                });
            } else {
                player.dialogue(new NPCDialogue(npc, "You need 30 coins to travel on this ship."));
            }
        });

        /** Charter Interface **/
        for(int CHART : CHARTERNPC) {
        NPCAction.register(CHART, "Charter", (player, npc) -> {
            OptionScroll.open(player, "Charter Network", getOptions(player));
        });
    }
        /** VEOS Port Sarim **/
        NPCAction.register(8630, "Port Piscarilius", (player, npc) -> {
                player.startEvent(event -> {
                    player.lock();
                    player.getPacketSender().fadeOut();
                    event.delay(2);
                    player.getMovement().teleport(1824, 3691, 0);
                    event.delay(2);
                    player.getPacketSender().fadeIn();
                    player.unlock();
                    player.dialogue(new MessageDialogue("You travel to Port Piscarilius."));
                });
        });

        NPCAction.register(8630, "Land's End", (player, npc) -> {
            player.startEvent(event -> {
                player.lock();
                player.getPacketSender().fadeOut();
                event.delay(2);
                player.getMovement().teleport(1504, 3399, 0);
                event.delay(2);
                player.getPacketSender().fadeIn();
                player.unlock();
                player.dialogue(new MessageDialogue("You travel to Land's End."));
            });
        });

        /** VEOS Lands End **/
        NPCAction.register(8484, "talk-to", (player, npc) -> {
            player.dialogue(
                    new NPCDialogue(npc, "Hello " + player.getName() + ", would you like to travel back to Port Sarim?"),
                    new OptionsDialogue(
                            new Option("YES", () -> {
                                player.startEvent(event -> {
                                    player.lock();
                                    player.getPacketSender().fadeOut();
                                    event.delay(2);
                                    player.getMovement().teleport(3055, 3245, 0);
                                    event.delay(2);
                                    player.getPacketSender().fadeIn();
                                    player.unlock();
                                    player.dialogue(new MessageDialogue("You travel to Port Sarim."));
                                });
                            }),
                            new Option("Not Right Now", () -> {

                            })
                    ));
        });

        /** VEOS Port Piscarilius **/
        NPCAction.register(2147, "Port Sarim", (player, npc) -> {
            player.startEvent(event -> {
                player.lock();
                player.getPacketSender().fadeOut();
                event.delay(2);
                player.getMovement().teleport(3055, 3245, 0);
                event.delay(2);
                player.getPacketSender().fadeIn();
                player.unlock();
                player.dialogue(new MessageDialogue("You travel to Port Sarim."));
            });
        });

        NPCAction.register(8630, "Land's End", (player, npc) -> {
            player.startEvent(event -> {
                player.lock();
                player.getPacketSender().fadeOut();
                event.delay(2);
                player.getMovement().teleport(1504, 3399, 0);
                event.delay(2);
                player.getPacketSender().fadeIn();
                player.unlock();
                player.dialogue(new MessageDialogue("You travel to Land's End."));
            });
        });

    }


    public static void open(Player player) {
        OptionScroll.open(player, "Charter Network", getOptions(player));
    }

    /** Charter Ships **/
    private static List<Option> getOptions(Player player) {
        List<Option> options = new ArrayList<>();
        options.add(new Option("Brimhaven (Cost 480)", () -> chartteleport(player, 2760, 3238, 0, 480)));
        options.add(new Option("Catherby (Cost 480)", () -> chartteleport(player, 2792, 3414, 0, 480)));
        options.add(new Option("Consair Cove (Cost 680)", () -> chartteleport(player, 2589, 2851, 0, 680)));
        options.add(new Option("Musa Point (Cost 200)", () -> chartteleport(player, 2954, 3158, 0, 200)));
        options.add(new Option("Mos Le'Harmless (Cost 1450)", () -> chartteleport(player, 3671, 2931, 0, 1450)));
        options.add(new Option("Port Khazard (Cost 1600)", () -> chartteleport(player, 2674, 3144, 0, 1600)));
        options.add(new Option("Port Phasmatys (Cost 2900)", () -> chartteleport(player, 3702, 3503, 0, 2900)));
        options.add(new Option("Port Sarim (Cost 1600)", () -> chartteleport(player, 3038, 3192, 0, 1600)));
        options.add(new Option("Port Tyras (Cost 3200)", () -> chartteleport(player, 2142, 3122, 0, 3200)));
        options.add(new Option("Ship Yard (Cost 400)", () -> chartteleport(player, 3001, 3032, 0, 400)));
        options.add(new Option("Prifddinas (Cost 3450)", () -> chartteleport(player, 2157, 3330, 0, 3450)));
        return options;
    }
}
