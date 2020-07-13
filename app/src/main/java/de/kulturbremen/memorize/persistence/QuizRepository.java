package de.kulturbremen.memorize.persistence;

import android.content.Context;
import android.util.Log;

import java.util.List;

import de.kulturbremen.memorize.model.QuizEntity;

/**
 * Repository module for handling data operations.
 */
public class QuizRepository {
    private static final String TAG = "QuizRepository";
    private AppDatabase appDatabase;

    public QuizRepository(Context context) {
        appDatabase = AppDatabase.getInstance(context);
    }


    public List<QuizEntity> getQuizzes(){
        int numberOfColumns = appDatabase.QuizDao().debug_all();
        Log.d(TAG, "getQuizzes: " + numberOfColumns);
        return appDatabase.QuizDao().getQuizzes();
    }

    public void addQuiz(QuizEntity quizEntity){
        appDatabase.QuizDao().insertQuiz(quizEntity);
    }

    public void deleteQuiz(QuizEntity quizEntity){
        appDatabase.QuizDao().deleteQuiz(quizEntity);
    }

    public void updateQuiz(QuizEntity quizEntity){
        appDatabase.QuizDao().updateQuiz(quizEntity);
    }

}
