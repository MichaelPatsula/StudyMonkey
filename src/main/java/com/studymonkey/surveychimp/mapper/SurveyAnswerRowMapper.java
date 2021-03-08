package com.studymonkey.surveychimp.mapper;

import com.studymonkey.surveychimp.entity.answers.Answer;
import com.studymonkey.surveychimp.entity.answers.McAnswer;
import com.studymonkey.surveychimp.entity.answers.TextAnswer;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SurveyAnswerRowMapper implements RowMapper<Answer> {

    @Override
    public Answer mapRow(ResultSet resultSet, int i) throws SQLException {

        Answer answer = new Answer();
        answer.setSurveyId(resultSet.getInt("survey_id"));
        answer.setQuestionOrder(resultSet.getInt("question_order_id"));
        answer.setUserId(resultSet.getInt("client_id"));

        String text = resultSet.getString("text_answer");
        if(resultSet.wasNull()) {
            TextAnswer textAnswer = (TextAnswer) answer;
            textAnswer.setQuestionAnswer(text);
            return textAnswer;
        }

        int mcOptionId = resultSet.getInt("mc_option_id");
        if(resultSet.wasNull()) {
            McAnswer mcAnswer = (McAnswer) answer;
            mcAnswer.setMcOptionIdAnswer(mcOptionId);
            return mcAnswer;
        }

        return answer;
    }


}