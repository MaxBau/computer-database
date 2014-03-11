package springapp.web;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import springapp.domain.ComputerDTO;


@Component
public class FormValidator implements Validator {

	public boolean supports(Class<?> arg0) {
		return ComputerDTO.class.isAssignableFrom(arg0);  
	
	}

	public void validate(Object arg0, Errors arg1) {
//		ComputerDTO cDto = (ComputerDTO) arg0;
		
		ValidationUtils.rejectIfEmpty(arg1, "name", "The name must be filled");
		ValidationUtils.rejectIfEmpty(arg1, "introduced", "The introduced date must be filled");
		ValidationUtils.rejectIfEmpty(arg1, "discontinued", "The discontinued date must be filled");
		
	}

}
