package mk.ukim.finki.lab.repository;


import mk.ukim.finki.lab.model.Dish;
import java.util.List;
import java.util.Optional;

public interface DishRepository {
    List<Dish> findAll();
    Dish findByDishId(String dishId);
    Optional<Dish> findById(Long id);
    Dish save(Dish dish);
    void deleteById(Long id);



}
