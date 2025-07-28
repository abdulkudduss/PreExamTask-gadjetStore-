package aks.level2_preexamtask.service.impl;

import aks.level2_preexamtask.config.JwtUtil;
import aks.level2_preexamtask.dto.SimpleResponse;
import aks.level2_preexamtask.dto.authDto.response.SignInResponse;
import aks.level2_preexamtask.dto.authDto.request.SignInRequest;
import aks.level2_preexamtask.dto.authDto.request.SignUpRequest;
import aks.level2_preexamtask.entities.Basket;
import aks.level2_preexamtask.entities.User;
import aks.level2_preexamtask.enums.Role;
import aks.level2_preexamtask.exceptions.AlreadyExistException;

import aks.level2_preexamtask.mapper.auth.SignInRequestMapperr;
import aks.level2_preexamtask.mapper.auth.SignUpRequestMapperr;
import aks.level2_preexamtask.repositories.BasketRepo;
import aks.level2_preexamtask.repositories.UserRepo;
import aks.level2_preexamtask.service.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;
import java.util.ArrayList;


@Service
public class AuthServImpl implements AuthService {
    private final BasketRepo basketRepo;
    private final UserRepo userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authManager;
    private final JwtUtil jwtUtil;
    private final  SignInRequestMapperr signInRequestMapper;
  private final   SignUpRequestMapperr signUpRequestMapper;
    public AuthServImpl(BasketRepo basketRepo, UserRepo userRepository, PasswordEncoder passwordEncoder,
                        AuthenticationManager authManager, JwtUtil jwtUtil,
                        SignInRequestMapperr signInRequestMapper, SignUpRequestMapperr signUpRequestMapper) {
        this.basketRepo = basketRepo;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.authManager = authManager;
        this.jwtUtil = jwtUtil;
        this.signInRequestMapper = signInRequestMapper;
        this.signUpRequestMapper = signUpRequestMapper;


    }




    @Override
    public SimpleResponse signUp(SignUpRequest signUpRequest) throws AlreadyExistException {
        if (userRepository.existsByEmail(signUpRequest.getEmail())) {
            throw new AlreadyExistException("Email уже используется");
        }
        User newUser = signUpRequestMapper.toEntity(signUpRequest);
     //   System.out.println(newUser);
        newUser.setRole(Role.USER);
        newUser.setPassword(passwordEncoder.encode(signUpRequest.getPassword()));
        newUser.setSignUpDate(ZonedDateTime.now());
        //   System.out.println("\n\t+++"+newUser);
        userRepository.save(newUser);
        Basket basket = new Basket();
        basket.setUser(newUser);
        basket.setProducts(new ArrayList<>());
        basketRepo.save(basket);
        return new SimpleResponse(HttpStatus.CREATED,"Success ! You Can Log In Now!");
    }

    @Override
    public SignInResponse signIn(SignInRequest signInRequest) {
        Authentication auth = authManager.authenticate(
                new UsernamePasswordAuthenticationToken(signInRequest.email(), signInRequest.password())
        );

        UserDetails userDetails = (UserDetails) auth.getPrincipal();
        String email = userDetails.getUsername();
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Пользователь не найден"));


        String token = jwtUtil.generateToken(user);

        return new SignInResponse(user.getId(), token,user.getRole());
    }
}
