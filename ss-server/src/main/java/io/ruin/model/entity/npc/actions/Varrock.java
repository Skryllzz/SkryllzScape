package io.ruin.model.entity.npc.actions;

import io.ruin.model.entity.shared.listeners.SpawnListener;

public class Varrock {

    static {
        SpawnListener.register(5422, npc -> npc.skipReachCheck = p -> p.equals(1264, 3500));
        SpawnListener.register(5422, npc -> npc.skipReachCheck = p -> p.equals(3302, 3491));
    }
}
