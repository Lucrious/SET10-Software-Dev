package com.example;

import io.javalin.Javalin;

public class Main {
    public static void main(String[] args) {
        Javalin app = Javalin.create().start(7000);

        // Tillatter React å samhandle med Java
        app.before(ctx -> {
            ctx.header("Access-Control-Allow-Origin", "*");
            ctx.header("Access-Control-Allow-Headers", "*");
            ctx.header("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
        });

        Getting getting = new Getting();

        app.get("/api/categories", ctx -> { // Returnerer alle kategoriene
            var categories = getting.getAllCategories();
            ctx.json(categories);
        });

        app.get("/api/courses", ctx -> { // Returnerer alle kursene
            var courses = getting.getAllCourses();
            ctx.json(courses);
        });

        app.get("/api/ohaddress", ctx -> { // Returnerer adressen til ØH i Fredrikstad
            var ohaddress = getting.getOHAdress();
            ctx.json(ohaddress);
        });

        app.get("/api/ohcontacts", ctx -> { // Returnerer adressen til ØH i Fredrikstad
            var ohcontacts = getting.getOHContacts();
            ctx.json(ohcontacts);
        });

        System.out.println("Javalin server running at http://localhost:7000");
    }
}