package de.kulturbremen.memorize.data;

import android.content.Context;

import java.util.List;

/**
 * Repository module for handling data operations.
 */
public class QuestionRepository {

    private AppDatabase appDatabase;

    public QuestionRepository(Context context) {
        appDatabase = AppDatabase.getInstance(context);
    }

    public List<Question> getQuestions(Quiz quiz){
        String quizName = quiz.name;
        return appDatabase.QuestionDao().getQuestions(quizName);
    }

    public void addQuestion(Question question) {
        appDatabase.QuestionDao().insertQuestion(question);
    }

    public void deleteQuestion(Question question) {
        appDatabase.QuestionDao().deleteQuestion(question);
    }
}
