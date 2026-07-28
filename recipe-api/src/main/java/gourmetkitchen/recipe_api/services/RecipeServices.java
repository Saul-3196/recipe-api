package gourmetkitchen.recipe_api.services;

import gourmetkitchen.recipe_api.entities.Recipe;
import gourmetkitchen.recipe_api.repositories.RecipeRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RecipeServices {

    private final RecipeRepository recipeRepository;

    public RecipeServices(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    // Method to find all recipes
    public List<Recipe> getAllRecipes() {
        return recipeRepository.findAll();
    }

    // Helper method to set the recipe reference in each ingredient
    private void setRecipeInIngredients(Recipe recipe) {
        if (recipe.getIngredients() != null) {
            recipe.getIngredients().forEach(ingredient -> ingredient.setRecipe(recipe));
        }
    }

    // Method to create a new recipe
    public Recipe createRecipe(Recipe recipe) {
        setRecipeInIngredients(recipe);
        return recipeRepository.save(recipe);
    }

    // Method to delete a recipe by its ID
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

            // Clear previous ingredients and add updated ones
            existingRecipe.getIngredients().clear();
            if (updatedRecipe.getIngredients() != null) {
                updatedRecipe.getIngredients().forEach(ingredient -> {
                    ingredient.setRecipe(existingRecipe);
                    existingRecipe.getIngredients().add(ingredient);
                });
            }

            return recipeRepository.save(existingRecipe);
        });
    }
}