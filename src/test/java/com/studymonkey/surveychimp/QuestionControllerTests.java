package com.studymonkey.surveychimp;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.studymonkey.surveychimp.entity.questions.Question;
import com.studymonkey.surveychimp.entity.questions.QuestionType;
import com.studymonkey.surveychimp.entity.wrapper.McQuestionWrapper;
import com.studymonkey.surveychimp.entity.wrapper.QuestionWrapper;
import com.studymonkey.surveychimp.entity.wrapper.RangeQuestionWrapper;
import com.studymonkey.surveychimp.entity.wrapper.TextQuestionWrapper;
import com.studymonkey.surveychimp.viewControllers.SurveyViewController;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.ui.Model;

import java.lang.reflect.Array;
import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@DirtiesContext
public class QuestionControllerTests {
    @LocalServerPort
    private int port;

    @Autowired
    private SurveyViewController controller;

    @Autowired
    private MockMvc mockMvc;

    private ObjectMapper objectMapper = new ObjectMapper();

    @Test
    void contextLoads() {
        assertThat(controller).isNotNull();
    }

    @BeforeAll
    public void setup() throws Exception {
        // Create the Survey first
        mockMvc.perform(post("/surveyV2/surveycreation")
                .param("name", "Test Survey")
                .param("description", "This is a test")
                .param("status", "1")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED))
                .andExpect(status().isOk());
    }

    @Test
    public void getQuestionFormTest() throws Exception{
        mockMvc.perform(get("/question")
                .param("surveyId", "1")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED))
                .andDo(print()).andExpect(status().isOk());
    }

    @Test
    public void getQuestionListTest() throws Exception{
        mockMvc.perform(get("/question/questionList")
                .param("surveyId", "1")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED))
                .andDo(print()).andExpect(status().isOk());
    }

    @Test
    public void createTextQuestionTest() throws Exception{
        TextQuestionWrapper wrapper = new TextQuestionWrapper(1L, "Test Question", QuestionType.TEXT);
        String questionJSON = objectMapper.writeValueAsString(wrapper);
        mockMvc.perform(post("/question")
                .param("questionType", "TEXT")
                .content(questionJSON)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void createMcQuestionTest() throws Exception{
        ArrayList<String> options = new ArrayList<String>();
        options.add("Option 1");
        options.add("Option 2");
        McQuestionWrapper wrapper = new McQuestionWrapper(0L, "Test Question", QuestionType.MULTIPLE_CHOICE);
        wrapper.setOptions(options);
        String questionJSON = objectMapper.writeValueAsString(wrapper);
        mockMvc.perform(post("/question")
                .param("questionType", "MULTIPLE_CHOICE")
                .content(questionJSON)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void createRangeQuestionTest() throws Exception{
        RangeQuestionWrapper wrapper = new RangeQuestionWrapper(0,10,0L, "Test Range Question", QuestionType.RANGE);
        String questionJSON = objectMapper.writeValueAsString(wrapper);
        mockMvc.perform(post("/question")
                .param("questionType", "RANGE")
                .content(questionJSON)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

}
