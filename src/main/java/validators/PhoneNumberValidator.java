
package validators;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

@FacesValidator("validators.PhoneNumberValidator")
public class PhoneNumberValidator implements Validator {

    private static final String PHONE_NUMBER_PATTERN = "^0[0-9]{9}";

    private Pattern pattern;
    private Matcher matcher;

    public PhoneNumberValidator() {
        pattern = Pattern.compile(PHONE_NUMBER_PATTERN);
    }

    @Override
    public void validate(FacesContext context, UIComponent component,
            Object value) throws ValidatorException {

        matcher = pattern.matcher(value.toString());
        if (!matcher.matches()) {

            FacesMessage msg
                    = new FacesMessage("Phone number validation failed." +
              "Please provide phone number starting 0 followed by 9 digits" );
            msg.setSeverity(FacesMessage.SEVERITY_ERROR);
            throw new ValidatorException(msg);

        }

    }
}

