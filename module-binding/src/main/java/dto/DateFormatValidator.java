package dto;

import java.lang.annotation.Annotation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.Payload;

import org.joda.time.LocalDate;
import org.joda.time.format.DateTimeFormat;
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

		if (object.length() != 10) {
			constraintContext.buildConstraintViolationWithTemplate("{DateSize}").addConstraintViolation();
			return false;
		}
		if (!object.matches(messageSource.getMessage("date.regexp", null, LocaleContextHolder.getLocale()))) {
			constraintContext.buildConstraintViolationWithTemplate("{DateFormat}").addConstraintViolation();

			return false;
		}

		LocalDate date = LocalDate.parse(object, DateTimeFormat.forPattern(messageSource.getMessage("date.format", null, LocaleContextHolder.getLocale())));

		if (date.getYear() < 1970) {
			constraintContext.buildConstraintViolationWithTemplate("{DateLimitMin}").addConstraintViolation();

			return false;
		}
		
		if (date.getMonthOfYear()==02 && date.getDayOfMonth()>28) {
			constraintContext.buildConstraintViolationWithTemplate("{DateImpossible}").addConstraintViolation();

			return false;
		}
		return true;

	}

	public Class<? extends Annotation> annotationType() {
		return null;
	}

	public String message() {
		return null;
	}

	public Class<?>[] groups() {
		return null;
	}

	public Class<? extends Payload>[] payload() {
		return null;
	}

}
