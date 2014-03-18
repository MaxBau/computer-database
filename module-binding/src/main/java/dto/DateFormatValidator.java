package dto;

import java.lang.annotation.Annotation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.Payload;

import org.springframework.context.MessageSource;
import org.springframework.context.MessageSourceAware;
import org.springframework.context.i18n.LocaleContextHolder;

public class DateFormatValidator implements ConstraintValidator<DateFormat, String>, MessageSourceAware {

	private MessageSource messageSource;
	 
	public void setMessageSource(MessageSource messageSource) {
		this.messageSource = messageSource;
	}

	public void initialize(DateFormat constraintAnnotation) {

	}

	public boolean isValid(String object, ConstraintValidatorContext constraintContext) {

		if (!object.matches(messageSource.getMessage("date.regexp", null, LocaleContextHolder.getLocale()))) {
			return false;
		}
		
		return true;

	}

	public Class<? extends Annotation> annotationType() {
		// TODO Auto-generated method stub
		return null;
	}

	public String message() {
		// TODO Auto-generated method stub
		return null;
	}

	public Class<?>[] groups() {
		// TODO Auto-generated method stub
		return null;
	}

	public Class<? extends Payload>[] payload() {
		// TODO Auto-generated method stub
		return null;
	}

}
