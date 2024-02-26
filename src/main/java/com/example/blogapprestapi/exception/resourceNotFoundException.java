package com.example.blogapprestapi.exception;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


/*

this custom exception is used to use the resourcenotfoundException if any exception happens on api's
*/

/*

The @ResponseStatus annotation takes a single parameter, which is the HTTP status code. It's a convenient way to specify the status code directly within the method declaration rather than explicitly setting it within the method body using the HttpServletResponse object.

Here are a few important HTTP status codes and their corresponding HttpStatus enum constants in Spring:

HttpStatus.OK: 200 OK
HttpStatus.CREATED: 201 Created
HttpStatus.NO_CONTENT: 204 No Content
HttpStatus.BAD_REQUEST: 400 Bad Request
HttpStatus.NOT_FOUND: 404 Not Found
HttpStatus.INTERNAL_SERVER_ERROR: 500 Internal Server Error
You can use these constants to specify the desired HTTP response status in conjunction with the @ResponseStatus annotation.

Overall, @ResponseStatus is a handy annotation in Spring MVC for explicitly declaring the HTTP status codes for controller methods, making the code more readable and expressive.
 */
@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class resourceNotFoundException extends RuntimeException{

    private String resourceName;
    private String fieldName;
    private long fieldValue;

    public resourceNotFoundException(String resourceName, String fieldName, Long fieldValue) {
        //parent class constructor to pass the message to the run time exception class constructor
        super(String.format("%s not found with %s : '%s'", resourceName, fieldName, fieldValue));
        // example message: Post not found with id : 1
        this.resourceName = resourceName;
        this.fieldName = fieldName;
        this.fieldValue = fieldValue;
    }

    public String getResourceName() {
        return resourceName;
    }

    public String getFieldName() {
        return fieldName;
    }

    public long getFieldValue() {
        return fieldValue;
    }

}
