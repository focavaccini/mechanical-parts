package br.com.inventory.mechanicalparts.controllers.exceptions;

import br.com.inventory.mechanicalparts.exceptions.*;
import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.s3.AmazonS3;
import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.AccountStatusException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.security.SignatureException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ProblemDetail handleSecurityException(Exception exception) {
        ProblemDetail errorDetail = null;

        if (exception instanceof BadCredentialsException) {
            errorDetail = ProblemDetail.forStatusAndDetail(HttpStatus.valueOf(401), exception.getMessage());
            errorDetail.setProperty("description", "The username or password is incorrect.");

            return errorDetail;
        }

        if (exception instanceof BadRequestException) {
            errorDetail = ProblemDetail.forStatus(HttpStatus.valueOf(400));
            errorDetail.setProperty("description", exception.getMessage());

            return errorDetail;
        }

        if (exception instanceof ObjectNotFound) {
            errorDetail = ProblemDetail.forStatus(HttpStatus.valueOf(404));
            errorDetail.setProperty("description", exception.getMessage());

            return errorDetail;
        }

        if (exception instanceof UniqueException) {
            errorDetail = ProblemDetail.forStatus(HttpStatus.valueOf(400));
            errorDetail.setProperty("description", exception.getMessage());

            return errorDetail;
        }

        if (exception instanceof NotFoundException) {
            errorDetail = ProblemDetail.forStatus(HttpStatus.valueOf(404));
            errorDetail.setProperty("description", exception.getMessage());

            return errorDetail;
        }

        if (exception instanceof FileException) {
            errorDetail = ProblemDetail.forStatus(HttpStatus.valueOf(400));
            errorDetail.setProperty("description", exception.getMessage());

            return errorDetail;
        }

        if (exception instanceof DataIntegrityException) {
            errorDetail = ProblemDetail.forStatus(HttpStatus.valueOf(400));
            errorDetail.setProperty("description", exception.getMessage());

            return errorDetail;
        }

        if (exception instanceof AmazonServiceException) {
            errorDetail = ProblemDetail.forStatus(HttpStatus.valueOf(400));
            errorDetail.setProperty("description", exception.getMessage());

            return errorDetail;
        }

        if (exception instanceof AmazonClientException) {
            errorDetail = ProblemDetail.forStatus(HttpStatus.valueOf(400));
            errorDetail.setProperty("description", exception.getMessage());

            return errorDetail;
        }

        if (exception instanceof AmazonS3) {
            errorDetail = ProblemDetail.forStatus(HttpStatus.valueOf(400));
            errorDetail.setProperty("description", exception.getMessage());

            return errorDetail;
        }

        if (exception instanceof AccountStatusException) {
            errorDetail = ProblemDetail.forStatusAndDetail(HttpStatus.valueOf(403), exception.getMessage());
            errorDetail.setProperty("description", "The account is locked.");
        }

        if (exception instanceof AccessDeniedException) {
            errorDetail = ProblemDetail.forStatusAndDetail(HttpStatus.valueOf(403), exception.getMessage());
            errorDetail.setProperty("description", "You are not authorized to access this resource.");
        }

        if (exception instanceof SignatureException) {
            errorDetail = ProblemDetail.forStatusAndDetail(HttpStatus.valueOf(403), exception.getMessage());
            errorDetail.setProperty("description", "The JWT signature is invalid.");
        }

        if (exception instanceof ExpiredJwtException) {
            errorDetail = ProblemDetail.forStatusAndDetail(HttpStatus.valueOf(403), exception.getMessage());
            errorDetail.setProperty("description", "The JWT token has expired.");
        }

        if (errorDetail == null) {
            errorDetail = ProblemDetail.forStatusAndDetail(HttpStatus.valueOf(500), exception.getMessage());
            errorDetail.setProperty("description", "Unknown internal server error.");
        }

        return errorDetail;
    }

}