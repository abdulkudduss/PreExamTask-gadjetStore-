package aks.level2_preexamtask.service;

import aks.level2_preexamtask.dto.SimpleResponse;

public interface CommentServ {
    SimpleResponse addComment(String comment,Long id);
}
