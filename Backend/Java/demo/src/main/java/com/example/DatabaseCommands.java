package com.example;

public class DatabaseCommands {
    public static void main(String[] args) {
        System.out.println("Henter Kategorier");
        // 1. Create an instance of your Getting class
        Getting getting = new Getting();
        // 2. Call the method to run the query
        getting.getAllMembers();
    }
}
