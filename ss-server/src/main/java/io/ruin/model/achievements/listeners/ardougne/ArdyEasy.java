package io.ruin.model.achievements.listeners.ardougne;

import io.ruin.model.achievements.Achievement;
import io.ruin.model.achievements.AchievementListener;
import io.ruin.model.achievements.AchievementStage;
import io.ruin.model.entity.player.Player;
import io.ruin.model.inter.dialogue.MessageDialogue;

import java.util.HashMap;
import java.util.Map;

public class ArdyEasy implements AchievementListener {

    private static final int REQUIRED_TASKS = 8; // Number of tasks required

    private static Map<Integer, Boolean> taskCompletionMap; // Map to track task completion

    public ArdyEasy(Player player) {
        taskCompletionMap = initializeTaskCompletionMap(player);
    }

    @Override
    public String name() {
        return "1: Easy";
    }

    @Override
    public AchievementStage stage(Player player) {
        if (player.ArdyEasyComplete) {
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
                "Have Wizard Cromperty teleport you to the Rune essence mine.",
                "Steal a cake from the East Ardougne market stalls.",
                "Sell silk to the Silk trader in East Ardougne for 60 coins each.",
                "Use the altar in East Ardougne's church.",
                "Enter the Combat Training Camp north of West Ardougne.",
                "Have Tindel Marchant identify a rusty sword for you.",
                "Use the Ardougne lever to teleport to the Wilderness.",
                "View Aleck's Hunter Emporium in Yanille."
        };

        for (int i = 0; i < REQUIRED_TASKS; i++) {
            String taskText = taskTexts[i]; // Get the text for the current task
            String taskIdentifier = "task_" + (i + 1); // Unique identifier for each task

            boolean isTaskCompleted = isTaskCompleted(player, i + 1);
            if (isTaskCompleted) {
                taskText = Achievement.slashIf(taskText, true);
            } else if (player.ardyeasytask[i] != null) {
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
            if (player != null && player.ardyeasytask[i - 1] != null) {
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
            player.ardyeasytask[taskId - 1] = new ArdyEasy(player);
        }
    }

    // Method to check if at least one task is completed
    private boolean atLeastOneTaskCompleted(Player player) {
        for (boolean isTaskCompleted : taskCompletionMap.values()) {
            if (isTaskCompleted) {
                return true;
            }
        }
        for (ArdyEasy task : player.ardyeasytask) {
            if (task != null) {
                return true;
            }
        }
        for (int i = 0; i < player.ardyeasytask.length; i++) {
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
        if (!player.ArdyEasyComplete) {
            player.ArdyEasyComplete = true;
            Achievement.ARDY_EASY_DIARY.update(player);
            player.dialogue(new MessageDialogue("Congratulations! You have completed all of the easy tasks in the Ardougne area. Speak to Two-pints at the Flying Horse Inn in Ardougne to claim your reward."));
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
            if (player.ardyeasytask[taskId - 1] != null) {
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