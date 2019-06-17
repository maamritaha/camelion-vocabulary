package org.camelion.vocabulary.endpoint;

import lombok.RequiredArgsConstructor;
import org.camelion.vocabulary.exception.VocabularyNotFoundException;
import org.camelion.vocabulary.dto.VocabularyDto;
import org.camelion.vocabulary.service.VocabularyService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * VocabularyController package all APIs for vocabulary entity
 *
 * @author Taha MAAMRI
 * @since 1.0
 */
@RestController
@RequestMapping(VocabularyController.PATH)
@RequiredArgsConstructor
public class VocabularyController {

    public static final String PATH = "/api/v1/entities/vocabulary";
    private final VocabularyService vocabularyService;


    public List<VocabularyDto> getAll() {
        return vocabularyService.getAll();
    }

    @GetMapping("/findByCode/{code}")
    public VocabularyDto findByCode(@PathVariable("code") String code) throws VocabularyNotFoundException {
        return vocabularyService.findByCode(code);
    }

    @PostMapping("/create")
    public VocabularyDto create(@RequestBody VocabularyDto vocabularyDto){
        return vocabularyService.create(vocabularyDto);
    }


}
