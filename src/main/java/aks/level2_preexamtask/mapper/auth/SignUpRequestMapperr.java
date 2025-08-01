package aks.level2_preexamtask.mapper.auth;

import aks.level2_preexamtask.dto.authDto.request.SignUpRequest;
import aks.level2_preexamtask.entities.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SignUpRequestMapperr {
    SignUpRequest signUpDto(User user);
    User toEntity(SignUpRequest request);
}
