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
    
    
    
    
}
