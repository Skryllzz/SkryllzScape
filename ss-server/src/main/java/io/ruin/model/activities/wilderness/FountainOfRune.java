package io.ruin.model.activities.wilderness;

import io.ruin.api.utils.Random;
import io.ruin.model.inter.dialogue.ItemDialogue;
import io.ruin.model.item.actions.ItemObjectAction;

public enum FountainOfRune {

    GLORY(1704, 11978, 1712),
    GLORY_T(10362, 11964, 11964),
    RING_OF_WEALTH(2572, 11980, 11980),
    RING_OF_WEALTH_I(12785, 20786, 20786),
    SKILLS_NECKLACE(11113, 11968, 11105),
    COMBAT_BRACELET(11126, 11972, 11118);

    public final int unchargedID, chargedID, HerosChargedID;

    FountainOfRune(int unchargedID, int chargedID, int HerosChargedID) {
        this.unchargedID = unchargedID;
        this.chargedID = chargedID;
        this.HerosChargedID = HerosChargedID;
    }

    static {
        for (FountainOfRune fountainOfRune : values()) {
            ItemObjectAction.register(fountainOfRune.unchargedID, 26782, (player, uncharged, obj) -> player.startEvent(event -> {
                player.lock();
                player.sendMessage("You hold the jewellery against the fountain..");
                event.delay(1);
                player.animate(832);
                int amount = uncharged.count();
                player.getInventory().remove(uncharged.getId(), amount);
                if(uncharged.getId() == GLORY.unchargedID && Random.rollDie(250, 1)) {
                    player.getInventory().add(19707, 1);
                    player.getInventory().add(fountainOfRune.chargedID, amount - 1);
                    player.dialogue(new ItemDialogue().one(19707, "The power of the fountain is transferred into an amulet of eternal glory."));
                } else {
                    player.getInventory().add(fountainOfRune.chargedID, amount);
                    player.dialogue(new ItemDialogue().one(fountainOfRune.chargedID, "You feel a power emanating from the fountain as it recharges your jewellery."));
                }
                player.unlock();
            }));
            ItemObjectAction.register(fountainOfRune.unchargedID, 2939, (player, uncharged, obj) -> player.startEvent(event -> { //Fountain of Heros
                player.lock();
                player.sendMessage("You hold the jewellery against the fountain..");
                event.delay(1);
                player.animate(832);
                int amount = uncharged.count();
                player.getInventory().remove(uncharged.getId(), amount);
                player.getInventory().add(fountainOfRune.HerosChargedID, amount);
                player.dialogue(new ItemDialogue().one(fountainOfRune.chargedID, "You feel a power emanating from the fountain as it recharges your jewellery."));
                player.unlock();
            }));
        }
    }
}
