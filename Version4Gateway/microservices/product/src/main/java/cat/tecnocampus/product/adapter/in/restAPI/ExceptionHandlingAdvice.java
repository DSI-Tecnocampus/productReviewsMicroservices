package cat.tecnocampus.product.adapter.in.restAPI;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ExceptionHandlingAdvice {

    @ExceptionHandler({ProductDoesNotExistException.class})
    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String objectAlreadyExists(Exception exception) {
        return exception.getMessage();
    }

}
