package mk.ukim.finki.lab.repository;


import mk.ukim.finki.lab.model.Dish;
import java.util.List;

public interface DishRepository {
    List<Dish> findAll();
    Dish findByDishId(String dishId);
}
