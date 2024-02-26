package com.example.blogapprestapi.exception;


import com.example.blogapprestapi.payload.exceptionDTO;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

//controller advice annotation used to declare this class as global exception handler, all the exceptions ocuuring in application will first reach here to check whether exception occured has been handled or not

@ControllerAdvice
//ResponseEntityExceptionHandler CLASS used here to handle validation for attributes in object coming from client and give the proper reponse to client
public class globalExceptionHandler extends ResponseEntityExceptionHandler{

    //catching all the exceptions in ONE PLACE by uisg this controller advice global exception class
    //globally handling all the exceptions in one place with custom structure of exceptions using exceptionDTO Class

    //handling resourceNotFoundException specifically and structuring the responseEntity with the details using the exceptionDTO class
    @ExceptionHandler(resourceNotFoundException.class)
    public ResponseEntity<exceptionDTO> handleResourceNotFoundException(resourceNotFoundException exception, WebRequest webRequest){

        exceptionDTO exceptionDTO = new exceptionDTO(new Date(), exception.getMessage(),webRequest.getDescription(false));

        return new ResponseEntity<>(exceptionDTO, HttpStatus.NOT_FOUND);

    }

    @ExceptionHandler(blogApiException.class)
    public ResponseEntity<exceptionDTO> handleblogApiException(blogApiException exception, WebRequest webRequest){

        exceptionDTO exceptionDTO = new exceptionDTO(new Date(), exception.getMessage(),webRequest.getDescription(false));

        return new ResponseEntity<>(exceptionDTO, HttpStatus.BAD_REQUEST);

    }

    //handling and customising common or general exception
    //handling all other exceptions excluding customised exception created specifically like resourceNotFoundException, blogApiException

    @ExceptionHandler(Exception.class)
    public ResponseEntity<exceptionDTO> handleAllOtherExceptions(Exception exception, WebRequest webRequest){

        exceptionDTO exceptionDTO = new exceptionDTO(new Date(), exception.getMessage(),webRequest.getDescription(false));

        return new ResponseEntity<>(exceptionDTO, HttpStatus.INTERNAL_SERVER_ERROR);

    }


    /*
    This exception handler is used to handle the validating the input attributes of any object which is using validation annotation for their attributes

    WITHOUT HANDLING THIS ----> when just extending this ResponseEntityExceptionHandler class will handle this exception automatically by springboot and provide a some structure response with a message

    we need a customising message for all the attributes which is violating validation. so, we are overriding this method which is used for validation handling

     */

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers,
                                                                  HttpStatusCode status,
                                                                  WebRequest request) {
        /*
        return response will be map like data structure
        sending response will have to be like this :

        {
            "description": "Post description should have at least 5 characters", --->> attribute of object and its message
            "title": "Post title should have at least 2 characters",
            "content": "must not be empty"
        }
         */
        //map ds to hold it like key value pair like structure of attributes and its message
        Map<String, String> errors = new HashMap<>();

        ex.getBindingResult().getAllErrors().forEach((error) ->{
            String fieldName = ((FieldError)error).getField();
            String message = error.getDefaultMessage();
            errors.put(fieldName, message);
        });

        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }


    //another way of handling validation error (another way writing above method)

    //when we are not handling errors, it will throw the default  MethodArgumentNotValidException class error while validation.
    //Just catching it and handling it --->> test the createAPI by commenting out these exception handlers of MethodArgumentNotValidException
    //while using this, if this not works, checkout by commenting above function and removing extending to ResponseEntityExceptionHandler class
//    @ExceptionHandler(MethodArgumentNotValidException.class)
//    public ResponseEntity<Object> handleMethodArgumentNotValid2(MethodArgumentNotValidException exception, WebRequest webRequest){
//
//        Map<String, String> errors = new HashMap<>();
//
//        exception.getBindingResult().getAllErrors().forEach((error) ->{
//            String fieldName = ((FieldError)error).getField();
//            String message = error.getDefaultMessage();
//            errors.put(fieldName, message);
//        });
//
//        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
//
//    }


}
