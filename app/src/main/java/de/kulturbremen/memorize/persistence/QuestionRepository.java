package de.kulturbremen.memorize.persistence;

import android.content.Context;

import java.util.List;

import de.kulturbremen.memorize.model.QuestionEntity;
import de.kulturbremen.memorize.model.QuizEntity;

/**
 * Repository module for handling data operations.
 */
public class QuestionRepository {
    private AppDatabase appDatabase;

    public QuestionRepository(Context context) {
        appDatabase = AppDatabase.getInstance(context);
    }

    public List<QuestionEntity> getQuestions(QuizEntity quizEntity){
        int quizId = quizEntity.getId();
        return appDatabase.QuestionDao().getQuestionsByQuizId(quizId);
    }

    /**
     * add a question to the database
     * @param questionEntity the question to add
     * @return QuestionEntity.id
     */
    public long addQuestion(QuestionEntity questionEntity) {
        return appDatabase.QuestionDao().insertQuestion(questionEntity);
    }

    public void deleteQuestion(QuestionEntity questionEntity) {
        appDatabase.QuestionDao().deleteQuestion(questionEntity);
    }

    public void deleteQuestions(long quizID) {
        appDatabase.QuestionDao().deleteQuestions(quizID);
    }
}
