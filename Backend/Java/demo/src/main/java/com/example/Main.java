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
        Filters filter = new Filters();

    /*  
    ██╗██╗░██████╗░███████╗████████╗██╗██╗░░░░░░░██████╗░██╗░░░██╗███████╗██████╗░██╗███████╗░██████╗
    ╚█║╚█║██╔════╝░██╔════╝╚══██╔══╝╚█║╚█║░░░░░░██╔═══██╗██║░░░██║██╔════╝██╔══██╗██║██╔════╝██╔════╝
    ░╚╝░╚╝██║░░██╗░█████╗░░░░░██║░░░░╚╝░╚╝█████╗██║██╗██║██║░░░██║█████╗░░██████╔╝██║█████╗░░╚█████╗░
    ░░░░░░██║░░╚██╗██╔══╝░░░░░██║░░░░░░░░░╚════╝╚██████╔╝██║░░░██║██╔══╝░░██╔══██╗██║██╔══╝░░░╚═══██╗
    ░░░░░░╚██████╔╝███████╗░░░██║░░░░░░░░░░░░░░░░╚═██╔═╝░╚██████╔╝███████╗██║░░██║██║███████╗██████╔╝
    ░░░░░░░╚═════╝░╚══════╝░░░╚═╝░░░░░░░░░░░░░░░░░░╚═╝░░░░╚═════╝░╚══════╝╚═╝░░╚═╝╚═╝╚══════╝╚═════╝░
    */
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

        app.get("/api/ohcontacts", ctx -> { // Returnerer kontaktpersonene til ØH
            var ohcontacts = getting.getOHContacts();
            ctx.json(ohcontacts);
        });

        app.get("/api/arrangers", ctx -> { // Returnerer arrangørene til ØH
            var arrangers = getting.getAllArrangers();
            ctx.json(arrangers);
        });

        app.get("/api/tickettypes", ctx -> { // Returnerer arrangørene til ØH
            var ticketTypes = getting.getAllTicketTypes();
            ctx.json(ticketTypes);
        });

        app.get("/api/waitinglists", ctx -> { // Returnerer arrangørene til ØH
            var waitingLists = getting.getAllWaitingLists();
            ctx.json(waitingLists);
        });

        app.get("/api/participations", ctx -> { // Returnerer arrangørene til ØH
            var participations = getting.getAllParticipations();
            ctx.json(participations);
        });

        app.get("/api/posts", ctx -> { // Returnerer arrangørene til ØH
            var posts = getting.getAllPosts();
            ctx.json(posts);
        });

        app.get("/api/faqs", ctx -> { // Returnerer arrangørene til ØH
            var faqs = getting.getAllFAQs();
            ctx.json(faqs);
        });

        app.get("/api/cities", ctx -> { // Returnerer arrangørene til ØH
            var cities = getting.getAllCities();
            ctx.json(cities);
        });

        app.get("/api/tickets", ctx -> { // Returnerer arrangørene til ØH
            var tickets = getting.getAllTickets();
            ctx.json(tickets);
        });

        app.get("/api/adminlogs", ctx -> { // Returnerer arrangørene til ØH
            var adminlogs = getting.getAllAdminLogs();
            ctx.json(adminlogs);
        });

        app.get("/api/buys", ctx -> { // Returnerer arrangørene til ØH
            var buys = getting.getAllBuys();
            ctx.json(buys);
        });

        app.get("/api/users", ctx -> { // Returnerer arrangørene til ØH
            var users = getting.getAllUsers();
            ctx.json(users);
        });

    /*
    ███████╗██╗██╗░░░░░████████╗███████╗██████╗░░██████╗
    ██╔════╝██║██║░░░░░╚══██╔══╝██╔════╝██╔══██╗██╔════╝
    █████╗░░██║██║░░░░░░░░██║░░░█████╗░░██████╔╝╚█████╗░
    ██╔══╝░░██║██║░░░░░░░░██║░░░██╔══╝░░██╔══██╗░╚═══██╗
    ██║░░░░░██║███████╗░░░██║░░░███████╗██║░░██║██████╔╝
    ╚═╝░░░░░╚═╝╚══════╝░░░╚═╝░░░╚══════╝╚═╝░░╚═╝╚═════╝░
    */


        System.out.println("Javalin server running at http://localhost:7000");
    }
}