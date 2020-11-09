package br.com.bct.usuario.anotation.validator;

import br.com.bct.usuario.anotation.ValidPassword;
import com.google.common.base.Joiner;
import org.passay.*;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Arrays;

public class PasswordConstraintValidator implements ConstraintValidator<ValidPassword, String> {

    @Override
    public void initialize(ValidPassword arg0) {
        //Não faz nada ao inicializar
    }

    @Override
    public boolean isValid(String password, ConstraintValidatorContext context) {
        PasswordValidator validator = new PasswordValidator(Arrays.asList(
                // deve ter entre 8 a 16 caracteres
                new LengthRule(6, 2147483647),

                // deve ter pelos menos 1 letra maiuscula
                new CharacterRule(EnglishCharacterData.UpperCase, 1),

                // deve ter pelo menos 1 caractere minuscula
                new CharacterRule(EnglishCharacterData.LowerCase, 1),

                // dete ter pelo menos 1 digito numerico
                new CharacterRule(EnglishCharacterData.Digit, 1),

                // deve ter pelo menos um caractere especial
                new CharacterRule(EnglishCharacterData.Special, 1)
        ));

        RuleResult result = validator.validate(new PasswordData(password));
        if (result.isValid()) {
            return true;
        }
        context.disableDefaultConstraintViolation();
        context.buildConstraintViolationWithTemplate(
                Joiner.on(",").join(validator.getMessages(result)))
                .addConstraintViolation();
        return false;
    }
}
