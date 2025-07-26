package aks.level2_preexamtask.dto.productDto;


import aks.level2_preexamtask.entities.Brand;
import aks.level2_preexamtask.entities.Comment;
import aks.level2_preexamtask.enums.Category;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Builder
@Getter
@Setter
public class ProductResponseForGetById {
    Long id;
    String name;
    int price;
    List<String> images;
    String description;
    boolean isFavorite;
    String madeIn;
    Category category;
    Brand brand;
    int likesCount;
    List<Comment> comments;
}
