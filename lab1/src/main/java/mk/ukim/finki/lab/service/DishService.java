package mk.ukim.finki.lab.service;


import mk.ukim.finki.lab.model.Dish;
import java.util.List;

public interface DishService {
    List<Dish> listDishes();
    Dish findByDishId(String dishId);
}