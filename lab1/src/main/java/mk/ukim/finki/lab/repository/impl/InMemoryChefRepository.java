package mk.ukim.finki.lab.repository.impl;

import mk.ukim.finki.lab.bootstrap.DataHolder;
import mk.ukim.finki.lab.model.Chef;
import mk.ukim.finki.lab.repository.ChefRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public class InMemoryChefRepository implements ChefRepository {

    @Override
    public List<Chef> findAll() {
        return DataHolder.chefs;
    }

    @Override
    public Optional<Chef> findById(Long id) {
        return DataHolder.chefs.stream()
                .filter(chef -> chef.getId().equals(id))
                .findFirst();
    }

    @Override
    public Chef save(Chef chef) {
        if (chef.getId() != null) {
            Optional<Chef> existing = findById(chef.getId());
            if (existing.isPresent()) {
                Chef existingChef = existing.get();
                existingChef.setFirstName(chef.getFirstName());
                existingChef.setLastName(chef.getLastName());
                existingChef.setBio(chef.getBio());
                existingChef.setDishes(chef.getDishes());
                return existingChef;
            }
        }
        DataHolder.chefs.add(chef);
        return chef;
    }
}