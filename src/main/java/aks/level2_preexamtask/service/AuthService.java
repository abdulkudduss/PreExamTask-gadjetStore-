package aks.level2_preexamtask.service;

import aks.level2_preexamtask.dto.SimpleResponse;
import aks.level2_preexamtask.dto.authDto.response.SignInResponse;
import aks.level2_preexamtask.dto.authDto.request.SignInRequest;
import aks.level2_preexamtask.dto.authDto.request.SignUpRequest;
import aks.level2_preexamtask.exceptions.AlreadyExistException;

public interface AuthService {
    SimpleResponse signUp(SignUpRequest signUpRequest) throws AlreadyExistException;
    SignInResponse signIn(SignInRequest signInRequest);
}
