package mk.ukim.finki.lab.repository.impl;

import mk.ukim.finki.lab.bootstrap.DataHolder;
import mk.ukim.finki.lab.model.Dish;
import mk.ukim.finki.lab.repository.DishRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

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

    @Override
    public Optional<Dish> findById(Long id) {
        if (id == null) return Optional.empty();

        return DataHolder.dishes
                .stream()
                .filter(d -> d.getId().equals(id))
                .findFirst();
    }

    @Override
    public Dish save(Dish dish) {
        if (dish == null) return null;

        // ако постои → избриши го и надградете го
        DataHolder.dishes.removeIf(d -> d.getId().equals(dish.getId()));

        // додади го новиот/ажурираниот
        DataHolder.dishes.add(dish);

        return dish;
    }

    @Override
    public void deleteById(Long id) {
        DataHolder.dishes.removeIf(d -> d.getId().equals(id));
    }


}