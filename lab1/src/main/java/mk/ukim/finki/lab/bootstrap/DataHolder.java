package mk.ukim.finki.lab.bootstrap;

import mk.ukim.finki.lab.model.Chef;
import mk.ukim.finki.lab.model.Dish;
import java.util.ArrayList;
import java.util.List;

public class DataHolder {
    public static List<Chef> chefs = new ArrayList<>();
    public static List<Dish> dishes = new ArrayList<>();

    static {
        // Initialize chefs
        chefs.add(new Chef(1L, "Gordon", "Ramsay", "World-renowned chef with 16 Michelin stars"));
        chefs.add(new Chef(2L, "Julia", "Child", "Legendary French cook and television personality"));
        chefs.add(new Chef(3L, "Wolfgang", "Puck", "Pioneer of modern American cuisine"));
        chefs.add(new Chef(4L, "Emeril", "Lagasse", "Celebrity chef known for Creole and Cajun cuisine"));
        chefs.add(new Chef(5L, "Alice", "Waters", "Pioneer of farm-to-table cooking"));

        // Initialize dishes
        dishes.add(new Dish("d1", "Beef Wellington", "British", 45));
        dishes.add(new Dish("d2", "Pasta Carbonara", "Italian", 20));
        dishes.add(new Dish("d3", "Chicken Tikka Masala", "Indian", 35));
        dishes.add(new Dish("d4", "Coq au Vin", "French", 90));
        dishes.add(new Dish("d5", "Sushi Platter", "Japanese", 40));
    }
}