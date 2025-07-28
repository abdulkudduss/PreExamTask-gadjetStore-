package aks.level2_preexamtask.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.ZonedDateTime;

@Entity
@Table(name = "comments")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String text;
    ZonedDateTime createdDate;
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    @JsonIgnore
    User user;
    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    @JsonIgnore
    Product product;
}
