package de.kulturbremen.memorize.persistence;

import android.content.Context;
import android.util.Log;

import java.util.List;

import de.kulturbremen.memorize.model.QuizEntity;

/**
 * Repository module for handling data operations.
 */
public class QuizRepository {
    private AppDatabase appDatabase;

    public QuizRepository(Context context) {
        appDatabase = AppDatabase.getInstance(context);
    }


    public List<QuizEntity> getQuizzes(){
        return appDatabase.QuizDao().getQuizzes();
    }

    /**
     * Add a quiz to the database
     * @param quizEntity The quiz to add to the database
     * @return QuizEntity.id
     */
    public long addQuiz(QuizEntity quizEntity){
        return appDatabase.QuizDao().insertQuiz(quizEntity);
    }

    public void deleteQuiz(QuizEntity quizEntity){
        appDatabase.QuizDao().deleteQuiz(quizEntity);
    }

    public void updateQuiz(QuizEntity quizEntity){
        appDatabase.QuizDao().updateQuiz(quizEntity);
    }

}
