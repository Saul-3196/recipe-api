package gourmetkitchen.recipe_api.controllers;

import gourmetkitchen.recipe_api.entities.Recipe;
import gourmetkitchen.recipe_api.services.RecipeServices;
import io.swagger.v3.oas.annotations.Parameter;
import org.springdoc.core.converters.models.PageableAsQueryParam;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/recipes")
public class RecipeController {

    private final RecipeServices recipeService;

    public RecipeController(RecipeServices recipeService) {
        this.recipeService = recipeService;
    }

    // GET /api/recipes - Retrieve a paginated and sorted list of recipes with optional filters
    @GetMapping
    @PageableAsQueryParam
    public ResponseEntity<Page<Recipe>> getAllRecipes(
            @RequestParam(required = false) String title,
            @RequestParam(required = false) String difficulty,
            @Parameter(hidden = true) @PageableDefault(page = 0, size = 10, sort = "id", direction = Sort.Direction.ASC) Pageable pageable) {
        Page<Recipe> recipes = recipeService.getAllRecipes(title, difficulty, pageable);
        return ResponseEntity.ok(recipes);
    }

    // POST /api/recipes - Save new recipe
    @PostMapping
    public ResponseEntity<Recipe> createRecipe(@Valid @RequestBody Recipe recipe) {
        Recipe savedRecipe = recipeService.createRecipe(recipe);
        return ResponseEntity.ok(savedRecipe);
    }

    // DELETE /api/recipes/{id} - Delete by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRecipe(@PathVariable Long id) {
        recipeService.deleteRecipe(id);
        return ResponseEntity.noContent().build();
    }

    // GET /api/recipes/{id} - Retrieve a specific recipe by ID
    @GetMapping("/{id}")
    public ResponseEntity<Recipe> getRecipeById(@PathVariable Long id) {
        Recipe recipe = recipeService.getRecipeById(id);
        return ResponseEntity.ok(recipe);
    }

    // PUT /api/recipes/{id} - Update an existing recipe by ID
    @PutMapping("/{id}")
    public ResponseEntity<Recipe> updateRecipe(@PathVariable Long id, @Valid @RequestBody Recipe recipe) {
        Recipe updated = recipeService.updateRecipe(id, recipe);
        return ResponseEntity.ok(updated);
    }
}