package com.studymonkey.surveychimp.dao.survey;

import com.studymonkey.surveychimp.entity.survey.CompletedSurvey;
import com.studymonkey.surveychimp.entity.survey.Survey;
import com.studymonkey.surveychimp.entity.survey.SurveyQuestions;

import java.util.List;

public interface SurveyQuestionsDao {
    public List<SurveyQuestions> findAll();
    public SurveyQuestions getSurvey(int surveyId);
    public CompletedSurvey getSurveyAndAnswers(int surveyId, int userId);
    public void insertSurvey(SurveyQuestions survey);
}