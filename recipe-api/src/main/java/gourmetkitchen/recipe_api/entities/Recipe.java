package gourmetkitchen.recipe_api.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Entity
@Table(name = "recipes")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Recipe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(length = 1000)
    private String description;

    private Integer preparationTime; // minutes

    private String difficulty; // Easy, medium, hard.

    private String imageUrl;//RecipePhoto
}