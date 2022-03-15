package org.camelion.vocabulary.endpoint;

import org.camelion.vocabulary.exception.VocabularyNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * For handling exceptions occurred in vocabulary APIs
 *
 * @Author Taha MAAMRI
 * @since 1.0
 */
@ControllerAdvice
public class VocabularyControllerAdvice {

    Logger logger = LoggerFactory.getLogger(VocabularyControllerAdvice.class);

    /**
     * Handel the VocabularyNotFoundException
     *
     * @param e the exception to handel
     * @return the rest message
     */
    @ResponseBody
    @ExceptionHandler(VocabularyNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String vocabularyNotfoundExceptionHandler(VocabularyNotFoundException e) {
        logger.error(e.getMessage(),e);
        return e.getMessage();
    }

    /**
     * Handel unexpected exceptions
     *
     * @param e the exception to handel
     * @return the rest message
     */
    @ResponseBody
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public String exceptionHandler(Exception e) {
        logger.error(e.getMessage(),e);
        return e.getMessage();
    }

}
