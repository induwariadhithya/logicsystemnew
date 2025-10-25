/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package logisticsystemnew;
import java.io.*;
import java.util.*;

/**
 *
 * @author INDUWARI ADITHYA
 */
public class LogisticSystemNew {
    
    // Commit 2: Define constants and arrays for cities and deliveries
    static final int MAX_CITIES = 30;
    static final int MAX_DELIVERIES = 50;
    static final double FUEL_PRICE = 310.0;
    
    // Arrays for storing city names and distance table
    static String[] cities = new String[MAX_CITIES];
    static int cityCount = 0;
    static int[][] distance = new int[MAX_CITIES][MAX_CITIES];
    
    // Vehicle details stored in arrays
    static String[] vehicleNames = {"Van", "Truck", "Lorry"};
    static int[] vehicleCapacity = {1000, 5000, 10000};
    static double[] ratePerKm = {30, 40, 80};
    static double[] avgSpeed = {60, 50, 45};
    static double[] efficiency = {12, 6, 4};
    
    // Arrays to record delivery information
    static String[] fromCity = new String[MAX_DELIVERIES];
    static String[] toCity = new String[MAX_DELIVERIES];
    
    
    static int deliveryCount = 0;
    static double totalRevenue = 0;
    static double totalProfit = 0;

    static Scanner sc = new Scanner(System.in);
   
    public static void main(String[] args) {
    
        
    }
    // method to Add a new city
    static void addCity(){
        if(cityCount >= MAX_CITIES){
            System.out.println("City list full!");
            return;
            
        }
        System.out.print("Enter city name: ");
        String name = sc.next();
        for (int i = 0; i < cityCount; i++) {
            if (cities[i].equalsIgnoreCase(name)) {
                System.out.println("City already exists!");
                return;
            }
            
        }         
        cities[cityCount++] = name;
        System.out.println("City added successfully!");
        
        
    }
     
    
    
  
    // method to Show list of cities
    static void showCities() {
        System.out.println("\nCities:");
        for (int i = 0; i < cityCount; i++) {
            System.out.println(i + " - " + cities[i]);
        }
    }
    
    
     static void renameOrRemoveCity() {
        if (cityCount == 0) {
            System.out.println("No cities available!");
            return;
        }
      
        showCities();
        System.out.println("1. Rename City");
        System.out.println("2. Remove City");
        System.out.print("Enter choice: ");
        int ch = sc.nextInt();
        System.out.print("Enter city index: ");
        int i = sc.nextInt();

        if (i < 0 || i >= cityCount) {
            System.out.println("Invalid index!");
            return;
        }
        if (ch == 1) {
            System.out.print("Enter new name: ");
            String newName = sc.next();
            cities[i] = newName;
            System.out.println("City renamed!");
        } else if (ch == 2) {
            for (int j = i; j < cityCount - 1; j++) {
                cities[j] = cities[j + 1];
                distance[j] = distance[j + 1];
                for (int k = 0; k < cityCount; k++) {
                    distance[k][j] = distance[k][j + 1];
                }
            }
            cityCount--;
            System.out.println("City removed!");
        } else {
            System.out.println("Invalid option!");
        }
    }
     
     
     // method to Edit distance between two cities
    static void editDistance() {
        if (cityCount < 2) {
            System.out.println("Add at least 2 cities first.");
            return;
        }
        showCities();
        System.out.print("Enter source city index: ");
        int i = sc.nextInt();
        System.out.print("Enter destination city index: ");
        int j = sc.nextInt();

        if (i == j) {
            System.out.println("Same city — distance = 0");
            return;
        }
        System.out.print("Enter distance (km): ");
        int d = sc.nextInt();
        distance[i][j] = d;
        distance[j][i] = d;
        System.out.println("Distance updated successfully!");
    }
    
    
    // method to Show the distance table
    
    static void showDistanceTable() {
        
        
        if (cityCount < 2) {
            System.out.println("Not enough cities!");
            return;
        }
        System.out.println("\n=== DISTANCE TABLE (km) ===");
        System.out.print("       ");
        for (int i = 0; i < cityCount; i++) System.out.printf("%10s", cities[i]);
        System.out.println();
        for (int i = 0; i < cityCount; i++) {
            System.out.printf("%10s", cities[i]);
            for (int j = 0; j < cityCount; j++) {
                System.out.printf("%10d", distance[i][j]);
            }
            System.out.println();
        }
    }
    
    
    //method to make delivery and calculate cost
    
    
    static void makeDelivery() {
        if (cityCount < 2) {
            System.out.println("No cities available!");
            return;
        }
        showCities();
        System.out.print("Source city index: ");
        int src = sc.nextInt();
        System.out.print("Destination city index: ");
        int dest = sc.nextInt();

        if (src == dest) {
            System.out.println("Invalid! Source and destination cannot be same.");
            return;
        }

        int dist = distance[src][dest];
        if (dist == 0) {
            System.out.println("Distance not set between these cities!");
            return;
        }
        
        //choose vehicle type
        System.out.println("Select Vehicle: 1.Van 2.Truck 3.Lorry");
        int v = sc.nextInt() - 1;
        System.out.print("Enter weight (kg): ");
        int w = sc.nextInt();
        
        
        
        
        // check if weight exceeds capacity
        if (w > vehicleCapacity[v]) {
            System.out.println("Weight exceeds vehicle capacity!");
            return;
        }
        
        
        //calculations
        double cost = dist * ratePerKm[v] * (1 + (w / 10000.0));
        double time = dist / avgSpeed[v];
        double fuelUsed = dist / efficiency[v];
        double fuelCost = fuelUsed * FUEL_PRICE;
        double totalCost = cost + fuelCost;
        double profit = cost * 0.25;
        double chargeAmount = totalCost + profit;

        totalRevenue += chargeAmount;
        totalProfit += profit;

        
        
        
       //save delivery record
        if (deliveryCount < MAX_DELIVERIES) {
            fromCity[deliveryCount] = cities[src];
            toCity[deliveryCount] = cities[dest];
            int[] distanceCovered = null;
            distanceCovered[deliveryCount] = dist;
            double[] deliveryTime = null;
            deliveryTime[deliveryCount] = time;
            double[] charge = null;
            charge[deliveryCount] = chargeAmount;
            deliveryCount++;
        }
        
        
        //print result
        System.out.println("\n==== DELIVERY COST ESTIMATION ====");
        System.out.println("From: " + cities[src] + " To: " + cities[dest]);
        System.out.println("Vehicle: " + vehicleNames[v]);
        System.out.println("Distance: " + dist + " km");
        System.out.printf("Base Cost: %.2f LKR%n", cost);
        System.out.printf("Fuel Cost: %.2f LKR%n", fuelCost);
        System.out.printf("Total Cost: %.2f LKR%n", totalCost);
        System.out.printf("Profit: %.2f LKR%n", profit);
        System.out.printf("Customer Charge: %.2f LKR%n", chargeAmount);
        System.out.printf("Estimated Time: %.2f hours%n", time);
    }
     
    
    //method to show performance report
    static void showReport() {
        if (deliveryCount == 0) {
            System.out.println("No deliveries yet!");
            return;
        }
        double totalDist = 0, totalTime = 0;
        double[] distanceCovered = null;
        double maxDist = distanceCovered[0];
        double minDist = distanceCovered[0];
        String longRoute = fromCity[0] + "→" + toCity[0];
        String shortRoute = fromCity[0] + "→" + toCity[0];

        for (int i = 0; i < deliveryCount; i++) {
            totalDist += distanceCovered[i];
           
            if (distanceCovered[i] > maxDist) {
                maxDist = distanceCovered[i];
                longRoute = fromCity[i] + "→" + toCity[i];
            }
            if (distanceCovered[i] < minDist) {
                minDist = distanceCovered[i];
                shortRoute = fromCity[i] + "→" + toCity[i];
            }
        }
        
        
        
        double avgTime = totalTime / deliveryCount;
        
        
        
        //print report
        System.out.println("\n==== PERFORMANCE REPORT ====");
        System.out.println("Total Deliveries: " + deliveryCount);
        System.out.printf("Total Distance Covered: %.2f km%n", totalDist);
        System.out.printf("Average Delivery Time: %.2f hours%n", avgTime);
        System.out.printf("Total Revenue: %.2f LKR%n", totalRevenue);
        System.out.printf("Total Profit: %.2f LKR%n", totalProfit);
        System.out.println("Longest Route: " + longRoute + " (" + maxDist + " km)");
        System.out.println("Shortest Route: " + shortRoute + " (" + minDist + " km)");
        
        
        
    }
    
    
    
    
    
    
    
    // method to Save data to text files
    static void saveData() {
        
     
        try 
            // Save city list and distance matrix
            (PrintWriter pw = new PrintWriter(new FileWriter("routes.txt"))) {
            pw.println(cityCount);
            for (int i = 0; i < cityCount; i++) pw.println(cities[i]);
            for (int i = 0; i < cityCount; i++) {
                for (int j = 0; j < cityCount; j++) pw.print(distance[i][j] + " ");
                pw.println();
            }
        } catch (IOException e) {
            System.out.println("Error saving routes!");
        }
        
        
        
        // Save delivery details to a file
        try (PrintWriter pw = new PrintWriter(new FileWriter("deliveries.txt"))) {
            pw.println(deliveryCount);
            for (int i = 0; i < deliveryCount; i++) {
                boolean[] charge = null;
                String[] distanceCovered = null;
                String[] deliveryTime = null;
                pw.println(fromCity[i] + "," + toCity[i] + "," + distanceCovered[i] + "," +
                           deliveryTime[i] + "," + charge[i]);
            }
        } catch (IOException e) {
            System.out.println("Error saving data!");
        }
    }
    
    
    
    
     
    
    
    
    
}
