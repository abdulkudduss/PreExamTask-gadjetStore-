package aks.level2_preexamtask.validation.validator;

import aks.level2_preexamtask.dto.authDto.request.SignUpRequest;
import aks.level2_preexamtask.validation.annotation.ValidPassword;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class PasswordValidator implements ConstraintValidator<ValidPassword, String> {

    private static final String PASSWORD_PATTERN =
            "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=!?.*_-])(?=\\S+$).{8,}$";

    @Override
    public boolean isValid(String password, ConstraintValidatorContext context) {
        if (password == null) return false;

        if (!password.matches(PASSWORD_PATTERN)) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(
                    "Пароль должен содержать минимум 8 символов, заглавную и строчную букву, цифру и специальный символ"
            ).addConstraintViolation();
            return false;
        }

        return true;
    }
}
