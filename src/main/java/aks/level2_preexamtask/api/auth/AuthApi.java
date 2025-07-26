package aks.level2_preexamtask.api.auth;

import aks.level2_preexamtask.dto.SimpleResponse;
import aks.level2_preexamtask.dto.authDto.response.SignInResponse;
import aks.level2_preexamtask.dto.authDto.request.SignInRequest;
import aks.level2_preexamtask.dto.authDto.request.SignUpRequest;
import aks.level2_preexamtask.exceptions.AlreadyExistException;
import aks.level2_preexamtask.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/auth")
public class AuthApi {
  private final AuthService authService;
    public AuthApi(AuthService authServicee) {
        this.authService = authServicee;
    }
    @PostMapping("/login")
    public SignInResponse login (@RequestBody SignInRequest signInRequest ){
        return authService.signIn(signInRequest);
    }
    @PostMapping("/signUp")
    public SimpleResponse register (@RequestBody @Valid SignUpRequest signUpRequest) throws AlreadyExistException {
        return authService.signUp(signUpRequest);
    }

}
