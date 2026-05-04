/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Programmingpoe;

/**
 *
 * @author ST10453511_Moegammad_Alexander
 */
import javax.swing.JOptionPane;
import java.util.ArrayList;

public class Tasks {

    private int totalHours = 0;
    public ArrayList<String> developers = new ArrayList<>();
    public ArrayList<String> taskNames = new ArrayList<>();
    public ArrayList<String> taskIDs = new ArrayList<>();
    public ArrayList<Integer> taskDurations = new ArrayList<>();
    public ArrayList<String> taskStatuses = new ArrayList<>();

    //Displays the welcome message and the main menu to the user
    //Allows user to choose from an option of adding tasks, showing reports, and quitting the program
    public void startTasks() {
        JOptionPane.showMessageDialog(null, "Welcome to EasyKanban");

        boolean isRunning = true;

        while (isRunning) {
            // Displays the menu and captures the user's choice
            String input = JOptionPane.showInputDialog("Choose an option:\n" +
                    "1. Add tasks\n" +
                    "2. Show report\n" +
                    "3. Quit");

            int choice = 0;

            try {
                choice = Integer.parseInt(input);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Invalid input. Returning to menu.");
            }

            switch (choice) {
                case 1:
                    addTasksMenu();
                    break;
                case 2:
                    showReport();
                    break;
                case 3:
                    JOptionPane.showMessageDialog(null, "Exiting...");
                    System.exit(0); //Ends the program immediately
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Invalid Option");
                    break;
            }
        }
    }

    //Allows the user to enter the number of tasks that they want to add and calls the createTask() method from the TaskManager class to create and capture each task
    //Updates the total hours and adds the task details to the respective list
    public void addTasksMenu() {

        String input = JOptionPane.showInputDialog("How many tasks do you want to add?");
        int numberOfTasks;

        try {
            numberOfTasks = Integer.parseInt(input);
        }  catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Invalid number. Returning to menu.");
            return;
        }

        TaskManager taskManager = new TaskManager(numberOfTasks);

        for (int i = 0; i < numberOfTasks; i++) {
            Task task = taskManager.createTask();
            if (task != null) {
                JOptionPane.showMessageDialog(null, "Task successfully captured.\n" + task.printTaskDetails());
                totalHours += task.getTaskDuration();
                developers.add(task.getDeveloperDetails());
                taskNames.add(task.getTaskName());
                taskIDs.add(task.getTaskID());
                taskDurations.add(task.getTaskDuration());
                taskStatuses.add(task.getTaskStatus());
            }
        }

        JOptionPane.showMessageDialog(null, "Total hours: " + totalHours);
    }

    //Displays the report option menu and executes the selected option by user
    private void showReport() {
        String[] reportOptions = {
            "Display tasks with the status 'Done'",
            "Display the task with the longest duration",
            "Search for a task by entering the task name",
            "Search for all the tasks assigned to a developer",
            "Delete a task by entering the task name",
            "Display a full report of all the captured tasks"
        };

        String message = "Select a Report Option by entering the corresponding number:\n";
        for (int i = 0; i < reportOptions.length; i++) {
            message += (i + 1) + ". " + reportOptions[i] + "\n";
        }

        boolean isValidOption = false;
        while (!isValidOption) {
            String reportChoice = JOptionPane.showInputDialog(null, message, "Task Report", JOptionPane.PLAIN_MESSAGE);

            if (reportChoice != null) {
                int choice = Integer.parseInt(reportChoice);

                switch (choice) {
                    case 1:
                        displayTasksWithStatusDone();
                        isValidOption = true;
                        break;
                    case 2:
                        displayTaskWithLongestDuration();
                        isValidOption = true;
                        break;
                    case 3:
                        searchTaskByName();
                        isValidOption = true;
                        break;
                    case 4:
                        searchTasksByDeveloper();
                        isValidOption = true;
                        break;
                    case 5:
                        deleteTaskByName();
                        isValidOption = true;
                        break;
                    case 6:
                        displayFullTaskReport();
                        isValidOption = true;
                        break;
                    default:
                        JOptionPane.showMessageDialog(null, "Invalid Option");
                        break;
                }
            } else {
                isValidOption = true; //Returns to Menu 1 if cancel is pressed
            }
        }
    }

    //Shows the developer, task name, and task duration for each task with the status 'Done'
    private void displayTasksWithStatusDone() {
        StringBuilder report = new StringBuilder("Tasks with the status 'Done':\n");

        for (int i = 0; i < taskStatuses.size(); i++) {
            if (taskStatuses.get(i).equals("Done")) {
                report.append("Developer: ").append(developers.get(i)).append(", ");
                report.append("Task Name: ").append(taskNames.get(i)).append(", ");
                report.append("Task Duration: ").append(taskDurations.get(i)).append(" hours\n");
            }
        }

        JOptionPane.showMessageDialog(null, report.toString());
        //Calls the showReport() method to return to the report menu
        showReport();
    }

    //Shows the developer and task duration for the task with the longest duration
    private void displayTaskWithLongestDuration() {
        int maxDuration = 0;
        int maxDurationIndex = -1;

        for (int i = 0; i < taskDurations.size(); i++) {
            if (taskDurations.get(i) > maxDuration) {
                maxDuration = taskDurations.get(i);
                maxDurationIndex = i;
            }
        }

        if (maxDurationIndex != -1) {
            JOptionPane.showMessageDialog(null, "Task with longest duration:\n" +
                    "Developer: " + developers.get(maxDurationIndex) + ", Task Duration: " + maxDuration + " hours\n");
        } else {
            JOptionPane.showMessageDialog(null, "No tasks found");
        }
        //Calls the showReport() method to return to the report menu
        showReport();
    }

    //Displays the task name, developer, and task status for the matching task
    public String searchTaskByName() {
        String searchName;

        while (true) {
            searchName = JOptionPane.showInputDialog("Enter the task name to search:");

            for (int i = 0; i < taskNames.size(); i++) {
                if (taskNames.get(i).equals(searchName)) {
                    String taskDetails = "Task Name: " + taskNames.get(i) + ", Developer: " + developers.get(i) + ", Task Status: " + taskStatuses.get(i);
                    JOptionPane.showMessageDialog(null, taskDetails);
                    showReport(); // Return to Menu 2
                    return taskDetails;
                }
            }

            JOptionPane.showMessageDialog(null, "Task does not exist, enter a valid task name to continue");
        }
    }

    //Displays the task name and task status for each task assigned to the developer
    private void searchTasksByDeveloper() {

        String searchDeveloper = JOptionPane.showInputDialog("Enter the developer's name to search:");

        boolean found = false;

        while (!found) {
            StringBuilder report = new StringBuilder("Tasks that are assigned to " + searchDeveloper + ":\n");

            boolean developerExists = false;
            for (int i = 0; i < developers.size(); i++) {
                if (developers.get(i).equals(searchDeveloper)) {

                    report.append("Task Name: ").append(taskNames.get(i)).append(", ");
                    report.append("Task Status: ").append(taskStatuses.get(i)).append("\n");
                    developerExists = true;

                }
            }

            if (!developerExists) {
                JOptionPane.showMessageDialog(null, "Developer does not exist, enter a valid developer name to continue");
                searchDeveloper = JOptionPane.showInputDialog("Enter the developer's name to search:");
            } else {
                JOptionPane.showMessageDialog(null, report.toString());
                found = true;
            }
        }
        //Calls the showReport() method to return to the report menu
        showReport();

    }

    //Removes the task details from the respective lists
    public void deleteTaskByName() {
        String deleteName = JOptionPane.showInputDialog("Enter task name that you want to delete:");

        int deleteIndex = -1;

        for (int i = 0; i < taskNames.size(); i++) {
            if (taskNames.get(i).equals(deleteName)) {
                deleteIndex = i;
                break;
            }
        }

        if (deleteIndex != -1) {
            developers.remove(deleteIndex);
            taskNames.remove(deleteIndex);
            taskIDs.remove(deleteIndex);
            taskDurations.remove(deleteIndex);
            taskStatuses.remove(deleteIndex);

            JOptionPane.showMessageDialog(null, "Task '" + deleteName + "' deleted successfully");
        } else {
            JOptionPane.showMessageDialog(null, "Task name does not exist, enter a valid task name to continue");
            deleteTaskByName(); //calls on method to allow user to re-enter
        }
        //Calls the showReport() method to return to the report menu
        showReport();
    }

    //Shows the task name, developer, task ID, task duration, and task status for each task
    private void displayFullTaskReport() {
        StringBuilder report = new StringBuilder("Full Task Report:\n");

        for (int i = 0; i < taskNames.size(); i++) {
            report.append("Task Name: ").append(taskNames.get(i)).append(", ");
            report.append("Developer: ").append(developers.get(i)).append(", ");
            report.append("Task ID: ").append(taskIDs.get(i)).append(", ");
            report.append("Task Duration: ").append(taskDurations.get(i)).append(" hours, ");
            report.append("Task Status: ").append(taskStatuses.get(i)).append("\n");
        }

        JOptionPane.showMessageDialog(null, report.toString());
        //Calls the showReport() method to return to the report menu
        showReport();
    }

    //Main method that creates a task class and starts the task management system
    public static void main(String[] args) {
        Tasks tasks = new Tasks();
        tasks.startTasks();
    }
}

//Task class that shows a task with a name, description, developer details, duration, ID, status
//It provides methods to check the task description, create a task ID, print task details, and get task information
//The task count is static and shared among all tasks
class Task {

    private String taskName;
    private static int taskCount = 0;
    private String taskDescription;
    private String developerDetails;
    private int taskDuration;
    private String taskID;
    private String taskStatus;
    private int numberOfTasks;

    //Initializes a task object with the provided parameters
    //taskName = the name of the task, taskDescription = the description of the task, developerDetails = the details of the developer assigned to the task, taskDuration = the duration of the task in hours, taskStatus = the status of the task, numberOfTasks = the total number of tasks
    public Task(String taskName, String taskDescription, String developerDetails, int taskDuration, String taskStatus, int numberOfTasks) {
        this.taskName = taskName;
        this.taskDescription = taskDescription;
        this.developerDetails = developerDetails;
        this.taskDuration = taskDuration;
        this.taskID = createTaskID();
        this.taskStatus = taskStatus;
        this.numberOfTasks = numberOfTasks;
    }

    //Checks if task description is not more than 50 characters
    public boolean checkTaskDescription() {
        return taskDescription.length() <= 50;
    }

    //Creates and returns the task ID based on the task name, task count and developer details
    public String createTaskID() {
        taskCount++;
        String taskNamePrefix = taskName.substring(0, 2).toUpperCase();
        String developerNameSuffix = developerDetails.substring(developerDetails.length() - 3).toUpperCase();
        return taskNamePrefix + ":" + taskCount + ":" + developerNameSuffix;
    }

    //Returns a string that contains the full details of the task
    public String printTaskDetails() {
        return "Task Status: " + taskStatus + "\n" +
                "Developer Details: " + developerDetails + "\n" +
                "Task Number: " + (taskCount) + "\n" +
                "Task Name: " + taskName + "\n" +
                "Task Description: " + taskDescription + "\n" +
                "Task ID: " + taskID + "\n" +
                "Task Duration: " + taskDuration + " hours";
    }

    //Returns task duration
    public int getTaskDuration() {
        return taskDuration;
    }

    //Returns the developer details
    public String getDeveloperDetails() {
        return developerDetails;
    }

    //Returns the task name
    public String getTaskName() {
        return taskName;
    }

    //Returns the task ID
    public String getTaskID() {
        return taskID;
    }

    //Returns the task status
    public String getTaskStatus() {
        return taskStatus;
    }
}

//TaskManager class that manages the creation of tasks
class TaskManager {
    private int numberOfTasks;
    private int taskCount;

    //Initializes a TaskManager object with the provided nuber of tasks
    public TaskManager(int numberOfTasks) {
        this.numberOfTasks = numberOfTasks;
        this.taskCount = 0;
    }

    //Allows user to enter the details of a task and creates a new task object with the entered details
    public Task createTask() {

        String taskName = JOptionPane.showInputDialog("Enter the task name:");
        if (taskName == null) {
            return null; //Return null to go back to the main menu
        }

        String taskDescription;
        while (true) {
            taskDescription = JOptionPane.showInputDialog("Enter the description of the task (maximum 50 characters):");
            if (taskDescription == null) {
                return null; //Return null to go back to the main menu
            } else if (taskDescription.length() <= 50) {
                break;
            } else {
                JOptionPane.showMessageDialog(null, "Please enter a task description of less than 50 characters.");
            }
        }

        String input = JOptionPane.showInputDialog("Enter the duration of the task in hours:");
        if (input == null) {
            return null; //Return null to go back to the main menu
        }

        int taskDuration;

        try {
            taskDuration = Integer.parseInt(input);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Invalid number. Returning to menu.");
            return null; //Return null to go back to the main menu
        }

        String[] statusOptions = {"To Do",
                "Done",
                "Doing"};

        String taskStatus = (String) JOptionPane.showInputDialog(null, "Select the status of the task:", "Task Status",
                JOptionPane.QUESTION_MESSAGE, null, statusOptions, statusOptions[0]);

        String developerDetails = JOptionPane.showInputDialog("Enter the developer's First and Last name (e.g. Moegammad Alexander):");
        if (developerDetails == null) {
            return null; //Return null to go back to the main menu
        }

        return new Task(taskName, taskDescription, developerDetails, taskDuration, taskStatus, numberOfTasks);
    }
}