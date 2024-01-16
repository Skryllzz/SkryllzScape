package io.ruin.model.item.actions.impl;

import io.ruin.api.utils.NumberUtils;
import io.ruin.api.utils.StringUtils;
import io.ruin.cache.Color;
import io.ruin.model.inter.Interface;
import io.ruin.model.inter.InterfaceHandler;
import io.ruin.model.inter.InterfaceType;
import io.ruin.model.inter.actions.SimpleAction;
import io.ruin.model.item.Item;
import io.ruin.model.item.actions.ItemAction;
import io.ruin.model.stat.Stat;
import io.ruin.model.stat.StatType;

public enum SkillLamp {

    ATTACK(3, StatType.Attack, true, false),
    STRENGTH(4, StatType.Strength, true, false),
    RANGED(5, StatType.Ranged, true, false),
    MAGIC(6, StatType.Magic, true, false),
    DEFENCE(7, StatType.Defence, true, false),
    HITPOINTS(8, StatType.Hitpoints, true, false),
    PRAYER(9, StatType.Prayer, true, false),
    AGILITY(10, StatType.Agility, false, false),
    HERBLORE(11, StatType.Herblore, false, false),
    THIEVING(12, StatType.Thieving, false, false),
    CRAFTING(13, StatType.Crafting, false, false),
    RUNECRAFTING(14, StatType.Runecrafting, false, false),
    SLAYER(22, StatType.Slayer, false, false),
    FARMING(23, StatType.Farming, false, false),
    MINING(15, StatType.Mining, false, false),
    SMITHING(16, StatType.Smithing, false, false),
    FISHING(17, StatType.Fishing, false, false),
    COOKING(18, StatType.Cooking, false, false),
    FIRE_MAKING(19, StatType.Firemaking, false, false),
    WOODCUTTING(20, StatType.Woodcutting, false, false),
    FLETCHING(21, StatType.Fletching, false, false),
    CONSTRUCTION(24, StatType.Construction, false, false),
    HUNTER(25, StatType.Hunter, false, false);

    private int childId;
    private StatType statType;
    private boolean combat;
    private boolean disabled;

    SkillLamp(int childId, StatType statType, boolean combat, boolean disabled) {
        this.childId = childId;
        this.statType = statType;
        this.combat = combat;
        this.disabled = disabled;
    }

    public static final int SKILL_CAMP = 2528;
    public static final int ANTIQUE_LAMP_ITEM_ID = 4447;

    static {
        ItemAction.registerInventory(SKILL_CAMP, "rub", (player, item) -> {
            player.openInterface(InterfaceType.MAIN, Interface.SKILL_LAMP);
            player.getPacketSender().sendSkillinterface("");
        });
        ItemAction.registerInventory(ANTIQUE_LAMP_ITEM_ID, "rub", (player, item) -> {
            player.openInterface(InterfaceType.MAIN, Interface.SKILL_LAMP);
            player.getPacketSender().sendSkillinterface("");
        });

        InterfaceHandler.register(Interface.SKILL_LAMP, h -> {
            for (SkillLamp skill : SkillLamp.values()) {
                h.actions[skill.childId] = (SimpleAction) player -> {
                    player.openInterface(InterfaceType.MAIN, Interface.SKILL_LAMP);
                    String skillName = StringUtils.getFormattedEnumName(skill);
                    if (skill.combat) {
                        player.sendMessage(Color.DARK_RED.wrap("You can't add experience to a combat stat!"));
                        return;
                    }
                    if (skill.disabled) {
                        player.sendMessage(Color.DARK_RED.wrap(skillName + " is currently disabled!"));
                        return;
                    }
                    player.sendMessage(Color.DARK_GREEN.wrap("You have selected the skill: " + skillName));
                    player.openInterface(InterfaceType.MAIN, Interface.SKILL_LAMP);
                    player.selectedSkillLampSkill = skill.statType;
                    player.getPacketSender().sendSkillinterface(skillName);
                };
            }
            h.actions[26] = (SimpleAction) player -> {
                Item skillCampLamp = player.getInventory().findItem(SKILL_CAMP);
                Item antiqueLamp = player.getInventory().findItem(ANTIQUE_LAMP_ITEM_ID);

                if (skillCampLamp == null && antiqueLamp == null) {
                    return;
                }

                StatType selectedStatType = StatType.valueOf(String.valueOf(player.selectedSkillLampSkill));
                Stat selectedStat = player.getStats().get(selectedStatType);
                int experience;

                if (antiqueLamp != null) {
                    if (selectedStat.currentLevel < 30) {
                        player.sendMessage(Color.DARK_RED.wrap("You must have a level of at least 30 in " + player.selectedSkillLampSkill.name() + " to use the antique lamp!"));
                        return;
                    }
                    experience = 2500; // Set the experience for the antique lamp
                    antiqueLamp.remove();
                } else {
                    experience = 10 * selectedStat.currentLevel; // Set the experience for the skill camp
                    skillCampLamp.remove();
                }

                int exprate = player.xpMode.getSkillRate();
                player.closeInterface(InterfaceType.MAIN);
                player.getStats().addXp(player.selectedSkillLampSkill, experience, true);
                player.sendMessage(Color.DARK_GREEN.wrap("You have been rewarded " + NumberUtils.formatNumber((long) experience * exprate) + " " + player.selectedSkillLampSkill.name() + " experience."));
            };
        });

    }
}
