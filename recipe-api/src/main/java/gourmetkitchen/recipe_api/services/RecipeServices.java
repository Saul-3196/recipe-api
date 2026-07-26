package gourmetkitchen.recipe_api.services;

import gourmetkitchen.recipe_api.entities.Recipe;
import gourmetkitchen.recipe_api.repositories.RecipeRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RecipeServices {

    private final RecipeRepository recipeRepository;

    // Dependency injection through constructor
    public RecipeServices(RecipeRepository recipeRepository) {
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

    //Endpoint to delete a recipe by ID
    public void deleteRecipe(Long id) {
        recipeRepository.deleteById(id);
    }
    // Method to find a single recipe by its ID
    public Optional<Recipe> getRecipeById(Long id) {
        return recipeRepository.findById(id);
    }
    // Method to update an existing recipe by ID
    public Optional<Recipe> updateRecipe(Long id, Recipe updatedRecipe) {
        return recipeRepository.findById(id).map(existingRecipe -> {
            existingRecipe.setTitle(updatedRecipe.getTitle());
            existingRecipe.setDescription(updatedRecipe.getDescription());
            existingRecipe.setPreparationTime(updatedRecipe.getPreparationTime());
            existingRecipe.setDifficulty(updatedRecipe.getDifficulty());
            existingRecipe.setImageUrl(updatedRecipe.getImageUrl());
            return recipeRepository.save(existingRecipe);
        });
    }
}