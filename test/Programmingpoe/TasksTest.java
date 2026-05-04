/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package Programmingpoe;

import Programmingpoe.Tasks;
import java.util.Collections;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * 
 * author Moegammad Yaaseen
 */
public class TasksTest {

    public Tasks t1 = new Tasks();

    @Before
    public void testStartTasks() {
        Collections.addAll(t1.developers, "Mike Smith", "Edward Harrison", "Samantha Paulson", "Glenda Oberholzer");
        Collections.addAll(t1.taskNames, "Create Login", "Create Add Features", "Create Reports", "Add Arrays");
        Collections.addAll(t1.taskIDs, "CR:1:ITH", "CR:2:SON", "CR:3:SON", "AD:4:ZER");
        Collections.addAll(t1.taskDurations, 5, 2, 8, 11);
        Collections.addAll(t1.taskStatuses, "To Do", "Doing", "Done");
    }

    @Test
    public void testAddTasks() {

    }

    /*@Test
    public void testCheckTaskDescription() {
        Tasks testing = new Tasks();
        boolean expected1 = true;
        boolean actual1;
        String taskDescriptionTest1 = "Create login to authenticate...";
        actual1 = testing.checkTaskDescription(taskDescriptionTest1);
        assertEquals(expected1, actual1);
        boolean expected2 = true;
        boolean actua2;
        String taskDescriptionTest2 = "Create login to add task users...";
        actua2 = testing.checkTaskDescription(taskDescriptionTest2);
        assertEquals(expected2, actua2);
    }*/

    @org.junit.Test
    public void testPrintTaskDetails() {
    }

    /*@org.junit.Test
    public void testReturnTotalHours() {
        Tasks test = new Tasks();
        test.taskDurations.add(5);
        test.taskDurations.add(2);
        test.taskDurations.add(8);
        test.taskDurations.add(11);
        int totalHours = test.totalHours();
        assertEquals(26, totalHours);
    }*/

    @org.junit.Test
    public void testShowReport() {
    }

    @org.junit.Test
    public void testShowTasksByStatus() {
        String expectedStatus = "Task name: Create Reports"
                + "\nDeveloper: Samantha Paulson"
                + "\nDuration: 8";
    }

    @Test
    public void testShowTaskWithLongestDuration() {
        String expectedMaxDuration = "Developer: Glenda Oberholzer" 
                                            + "\nDuration: 1"
                                             + " hours";
    }

    @org.junit.Test
    public void testSearchTaskByName() {
        String expectedTaskName = "Task Name: Create Logn"
                + "\nDeveloper: Mike Smith"
                + "\nTask Status: To Do";
    }

    @org.junit.Test
    public void testSearchTasksByDeveloper() {
        String expectedDN = "Developer: Samantha Paulson"
                + "\nTask Name: Create Reports"
                + "\nTask Status: Done";
        //assertEquals(expectedDN, t1.searchTaskByName());
        //System.out.println(t1.searchTaskByName());
    }

    @org.junit.Test
    public void testDeleteTask() {
        t1.deleteTaskByName();
        assertFalse(t1.taskNames.contains("Create Reports"));
        assertFalse(t1.developers.contains("Samantha Paulson"));
        assertFalse(t1.taskDurations.contains(8));
        assertFalse(t1.taskStatuses.contains("Done"));
    }

    @org.junit.Test
    public void testDisplayAllTasks() {
        String expectedDisplayOutput = "Task Name: Create Login" 
                + "\nDeveloper: Mike Smith"
                + "\nTask ID: CR:1:ITH" 
                + "\nDuration: 5" 
                + "\nStatus: To Do";
        String expectedDisplayOutput2 = "Task Name: Create Add Features" 
                    + "\nDeveloper: Edward Harrison"
                    + "\nTask ID: CR:2:SON" 
                    + "\nDuration: 2" 
                    + "\nStatus: Doing ";
        String expectedDisplayOutput3 = "Task Name: Create Reports"
                + "\nDeveloper: Samantha Paulson"
                + "\nTask ID: CR:3:SON" 
                + "\nDuration: 8" 
                + "\nStatus: Done";
        String expectedDisplayOutput4 = "Task Name: Add Arrays" 
                + "\nDeveloper: Glenda Oberholzer"
                + "\nTask ID: AD:4:ZER" 
                + "\nDuration: 11" 
                + "\nStatus: To Do";
        //assertEquals(expectedDisplayOutput, t1.displayAllTasks());
    }
}
