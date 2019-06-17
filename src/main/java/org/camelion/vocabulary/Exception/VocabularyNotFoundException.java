package org.camelion.vocabulary.Exception;

public class VocabularyNotFoundException extends Exception {

    public VocabularyNotFoundException(Integer id) {
        super("could not find vocabulary for the id : " + id);
    }

    public VocabularyNotFoundException(String code) {
        super("could not find vocabulary for the code : " + code);
    }

    public VocabularyNotFoundException() {
    }
}
