package io.ruin.model.skills.agility;

import io.ruin.model.inter.dialogue.MessageDialogue;
import io.ruin.model.map.Position;
import io.ruin.model.map.Tile;
import io.ruin.model.map.object.actions.ObjectAction;
import io.ruin.model.skills.agility.shortcut.*;

public class Shortcuts {
    static {

        // Stile at Fred the Farmer's sheep field and the stile at Falador cabbage patch
        // Stile at Taverly which is required for the clue scroll south of the long house
        // Stile into the beahive in Camelot
        ObjectAction.register(993, "climb-over", (p, obj) -> Stile.shortcut(p, obj, 1));

        // Draynor Stile into cabbage field
        ObjectAction.register(7527, "climb-over", (p, obj) -> Stile.shortcut(p, obj, 1));

        // lumberyard stile
        ObjectAction.register(2618, "climb-over", (p, obj) -> Stile.shortcutN(p, obj, 1));

        // Lumbridge Stile into sheep farm
        ObjectAction.register(12892, "climb-over", (p, obj) -> Stile.shortcut(p, obj, 1));

        // Falador Agility Shortcut
        ObjectAction.register(24222, "climb-over", (p, obj) -> CrumblingWall.shortcut(p, obj, 5));

        // Zanaris Agility Shortcut
        ObjectAction.register(17002, 2400, 4403, 0, "squeeze-past", (p, obj) -> JuttingWall.shortcut(p, obj, 46));
        ObjectAction.register(17002, 2409, 4401, 0, "squeeze-past", (p, obj) -> JuttingWall.shortcut(p, obj, 66));

        // Morytainia Bridge Agility Shortcut
        ObjectAction.register(3522, "jump", Bridge::shortcut);

        // Camelot loose railing
        ObjectAction.register(51, 2662, 3500, 0, "squeeze-through", (p, obj) -> LooseRailing.shortcut(p, obj, 1));

        // Consair Cove Climb Over Rock
        ObjectAction.register(31757, 2546, 2872, 0, "climb", Rocks::climb);

        // Cross River Lum to Al Kharid
        ObjectAction.register(17068, 3252, 3179, 0, "grapple", (p, obj) -> Grappling.grapple(p, obj, 8, 37, 19, 4460, 760, 10, Position.of(3246, 3179, 0), Position.of(3259, 3180, 0)));

        // Rope swing to Moss Giant Island
        ObjectAction.register(23568, 2705, 3209, 0, "swing-on", (p, obj) -> RopeSwing.shortcut(p, obj, 10, Position.of(2709, 3209, 0), Position.of(2704, 3209, 0)));
        ObjectAction.register(23569, 2703, 3205, 0, "swing-on", (p, obj) -> RopeSwing.shortcut(p, obj, 10, Position.of(2705, 3205, 0), Position.of(2709, 3205, 0)));

        // Dwarf Mining Crevince
        ObjectAction.register(16543, 3034, 9806, 0, "squeeze-through", (p, obj) -> Crevice.shortcut(p, obj, 42, Position.of(3035, 9806, 0), Position.of(3028, 9806, 0)));
        ObjectAction.register(16543, 3029, 9806, 0, "squeeze-through", (p, obj) -> Crevice.shortcut(p, obj, 42, Position.of(3028, 9806, 0), Position.of(3035, 9806, 0)));
        // (Grapple) Scale Falador wall
        ObjectAction.register(17050, 3032, 3389, 0, "grapple", (p, obj) -> Grappling.grapple(p, obj, 11, 19, 37, 4455, 760, 10, Position.of(3032, 3388, 0), Position.of(3032, 3389, 1)));
        ObjectAction.register(17049, 3033, 3390, 0, "grapple", (p, obj) -> Grappling.grapple(p, obj, 11, 19, 37, 4455, 760, 10, Position.of(3033, 3390, 0), Position.of(3033, 3389, 1)));
        ObjectAction.register(17051, 3033, 3390, 1, "jump", ((player, obj) -> {
            player.startEvent(event -> {
                player.animate(2586);
                event.delay(1);
                player.getMovement().teleport(3033, 3390, 0);
            });
        }));
        ObjectAction.register(17052, 3032, 3388, 1, "jump", ((player, obj) -> {
            player.startEvent(event -> {
                player.animate(2586);
                event.delay(1);
                player.getMovement().teleport(3032, 3388, 0);
            });
        }));

        // Stepping stones in Brimhaven Dungeon
        // Varrock south fence jump
        ObjectAction.register(16518, 3240, 3335, 0, "jump-over", (p, obj) -> Fence.jump(p, obj, 13));

        // Scale Goblin Village wall
        ObjectAction.register(16468, "manoeuvre-past", (p, obj) -> TightGap.shortcut(p, obj, 14));

        // Monkey bars under Edgeville
        // Yanille Agility Shortcut
        ObjectAction.register(16520, 2575, 3111, 0, "climb-into", (p, obj) -> Hole.shortcut(p, obj, 16));
        ObjectAction.register(16519, 2575, 3108, 0, "climb-under", (p, obj) -> Hole.shortcut(p, obj, 16));

        // Watchtower wall climb
        // Coal Truck log balance
        ObjectAction.register(23274, 2602, 3477, 0, "walk-across", (p, obj) -> LogBalance.shortcut(p, obj, 20));
        ObjectAction.register(23274, 2599, 3477, 0, "walk-across", (p, obj) -> LogBalance.shortcut(p, obj, 20));

        // Grand Exchange Agility Shortcut
        ObjectAction.register(16529, "climb-into", (p, obj) -> UnderwallTunnel.shortcut(p, obj, 21));
        ObjectAction.register(16530, "climb-into", (p, obj) -> UnderwallTunnel.shortcut(p, obj, 21));

        // Pipe contortion in Brimhaven Dungeon
        // Eagles' Peak Agility Shortcut
        // Underwall tunnel	Falador Agility Shortcut
        ObjectAction.register(16528, 2948, 3312, 0, "climb-into", (p, obj) -> Hole.shortcut(p, obj, 26));
        ObjectAction.register(16527, 2948, 3310, 0, "climb-into", (p, obj) -> Hole.shortcut(p, obj, 26));

        // Stepping stones in Brimhaven Dungeon
        // Draynor Manor stones to Champions' Guild
        ObjectAction.register(16533, "jump-onto", ((player, obj) -> SteppingStone.crossEW(player, obj, 31)));

        // (Grapple) Scale Catherby cliffside
        // Cairn Isle rock slide climb
        // Ardougne log balance shortcut
        ObjectAction.register(16548, 2601, 3336, 0, "walk-across", (p, obj) -> LogBalance.shortcut(p, obj, 33));
        ObjectAction.register(16546, 2599, 3336, 0, "walk-across", (p, obj) -> LogBalance.shortcut(p, obj, 33));

        // Pipe contortion in Brimhaven Dungeon
        // Trollweiss/Rellekka Hunter area cliff scramble
        // (Grapple) Escape from the Water Obelisk Island
        // Gnome Stronghold Shortcut

        // Al Kharid mining pit cliffside scramble


        // (Grapple) Scale Yanille wall
        // Yanille Agility dungeon balance ledge
        // Kourend lake isle jump
        // Trollheim easy cliffside scramble
        // Dwarven Mine narrow crevice
        // Draynor narrow tunnel
        // Trollheim medium cliffside scramble
        // Trollheim advanced cliffside scramble
        // Kourend river jump
        // Tirannwn log balance
        // Cosmic Temple - medium narrow walkway
        // Deep Wilderness Dungeon narrow shortcut
        // Trollheim hard cliffside scramble
        // Log balance to Fremennik Province
        // Contortion in Yanille Dungeon small room
        // Arceuus essence mine boulder leap
        // Stepping stone into Morytania near the Nature Grotto
        Tile.getObject(13504, 3419, 3325, 0, 22, -1).skipReachCheck = p -> true;
        ObjectAction.register(13504, "cross", ((player, obj) -> SteppingStone.crossEW(player, obj, 1)));
        // Pipe from Edgeville dungeon to Varrock Sewers
        // Arceuus essence mine eastern scramble
        // (Grapple) Karamja Volcano
        // Motherlode Mine wall shortcut
        // Stepping stone by Miscellania docks
        // Monkey bars under Yanille
        // Stepping stones in the Cave Kraken lake
        // Rellekka east fence shortcut
        // Port Phasmatys Ectopool Shortcut
        // Elven Overpass (Arandar) easy cliffside scramble
        // Wilderness from God Wars Dungeon area climb
        // Squeeze through to God Wars Dungeon surface access
        // Estuary crossing on Mos Le'Harmless
        // Slayer Tower medium spiked chain climb
        // Fremennik Slayer Dungeon narrow crevice
        // Taverley Dungeon lesser demon fence
        // Trollheim Wilderness Route
        // Temple on the Salve to Morytania shortcut
        // Cosmic Temple advanced narrow walkway
        // Lumbridge Swamp to Al Kharid narrow crevice
        // Heroes' Guild tunnel
        ObjectAction.register(9739, "use", ((player, obj) -> {
          player.dialogue(new MessageDialogue("This shortcut will be added with the Falador Hard Diary. Please check back later."));
        }));
        ObjectAction.register(9740, "use", ((player, obj) -> {
            player.dialogue(new MessageDialogue("This shortcut will be added with the Falador Hard Diary. Please check back later."));
        }));
        // Yanille Dungeon's rubble climb
        // Elven Overpass (Arandar) medium cliffside scramble
        // Arceuus essence mine northern scramble
        // Taverley Dungeon pipe squeeze to Blue dragon lair
        // (Grapple) Cross cave, south of Dorgesh-Kaan
        // Rope descent to Saradomin's Encampment
        // Slayer Tower advanced spiked chain climb
        // Stronghold Slayer Cave wall-climb
        // Troll Stronghold wall-climb
        // Arceuus essence mine western descent
        // Lava Dragon Isle jump
        // Island crossing near Zul-Andra
        // Shilo Village stepping stones over the river
        // Kharazi Jungle vine climb
        // Cave crossing south of Dorgesh-Kaan
        // Taverley Dungeon spiked blades jump
        // Fremennik Slayer Dungeon chasm jump
        // Lava Maze northern jump
        // Brimhaven Dungeon eastern stepping stones
        // Elven Overpass (Arandar) advanced cliffside scramble
        // Kalphite Lair wall shortcut
        // Brimhaven Dungeon vine to baby green dragons


        ObjectAction.register(34397, 1324, 3778, 0, "climb", ClimbingRocks::climb);
        ObjectAction.register(34397, 1324, 3784, 0, "climb", ClimbingRocks::climb);
        ObjectAction.register(34396, 1324, 3788, 0, "climb",  ClimbingRocks::climb);
        ObjectAction.register(34396, 1324, 3794, 0, "climb",  ClimbingRocks::climb);

    }
}
