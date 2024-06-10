package validator;

import jakarta.faces.application.FacesMessage;
import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.validator.FacesValidator;
import jakarta.faces.validator.ValidatorException;

@FacesValidator("validator")
public class Validator implements jakarta.faces.validator.Validator{


    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
            String input = value.toString();
            if (input.matches(".*\\D.")){
                FacesMessage message= new FacesMessage(
                        "Values must be numeric");
                throw new ValidatorException(message);
            }
            if (Integer.valueOf(input) <-3){
                FacesMessage message= new FacesMessage(
                        "x need be >-3");
                throw new ValidatorException(message);
            }
            
            if (Integer.valueOf(input)>5){
                FacesMessage message= new FacesMessage(
                        "x need be <5");
                throw new ValidatorException(message);
            }
        }
}