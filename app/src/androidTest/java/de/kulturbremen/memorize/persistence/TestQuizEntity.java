package de.kulturbremen.memorize.persistence;

import android.content.Context;

import androidx.room.Room;
import androidx.test.core.app.ApplicationProvider;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

import de.kulturbremen.memorize.persistence.dao.QuizDao;
import de.kulturbremen.memorize.model.QuizEntity;

import static org.junit.Assert.assertEquals;

@RunWith(AndroidJUnit4.class)
public class TestQuizEntity {
    private QuizDao quizDao;
    private AppDatabase db;
    private QuizEntity testQuiz = new QuizEntity("quiz name");

    @Before
    public void createDb() {
        Context context = ApplicationProvider.getApplicationContext();
        db = Room.inMemoryDatabaseBuilder(context, AppDatabase.class).build();
        quizDao = db.QuizDao();
    }

    @After
    public void closeDb() {
        db.close();
    }

    @Test
    public void addAndGetQuiz() {
        long id = quizDao.insertQuiz(testQuiz);
        QuizEntity quiz = quizDao.getQuizById(id);
        assertEquals(testQuiz.getName(), quiz.getName());
    }

    @Test
    public void addAndGetQuizzes() {
        quizDao.insertQuiz(testQuiz);
        QuizEntity newQuiz = new QuizEntity("another one");
        quizDao.insertQuiz(newQuiz);
        List<QuizEntity> quizzes = quizDao.getQuizzes();
        assertEquals(2, quizzes.size());

    }

    @Test
    public void updateQuiz() {
        String newQuizName = "new quiz";
        // GIVEN a quiz that is already present
        long id = quizDao.insertQuiz(testQuiz);
        // WHEN the quiz is updated
        QuizEntity quiz = quizDao.getQuizById(id);
        quiz.setName(newQuizName);
        quizDao.updateQuiz(quiz);
        // THEN the quiz in the db should be updated
        String queriedQuizName = quizDao.getQuizById(id).getName();
        assertEquals(newQuizName, queriedQuizName);
    }

    @Test
    public void deleteQuiz() {
        // GIVEN a quiz that is already present
        long id = quizDao.insertQuiz(testQuiz);
        // WHEN the quiz is deleted
        QuizEntity quiz = quizDao.getQuizById(id);
        quizDao.deleteQuiz(quiz);
        // THEN that question should no longer be there
        List<QuizEntity> quizzes = quizDao.getQuizzes();
        assertEquals(0, quizzes.size());
    }

}
