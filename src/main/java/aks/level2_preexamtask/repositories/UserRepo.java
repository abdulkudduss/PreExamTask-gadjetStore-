package aks.level2_preexamtask.repositories;

import aks.level2_preexamtask.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<User,Long> {

    Optional<User> findByEmail(String username);

    boolean existsByEmail(String email);


    Optional<User> findUserByEmail(String s);
}
