package aks.level2_preexamtask.repositories;

import aks.level2_preexamtask.entities.Favorite;
import aks.level2_preexamtask.entities.Product;
import aks.level2_preexamtask.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FavoriteRepo extends JpaRepository<Favorite,Long> {
    Optional<Object> findByUserAndProduct(User user, Product product);

    List<Favorite> findByUser(User user);
}
