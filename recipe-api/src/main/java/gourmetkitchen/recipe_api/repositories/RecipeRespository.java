package gourmetkitchen.recipe_api.repositories;

import gourmetkitchen.recipe_api.entities.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecipeRepository extends JpaRepository<Recipe, Long> {
    // Spring Data JPA will generate authomatically the CRUD methods
}