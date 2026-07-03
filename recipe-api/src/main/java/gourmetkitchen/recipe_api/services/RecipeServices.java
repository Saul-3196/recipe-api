package gourmetkitchen.recipe_api.services;

import gourmetkitchen.recipe_api.entities.Recipe;
import gourmetkitchen.recipe_api.repositories.RecipeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecipeService {

    private final RecipeRepository recipeRepository;

    // Dependency injection through constructor
    public RecipeService(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    // Endpoint to retrieve all recipes from database
    public List<Recipe> getAllRecipes() {
        return recipeRepository.findAll();
    }

    // Endpoint to create and save a new recipe
    public Recipe saveRecipe(Recipe recipe) {
        return recipeRepository.save(recipe);// returns HTTP 200 OK with the created recipe
    }
}