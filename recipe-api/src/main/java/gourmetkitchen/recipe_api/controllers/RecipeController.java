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
    // DELETE /api/recipes/{id} - Delete by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRecipe(@PathVariable Long id) {
        recipeService.deleteRecipe(id);
        return ResponseEntity.noContent().build(); // Returns HTTP 204 Not Found
    }
    // GET /api/recipes/{id} - Retrieve a specific recipe by ID
    @GetMapping("/{id}")
    public ResponseEntity<Recipe> getRecipeById(@PathVariable Long id) {
        return recipeService.getRecipeById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build()); // Returns HTTP 404 if not found
    }
    // PUT /api/recipes/{id} - Update an existing recipe by ID
    @PutMapping("/{id}")
    public ResponseEntity<Recipe> updateRecipe(@PathVariable Long id, @RequestBody Recipe updatedRecipe) {
        return recipeService.updateRecipe(id, updatedRecipe)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

}
