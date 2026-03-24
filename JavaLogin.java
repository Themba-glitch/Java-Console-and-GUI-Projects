/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
import java.util.Scanner;
 
 
/**
 *
 * @author RC_Student_lab
 */
public class JavaLogin {

  /**
 *
 * @author RC_Student_lab
     * @param args
 */


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("=== Welcome to the Registration Page ===");

        // Registration
        System.out.print("Register a username: ");
        String registeredUsername = scanner.nextLine();

        System.out.print("Register a password: ");
        String registeredPassword = scanner.nextLine();

        System.out.println("\nRegistration successful!");
        System.out.println("Please log in to continue");

        // Login
        int attempts = 0;
        int maxAttempts = 3;
        boolean isLoggedIn = false;

        while (attempts < maxAttempts && !isLoggedIn) {
            System.out.print("Enter Username: ");
            String username = scanner.nextLine();

            System.out.print("Enter Password: ");
            String password = scanner.nextLine();

            if (username.equals(registeredUsername) && password.equals(registeredPassword)) {
                System.out.println("Login Successful! Welcome, " + username + "!");
                isLoggedIn = true;
            } else {
                attempts++;
                int remaining = maxAttempts - attempts;
                System.out.println("Incorrect username or password. Attempts remaining: " + remaining);
            }
        }

        if (!isLoggedIn) {
            System.out.println("Too many failed attempts. Account Locked.");
        }

        scanner.close();
    }
}


    
      

