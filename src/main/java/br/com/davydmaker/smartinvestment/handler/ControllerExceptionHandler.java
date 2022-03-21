package br.com.davydmaker.smartinvestment.handler;

import br.com.davydmaker.smartinvestment.domain.ResponseRequest;
import br.com.davydmaker.smartinvestment.exception.BusinessException;
import br.com.davydmaker.smartinvestment.exception.SystemException;
import com.fasterxml.jackson.databind.JsonMappingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;

@RestControllerAdvice
public class ControllerExceptionHandler {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @ExceptionHandler(value = {JsonMappingException.class, HttpMessageNotReadableException.class})
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ResponseRequest jsonMappingException(Exception ex, WebRequest request) {
        logger.error("jsonMappingException: {}", ex.getMessage());
        return ResponseRequest.builder()
                .timestamp(new Date())
                .message("JSON mal formatado.")
                .description(request.getDescription(false))
                .build();
    }

    @ExceptionHandler(value = {MethodArgumentNotValidException.class})
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ResponseRequest methodArgumentNotValidException(MethodArgumentNotValidException ex, WebRequest request) {
        return ResponseRequest.builder()
                .timestamp(new Date())
                .message(ex.getBindingResult().getFieldError().getDefaultMessage())
                .description(request.getDescription(false))
                .build();
    }

    @ExceptionHandler(BusinessException.class)
    @ResponseStatus(value = HttpStatus.PRECONDITION_FAILED)
    public ResponseRequest businessException(BusinessException ex, WebRequest request) {
        return ResponseRequest.builder()
                .timestamp(new Date())
                .message("Falha no Processamento")
                .description(ex.getMessage())
                .build();
    }

    @ExceptionHandler(SystemException.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseRequest systemException(SystemException ex, WebRequest request) {
        return ResponseRequest.builder()
                .timestamp(new Date())
                .message("Erro Inesperado no Sistema")
                .description(ex.getMessage())
                .build();
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseRequest globalExceptionHandler(Exception ex, WebRequest request) {
        logger.error("globalExceptionHandler: {}", ex.getMessage());
        return ResponseRequest.builder()
                .timestamp(new Date())
                .message("Erro Interno no Sistema")
                .description(request.getDescription(false))
                .build();
    }

}