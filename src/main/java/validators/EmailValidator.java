/*
 * A Validator class for email
 */
package validators;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

/**
 *
 * A validator class to validate the email address provided by the users
 */
@FacesValidator("validators.EmailValidator")
public class EmailValidator implements Validator {

    private static final String EMAIL_PATTERN = "^[_A-Za-z0-9-]+(\\."
            + "[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*"
            + "(\\.[A-Za-z]{2,})$";

    private Pattern pattern;
    private Matcher matcher;

    /**
     * Default constructor
     */
    public EmailValidator() {
        pattern = Pattern.compile(EMAIL_PATTERN);
    }

    
    @Override
    public void validate(FacesContext context, UIComponent component,
            Object value) throws ValidatorException {

        matcher = pattern.matcher(value.toString());
        if (!matcher.matches()) {

            FacesMessage msg
                    = new FacesMessage("E-mail validation failed." +
              "Please provide E-mail address in this format: abcd@abc.com" );
            msg.setSeverity(FacesMessage.SEVERITY_ERROR);
            throw new ValidatorException(msg);

        }

    }
}
