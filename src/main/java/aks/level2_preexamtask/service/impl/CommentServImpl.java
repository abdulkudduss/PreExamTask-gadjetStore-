package aks.level2_preexamtask.service.impl;

import aks.level2_preexamtask.config.JwtUtil;
import aks.level2_preexamtask.dto.SimpleResponse;
import aks.level2_preexamtask.entities.Comment;
import aks.level2_preexamtask.exceptions.NotFoundException;
import aks.level2_preexamtask.repositories.CommentRepo;
import aks.level2_preexamtask.repositories.ProductRepo;
import aks.level2_preexamtask.service.CommentServ;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;

@Service
@AllArgsConstructor
public class CommentServImpl implements CommentServ {

    private final CommentRepo commentRepo;
    private final JwtUtil jwtService;
    private final ProductRepo productRepo;
    @Override
    public SimpleResponse addComment(String comment,Long id) {
        Comment comment1 = new Comment();
        comment1.setCreatedDate(ZonedDateTime.now());
        comment1.setUser(jwtService.getUserViaToken());
        comment1.setText(comment);
        comment1.setProduct(productRepo.findById(id).orElseThrow(()-> new NotFoundException("product not found {comment-method}")));
        commentRepo.save(comment1);
        return new SimpleResponse(HttpStatus.CREATED,"comment added");
    }
}
