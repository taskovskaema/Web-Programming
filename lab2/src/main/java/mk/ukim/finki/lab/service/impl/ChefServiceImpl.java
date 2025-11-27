package mk.ukim.finki.lab.service.impl;

import mk.ukim.finki.lab.model.Chef;
import mk.ukim.finki.lab.model.Dish;
import mk.ukim.finki.lab.repository.ChefRepository;
import mk.ukim.finki.lab.repository.DishRepository;
import mk.ukim.finki.lab.service.ChefService;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ChefServiceImpl implements ChefService {
    private final ChefRepository chefRepository;
    private final DishRepository dishRepository;

    public ChefServiceImpl(ChefRepository chefRepository, DishRepository dishRepository) {
        this.chefRepository = chefRepository;
        this.dishRepository = dishRepository;
    }

    @Override
    public List<Chef> listChefs() {
        return chefRepository.findAll();
    }

    @Override
    public Chef findById(Long id) {
        return chefRepository.findById(id).orElse(null);
    }

    @Override
    public Chef addDishToChef(Long chefId, String dishId) {
        Chef chef = findById(chefId);
        if (chef != null) {
            Dish dish = dishRepository.findByDishId(dishId);
            if (dish != null) {
                chef.addDish(dish);
                return chefRepository.save(chef);
            }
        }
        return null;
    }
}