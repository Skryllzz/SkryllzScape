package io.ruin.model.map.object.actions.impl;

import io.ruin.Server;
import io.ruin.cache.ItemID;
import io.ruin.cache.ObjectDef;
import io.ruin.model.achievements.listeners.ardougne.ArdyEasy;
import io.ruin.model.content.QuestReqs;
import io.ruin.model.entity.player.Player;
import io.ruin.model.entity.shared.StepType;
import io.ruin.model.inter.dialogue.MessageDialogue;
import io.ruin.model.inter.dialogue.NPCDialogue;
import io.ruin.model.map.Tile;
import io.ruin.model.map.object.GameObject;
import io.ruin.model.map.object.actions.ObjectAction;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

// ~~~ DIRECTIONS ~~~ \\
// 0 = east, 1 = south \\
// 2 = west, 3 = north \\
// ~~~~~~~~~~~~~~~~~~~~ \\
public class Door {

    public static void handle(Player player, GameObject obj) {
        handle(player, obj, false);
    }

    public static void handle(Player player, GameObject obj, boolean skipJammedCheck) {
        ObjectDef def = obj.getDef();
        if (def.id == 2144 || def.id == 2143) {
            if (player.getAbsX() <= 2888) {
                player.getMovement().teleport(2889, player.getAbsY());
                return;
            }
            if (player.getAbsX() >= 2889) {
                player.getMovement().teleport(2888, player.getAbsY());
                return;
            }
            player.sendMessage("Unhandled door, report this to a staff member! ID: "+def.id);
        }
        if (def.id == 15056) { //Father Urhney House
            if (player.getAbsY() == 3172) {
                player.getMovement().teleport(player.getAbsX(), 3173);
                return;
            }
            if (player.getAbsY() == 3173) {
                player.getMovement().teleport(player.getAbsX(), 3172);
                return;
            }
            player.sendMessage("Unhandled door, report this to a staff member! ID: "+def.id);
        }
        if (def.id == 3444) { //Gate Under Paterdomus
            if (player.getAbsX() == 3405 && player.getAbsY() == 9895) {
                player.getMovement().teleport(player.getAbsX(), 9894);
                return;
            }
            if (player.getAbsX() == 3405 && player.getAbsY() == 9894) {
                player.getMovement().teleport(player.getAbsX(), 9895);
                return;
            }
            player.sendMessage("Unhandled door, report this to a staff member! ID: "+def.id);
        }
        if (def.id == 3445) { //Gate Near Drezel
            if (player.getAbsX() == 3431 && player.getAbsY() == 9897) {
                player.getMovement().teleport(3432, player.getAbsY());
                return;
            }
            if (player.getAbsX() == 3432 && player.getAbsY() == 9897) {
                player.getMovement().teleport(3431, player.getAbsY());
                return;
            }
            player.sendMessage("Unhandled door, report this to a staff member! ID: "+def.id);
        }
        if (def.id == 4787 || def.id == 4788) { //Ape Atoll Gate
            if (player.getAbsX() >= 2720 && player.getAbsY() == 2765) {
                player.getMovement().teleport(player.getAbsX(), 2767);
                return;
            }
            if (player.getAbsX() >= 2720 && player.getAbsY() == 2767) {
                player.getMovement().teleport(player.getAbsX(), 2765);
                return;
            }
            player.sendMessage("Unhandled door, report this to a staff member! ID: "+def.id);
        }
        if (def.id == 1967 || def.id == 1968) { //Grand Tree Doors
            if (player.isAt(2465, 3491) || player.isAt(2466, 3491)) {
                player.getMovement().teleport(player.getAbsX(), player.getAbsY() + 2);
                return;
            }
            if (player.isAt(2465, 3493) || player.isAt(2466, 3493)) {
                player.getMovement().teleport(player.getAbsX(), player.getAbsY() - 2);
                return;
            }
            player.sendMessage("Unhandled door, report this to a staff member! ID: "+def.id);
        }
        if (def.id == 190) { //Tree Gnome Stronghold
            if (player.isAt(2461, 3385)) {
                player.getMovement().teleport(player.getAbsX(), player.getAbsY() - 3);
                return;
            }
            if (player.isAt(2461, 3382)) {
                player.getMovement().teleport(player.getAbsX(), player.getAbsY() + 3);
                return;
            }
            player.sendMessage("You need to stand in the center in front of the door.");
        }
        if (def.id == 1805 && obj.x == 3191 && obj.y == 3363) { //Champions Guild
            if (player.getStats().totalLevel >= 400 ) {
                if (player.getAbsX() <= 3194 && player.getAbsY() == 3363) {
                    player.stepAbs(3191, 3362, StepType.FORCE_WALK);
                    player.dialogue(new NPCDialogue(814, "Greetings bold adventurer. Welcome to the guild of Champion"));
                    return;
                }
                if (player.getAbsX() == 3191 && player.getAbsY() == 3362) {
                    player.stepAbs(3191, 3363, StepType.FORCE_WALK);
                    return;
                }
            } else {
                player.dialogue(new NPCDialogue(814, "You are not worthy of entering the guild of a Champion."), new MessageDialogue("You need a total level of at least 400 to enter."));
                return;
            }
        }
        if (def.id == 2406 && obj.x == 3202 && obj.y == 3169) { //Lumby Swamp Zannris Entrance
            if (player.getEquipment().hasId(ItemID.DRAMEN_STAFF) && player.getAbsX() == 3201) {
                player.startEvent(event -> {
                    player.stepAbs(3202, 3169, StepType.FORCE_WALK);
                    player.graphics(569);
                    event.delay(3);
                    player.getMovement().teleport(2452, 4473, 0);
                });
            } else {
                if (player.getAbsX() == 3201) {
                    player.stepAbs(3202, 3169, StepType.FORCE_WALK);
                    return;
                } else if (player.getAbsX() == 3202) {
                    player.stepAbs(3201, 3169, StepType.FORCE_WALK);
                    return;
                }
            }
        }
        if ((def.id == 2624 && obj.x == 2902 && obj.y == 3510 || (def.id == 2625 && obj.x == 2902 && obj.y == 3511))) { //Heros Guild entrance
            if (QuestReqs.HerosQuest(player)) {
                if (player.getAbsX() > 2901) {
                    player.stepAbs(player.getAbsX() - 1, player.getAbsY(), StepType.FORCE_WALK);
                    return;
                } else if (player.getAbsX() == 2901) {
                    player.stepAbs(player.getAbsX() + 1, player.getAbsY(), StepType.FORCE_WALK);
                    return;
                }
            }
        }
        if (player.getAbsX() == 3098 && player.getAbsY() == 3107 && def.id == 9398) {
            player.sendMessage("The " + (def.gateType ? "gates" : "doors") + " seem to be stuck.");
            return;
        }
        if (player.getAbsX() == 3097 && player.getAbsY() == 3107 && def.id == 9398) { //Tutorial Island
            //new NPCDialogue(3308, "You cannot go out that way. Speak to me if you wish to leave.");
            player.dialogue(new NPCDialogue(3308, "You cannot go out that way. Speak to me if you wish to leave.") {});
            return;
        }
        if (def.doorOppositeId == -1) {
            player.sendMessage("The " + (def.gateType ? "gate" : "door") + " won't seem to budge.");
            return;
        }
//        if (obj.conOwner != -1 && obj.conOwner != player.getUserId()) {
//            player.sendMessage("Only the host can " + (def.doorClosed ? "open" : "close") + " these doors.");
//            return;
//        }
        int dir = obj.direction;
        if (def.doorReversed)
            dir = (dir + 2) & 0x3;
        GameObject pairObj = null;
        boolean verticalFlip = def.verticalFlip;
        if (def.reversedConstructionDoor)
            verticalFlip = !verticalFlip;
        if (def.doorClosed) {
            if (dir == 3) { //North
                if (verticalFlip)
                    pairObj = findPair(obj, 1, 0);
                else
                    pairObj = findPair(obj, -1, 0);
            } else if (dir == 1) { //South
                if (verticalFlip)
                    pairObj = findPair(obj, -1, 0);
                else
                    pairObj = findPair(obj, 1, 0);
            } else if (dir == 0) { //East
                if (verticalFlip)
                    pairObj = findPair(obj, 0, -1);
                else
                    pairObj = findPair(obj, 0, 1);
            } else if (dir == 2) { //West
                if (verticalFlip)
                    pairObj = findPair(obj, 0, 1);
                else
                    pairObj = findPair(obj, 0, -1);
            }
        } else {
            if (def.longGate) {
                if (dir == 3) { //North
                    if (def.verticalFlip)
                        pairObj = findPair(obj, 1, 0);
                    else
                        pairObj = findPair(obj, -1, 0);
                } else if (dir == 1) { //South
                    if (def.verticalFlip)
                        pairObj = findPair(obj, -1, 0);
                    else
                        pairObj = findPair(obj, 1, 0);
                } else if (dir == 0) { //East
                    if (def.verticalFlip)
                        pairObj = findPair(obj, 0, -1);
                    else
                        pairObj = findPair(obj, 0, 1);
                } else if (dir == 2) { //West
                    if (def.verticalFlip)
                        pairObj = findPair(obj, 0, 1);
                    else
                        pairObj = findPair(obj, 0, -1);
                }
            } else {
                if (dir == 3) { //North
                    if (def.doorReversed)
                        pairObj = findPair(obj, 0, -1);
                    else
                        pairObj = findPair(obj, 0, 1);
                } else if (dir == 1) { //South
                    if (def.doorReversed)
                        pairObj = findPair(obj, 0, 1);
                    else
                        pairObj = findPair(obj, 0, -1);
                } else if (dir == 0) { //East
                    if (def.doorReversed)
                        pairObj = findPair(obj, -1, 0);
                    else
                        pairObj = findPair(obj, 1, 0);
                } else if (dir == 2) { //West
                    if (def.doorReversed)
                        pairObj = findPair(obj, 1, 0);
                    else
                        pairObj = findPair(obj, -1, 0);
                }
            }
        }
        if (pairObj != null) {
            if (!def.doorClosed) {
                if (!skipJammedCheck && isJammed(player, def.verticalFlip ? pairObj : obj)) {
                    player.sendMessage("The " + (def.gateType ? "gates" : "doors") + " seem to be stuck.");
                    return;
                }
                if (def.doorCloseSound != -1)
                    player.privateSound(def.doorCloseSound);
            } else {
                if (def.doorOpenSound != -1)
                    player.privateSound(def.doorOpenSound);
            }
            changeState(obj, !def.reversedConstructionDoor);
            changeState(pairObj, !pairObj.getDef().reversedConstructionDoor);
        } else {
            if (!def.doorClosed) {
                if (!skipJammedCheck && isJammed(player, obj)) {
                    player.sendMessage("The " + (def.gateType ? "gate" : "door") + " seems to be stuck.");
                    return;
                }
                if (def.doorCloseSound != -1)
                    player.privateSound(def.doorCloseSound);
            } else {
                if (def.doorOpenSound != -1)
                    player.privateSound(def.doorOpenSound);
            }
            if (obj.type == 9) {
                int playerX = player.getAbsX();
                int playerY = player.getAbsY();

                int objectX = obj.x;
                int objectY = obj.y;
                int doorDirection = obj.direction;

                if (!def.doorClosed) {
                    if (doorDirection == 0 && playerX > objectX && playerY == objectY) {
                        walkDiagonal(player, obj, playerX + 1, playerY);
                        return;
                    } else if (doorDirection == 2 && playerX < objectX && playerY == objectY) {
                        walkDiagonal(player, obj, playerX - 1, playerY);
                        return;
                    } else if (doorDirection == 1 && playerY < objectY) {
                        walkDiagonal(player, obj, playerX + 1, objectY - 2);
                        return;
                    } else if (doorDirection == 3 && playerY > objectY) {
                        walkDiagonal(player, obj, playerX - 1, objectY + 2);
                        return;
                    }
                } else {
                    if (doorDirection == 0 && playerX == objectX && playerY > objectY) {
                        walkDiagonal(player, obj, objectX - 1, objectY + 1);
                        return;
                    } else if (doorDirection == 1 && playerX > objectX && playerY == objectY) {
                        walkDiagonal(player, obj, objectX, objectY + 1);
                        return;
                    } else if (doorDirection == 2 && playerX == objectX && playerY < objectY) {
                        walkDiagonal(player, obj, objectX + 1, objectY - 1);
                        return;
                    } else if (doorDirection == 3 && playerX < objectX && playerY == objectY) {
                        walkDiagonal(player, obj, playerX + 1, playerY - 1);
                        return;
                    }
                }
            }

            changeState(obj, false);
        }
    }

    private static void walkDiagonal(Player player, GameObject door, int x, int y) {
        player.startEvent(event -> {
            player.stepAbs(x, y, StepType.FORCE_WALK);
            event.waitForMovement(player);
            changeState(door, false);
            player.face(door);
        });
    }

    private static GameObject findPair(GameObject obj, int offsetX, int offsetY) {
        Tile tile = Tile.get(obj.x + offsetX, obj.y + offsetY, obj.z, false);
        if (tile == null || tile.gameObjects == null)
            return null;
        int size = tile.gameObjects.size();
        if (size == 0)
            return null;
        for (int i = (size - 1); i >= 0; i--) { //keep backwards loop
            GameObject pairedObj = tile.gameObjects.get(i);
            if (pairedObj.id != -1 && pairedObj.type == obj.type && pairedObj.getDef().doorOppositeId != -1)
                return pairedObj;
        }
        return null;
    }

    private static boolean isJammed(Player pCloser, GameObject obj) {
        if (obj.doorReplaced != null)
            obj = obj.doorReplaced;
        if (Server.isPast(obj.doorJamEnd)) {
            obj.doorCloses = 0;
            obj.doorJamEnd = Server.getEnd(pCloser.wildernessLevel == 0 ? 500 : 50);
        }
        return ++obj.doorCloses >= 5;
    }

    public static void changeState(GameObject obj, boolean paired) {
        if (obj.doorReplaced != null) {
            if (obj.doorReplaced.id == -1) {
                obj.remove();
                int prev = obj.doorReplaced.get("DOOR_ORIG_ID", -1);
                if (prev != -1) {
                    obj.doorReplaced.setId(prev);
                    obj.remove("DOOR_ORIG_ID");
                } else {
                    obj.doorReplaced.restore();
                }
            } else {
                Tile tile = obj.tile;
                tile.removeObject(obj);
                for (Player player : tile.region.players)
                    obj.doorReplaced.sendCreate(player);
            }
            obj.doorReplaced = null;
            return;
        }
        ObjectDef def = obj.getDef();
        int dir = obj.direction;
        int diffX = 0, diffY = 0, diffDir = 0;
        if (paired) {
            /**
             * Double
             */
            if (def.longGate) {
                if (def.doorClosed) {
                    if (def.verticalFlip) {
                        diffDir--;

                        if (dir == 0) {
                            diffX -= 2;
                            diffY--;
                        } else if (dir == 1) {
                            diffX--;
                            diffY += 2;
                        } else if (dir == 2) {
                            diffY++;
                            diffX += 2;
                        } else if (dir == 3) {
                            diffX++;
                            diffY -= 2;
                        }
                    } else {
                        diffDir--;
                        if (dir == 0) {
                            diffX--;
                        } else if (dir == 1) {
                            diffY++;
                        } else if (dir == 2) {
                            diffX++;
                        } else if (dir == 3) {
                            diffY--;
                        }
                    }
                } else {
                    if (def.verticalFlip)
                        diffDir--;
                    else
                        diffDir++;
                    if (dir == 0) {
                        if (def.verticalFlip)
                            diffY--;
                        else
                            diffY++;
                    } else if (dir == 1) {
                        if (def.verticalFlip)
                            diffX++;
                        else
                            diffX--;
                    } else if (dir == 2) {
                        if (def.verticalFlip)
                            diffY++;
                        else
                            diffY--;
                    } else if (dir == 3) {
                        if (def.verticalFlip)
                            diffX--;
                        else
                            diffX++;
                    }
                }
            } else if (def.doorClosed) {
                if (def.verticalFlip)
                    diffDir++;
                else
                    diffDir--;
                if (dir == 0)
                    diffX--;
                else if (dir == 1)
                    diffY++;
                else if (dir == 2)
                    diffX++;
                else if (dir == 3)
                    diffY--;
            } else {
                if (def.verticalFlip)
                    diffDir--;
                else
                    diffDir++;
                if (dir == 0) {
                    if (def.verticalFlip)
                        diffY--;
                    else
                        diffY++;
                } else if (dir == 1) {
                    if (def.verticalFlip)
                        diffX++;
                    else
                        diffX--;
                } else if (dir == 2) {
                    if (def.verticalFlip)
                        diffY++;
                    else
                        diffY--;
                } else if (dir == 3) {
                    if (def.verticalFlip)
                        diffX--;
                    else
                        diffX++;
                }
            }
        } else if (obj.type == 9) {
            /**
             * Single diagonal
             */
            if (def.doorClosed) {
                if (def.verticalFlip) {
                    diffDir--;
                } else {
                    diffDir++;
                    if (dir == 0)
                        diffY++;
                    if (dir == 1)
                        diffX++;
                    if (dir == 2)
                        diffY--;
                    if (dir == 3)
                        diffX--;
                }
            } else {
                if (def.verticalFlip) {
                    diffDir++;
                } else {
                    diffDir--;
                    if(dir == 0) {
                        diffX++;
                    }
                    if (dir == 2) {
                        diffX--;
                    }
                }
            }
        } else {
            /**
             * Single regular
             */
            if (def.doorClosed) {
                if (def.verticalFlip)
                    diffDir--;
                else
                    diffDir++;
                if (dir == 0)
                    diffX--;
                else if (dir == 1)
                    diffY++;
                else if (dir == 2)
                    diffX++;
                else if (dir == 3)
                    diffY--;
            } else {
                if (def.verticalFlip)
                    diffDir++;
                else
                    diffDir--;
                if (dir == 0)
                    diffY++;
                else if (dir == 1)
                    diffX++;
                else if (dir == 2)
                    diffY--;
                else if (dir == 3)
                    diffX--;
            }
        }
        if (obj.conOwner == -1 && def.doorReversed != ObjectDef.get(def.doorOppositeId).doorReversed) {
            diffX = diffY = 0;
            diffDir += 2;
        } else if (def.doorReversed) {
            diffDir += 2;
        }
        if (diffX == 0 && diffY == 0)
            obj.clip(true);
        else {
            if (obj.id != obj.originalId)
                obj.set("DOOR_ORIG_ID", obj.id);
            obj.remove();
        }
        GameObject replacement = GameObject.spawn(def.doorOppositeId, obj.x + diffX, obj.y + diffY, obj.z, obj.type, (dir + diffDir) & 0x3);
        replacement.doorReplaced = obj;
    }

    static {
        /**
         * This array is used to manually override opposite ids.
         * Example: Sometimes an open door will use a different model id from it's closed version, causing it not to pair.
         */
        int[][] oppositeOverrideIds = {
                {24060, 24061}, //Double doors on the top of the Falador Castle.
                {24062, 24063}, //Double doors on the top of the Falador Castle.
                {13314, 13315}, // oak cage door
                {13317, 13318}, // oak and steel cage door
                {13320, 13321}, // steel cage door
                {13323, 13324}, // spiked cage door
                {13326, 13327}, // bone cage door
                {13344, 13350}, {13345, 13351}, // oak dungeon door
                {13346, 13352}, {13347, 13353}, // steel dungeon door
                {13348, 13354}, {13349, 13355}, // marble dungeon door
                {9038, 9039}, //Karamja teak tree entrance
                {1511, 1511}, {1513, 1513},
                {58, 59}
        };
        /**
         * These objects face 180 degrees different than others.
         * Example: An object in this list with a direction of 0 (East) will look as if it's facing direction 2 (West)
         */
        int[] reversedIds = {
                24060, 24062,   //Double doors on the top of the Falador Castle.
                22435, 22437,   //Double (closed) doors (Not sure what island these are on..)
                22436, 22438,   //Double (opened) doors (Not sure what island these are on..)
                14233, 14235,   //Double (closed) gates in Pest Control.
                14234, 14236,   //Double (opened) gates in Pest Control.
                13314, 13315, // oak cage door
                13317, 13318, // oak and steel cage door
                13320, 13321, // steel cage door
                13323, 13324, // spiked cage door
                13006, 13007, 13008, 13009, // whitewashed stone style doors
//                13344, 13350, 13345, 13351, // oak dungeon door

                /* construction doors (deathly mansion doors not reversed!) */
//                HouseStyle.BASIC_WOOD.doorId1, HouseStyle.BASIC_WOOD.doorId2,
//                HouseStyle.BASIC_STONE.doorId1, HouseStyle.BASIC_STONE.doorId2,
//                HouseStyle.WHITEWASHED_STONE.doorId1, HouseStyle.WHITEWASHED_STONE.doorId2,
//                HouseStyle.FREMENNIK_WOOD.doorId1, HouseStyle.FREMENNIK_WOOD.doorId2,
//                HouseStyle.TROPICAL_WOOD.doorId1, HouseStyle.TROPICAL_WOOD.doorId2,
//                HouseStyle.FANCY_STONE.doorId1, HouseStyle.FANCY_STONE.doorId2,
        };

        int[] reversedConstructionDoors = {
                13345, 13351, // oak dungeon door
                13347, 13353, // steel dungeon door
                13349, 13355, // marble dungeon door
        };
        /**
         * Registering
         */
        ObjectDef.forEach(def -> {
            if (def.id >= 26502 && def.id <= 26505) // gwd doors
                return;
            if (def.id == 34553 || def.id == 34554) // alchemical hydra doors
                return;
            String name = def.name.toLowerCase();
            if (name.contains("gate")) {
                def.gateType = true;
                if (def.modelIds[0] == 7371 || (def.modelIds[0] == 966 && def.modelIds[1] == 967 && def.modelIds[2] == 968)) {
                    def.longGate = true;
                    setSound(def, 67, 66);
                } else {
                    setSound(def, 69, 68);
                }
            } else if (!name.contains("door")) {
                return;
            } else {
                setSound(def, 62, 60);
            }

            if(def.id == 12657)
                def.doorOppositeId = 12658;
            else if (def.id == 12658)
                def.doorOppositeId = 12657;
            else
                def.doorOppositeId = findOppositeId(def);

            for (int[] opp : oppositeOverrideIds) {
                int id1 = opp[0];
                int id2 = opp[1];
                if (def.id == id1) {
                    def.doorOppositeId = id2;
                    break;
                }
                if (def.id == id2) {
                    def.doorOppositeId = id1;
                    break;
                }
            }
            for (int reversedId : reversedIds) {
                if (def.id == reversedId) {
                    def.doorReversed = true;
                    break;
                }
            }
            for (int reversedId : reversedConstructionDoors) {
                if (def.id == reversedId) {
                    def.reversedConstructionDoor = true;
                    break;
                }
            }
            if (def.doorClosed = def.hasOption("open"))
                ObjectAction.register(def.id, "open", Door::handle);
            else
                ObjectAction.register(def.id, "close", Door::handle);
        });
        ObjectAction.register(12657, 2319, 3690, 0, "open", (player, obj) -> player.sendFilteredMessage("The door seems to be stuck."));
        ObjectAction.register(2039, 2517, 3356, 0, "open", (player, obj) -> {
            if (!ArdyEasy.isTaskCompleted(player, 5)) {
                ArdyEasy.completeTask(player, 5);
                player.sendMessage("<col=800000>Well done! You have completed an easy task in the Ardougne area. Your Achievement Diary has been updated.</col>");
            }
            Door.handle(player, obj);
        });
        ObjectAction.register(2041, 2518, 3356, 0, "open", (player, obj) -> {
            if (!ArdyEasy.isTaskCompleted(player, 5)) {
                ArdyEasy.completeTask(player, 5);
                player.sendMessage("<col=800000>Well done! You have completed an easy task in the Ardougne area. Your Achievement Diary has been updated.</col>");
            }
            Door.handle(player, obj);
        });
    }

    private static void setSound(ObjectDef def, int open, int close) {
        if (def.hasOption("open"))
            def.doorOpenSound = open;
        else
            def.doorCloseSound = close;
    }

    private static int findOppositeId(ObjectDef originalDef) {
        if (originalDef.getOption("open", "close") == -1)
            return -1;
        ArrayList<Integer> ids = new ArrayList<>();
        defs:
        for (ObjectDef def : ObjectDef.LOADED.values()) {
            if (def == null || def.id == originalDef.id)
                continue;
            if (!Objects.equals(def.name, originalDef.name))
                continue;
            if (def.render0x2 != originalDef.render0x2)
                continue;
            if (!Arrays.equals(def.modelIds, originalDef.modelIds))
                continue;
            if (!Arrays.equals(def.modelTypes, originalDef.modelTypes))
                continue;
            if (!Arrays.equals(def.modifiedModelColors, originalDef.modifiedModelColors))
                continue;
            if (def.verticalFlip != originalDef.verticalFlip)
                continue;
            if (Arrays.equals(def.options, originalDef.options))
                continue;
            for (int i = 0; i < def.options.length; i++) {
                String s1 = def.options[i];
                String s2 = originalDef.options[i];
                if (!Objects.equals(s1, s2)) {
                    if ("open".equalsIgnoreCase(s1) && "close".equalsIgnoreCase(s2))
                        continue;
                    if ("close".equalsIgnoreCase(s1) && "open".equalsIgnoreCase(s2))
                        continue;
                    continue defs;
                }
            }
            ids.add(def.id);
        }
        if (!ids.isEmpty()) {
            ids.sort((i1, i2) -> {
                int diff1 = Math.abs(i1 - originalDef.id);
                int diff2 = Math.abs(i2 - originalDef.id);
                return Integer.compare(diff1, diff2);
            });
            return ids.get(0);
        }
        return -1;
    }

}
