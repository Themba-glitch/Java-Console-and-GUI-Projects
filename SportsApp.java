package com.mycompany.sportsapp;

import java.util.*;

class Team {
    String name;
    int played, won, drawn, lost, goalsFor, goalsAgainst, points;

    public Team(String name) {
        this.name = name;
    }

    public void updateStats(int goalsScored, int goalsConceded) {
        played++;
        goalsFor += goalsScored;
        goalsAgainst += goalsConceded;

        if (goalsScored > goalsConceded) {
            won++;
            points += 3;
        } else if (goalsScored == goalsConceded) {
            drawn++;
            points += 1;
        } else {
            lost++;
        }
    }

    @Override
    public String toString() {
        return String.format("%-12s %2d %2d %2d %2d %3d %3d %3d",
                name, played, won, drawn, lost, goalsFor, goalsAgainst, points);
    }
}

public class SportsApp {
    static Scanner sc = new Scanner(System.in);
    static Map<String, Team> teams = new HashMap<>();

    public static void main(String[] args) {
        while (true) {
            System.out.println("\n=== Adam Updates Console App ===");
            System.out.println("1. Add Team");
            System.out.println("2. Add Match Result");
            System.out.println("3. View League Table");
            System.out.println("4. Exit");
            System.out.print("Choose an option: ");

            int choice = sc.nextInt();
            sc.nextLine(); // consume newline

            switch (choice) {
                case 1 -> addTeam();
                case 2 -> addMatchResult();
                case 3 -> showTable();
                case 4 -> {
                    System.out.println("Exiting... Goodbye!");
                    return;
                }
                default -> System.out.println("Invalid choice, try again!");
            }
        }
    }

    static void addTeam() {
        System.out.print("Enter team name: ");
        String name = sc.nextLine();
        if (teams.containsKey(name)) {
            System.out.println("Team already exists!");
        } else {
            teams.put(name, new Team(name));
            System.out.println("Team added successfully!");
        }
    }

    static void addMatchResult() {
        System.out.print("Enter home team: ");
        String home = sc.nextLine();
        System.out.print("Enter away team: ");
        String away = sc.nextLine();

        if (!teams.containsKey(home) || !teams.containsKey(away)) {
            System.out.println("One or both teams not found!");
            return;
        }

        System.out.print("Enter home goals: ");
        int homeGoals = sc.nextInt();
        System.out.print("Enter away goals: ");
        int awayGoals = sc.nextInt();
        sc.nextLine(); // consume newline

        teams.get(home).updateStats(homeGoals, awayGoals);
        teams.get(away).updateStats(awayGoals, homeGoals);

        System.out.println("Result added successfully!");
    }

    static void showTable() {
        System.out.println("\n=== League Table ===");
        System.out.printf("%-12s %2s %2s %2s %2s %3s %3s %3s\n",
                "Team", "P", "W", "D", "L", "GF", "GA", "Pts");

        teams.values().stream()
                .sorted(Comparator.comparingInt((Team t) -> t.points).reversed())
                .forEach(System.out::println);
    }
}
