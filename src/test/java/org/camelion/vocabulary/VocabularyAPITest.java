package org.camelion.vocabulary;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.camelion.vocabulary.dto.VocabularyDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@RunWith(SpringRunner.class)
@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
        classes = VocabularyApplication.class
)
@AutoConfigureMockMvc
public class VocabularyAPITest {

    @Autowired
    MockMvc mockMvc;



    @Test
    public void getAll() throws Exception {

        VocabularyDto vocabularyDto = new VocabularyDto();
        vocabularyDto.setCode("Test");
        vocabularyDto.setLabel("test vocabulary");
        vocabularyDto.setDescription("test vocabulary");

        ObjectMapper objectMapper = new ObjectMapper();


        String jsonParam = objectMapper.writeValueAsString(vocabularyDto);
        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/entities/vocabulary/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonParam)).andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());

        mockMvc.perform(MockMvcRequestBuilders
                .get("/api/v1/entities/vocabulary/getAll").
                        accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());


    }
}
