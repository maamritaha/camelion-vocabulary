package org.camelion.vocabulary.exception;

public class VocabularyNotFoundException extends RuntimeException {

    public VocabularyNotFoundException(Integer id) {
        super("could not find vocabulary for the id : " + id);
    }

    public VocabularyNotFoundException(String code) {
        super("could not find vocabulary for the code : " + code);
    }

    public VocabularyNotFoundException() {
        super("could not find vocabulary");
    }

    public VocabularyNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public VocabularyNotFoundException(Throwable cause) {
        super(cause);
    }
}
