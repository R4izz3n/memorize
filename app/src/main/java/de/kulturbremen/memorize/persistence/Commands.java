package de.kulturbremen.memorize.persistence;

import android.content.Context;

import de.kulturbremen.memorize.models.Question;
import de.kulturbremen.memorize.models.Quiz;

public class Commands {

    private AppDatabase appDatabase;

    public Commands(Context context) {
        appDatabase = AppDatabase.getInstance(context);
    }

    public void getQuizzes(){}

    public void addQuiz(Quiz quiz){}

    public void deleteQuiz(Quiz quiz){}

    public void updateQuiz(){}

    public void getQuestions(Quiz quiz){}

    public void addQuestion(Question question) {}

    public void deleteQuestion(Question question) {}
}
