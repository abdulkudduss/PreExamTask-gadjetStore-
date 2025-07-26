package aks.level2_preexamtask.init;

import aks.level2_preexamtask.entities.User;
import aks.level2_preexamtask.enums.Role;
import aks.level2_preexamtask.repositories.UserRepo;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class DefaultAdmin {
    private final UserRepo userRepo;
    private final PasswordEncoder passwordEncoder;
    @PostConstruct
    public void saveAdmin (){
       // Optional<User> user = userRepo.findUserByEmail("admin@gmail.com");
        String adminEmail = "admin@gmail.com";
        if (!userRepo.existsByEmail(adminEmail)) {
            userRepo.save(User.builder()
                    .firstName("admin")
                    .lastName("adminov")
                    .email(adminEmail)
                    .password(passwordEncoder.encode("admin123"))
                    .role(Role.ADMIN)
                    .build());
            System.out.println(" Admin user created.");
        }else {
            System.out.println(" Admin already exists.");
        }

    }

}
