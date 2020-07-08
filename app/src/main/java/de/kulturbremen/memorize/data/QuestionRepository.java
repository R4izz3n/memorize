package de.kulturbremen.memorize.data;

import android.content.Context;

import java.util.List;

import de.kulturbremen.memorize.data.entity.QuestionEntity;
import de.kulturbremen.memorize.data.entity.QuizEntity;

/**
 * Repository module for handling data operations.
 */
public class QuestionRepository {

    private AppDatabase appDatabase;

    public QuestionRepository(Context context) {
        appDatabase = AppDatabase.getInstance(context);
    }

    public List<QuestionEntity> getQuestions(QuizEntity quizEntity){
        String quizName = quizEntity.getName();
        return appDatabase.QuestionDao().getQuestions(quizName);
    }

    public void addQuestion(QuestionEntity questionEntity) {
        appDatabase.QuestionDao().insertQuestion(questionEntity);
    }

    public void deleteQuestion(QuestionEntity questionEntity) {
        appDatabase.QuestionDao().deleteQuestion(questionEntity);
    }
}
