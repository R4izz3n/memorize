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

    /**
     * Either add or update a quiz with questions to the database
     * @param quiz the QuizEntity to be edited
     * @param questions the QuestionEntities to be edited
     */
    public void editQuiz(QuizEntity quiz, List<QuestionEntity> questions){
        quiz.setName(quiz.getName().trim());
        if (quiz.getId() != 0) {
            quizRepository.deleteQuiz(quiz);
        }
        quizRepository.addQuiz(quiz);

        questionRepository.deleteQuestions(quiz);
        for (QuestionEntity questionEntity: questions){
            String question = questionEntity.getQuestion().trim();
            String answer = questionEntity.getAnswer().trim();
            if (question.equals("") && answer.equals("")){
                continue;
            }
            questionEntity.setQuestion(question);
            questionEntity.setAnswer(answer);
            questionEntity.setQuizId(quiz.getId());
            questionRepository.addQuestion(questionEntity);
            String temp = "String";
            StringBuilder newString = new StringBuilder();
            for (int i = 0; i < temp.length(); i++){
                newString.insert(0, temp.charAt(i));
            }
            StringBuilder bla = new StringBuilder("nahahaha");
            bla.reverse();
            "balbbbb".split("");
        }
    }

    /**
     * Delete a QuizEntity and the corresponding QuizEntities
     * @param quiz the quiz to be deleted
     */
    public void deleteQuiz(QuizEntity quiz) {
        questionRepository.deleteQuestions(quiz);
        quizRepository.deleteQuiz(quiz);
    }

    public List<QuestionEntity> getQuestions(QuizEntity quiz){
        return questionRepository.getQuestions(quiz);
    }
}
