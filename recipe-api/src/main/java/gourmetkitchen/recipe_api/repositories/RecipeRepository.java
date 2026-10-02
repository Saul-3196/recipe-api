package gourmetkitchen.recipe_api.repositories;

import gourmetkitchen.recipe_api.entities.Recipe;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecipeRepository extends JpaRepository<Recipe, Long> {

    // Find recipes where title contains the text (case-insensitive)
    Page<Recipe> findByTitleContainingIgnoreCase(String title, Pageable pageable);

    // Find recipes by difficulty (case-insensitive)
    Page<Recipe> findByDifficultyIgnoreCase(String difficulty, Pageable pageable);

    // Find recipes matching both title and difficulty
    Page<Recipe> findByTitleContainingIgnoreCaseAndDifficultyIgnoreCase(String title, String difficulty, Pageable pageable);
}