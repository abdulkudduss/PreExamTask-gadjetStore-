package aks.level2_preexamtask.validation.annotation;

import aks.level2_preexamtask.validation.validator.PasswordValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = PasswordValidator.class)
@Target({ ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER,ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidPassword {

    String message() default "Пароль должен содержать минимум 8 символов, заглавную и строчную букву, цифру и специальный символ";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}