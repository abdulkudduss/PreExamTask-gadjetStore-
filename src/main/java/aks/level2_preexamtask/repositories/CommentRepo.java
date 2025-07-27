package aks.level2_preexamtask.repositories;

import aks.level2_preexamtask.entities.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepo extends JpaRepository<Comment,Long> {
}
