package com.example.demo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import com.example.demo.payloads.ApiResponse;

@RestControllerAdvice
public class GlobalExceptionHandeler 
{

	@ExceptionHandler( ResourceNotFoundException.class)
	public ResponseEntity<ApiResponse> resourseNotFoundExceptionHandler(ResourceNotFoundException ex)
	{
		String umessage = "user not found";
		String message = ex.getMessage();
		ApiResponse apiresponse = new ApiResponse(umessage,message,false);
		return new ResponseEntity<ApiResponse>(apiresponse,HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler( MethodArgumentTypeMismatchException.class)
	public ResponseEntity<ApiResponse> MethodArgumentTypeMismatchExceptionHandler(MethodArgumentTypeMismatchException ex) 
	{
		String message = ex.getMessage();
		String umessage = "enter only number value ";
		ApiResponse apiresponse = new ApiResponse(umessage,message, false);
		return new ResponseEntity<ApiResponse>(apiresponse,HttpStatus.NOT_FOUND);

	}
	
	@ExceptionHandler( NullPointerException.class)
	public ResponseEntity<?> NullPointerExceptionHandler(NullPointerException ex)
	{
		@SuppressWarnings("unused")
		String umessage = "user not found";
		String message = ex.getMessage();
		//ApiResponse apiresponse = new ApiResponse(umessage,message,false);
		return new ResponseEntity<>(message,HttpStatus.NOT_FOUND);
	}
	@ExceptionHandler(value = { Exception.class })
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<?> unKnownException(Exception ex)
    {
        return new ResponseEntity<>( "Employee Not Found",HttpStatus.NOT_FOUND);
    }
	
	
	public ResponseEntity<?> UsernameNotFoundExceptionHandeler(UsernameNotFoundException ex)
	{
		@SuppressWarnings("unused")
		String umessage = "user not found";
		String message = ex.getMessage();
		//ApiResponse apiresponse = new ApiResponse(umessage,message,false);
		return new ResponseEntity<>(message,HttpStatus.NOT_FOUND);
	}

}
