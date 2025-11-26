package mk.ukim.finki.lab.repository.impl;

import mk.ukim.finki.lab.bootstrap.DataHolder;
import mk.ukim.finki.lab.model.Dish;
import mk.ukim.finki.lab.repository.DishRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public class InMemoryDishRepository implements DishRepository {

    @Override
    public List<Dish> findAll() {
        return DataHolder.dishes;
    }

    @Override
    public Dish findByDishId(String dishId) {
        return DataHolder.dishes.stream()
                .filter(dish -> dish.getDishId().equals(dishId))
                .findFirst()
                .orElse(null);
    }
}