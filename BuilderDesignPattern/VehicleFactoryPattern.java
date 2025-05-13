/**
 * VehicleFactoryPattern.java
 *
 * This file demonstrates the Factory Design Pattern using a Vehicle example.
 *
 * ----------------------------------------------------------
 * 🏗️ FACTORY DESIGN PATTERN - OVERVIEW
 * ----------------------------------------------------------
 * ➤ DEFINITION:
 *    The Factory Design Pattern provides a way to delegate the instantiation logic 
 *    to a separate component (a "factory") instead of calling constructors directly.
 *
 * ➤ USE CASES:
 *    - When object creation logic is complex or involves conditions.
 *    - When the code needs to return objects of a superclass or interface, but
 *      the actual object type is determined at runtime.
 *    - When you want to decouple the client code from concrete implementations.
 *
 * ➤ ADVANTAGES:
 *    - Promotes loose coupling.
 *    - Simplifies object creation logic.
 *    - Easier to add new types without modifying client code.
 *
 * ----------------------------------------------------------
 * This example includes:
 *   - An abstract class `Vehicle` (the product interface).
 *   - Two concrete implementations: `Car` and `Truck`.
 *   - A `VehicleFactory` that creates objects based on input.
 *   - A `main` method that demonstrates how to use the factory.
 */

public class VehicleFactoryPattern {

    // -----------------------------
    // 🔧 Abstract Product: Vehicle
    // -----------------------------
    abstract static class Vehicle {
        public abstract String getType();
        public abstract int getHorsePower();

        @Override
        public String toString() {
            return "Type: " + getType() + ", Horsepower: " + getHorsePower();
        }
    }

    // ---------------------------------------
    // 🚗 Concrete Product: Car
    // ---------------------------------------
    static class Car extends Vehicle {
        private int horsepower;

        public Car(int horsepower) {
            this.horsepower = horsepower;
        }

        @Override
        public String getType() {
            return "Car";
        }

        @Override
        public int getHorsePower() {
            return horsepower;
        }
    }

    // ---------------------------------------
    // 🚛 Concrete Product: Truck
    // ---------------------------------------
    static class Truck extends Vehicle {
        private int horsepower;

        public Truck(int horsepower) {
            this.horsepower = horsepower;
        }

        @Override
        public String getType() {
            return "Truck";
        }

        @Override
        public int getHorsePower() {
            return horsepower;
        }
    }

    // ---------------------------------------------
    // 🏭 Factory Class: VehicleFactory
    // ---------------------------------------------
    static class VehicleFactory {
        /**
         * Factory method to return a Vehicle object based on input type.
         *
         * @param type        Type of vehicle ("car" or "truck")
         * @param horsepower  Horsepower of the vehicle
         * @return Vehicle object (Car or Truck), or null if type is invalid
         */
        public static Vehicle getVehicle(String type, int horsepower) {
            if ("car".equalsIgnoreCase(type)) {
                return new Car(horsepower);
            } else if ("truck".equalsIgnoreCase(type)) {
                return new Truck(horsepower);
            }
            return null;
        }
    }

    // ---------------------------------------------
    // 🎬 Main Method: Demonstrates usage
    // ---------------------------------------------
    public static void main(String[] args) {
        // Create a Car using the factory
        Vehicle car = VehicleFactory.getVehicle("car", 150);

        // Create a Truck using the factory
        Vehicle truck = VehicleFactory.getVehicle("truck", 400);

        // Output the details
        System.out.println("Vehicle 1: " + car);
        System.out.println("Vehicle 2: " + truck);
    }
}
