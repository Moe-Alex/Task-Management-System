/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Programmingpoe;

/**
 *
 * @author ST10453511_Moegammad_Alexander
 */

import java.util.Scanner;
import javax.swing.JOptionPane;

public class Login {
    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        String firstName;
        String lastName;
        String userID;
        String password;
        ProgramChecker user;

        boolean isUserIdValid = false;
        boolean isUserPasswordValid = false;

        System.out.println("Enter your First Name: ");
        firstName = input.nextLine();

        System.out.println("Enter your Last Name: ");
        lastName = input.nextLine();

        System.out.println("Enter a userID that is no more than 5 characters long and contains an underscore: ");
        userID = input.nextLine();
        while(!isUserIdValid) {
            user = new ProgramChecker(userID, "");
            isUserIdValid = user.checkUserID();
            if(!isUserIdValid) {
                System.out.println("userID is not correctly formatted, please ensure that your userID contains an underscore and is no more than 5 characters in length.");
                System.out.println("Re-enter userID");
                userID = input.nextLine();
            }
        }

        System.out.println("Enter a password that is at least 8 characters long, contains a capital letter, a special character and a number: ");
        password = input.nextLine();
        while(!isUserPasswordValid) {
            user = new ProgramChecker(userID, password);
            isUserPasswordValid = user.checkPasswordComplexity();
            if(!isUserPasswordValid) {
                System.out.println("password is not correctly formatted, please ensure that the password contains at least 8 characters, a capital letter, a number and a special character.");
                System.out.println("Re-enter password");
                password = input.nextLine();
            }
        }

        System.out.println(firstName);
        System.out.println(lastName);
        System.out.println(userID);
        System.out.println(password);

        user = new ProgramChecker(userID, password);
        String registrationStatus = user.registerUser();
        System.out.println(registrationStatus);

        System.out.println("Enter your userID to log in: ");
        String loginUserID = input.nextLine();

        boolean loginStatus = false;
        while(!loginStatus) {
            System.out.println("Enter your password to log in: ");
            String loginPassword = input.nextLine();

            loginStatus = user.loginUser(loginUserID, loginPassword);
            if (loginStatus) {
                System.out.println("Welcome " + firstName + " " + lastName + ". It's good to see you!");
            } else {
                System.out.println("userID or password incorrect, please try again.");
                System.out.println("Enter your userID to log in: ");
                loginUserID = input.nextLine();
            }
        }
        
        //Start the Task class immediately after the Login class is completed
        Tasks afterLogin = new Tasks();
        afterLogin.startTasks();
        afterLogin.addTasksMenu();
    }
}
