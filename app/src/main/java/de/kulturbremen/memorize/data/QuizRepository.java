package de.kulturbremen.memorize.data;

import android.content.Context;

import java.util.List;

import de.kulturbremen.memorize.data.entity.QuizEntity;

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
