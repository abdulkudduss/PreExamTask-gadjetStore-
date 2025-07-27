package aks.level2_preexamtask.api.user;

import aks.level2_preexamtask.dto.SimpleResponse;
import aks.level2_preexamtask.service.CommentServ;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")

public class UserApi {

    private final CommentServ commentServ;

    public UserApi(CommentServ commentServ) {
        this.commentServ = commentServ;
    }

    @PostMapping("/comments/{productId}")
    @Secured("USER")
    public SimpleResponse addCommentToProduct(@PathVariable Long productId, @RequestBody String comment) {



        return commentServ.addComment(comment,productId);


    }


}
