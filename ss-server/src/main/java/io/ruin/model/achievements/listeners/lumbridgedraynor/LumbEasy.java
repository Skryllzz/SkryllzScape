package io.ruin.model.achievements.listeners.lumbridgedraynor;

import io.ruin.model.achievements.Achievement;
import io.ruin.model.achievements.AchievementListener;
import io.ruin.model.achievements.AchievementStage;
import io.ruin.model.entity.player.Player;
import io.ruin.model.inter.dialogue.MessageDialogue;

import java.util.HashMap;
import java.util.Map;

public class LumbEasy implements AchievementListener {

    private static final int REQUIRED_TASKS = 12; // Number of tasks required

    private static Map<Integer, Boolean> taskCompletionMap; // Map to track task completion

    public LumbEasy(Player player) {
        taskCompletionMap = initializeTaskCompletionMap(player);
    }

    @Override
    public String name() {
        return "1: Easy";
    }

    @Override
    public AchievementStage stage(Player player) {
        if (player.LumbEasyComplete) {
            return AchievementStage.FINISHED;
        } else if (atLeastOneTaskCompleted(player)) {
            return AchievementStage.IN_PROGRESS;
        } else {
            return AchievementStage.NOT_STARTED;
        }
    }

    @Override
    public String[] lines(Player player, boolean finished) {
        String[] lines = new String[REQUIRED_TASKS];

        String[] taskTexts = {
                "Complete a lap of the Draynor Village Rooftop Course.",
                "Slay a cave bug in the Lumbridge Swamp Caves.",
                "Have Archmage Sedridor teleport you to the Rune essence mine.",
                "Craft some water runes from Essence..",
                "Learn your age from Hans in Lumbridge.",
                "Pickpocket a man or woman in Lumbridge.",
                "Chop and burn some oak logs in Lumbridge.",
                "Kill a zombie in the Draynor Sewers.",
                "Catch some anchovies in Al-Kharid.",
                "Bake some bread on the Lumbridge castle kitchen range.",
                "Mine some iron ore at the Al-Kharid mine.",
                "Enter the H.A.M. Hideout."
        };

        for (int i = 0; i < REQUIRED_TASKS; i++) {
            String taskText = taskTexts[i]; // Get the text for the current task
            String taskIdentifier = "task_" + (i + 1); // Unique identifier for each task

            boolean isTaskCompleted = isTaskCompleted(player, i + 1);
            if (isTaskCompleted) {
                taskText = Achievement.slashIf(taskText, true);
            } else if (player.lumbeasytask[i] != null) {
                taskText = Achievement.slashIf(taskText, true);
            } else if (finished) {
                taskText = "In progress";
            }

            lines[i] = taskText;
        }

        return lines;
    }

    @Override
    public void started(Player player) {
        // Nothing to do here
    }

    @Override
    public void finished(Player player) {
        // Nothing to do here
    }

    // Method to initialize the task completion map
    private Map<Integer, Boolean> initializeTaskCompletionMap(Player player) {
        Map<Integer, Boolean> map = new HashMap<>();
        for (int i = 1; i <= REQUIRED_TASKS; i++) {
            if (player != null && player.lumbeasytask[i - 1] != null) {
                map.put(i, true);
            } else {
                map.put(i, false);
            }
        }
        return map;
    }

    // Method to mark a task as completed
    public static void completeTask(Player player, int taskId) {
        if (taskCompletionMap.containsKey(taskId) && !taskCompletionMap.get(taskId)) {
            taskCompletionMap.put(taskId, true);
            player.lumbeasytask[taskId - 1] = new LumbEasy(player);
        }
    }

    // Method to check if at least one task is completed
    private boolean atLeastOneTaskCompleted(Player player) {
        for (boolean isTaskCompleted : taskCompletionMap.values()) {
            if (isTaskCompleted) {
                return true;
            }
        }
        for (LumbEasy task : player.lumbeasytask) {
            if (task != null) {
                return true;
            }
        }
        for (int i = 0; i < player.lumbeasytask.length; i++) {
            if (taskCompletionMap.containsKey(i + 1) && taskCompletionMap.get(i + 1)) {
                return true;
            }
        }
        return false;
    }

    // Method to mark all tasks as completed
    private static void complete(Player player) {
        // Perform any necessary actions when all tasks are completed
        // For example, you can grant rewards or update player data
        if (!player.LumbEasyComplete) {
            player.LumbEasyComplete = true;
            Achievement.LUMBRIDGE_EASY_DIARY.update(player);
            player.dialogue(new MessageDialogue("Congratulations! You have completed all of the easy tasks in the Lumbridge area. Speak to Hatius Cosaintus outside of the Lumbridge castle to claim your reward."));
        }
    }

    // Method to check if all tasks are completed
    private static boolean allTasksCompleted() {
        for (boolean isTaskCompleted : taskCompletionMap.values()) {
            if (!isTaskCompleted) {
                return false;
            }
        }
        return true;
    }

    // Method to check if a specific task is completed
    /**public static boolean isTaskCompleted(Player player, int taskId) {
     return taskCompletionMap.get(taskId);
     }**/
    public static boolean isTaskCompleted(Player player, int taskId) {
        boolean isCompleted = taskCompletionMap.get(taskId);
        if (!isCompleted) {
            if (player.lumbeasytask[taskId - 1] != null) {
                completeTask(player, taskId);
                isCompleted = true;
            }
        }
        if (allTasksCompleted()) {
            complete(player);
        }
        return isCompleted;
    }
}