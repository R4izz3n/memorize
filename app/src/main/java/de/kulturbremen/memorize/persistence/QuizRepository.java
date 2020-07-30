package de.kulturbremen.memorize.persistence;

import android.content.Context;

import java.util.List;

import de.kulturbremen.memorize.model.QuizEntity;
import de.kulturbremen.memorize.persistence.dao.QuizDao;

/**
 * Repository module for handling data operations.
 */
public class QuizRepository {
    private QuizDao quizDao;

    /**
     * constructor for business (or ui) layer
     * @param context application Context
     */
    public QuizRepository(Context context) {
        AppDatabase appDatabase = AppDatabase.getInstance(context);
        quizDao = appDatabase.QuizDao();
    }

    /**
     * constructor for testing
     * @param quizDao a quizDao from a test database
     */
    public QuizRepository(QuizDao quizDao){
        this.quizDao = quizDao;
    }


    public List<QuizEntity> getQuizzes(){
        return quizDao.getQuizzes();
    }

    /**
     * Add a quiz to the database
     * @param quizEntity The quiz to add to the database
     * @return QuizEntity.id
     */
    public long addQuiz(QuizEntity quizEntity){
        return quizDao.insertQuiz(quizEntity);
    }

    public void deleteQuiz(QuizEntity quizEntity){
        quizDao.deleteQuiz(quizEntity);
    }

}
