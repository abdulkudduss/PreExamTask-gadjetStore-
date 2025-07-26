package aks.level2_preexamtask.dto.productDto;


import aks.level2_preexamtask.entities.Brand;
import aks.level2_preexamtask.enums.Category;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class ProductResponseForGetAll {
    Long id;
    String name;
    int price;
    String madeIn;
    Category category;
    Brand brand;

}
