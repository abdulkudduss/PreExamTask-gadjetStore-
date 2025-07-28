package aks.level2_preexamtask.repositories;

import aks.level2_preexamtask.entities.Basket;
import aks.level2_preexamtask.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BasketRepo extends JpaRepository<Basket,Long> {
    Optional<Object> findByUser(User user);
}
