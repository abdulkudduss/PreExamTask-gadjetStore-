package aks.level2_preexamtask.dto.productDto;


import aks.level2_preexamtask.dto.commentDto.CommentRequest;
import aks.level2_preexamtask.dto.commentDto.CommentResponse;
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

    public ProductResponseForGetById() {
    }

    public ProductResponseForGetById(Long id, String name, int price, List<String> images, String description, boolean isFavorite, String madeIn, Category category, Brand brand, int likesCount, List<Comment> comments) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.images = images;
        this.description = description;
        this.isFavorite = isFavorite;
        this.madeIn = madeIn;
        this.category = category;
        this.brand = brand;
        this.likesCount = likesCount;
        this.comments = comments;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isFavorite() {
        return isFavorite;
    }

    public void setFavorite(boolean favorite) {
        isFavorite = favorite;
    }

    public String getMadeIn() {
        return madeIn;
    }

    public void setMadeIn(String madeIn) {
        this.madeIn = madeIn;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Brand getBrand() {
        return brand;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }

    public int getLikesCount() {
        return likesCount;
    }

    public void setLikesCount(int likesCount) {
        this.likesCount = likesCount;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }
}
