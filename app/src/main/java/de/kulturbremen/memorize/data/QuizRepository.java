package de.kulturbremen.memorize.data;

import android.content.Context;

import java.util.List;

/**
 * Repository module for handling data operations.
 */
public class QuizRepository {

    private AppDatabase appDatabase;

    public QuizRepository(Context context) {
        appDatabase = AppDatabase.getInstance(context);
    }


    public List<Quiz> getQuizzes(){
        return appDatabase.QuizDao().getQuizzes();
    }

    public void addQuiz(Quiz quiz){
        appDatabase.QuizDao().insertQuiz(quiz);
    }

    public void deleteQuiz(Quiz quiz){
        appDatabase.QuizDao().deleteQuiz(quiz);
    }

    public void updateQuiz(Quiz quiz){
        appDatabase.QuizDao().updateQuiz(quiz);
    }

}
