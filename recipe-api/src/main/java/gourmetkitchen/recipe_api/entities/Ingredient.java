package gourmetkitchen.recipe_api.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "ingredients")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Ingredient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Ingredient name (e.g., "Water", "Flour")
    @NotBlank(message = "Ingredient name is mandatory")
    @Column(nullable = false)
    private String name;

    // Quantity or measure (e.g., "4 units", "200g")
    @NotBlank(message = "Quantity is mandatory")
    private String quantity;

    // Many ingredients belong to one recipe
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "recipe_id")
    @JsonIgnore // Prevents infinite recursion during JSON serialization
    private Recipe recipe;
}