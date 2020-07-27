package de.kulturbremen.memorize.manager;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

import de.kulturbremen.memorize.model.QuestionEntity;
import de.kulturbremen.memorize.persistence.QuestionRepository;
import de.kulturbremen.memorize.persistence.QuizRepository;
import de.kulturbremen.memorize.model.QuizEntity;

public class QuizManager {
    private QuizRepository quizRepository;
    private QuestionRepository questionRepository;
    private Context context;

    public QuizManager(Context context) {
        this.context = context;
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

    public void addQuiz(String quizName, List<QuestionEntity> questions){
        QuizEntity quiz = new QuizEntity(quizName.trim());
        long quizID = quizRepository.addQuiz(quiz);

        for (QuestionEntity questionEntity: questions){
            String question = questionEntity.getQuestion().trim();
            String answer = questionEntity.getAnswer().trim();
            if (question.equals("") && answer.equals("")){
                continue;
            }
            questionEntity.setQuestion(question);
            questionEntity.setAnswer(answer);
            questionEntity.setQuizId(quizID);
            questionRepository.addQuestion(questionEntity);
        }
    }

    public List<QuestionEntity> getQuizData(QuizEntity quiz){

        QuestionRepository questionRepository = new QuestionRepository(context);
        return questionRepository.getQuestions(quiz);

    }
}
