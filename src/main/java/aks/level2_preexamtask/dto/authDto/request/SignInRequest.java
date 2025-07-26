package aks.level2_preexamtask.dto.authDto.request;

import aks.level2_preexamtask.validation.annotation.ValidPassword;
import jakarta.validation.constraints.Email;

public record SignInRequest(
        @Email
        String email,
        @ValidPassword
        String password
) {
}
