package gourmetkitchen.recipe_api.controllers;

import gourmetkitchen.recipe_api.entities.Recipe;
import gourmetkitchen.recipe_api.services.RecipeServices;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/recipes")
public class RecipeController {

    private final RecipeServices recipeService;

    public RecipeController(RecipeServices recipeService) {
        this.recipeService = recipeService;
    }

    // GET /api/recipes - Get all recipes
    @GetMapping
    public List<Recipe> getAllRecipes() {
        return recipeService.getAllRecipes();
    }

    // POST /api/recipes - Save new recipe
    @PostMapping
    public ResponseEntity<Recipe> createRecipe(@RequestBody Recipe recipe) {
        Recipe savedRecipe = recipeService.saveRecipe(recipe);
        return ResponseEntity.ok(savedRecipe);
    }
}
