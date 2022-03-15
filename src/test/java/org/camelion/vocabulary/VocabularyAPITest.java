package org.camelion.vocabulary;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.camelion.vocabulary.dto.VocabularyDto;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;



@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
        classes = VocabularyApplication.class
)
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class VocabularyAPITest {

    @Autowired
    MockMvc mockMvc;

    @Test
    @Order(1)
    public void create() throws Exception{
        ObjectMapper objectMapper = new ObjectMapper();
        VocabularyDto vocabularyDto = new VocabularyDto();
        vocabularyDto.setCode("Test");
        vocabularyDto.setLabel("test vocabulary");
        vocabularyDto.setDescription("test vocabulary");
        String jsonParam = objectMapper.writeValueAsString(vocabularyDto);
        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/entities/vocabulary/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonParam)).andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("code", is("Test")))
                .andExpect(jsonPath("label", is("test vocabulary")))
                .andExpect(jsonPath("description", is("test vocabulary")))
                .andDo(MockMvcResultHandlers.print());
        vocabularyDto.setCode("Test 1");
        vocabularyDto.setLabel("test vocabulary 1");
        vocabularyDto.setDescription("test vocabulary 1");
        jsonParam = objectMapper.writeValueAsString(vocabularyDto);
        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/entities/vocabulary/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonParam)).andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("code", is("Test 1")))
                .andExpect(jsonPath("label", is("test vocabulary 1")))
                .andExpect(jsonPath("description", is("test vocabulary 1")))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    @Order(2)
    public void getAll() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                .get("/api/v1/entities/vocabulary/getAll")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$" , hasSize(2)))
                .andExpect(jsonPath("$[0].code", is("Test")))
                .andExpect(jsonPath("$[0].label", is("test vocabulary")))
                .andExpect(jsonPath("$[0].description", is("test vocabulary")))
                .andExpect(jsonPath("$[1].code", is("Test 1")))
                .andExpect(jsonPath("$[1].label", is("test vocabulary 1")))
                .andExpect(jsonPath("$[1].description", is("test vocabulary 1")))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    @Order(3)
    public void findByCode()throws Exception{
        mockMvc.perform(MockMvcRequestBuilders
                .get("/api/v1/entities/vocabulary//findByCode/{code}", "Test")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("code", is("Test")))
                .andExpect(jsonPath("label", is("test vocabulary")))
                .andExpect(jsonPath("description", is("test vocabulary")))
                .andDo(MockMvcResultHandlers.print());

    }

    @Test
    @Order(4)
    public void vocabularyNotFind()throws Exception{
        mockMvc.perform(MockMvcRequestBuilders
                .get("/api/v1/entities/vocabulary//findByCode/{code}", "not_exist_code")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isNotFound())
                .andDo(MockMvcResultHandlers.print());

    }
}
