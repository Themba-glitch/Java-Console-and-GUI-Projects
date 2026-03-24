package com.mycompany.mzanzirides;

public class MzanziRides {

    // Base Class: Person
    static class Person {
        protected String name;
        protected int age;

        public Person(String name, int age) {
            this.name = name;
            this.age = age;
        }

        public void displayInfo() {
            System.out.println("Name: " + name);
            System.out.println("Age: " + age);
        }

        public void walk() {
            System.out.println(name + " is walking.");
        }
    }

    // Subclass: Driver
    static class Driver extends Person {
        private String licenseType;

        public Driver(String name, int age, String licenseType) {
            super(name, age);
            this.licenseType = licenseType;
        }

        @Override
        public void displayInfo() {
            super.displayInfo();
            System.out.println("License Type: " + licenseType);
        }
    }

    // Subclass: Rider
    static class Rider extends Person {
        private double fee;

        public Rider(String name, int age, double fee) {
            super(name, age);
            this.fee = fee;
        }

        @Override
        public void displayInfo() {
            super.displayInfo();
            System.out.println("Rider Fee: R" + fee);
        }
    }

    // Main method to test the classes
    public static void main(String[] args) {
        Person person = new Person("Lebo", 22);
        Driver driver = new Driver("Sipho", 35, "Code 10");
        Rider rider = new Rider("Thando", 27, 50.0);

        System.out.println("--- Person Info ---");
        person.displayInfo();
        person.walk();

        System.out.println("\n--- Driver Info ---");
        driver.displayInfo();
        driver.walk();

        System.out.println("\n--- Rider Info ---");
        rider.displayInfo();
        rider.walk();
    }
}
