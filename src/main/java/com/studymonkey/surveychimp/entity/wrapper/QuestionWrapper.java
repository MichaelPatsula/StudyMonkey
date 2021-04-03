package com.studymonkey.surveychimp.entity.wrapper;

import com.studymonkey.surveychimp.entity.questions.Question;

public class QuestionWrapper {

    private long surveyId;
    private long questionId;

    public long getQuestionId() {
        return questionId;
    }

    public void setQuestionId(long questionId) {
        this.questionId = questionId;
    }

    private int min;
    private int max;


    public int getMin() {
        return min;
    }

    public void setMin(int min) {
        this.min = min;
    }

    public int getMax() {
        return max;
    }

    public void setMax(int max) {
        this.max = max;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    private Question question;

    public QuestionWrapper(){
    }

    public QuestionWrapper(long surveyId, Question question){
        this.surveyId = surveyId;
        this.question = question;
    }

    public QuestionWrapper(long surveyId, long questionId, int min, int max, Question question){
        this.surveyId = surveyId;
        this.question = question;
        this.questionId = questionId;
        this.min = min;
        this.max = max;

    }

    public long getSurveyId() {
        return surveyId;
    }

    public void setSurveyId(long surveyId) {
        this.surveyId = surveyId;
    }
}
