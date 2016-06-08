
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
 * A validator class to validate the zipcode provided is of 4 digit number
 */
@FacesValidator("validators.ZipCodeValidator")
public class ZipCodeValidator implements Validator {

    private static final String ZIP_PATTERN = "[0-9][0-9][0-9][0-9]";

    private Pattern pattern;
    private Matcher matcher;

    /**
     * Default constructor
     */
    public ZipCodeValidator() {
        pattern = Pattern.compile(ZIP_PATTERN);
    }

    @Override
    public void validate(FacesContext context, UIComponent component,
            Object value) throws ValidatorException {

        matcher = pattern.matcher(value.toString());
        if (!matcher.matches()) {

            FacesMessage msg
                    = new FacesMessage("Zip Code validation failed." +
               " Please give 4 digit Zip Code");
            msg.setSeverity(FacesMessage.SEVERITY_ERROR);
            throw new ValidatorException(msg);

        }

    }
}
