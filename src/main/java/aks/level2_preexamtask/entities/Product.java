package aks.level2_preexamtask.entities;


import aks.level2_preexamtask.enums.Category;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Entity
@Table(name = "products")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String name;
    String price;
    @ElementCollection
    List<String> images;
    String description;
    boolean isFavorite;
    String madeIn;
    @Enumerated(EnumType.STRING)
    Category category;
    @ManyToOne
    Brand brand;
    @OneToMany(mappedBy = "product")
    List<Favorite> favorites;
    @OneToMany(mappedBy = "product")
    List<Comment> comments;

}
