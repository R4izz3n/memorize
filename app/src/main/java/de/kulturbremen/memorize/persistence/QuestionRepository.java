package de.kulturbremen.memorize.persistence;

import android.content.Context;

import java.util.List;

import de.kulturbremen.memorize.model.QuestionEntity;
import de.kulturbremen.memorize.model.QuizEntity;

/**
 * Repository module for handling data operations.
 */
public class QuestionRepository {
    public static final String TAG = "QuestionRepository";
    private AppDatabase appDatabase;

    public QuestionRepository(Context context) {
        appDatabase = AppDatabase.getInstance(context);
    }

    public List<QuestionEntity> getQuestions(QuizEntity quizEntity){
        int quizId = quizEntity.getId();
        return appDatabase.QuestionDao().getQuestionsByQuizId(quizId);
    }

    public void addQuestion(QuestionEntity questionEntity) {
        appDatabase.QuestionDao().insertQuestion(questionEntity);
    }

    public void deleteQuestion(QuestionEntity questionEntity) {
        appDatabase.QuestionDao().deleteQuestion(questionEntity);
    }
}
