package com.mycompany.tvseries;

import java.util.ArrayList;
import java.util.Scanner;

public class TVSeries {

    // --- Inner class for series ---
    static class SeriesModel {
        public String SeriesId;
        public String SeriesName;
        public String SeriesAge;
        public String SeriesNumberOfEpisodes;
        public String NextEpisodeDate;
        public String NextSeasonDate;

        private ArrayList<Integer> ratings = new ArrayList<>();
        private ArrayList<String> reviewReasons = new ArrayList<>();

        // Add review
        public void addReview(int rating, String reason) {
            ratings.add(rating);
            reviewReasons.add(reason);
        }

        // Get average rating
        public double getAverageRating() {
            if (ratings.isEmpty()) return 0.0;
            int total = 0;
            for (int r : ratings) total += r;
            return (double) total / ratings.size();
        }

        public ArrayList<Integer> getRatings() {
            return ratings;
        }

        public ArrayList<String> getReviews() {
            return reviewReasons;
        }
    }

    // --- Main Program ---
    private static ArrayList<SeriesModel> seriesList = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            System.out.println("\n=== Series Manager 2025 ===");
            System.out.println("1. Add New Series");
            System.out.println("2. Search Series");
            System.out.println("3. Update Age Restriction");
            System.out.println("4. Delete Series");
            System.out.println("5. Add Review");
            System.out.println("6. Show Report");
            System.out.println("7. Exit");
            System.out.print("Choose an option: ");

            String choice = scanner.nextLine();

            switch (choice) {
                case "1" -> addSeries();
                case "2" -> searchSeries();
                case "3" -> updateAgeRestriction();
                case "4" -> deleteSeries();
                case "5" -> addReview();
                case "6" -> showReport();
                case "7" -> {
                    System.out.println("Exiting...");
                    System.exit(0);
                }
                default -> System.out.println("Invalid choice. Try again.");
            }
        }
    }

    private static void addSeries() {
        System.out.print("Enter Series ID: ");
        String id = scanner.nextLine().trim();
        if (id.isEmpty()) return;

        for (SeriesModel s : seriesList) {
            if (s.SeriesId.equalsIgnoreCase(id)) {
                System.out.println("ERROR: Series ID already exists!");
                return;
            }
        }

        SeriesModel newSeries = new SeriesModel();
        newSeries.SeriesId = id;

        System.out.print("Enter Series Name: ");
        newSeries.SeriesName = scanner.nextLine();

        System.out.print("Enter Age Restriction (2-18): ");
        newSeries.SeriesAge = scanner.nextLine();

        System.out.print("Enter Number of Episodes: ");
        newSeries.SeriesNumberOfEpisodes = scanner.nextLine();

        System.out.print("Enter Next Episode Air Date (YYYY-MM-DD): ");
        newSeries.NextEpisodeDate = scanner.nextLine();

        System.out.print("Enter Next Season Release Date (YYYY-MM-DD): ");
        newSeries.NextSeasonDate = scanner.nextLine();

        seriesList.add(newSeries);
        System.out.println("Series added successfully!");
    }

    private static void searchSeries() {
        if (seriesList.isEmpty()) {
            System.out.println("No series available.");
            return;
        }

        System.out.print("Enter Series ID to search: ");
        String id = scanner.nextLine().trim();

        for (SeriesModel s : seriesList) {
            if (s.SeriesId.equalsIgnoreCase(id)) {
                System.out.println(formatDetails(s));
                return;
            }
        }
        System.out.println("Series not found.");
    }

    private static void updateAgeRestriction() {
        if (seriesList.isEmpty()) {
            System.out.println("No series available.");
            return;
        }

        System.out.print("Enter Series ID to update: ");
        String id = scanner.nextLine().trim();

        for (SeriesModel s : seriesList) {
            if (s.SeriesId.equalsIgnoreCase(id)) {
                System.out.print("Enter new Age Restriction (2-18): ");
                s.SeriesAge = scanner.nextLine();
                System.out.println("Age restriction updated!");
                return;
            }
        }
        System.out.println("Series not found.");
    }

    private static void deleteSeries() {
        if (seriesList.isEmpty()) {
            System.out.println("No series available.");
            return;
        }

        System.out.print("Enter Series ID to delete: ");
        String id = scanner.nextLine().trim();

        for (int i = 0; i < seriesList.size(); i++) {
            if (seriesList.get(i).SeriesId.equalsIgnoreCase(id)) {
                seriesList.remove(i);
                System.out.println("Series deleted successfully!");
                return;
            }
        }
        System.out.println("Series not found.");
    }

    private static void addReview() {
        if (seriesList.isEmpty()) {
            System.out.println("No series available.");
            return;
        }

        System.out.print("Enter Series ID to review: ");
        String id = scanner.nextLine().trim();

        for (SeriesModel s : seriesList) {
            if (s.SeriesId.equalsIgnoreCase(id)) {
                try {
                    System.out.print("Enter rating (1-5): ");
                    int rating = Integer.parseInt(scanner.nextLine());
                    if (rating < 1 || rating > 5) {
                        System.out.println("Rating must be between 1 and 5.");
                        return;
                    }

                    System.out.print("Why did you give this rating? ");
                    String reason = scanner.nextLine();

                    s.addReview(rating, reason);
                    System.out.println("Review added successfully!");
                } catch (NumberFormatException ex) {
                    System.out.println("Invalid rating. Please enter a number between 1 and 5.");
                }
                return;
            }
        }
        System.out.println("Series not found.");
    }

    private static void showReport() {
        if (seriesList.isEmpty()) {
            System.out.println("No series available.");
            return;
        }

        System.out.println("\n=== SERIES REPORT 2025 ===");
        for (SeriesModel s : seriesList) {
            System.out.println(formatDetails(s));
        }
    }

    private static String formatDetails(SeriesModel s) {
        StringBuilder sb = new StringBuilder();
        sb.append("ID: ").append(s.SeriesId).append("\n");
        sb.append("Name: ").append(s.SeriesName).append("\n");
        sb.append("Age Restriction: ").append(s.SeriesAge).append("\n");
        sb.append("Episodes: ").append(s.SeriesNumberOfEpisodes).append("\n");
        sb.append("Next Episode Air Date: ").append(s.NextEpisodeDate).append("\n");
        sb.append("Next Season Release Date: ").append(s.NextSeasonDate).append("\n");

        if (s.getAverageRating() > 0) {
            sb.append("Average Rating: ").append(String.format("%.1f", s.getAverageRating())).append(" stars\n");
            sb.append("Reviews:\n");
            for (int i = 0; i < s.getReviews().size(); i++) {
                sb.append(" - ").append(s.getRatings().get(i)).append(" stars: ").append(s.getReviews().get(i)).append("\n");
            }
        } else {
            sb.append("No reviews yet.\n");
        }

        sb.append("------------------------------\n");
        return sb.toString();
    }
}
