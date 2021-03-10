package com.studymonkey.surveychimp.controllers;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.studymonkey.surveychimp.entity.survey.Survey;
import com.studymonkey.surveychimp.entity.survey.SurveyQuestions;
import com.studymonkey.surveychimp.service.SurveyQuestionService;
import com.studymonkey.surveychimp.service.SurveyService;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:3000", maxAge = 3600)
@RestController
@RequestMapping("/survey")
public class SurveyController {

    @Resource
    SurveyService surveyService;

    @Resource
    SurveyQuestionService surveyQuestionService;

    /*
    Example:
    http://localhost:8080/survey/surveyList
     */
    @GetMapping(value = "/surveyList")
    public List<Survey> getSurveys() {
        return surveyService.findAll();
    }

    /*
    Example:
    http://localhost:8080/survey/createSurvey
    {
        "name": "Christophe's Survey 2",
        "description": "Testing 2!",
        "status": "CLOSED"
    }
     */
    @PostMapping(value = "/createSurvey")
    public List<Map<String, Object>> createSurvey(@RequestBody Survey survey) {
        return surveyService.insertSurvey(survey);
    }

    /*
    Example:
    {
        "id": 3,
        "name": "Christophe Updated!!!",
        "description": "CHANGED for the second time",
        "status": "OPEN"
    }
     */
    @PutMapping(value = "/updateSurvey")
    public void updateSurvey(@RequestBody Survey survey) {
        surveyService.updateSurvey(survey);
    }

    // This was not tested yet. Not needed for now
    @PutMapping(value = "/executeUpdateSurvey")
    public void executeUpdateSurvey(@RequestBody Survey survey) {
        surveyService.executeUpdateSurvey(survey);
    }

    /*
    Example:
    http://localhost:8080/survey/deleteSurvey/4
     */
    @DeleteMapping(value = "/deleteSurvey/{id}")
    public void deleteSurvey(@PathVariable int id) {
        surveyService.deleteSurvey(id);
    }

    // Not tested
    @GetMapping(value = "/surveyQuestions/{id}")
    public SurveyQuestions getSurveyQuestions(@PathVariable int id) {
        return surveyQuestionService.findSurvey(id);
    }


}
