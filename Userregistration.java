/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */


/**
 *
 * @author RC_Student_lab
 */
package com.mycompany.userregistration;

import java.util.Scanner;

public class Userregistration {

    // Validate South African phone number (must be 10 digits starting with 0)
    public static boolean isValidPhoneNumber(String phone) {
        return phone.matches("0\\d{9}");
    }

    // Validate password: 8-12 characters, 1 uppercase, 1 number
    public static boolean isValidPassword(String password) {
        return password.length() >= 8 &&
               password.length() <= 12 &&
               password.matches(".*[A-Z].*") &&
               password.matches(".*\\d.*");
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Collect personal info from the user
        System.out.print("Enter your name: ");
        String name = scanner.nextLine();

        System.out.print("Enter your surname: ");
        String surname = scanner.nextLine();

        System.out.print("Enter your ID number: ");
        String idNumber = scanner.nextLine();

        System.out.print("Enter your 10-digit South African phone number: ");
        String phoneNumber = scanner.nextLine();

        if (!isValidPhoneNumber(phoneNumber)) {
            System.out.println("Invalid phone number. It must be 10 digits and start with 0.");
            return;
        }

        // Create username and password with 3 attempts to enter correctly
        int attempts = 0;
        String username = "";
        String password = "";
        boolean success = false;

        while (attempts < 3 && !success) {
            System.out.print("Create a username: ");
            username = scanner.nextLine();

            System.out.print("Create a password (8-12 characters, at least 1 uppercase, 1 number): ");
            password = scanner.nextLine();

            if (isValidPassword(password)) {
                success = true;
            } else {
                System.out.println("Invalid password. Try again.");
                attempts++;
            }
        }

        // If registration is successful
        if (success) {
            System.out.println("\nRegistration Successful! Welcome " + username);
            System.out.println("Name: " + name);
            System.out.println("Surname: " + surname);
            System.out.println("ID: " + idNumber);
            System.out.println("Phone: " + phoneNumber);
            System.out.println("Username: " + username);
        } else {
            System.out.println("Maximum attempts reached. Registration failed.");
        }

        scanner.close();
    }
}
