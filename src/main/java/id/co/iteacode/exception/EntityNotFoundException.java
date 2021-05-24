package id.co.iteacode.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=HttpStatus.NOT_FOUND, reason="No such Keyword")
public class EntityNotFoundException extends RuntimeException {

	private static final long serialVersionUID = -3064632472265167426L;

	public EntityNotFoundException(String message) {
        super(message);
    }

   

}