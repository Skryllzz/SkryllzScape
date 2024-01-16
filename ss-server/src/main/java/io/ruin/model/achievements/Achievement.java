package io.ruin.model.achievements;

import io.ruin.cache.Color;
import io.ruin.model.achievements.listeners.ardougne.ArdyEasy;
import io.ruin.model.achievements.listeners.ardougne.ArdyElite;
import io.ruin.model.achievements.listeners.ardougne.ArdyHard;
import io.ruin.model.achievements.listeners.ardougne.ArdyMed;
import io.ruin.model.entity.player.Player;
import io.ruin.model.entity.shared.listeners.LoginListener;
import io.ruin.model.inter.dialogue.MessageDialogue;
import io.ruin.model.inter.journal.Journal;
import io.ruin.model.inter.journal.JournalEntry;

public enum Achievement {

    /** Ardouge Diary **/

    ARDY_EASY_DIARY(new ArdyEasy(null), AchievementCategory.Ardougne),
    ARDY_MED_DIARY(new ArdyMed(null), AchievementCategory.Ardougne),
    ARDY_HARD_DIARY(new ArdyHard(), AchievementCategory.Ardougne),
    ARDY_ELITE_DIARY(new ArdyElite(), AchievementCategory.Ardougne);

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