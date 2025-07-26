package aks.level2_preexamtask.mapper;


import aks.level2_preexamtask.dto.authDto.request.SignInRequest;
import aks.level2_preexamtask.dto.authDto.response.SignInResponse;

import aks.level2_preexamtask.entities.User;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring")
public interface SignInRequestMapper {
  //  @Mapping(target = "fullName", expression = "java(user.getFirstName() + \" \" + user.getLastName())")
    SignInRequest requestDto(User user);
    User toEntity(SignInRequest signInRequest);
}