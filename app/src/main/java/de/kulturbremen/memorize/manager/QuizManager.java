package de.kulturbremen.memorize.manager;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

import de.kulturbremen.memorize.model.QuestionEntity;
import de.kulturbremen.memorize.model.QuestionModel;
import de.kulturbremen.memorize.persistence.QuestionRepository;
import de.kulturbremen.memorize.persistence.QuizRepository;
import de.kulturbremen.memorize.model.QuizEntity;

public class QuizManager {
    private QuizRepository quizRepository;
    private QuestionRepository questionRepository;

    public QuizManager(Context context) {
        this.quizRepository = new QuizRepository(context);
        this.questionRepository = new QuestionRepository(context);
    }

    public List<QuizEntity> getQuizzes() {
        List<QuizEntity> quizzes = quizRepository.getQuizzes();
        if (quizzes == null) {
            return new ArrayList<>();
        } else {
            return quizzes;
        }
    }

    public void addQuiz(String quizName, List<QuestionModel> questions){
        QuizEntity quiz = new QuizEntity(quizName.trim());
        long quizID = quizRepository.addQuiz(quiz);

        for (QuestionModel questionModel: questions){
            String question = questionModel.getQuestion().trim();
            String answer = questionModel.getAnswer().trim();
            if (question.equals("") && answer.equals("")){
                continue;
            }
            QuestionEntity questionEntity = new QuestionEntity(question, answer, quizID);
            questionRepository.addQuestion(questionEntity);
        }
    }
}
