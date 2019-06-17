package org.camelion.vocabulary.advice;

import org.camelion.vocabulary.Exception.VocabularyNotFoundException;
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
public class VocabularyAdvice {

    /**
     *  Handel the VocabularyNotFoundException
     * @param e the exception to handel
     * @return the rest message
     */
    @ResponseBody
    @ExceptionHandler(VocabularyNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String vocabularyNotfoundHandler(VocabularyNotFoundException e){
        return e.getMessage();
    }
}
