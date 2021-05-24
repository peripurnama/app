package id.co.iteacode.exception;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.NoHandlerFoundException;

@ControllerAdvice
public class CustomExceptionHandler{

	@ExceptionHandler(Exception.class)
    public final ModelAndView handleAllExceptions(Exception ex, WebRequest request) {
		List<String> details = new ArrayList<>();
        details.add(ex.getLocalizedMessage());
        ErrorResponse error = new ErrorResponse(ex.getMessage(), details);
        System.out.println("Error: " + ex.getMessage());
        ModelAndView model = new ModelAndView();     
        model.addObject("exception", error);
        model.setViewName("error/500");
        
        return model;
    }
	
	@ExceptionHandler(NoHandlerFoundException.class)
    public ModelAndView handle(Exception ex) {

		ModelAndView mv = new ModelAndView();
        mv.addObject("exception", ex.getMessage());
        mv.setViewName("error/404");

        return mv;
    }
 
    @ExceptionHandler(EntityNotFoundException.class)
    public final ModelAndView handleUserNotFoundException(EntityNotFoundException ex) {
        List<String> details = new ArrayList<>();
        details.add(ex.getLocalizedMessage());
        ErrorResponse error = new ErrorResponse(ex.getMessage(), details);
        
        ModelAndView model = new ModelAndView();     
        model.addObject("exception", error);
        model.setViewName("error/404");
        
        return model;
    }

}
