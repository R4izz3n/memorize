package de.kulturbremen.memorize.persistence;

import android.content.Context;

import java.util.List;

import de.kulturbremen.memorize.model.QuestionEntity;
import de.kulturbremen.memorize.model.QuizEntity;
import de.kulturbremen.memorize.persistence.dao.QuestionDao;

/**
 * Repository module for handling data operations.
 */
public class QuestionRepository {
    private QuestionDao questionDao;

    /**
     * constructor to be used from business / ui layer
     * @param context application context
     */
    public QuestionRepository(Context context) {
        AppDatabase appDatabase = AppDatabase.getInstance(context);
        this.questionDao = appDatabase.QuestionDao();
    }

    /**
     * constructor used for testing
     * @param questionDao for querying database
     */
    public QuestionRepository(QuestionDao questionDao){
        this.questionDao = questionDao;
    }

    public List<QuestionEntity> getQuestions(QuizEntity quizEntity){
        long quizId = quizEntity.getId();
        return questionDao.getQuestionsByQuizId(quizId);
    }

    /**
     * add a question to the database
     * @param questionEntity the question to add
     * @return QuestionEntity.id
     */
    public long addQuestion(QuestionEntity questionEntity) {
        return questionDao.insertQuestion(questionEntity);
    }

    public void deleteQuestion(QuestionEntity questionEntity) {
        questionDao.deleteQuestion(questionEntity);
    }

    public void deleteQuestions(QuizEntity quizEntity){
        questionDao.deleteQuestions(quizEntity.getId());
    }
}
