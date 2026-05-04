/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Programmingpoe;

/**
 *
 * @author ST10453511_Moegammad_Alexander
 */
public class ProgramChecker {

    private String userID;
    private String password;

    public ProgramChecker(String userID, String password) {
        this.userID = userID;
        this.password = password;
    }

    // Method to check if the userID is correctly formatted
    public boolean checkUserID() {
        return userID.length() <= 5 && userID.contains("_");
    }

    // Method to check if the password meets the complexity requirements
    public boolean checkPasswordComplexity() {
        return password.length() >= 8 && password.matches(".*[A-Z].*") && password.matches(".*[!@#$%^&*].*") && password.matches(".*\\d.*");
    }

    // Method to register a user and return the registration status
    public String registerUser() {
        if (!checkUserID()) {
            return "userID is not correctly formatted, please ensure that your userID contains an underscore and is no more than 5 characters in length.";
        } else if (!checkPasswordComplexity()) {
            return "password is not correctly formatted, please ensure that the password contains at least 8 characters, a capital letter, a number and a special character.";
        } else {
            return "User registered successfully.";
        }
    }

    // Method to check if the login details are correct
    public boolean loginUser(String loginUserID, String loginPassword) {
        return userID.equals(loginUserID) && password.equals(loginPassword);
    }
}
