package br.com.bct.usuario.anotation;

import br.com.bct.usuario.anotation.validator.PasswordConstraintValidator;
import br.com.bct.usuario.model.constants.EXCEPTION;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Documented
@Constraint(validatedBy = PasswordConstraintValidator.class)
@Target({TYPE, FIELD, ANNOTATION_TYPE})
@Retention(RUNTIME)
public @interface ValidPassword {

    String message() default EXCEPTION.SENHA_INVALIDA;

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
