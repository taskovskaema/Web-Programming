package mk.ukim.finki.lab.repository;

import mk.ukim.finki.lab.model.Chef;
import java.util.List;
import java.util.Optional;

public interface ChefRepository {
    List<Chef> findAll();
    Optional<Chef> findById(Long id);
    Chef save(Chef chef);
}