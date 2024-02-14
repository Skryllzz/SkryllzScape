package io.ruin.model.achievements;

import io.ruin.cache.Color;
import io.ruin.model.achievements.listeners.ardougne.ArdyEasy;
import io.ruin.model.achievements.listeners.ardougne.ArdyElite;
import io.ruin.model.achievements.listeners.ardougne.ArdyHard;
import io.ruin.model.achievements.listeners.ardougne.ArdyMed;
import io.ruin.model.achievements.listeners.lumbridgedraynor.LumbEasy;
import io.ruin.model.entity.player.Player;
import io.ruin.model.entity.shared.listeners.LoginListener;
import io.ruin.model.inter.dialogue.MessageDialogue;
import io.ruin.model.inter.journal.Journal;
import io.ruin.model.inter.journal.JournalEntry;

public enum Achievement {

    /** Ardouge Diary **/

    ARDY_EASY_DIARY(new ArdyEasy(null), AchievementCategory.Ardougne),
    ARDY_MED_DIARY(new ArdyMed(null), AchievementCategory.Ardougne),
    ARDY_HARD_DIARY(new ArdyHard(null), AchievementCategory.Ardougne),
    ARDY_ELITE_DIARY(new ArdyElite(null), AchievementCategory.Ardougne),

//    /** Desert Diary **/
//
//    DESERT_EASY_DIARY(new DesertEasy(null), AchievementCategory.Desert),
//    DESERT_MED_DIARY(new DesertMed(null), AchievementCategory.Desert),
//    DESERT_HARD_DIARY(new DesertHard(), AchievementCategory.Desert),
//    DESERT_ELITE_DIARY(new DesertElite(), AchievementCategory.Desert),
//
//    /** Falador Diary **/
//
//    FALADOR_EASY_DIARY(new FaladorEasy(null), AchievementCategory.Falador),
//    FALADOR_MED_DIARY(new FaladorMed(null), AchievementCategory.Falador),
//    FALADOR_HARD_DIARY(new FaladorHard(), AchievementCategory.Falador),
//    FALADOR_ELITE_DIARY(new FaladorElite(), AchievementCategory.Falador),
//
//    /** Fremennik Diary **/
//
//    FREMENNIK_EASY_DIARY(new FremmenikEasy(null), AchievementCategory.Fremmenik),
//    FREMENNIK_MED_DIARY(new FremmenikMed(null), AchievementCategory.Fremmenik),
//    FREMENNIK_HARD_DIARY(new FremmenikHard(), AchievementCategory.Fremmenik),
//    FREMENNIK_ELITE_DIARY(new FremmenikElite(), AchievementCategory.Fremmenik),
//
//    /** Kandarin Diary **/
//
//    KANDARIN_EASY_DIARY(new KandarinEasy(null), AchievementCategory.Kandarin),
//    KANDARIN_MED_DIARY(new KandarinMed(null), AchievementCategory.Kandarin),
//    KANDARIN_HARD_DIARY(new KandarinHard(), AchievementCategory.Kandarin),
//    KANDARIN_ELITE_DIARY(new KandarinElite(), AchievementCategory.Kandarin),
//
//    /** Karamja Diary **/
//
//    KARAMJA_EASY_DIARY(new KaramjaEasy(null), AchievementCategory.Karamja),
//    KARAMJA_MED_DIARY(new KaramjaMed(null), AchievementCategory.Karamja),
//    KARAMJA_HARD_DIARY(new KaramjaHard(), AchievementCategory.Karamja),
//    KARAMJA_ELITE_DIARY(new KaramjaElite(), AchievementCategory.Karamja),
//
//    /** Kourend & Kebos Diary **/
//
//    KOUREND_EASY_DIARY(new KourendEasy(null), AchievementCategory.Kourend),
//    KOUREND_MED_DIARY(new KourendMed(null), AchievementCategory.Kourend),
//    KOUREND_HARD_DIARY(new KourendHard(), AchievementCategory.Kourend),
//    KOUREND_ELITE_DIARY(new KourendElite(), AchievementCategory.Kourend),
//
    /** Lumbridge & Draynor Diary **/

    LUMBRIDGE_EASY_DIARY(new LumbEasy(null), AchievementCategory.Lumbridge);
    //LUMBRIDGE_MED_DIARY(new LumbridgeMed(null), AchievementCategory.Lumbridge),
    //LUMBRIDGE_HARD_DIARY(new LumbridgeHard(), AchievementCategory.Lumbridge),
    //LUMBRIDGE_ELITE_DIARY(new LumbridgeElite(), AchievementCategory.Lumbridge);
//
//    /** Morytania Diary **/
//
//    MORYTANIA_EASY_DIARY(new MorytaniaEasy(null), AchievementCategory.Morytania),
//    MORYTANIA_MED_DIARY(new MorytaniaMed(null), AchievementCategory.Morytania),
//    MORYTANIA_HARD_DIARY(new MorytaniaHard(), AchievementCategory.Morytania),
//    MORYTANIA_ELITE_DIARY(new MorytaniaElite(), AchievementCategory.Morytania),
//
//    /** Varrock Diary **/
//
//    VARROCK_EASY_DIARY(new VarrockEasy(null), AchievementCategory.Varrock),
//    VARROCK_MED_DIARY(new VarrockMed(null), AchievementCategory.Varrock),
//    VARROCK_HARD_DIARY(new VarrockHard(), AchievementCategory.Varrock),
//    VARROCK_ELITE_DIARY(new VarrockElite(), AchievementCategory.Varrock),
//
//    /** Western Provinces Diary **/
//
//    WESTPROV_EASY_DIARY(new WestProvEasy(null), AchievementCategory.WestProv),
//    WESTPROV_MED_DIARY(new WestProvMed(null), AchievementCategory.WestProv),
//    WESTPROV_HARD_DIARY(new WestProvHard(), AchievementCategory.WestProv),
//    WESTPROV_ELITE_DIARY(new WestProvElite(), AchievementCategory.WestProv),
//
//    /** Wilderness Diary **/
//
//    WILD_EASY_DIARY(new WildernessEasy(null), AchievementCategory.Wilderness),
//    WILD_MED_DIARY(new WildernessMed(null), AchievementCategory.Wilderness),
//    WILD_HARD_DIARY(new WildernessHard(), AchievementCategory.Wilderness),
//    WILD_ELITE_DIARY(new WildernessElite(), AchievementCategory.Wilderness);

    private final AchievementListener listener;
    private final AchievementCategory category;

    private JournalEntry entry;

    Achievement(AchievementListener listener, AchievementCategory category) {
        this.listener = listener;
        this.category = category;
    }

    public void update(Player player) {
        if(entry == null) {
            //never displayed on this world
            return;
        }
        AchievementStage oldStage = player.achievementStages[ordinal()];
        entry.send(player);
        AchievementStage newStage = player.achievementStages[ordinal()];
        if(newStage != oldStage) {
            if(newStage == AchievementStage.STARTED) {
                player.sendMessage("<col=000080>You have started the achievement: <col=800000>" + getListener().name());
                getListener().started(player);
            } else if (newStage == AchievementStage.IN_PROGRESS) {
                getListener().started(player);
            } else if(newStage == AchievementStage.FINISHED) {
                player.dialogue(new MessageDialogue("Congratulations! You have completed all of the easy tasks in the Ardougne area. Speak to Two-pints at the Flying Horse Inn in Ardougne to claim your reward."));
                getListener().finished(player);
            }
        }
    }

    public JournalEntry toEntry() {
        return entry = new JournalEntry() {
            @Override
            public void send(Player player) {
                AchievementStage stage = player.achievementStages[ordinal()] = getListener().stage(player);
                if (player.journal != Journal.ACHIEVEMENTS) {
                    return;
                }
                if(stage == AchievementStage.FINISHED)
                    send(player, getListener().name(), Color.GREEN);
                else if(stage == AchievementStage.IN_PROGRESS)
                    send(player, getListener().name(), Color.YELLOW);
                else
                    send(player, getListener().name(), Color.RED);
            }

            @Override
            public void asend(Player player) {
                AchievementStage stage = player.achievementStages[ordinal()] = getListener().stage(player);
                if (player.journal != Journal.ACHIEVEMENTS) {
                    return;
                }
                if(stage == AchievementStage.FINISHED)
                    send(player, getListener().name(), Color.GREEN);
                else if(stage == AchievementStage.IN_PROGRESS)
                    send(player, getListener().name(), Color.YELLOW);
                else
                    send(player, getListener().name(), Color.RED);
            }

            @Override
            public void select(Player player) {
                player.sendScroll("<col=800000>" + getListener().name(), getListener().lines(player, isFinished(player)));
            }
        };
    }

    public boolean isStarted(Player player) {
        return player.achievementStages[ordinal()] != AchievementStage.NOT_STARTED;
    }

    public boolean isFinished(Player player) {
        return player.achievementStages[ordinal()] == AchievementStage.FINISHED;
    }

    /**
     * Misc
     */

    public static String slashIf(String string, boolean slash) {
        return slash ? ("<str>" + string + "</str>") : string;
    }


    public AchievementListener getListener() {
        return listener;
    }

    public AchievementCategory getCategory() {
        return category;
    }

    public static AchievementStage counterStage(int current, int start, int finish) {
        if (current >= finish)
            return AchievementStage.FINISHED;
        else if (current <= start)
            return AchievementStage.NOT_STARTED;
        return AchievementStage.STARTED;
    }

    public static void staticInit(){
        for (Achievement achievement : values()) {
            if(achievement.listener != null) achievement.listener.init();
        }
    }
    static {
        LoginListener.register(player -> {
            for (Achievement achievement : values()) {
                player.achievementStages[achievement.ordinal()] = achievement.getListener().stage(player); // forces achievements to have the correct state on login
            }
        });
    }
}