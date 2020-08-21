package de.kulturbremen.memorize.manager;

import android.content.Context;
import android.util.Log;

import java.util.List;

import de.kulturbremen.memorize.model.QuestionEntity;
import de.kulturbremen.memorize.model.QuizEntity;
import de.kulturbremen.memorize.persistence.QuestionRepository;
import de.kulturbremen.memorize.persistence.QuizRepository;

/**
 * business layer class to access or update data
 * related to the models QuestionEntity and QuizEntity
 */
public class QuizManager {
    private QuizRepository quizRepository;
    private QuestionRepository questionRepository;

    /**
     * constructor for ui layer
     * @param context application context
     */
    public QuizManager(Context context) {
        this.quizRepository = new QuizRepository(context);
        this.questionRepository = new QuestionRepository(context);
    }

    /**
     * constructor for testing
     * @param quizRepository a stand in object for QuizRepository
     * @param questionRepository a stand in object for QuestionRepository
     */
    public QuizManager(QuizRepository quizRepository, QuestionRepository questionRepository){
        this.quizRepository = quizRepository;
        this.questionRepository = questionRepository;
    }

    public List<QuizEntity> getQuizzes() {
        return quizRepository.getQuizzes();
    }

    public List<QuestionEntity> getQuestions(QuizEntity quiz){
        return questionRepository.getQuestions(quiz);
    }

    /**
     * Either add or update a quiz with questions to the database
     * @param quiz the QuizEntity to be edited
     * @param questions the QuestionEntities to be edited
     */
    public void editQuizAndQuestions(QuizEntity quiz, List<QuestionEntity> questions){
        deleteQuizAndQuestions(quiz);

        long quizID = quizRepository.addQuiz(quiz);

        for (QuestionEntity questionEntity: questions){
            if (questionEntity.getQuestion().equals("") && questionEntity.getAnswer().equals("")){
                continue;
            }
            questionEntity.setQuizId(quizID);
            questionRepository.addQuestion(questionEntity);
        }
    }

    /**
     * Delete a QuizEntity and the corresponding QuestionEntities
     * @param quiz the quiz to be deleted
     */
    public void deleteQuizAndQuestions(QuizEntity quiz) {
        questionRepository.deleteQuestions(quiz);
        quizRepository.deleteQuiz(quiz);
    }
}
