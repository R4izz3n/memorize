package de.kulturbremen.memorize.manager;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

import de.kulturbremen.memorize.persistence.QuizRepository;
import de.kulturbremen.memorize.model.QuizEntity;

public class QuizManager {
    private QuizRepository quizRepository;

    public QuizManager(Context context) {
        this.quizRepository = new QuizRepository(context);
    }

    public List<QuizEntity> getQuizzes() {
        List<QuizEntity> quizzes = quizRepository.getQuizzes();
        if (quizzes == null) {
            return new ArrayList<>();
        } else {
            return quizzes;
        }
    }
}
