package de.kulturbremen.memorize.data;

import android.content.Context;

import androidx.room.Room;
import androidx.test.core.app.ApplicationProvider;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.List;

import de.kulturbremen.memorize.model.QuizEntity;
import de.kulturbremen.memorize.persistence.AppDatabase;
import de.kulturbremen.memorize.persistence.QuizRepository;

import static org.junit.Assert.*;

@RunWith(AndroidJUnit4.class)
public class TestQuizRepository {
    private AppDatabase db;
    private QuizRepository quizRepository;
    private QuizEntity testQuiz = new QuizEntity("quiz name");

    @Before
    public void createAndFillDb() {
        Context context = ApplicationProvider.getApplicationContext();
        db = Room.inMemoryDatabaseBuilder(context, AppDatabase.class).build();
        quizRepository = new QuizRepository(db.QuizDao());
        quizRepository.addQuiz(testQuiz);
        quizRepository.addQuiz(testQuiz);
    }

    @After
    public void closeDb() {
        db.close();
    }

    @Test
    public void getQuizzesReturns(){
        List<QuizEntity> quizzes = quizRepository.getQuizzes();
        assertTrue(quizzes.size() > 1);
    }

    @Test
    public void addQuizReturnsLong(){
        QuizEntity newQuiz = new QuizEntity("hey another");
        long id = quizRepository.addQuiz(newQuiz);
        assertTrue(id > 0);
    }

    @Test
    public void addQuizAdds(){
        // GIVEN a QuizEntity
        QuizEntity newQuiz = new QuizEntity("animals");
        // WHEN the QuizEntity is added
        quizRepository.addQuiz(newQuiz);
        // THEN the name of that QuizEntity should be in the list of names retrieved from getQuizzes
        List<QuizEntity> quizzes = quizRepository.getQuizzes();
        ArrayList<String> quizNames = getQuizNames(quizzes);
        assertTrue(quizNames.contains(newQuiz.getName()));
    }

    @Test
    public void addAndDeleteQuiz(){
        QuizEntity newQuiz = new QuizEntity("plants");
        long id = quizRepository.addQuiz(newQuiz);
        newQuiz.setId(id);
        quizRepository.deleteQuiz(newQuiz);
        List<QuizEntity> quizzes = quizRepository.getQuizzes();
        ArrayList<String> quizNames = getQuizNames(quizzes);
        assertFalse(quizNames.contains(newQuiz.getName()));
    }

    private ArrayList<String> getQuizNames(List<QuizEntity> quizzes){
        ArrayList<String> quizNames = new ArrayList<>();
        for (QuizEntity quiz: quizzes){
            quizNames.add(quiz.getName());
        }
        return quizNames;
    }
}
