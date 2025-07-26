package aks.level2_preexamtask.dto.authDto.response;

import aks.level2_preexamtask.enums.Role;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class SignInResponse {
    Long id;
    String token;
    Role role;

    public SignInResponse(Long id, String token, Role role) {
        this.id = id;
        this.token = token;
        this.role = role;
    }

    public SignInResponse() {
    }
}
