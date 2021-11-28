package ${package}.common;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Iterator;
import java.util.Set;

public class ValidationUtil {

    private ValidationUtil() {
        throw new UnsupportedOperationException();
    }

    private static final Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

    public static String validate(Object obj) {
        Set<ConstraintViolation<Object>> constraintViolations = validator.validate(obj);
        Iterator<ConstraintViolation<Object>> iter = constraintViolations.iterator();
        while (iter.hasNext()) {
            return iter.next().getMessage();
        }
        return "";
    }

}
