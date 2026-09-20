package gourmetkitchen.recipe_api.services;

import gourmetkitchen.recipe_api.entities.Ingredient;
import gourmetkitchen.recipe_api.entities.Recipe;
import gourmetkitchen.recipe_api.repositories.RecipeRepository;
import org.springframework.stereotype.Service;
import gourmetkitchen.recipe_api.exceptions.ResourceNotFoundException;

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

    // Method to find a single recipe by its ID
    public Recipe getRecipeById(Long id) {
        return recipeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Receta no encontrada con ID: " + id));
    }

    // Method to update a recipe
    public Recipe updateRecipe(Long id, Recipe updatedRecipe) {
        Recipe existingRecipe = recipeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Receta no encontrada con ID: " + id));

        existingRecipe.setTitle(updatedRecipe.getTitle());
        existingRecipe.setDescription(updatedRecipe.getDescription());
        existingRecipe.setPreparationTime(updatedRecipe.getPreparationTime());
        existingRecipe.setDifficulty(updatedRecipe.getDifficulty());
        existingRecipe.setImageUrl(updatedRecipe.getImageUrl());

        // Updated list ingredients
        if (updatedRecipe.getIngredients() != null) {
            existingRecipe.getIngredients().clear();
            for (Ingredient ingredient : updatedRecipe.getIngredients()) {
                ingredient.setRecipe(existingRecipe);
                existingRecipe.getIngredients().add(ingredient);
            }
        }

        return recipeRepository.save(existingRecipe);
    }

    // Method to delete a recipe
    public void deleteRecipe(Long id) {
        if (!recipeRepository.existsById(id)) {
            throw new ResourceNotFoundException("Receta no encontrada con ID: " + id);
        }
        recipeRepository.deleteById(id);
    }
}