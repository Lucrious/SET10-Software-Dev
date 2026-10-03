package com.example;

import java.util.ArrayList;

import com.example.Entities.Course;
import com.example.Entities.Post;

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
        Filters filters = new Filters();

    /*  
    ██╗██╗░██████╗░███████╗████████╗██╗██╗░░░░░░░██████╗░██╗░░░██╗███████╗██████╗░██╗███████╗░██████╗
    ╚█║╚█║██╔════╝░██╔════╝╚══██╔══╝╚█║╚█║░░░░░░██╔═══██╗██║░░░██║██╔════╝██╔══██╗██║██╔════╝██╔════╝
    ░╚╝░╚╝██║░░██╗░█████╗░░░░░██║░░░░╚╝░╚╝█████╗██║██╗██║██║░░░██║█████╗░░██████╔╝██║█████╗░░╚█████╗░
    ░░░░░░██║░░╚██╗██╔══╝░░░░░██║░░░░░░░░░╚════╝╚██████╔╝██║░░░██║██╔══╝░░██╔══██╗██║██╔══╝░░░╚═══██╗
    ░░░░░░╚██████╔╝███████╗░░░██║░░░░░░░░░░░░░░░░╚═██╔═╝░╚██████╔╝███████╗██║░░██║██║███████╗██████╔╝
    ░░░░░░░╚═════╝░╚══════╝░░░╚═╝░░░░░░░░░░░░░░░░░░╚═╝░░░░╚═════╝░╚══════╝╚═╝░░╚═╝╚═╝╚══════╝╚═════╝░
    */
        app.get("/api/categories", ctx -> {
            var categories = getting.getAllCategories();
            ctx.json(categories);
        });

        app.get("/api/courses", ctx -> {
            var courses = getting.getAllCourses();
            ctx.json(courses);
        });

        app.get("/api/ohaddress", ctx -> {
            var ohaddress = getting.getOHAdress();
            ctx.json(ohaddress);
        });

        app.get("/api/ohcontacts", ctx -> {
            var ohcontacts = getting.getOHContacts();
            ctx.json(ohcontacts);
        });

        app.get("/api/arrangers", ctx -> { 
            var arrangers = getting.getAllArrangers();
            ctx.json(arrangers);
        });

        app.get("/api/tickettypes", ctx -> { 
            var ticketTypes = getting.getAllTicketTypes();
            ctx.json(ticketTypes);
        });

        app.get("/api/waitinglists", ctx -> { 
            var waitingLists = getting.getAllWaitingLists();
            ctx.json(waitingLists);
        });

        app.get("/api/participations", ctx -> { 
            var participations = getting.getAllParticipations();
            ctx.json(participations);
        });

        app.get("/api/posts", ctx -> { 
            var posts = getting.getAllPosts();
            ctx.json(posts);
        });

        app.get("/api/faqs", ctx -> { 
            var faqs = getting.getAllFAQs();
            ctx.json(faqs);
        });

        app.get("/api/cities", ctx -> { 
            var cities = getting.getAllCities();
            ctx.json(cities);
        });

        app.get("/api/tickets", ctx -> { 
            var tickets = getting.getAllTickets();
            ctx.json(tickets);
        });

        app.get("/api/adminlogs", ctx -> { 
            var adminlogs = getting.getAllAdminLogs();
            ctx.json(adminlogs);
        });

        app.get("/api/buys", ctx -> { 
            var buys = getting.getAllBuys();
            ctx.json(buys);
        });

        app.get("/api/users", ctx -> { 
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

        app.get("/api/courses/search", ctx -> {
            String query = ctx.queryParam("q"); // eks. /api/courses/search?q=husflid
            if (query == null) {
                query = "";
            }
            
            ArrayList<Course> results = filters.searchCourses(query);
            ctx.json(results);
        });

        app.get("/api/posts/search", ctx -> {
            String query = ctx.queryParam("q");
            if (query == null) {
                query = "";
            }
            
            ArrayList<Post> results = filters.searchPosts(query);
            ctx.json(results); 
        });


        System.out.println("Javalin server running at http://localhost:7000");
    }
}