package gourmetkitchen.recipe_api.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "recipes")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Recipe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Title cannot be empty or blank, and must be between 3 and 100 characters
    @NotBlank(message = "Title is mandatory")
    @Size(min = 3, max = 100, message = "Title must be between 3 and 100 characters")
    @Column(nullable = false)
    private String title;

    // Description cannot be blank
    @NotBlank(message = "Description is mandatory")
    @Column(length = 1000)
    private String description;

    // Preparation time must be at least 1 minute
    @NotNull(message = "Preparation time is mandatory")
    @Min(value = 1, message = "Preparation time must be at least 1 minute")
    private Integer preparationTime; // minutes

    private String difficulty; // Easy, medium, hard.

    private String imageUrl; // RecipePhoto

    //One recipe can hace many ingredients
    @OneToMany(mappedBy = "recipe", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Ingredient> ingredients = new ArrayList<>();
}