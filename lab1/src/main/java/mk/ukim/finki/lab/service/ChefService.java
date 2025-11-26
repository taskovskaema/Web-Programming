package mk.ukim.finki.lab.service;

import mk.ukim.finki.lab.model.Chef;
import java.util.List;

public interface ChefService {
    List<Chef> listChefs();
    Chef findById(Long id);
    Chef addDishToChef(Long chefId, String dishId);
}