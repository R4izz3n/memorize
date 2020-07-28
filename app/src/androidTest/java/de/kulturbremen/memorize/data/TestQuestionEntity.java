package de.kulturbremen.memorize.data;

import android.content.Context;

import androidx.room.Room;
import androidx.test.core.app.ApplicationProvider;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import static org.junit.Assert.*;

import java.util.List;

import de.kulturbremen.memorize.persistence.AppDatabase;
import de.kulturbremen.memorize.persistence.dao.QuestionDao;
import de.kulturbremen.memorize.model.QuestionEntity;

@RunWith(AndroidJUnit4.class)
public class TestQuestionEntity {
    private QuestionDao questionDao;
    private AppDatabase db;
    private QuestionEntity testQuestion = new QuestionEntity(
            "why?", "that's why", (long) 1);

    @Before
    public void createDb() {
        Context context = ApplicationProvider.getApplicationContext();
        db = Room.inMemoryDatabaseBuilder(context, AppDatabase.class).build();
        questionDao = db.QuestionDao();
    }

    @After
    public void closeDb() {
        db.close();
    }

    @Test
    public void addAndGetQuestion() {
        questionDao.insertQuestion(testQuestion);
        List<QuestionEntity> questions = questionDao.getQuestionsByQuizId(testQuestion.getQuizId());
        assertEquals(testQuestion.getAnswer(), questions.get(0).getAnswer());
    }

    @Test
    public void getQuestionById() {
        // GIVEN a question that is already present
        long id = questionDao.insertQuestion(testQuestion);
        // WHEN that question is queried
        QuestionEntity question = questionDao.getQuestionById(id);
        // THEN the right question should be retrieved
        assertEquals(testQuestion.getAnswer(), question.getAnswer());

    }

    @Test
    public void updateQuestion() {
        String newAnswer = "new answer";
        // GIVEN a question that is already present
        long id = questionDao.insertQuestion(testQuestion);
        // WHEN the question is updated
        QuestionEntity question = questionDao.getQuestionById(id);
        question.setAnswer(newAnswer);
        questionDao.updateQuestion(question);
        // THEN the question in the db should be updated
        String queriedAnswer = questionDao.getQuestionById(id).getAnswer();
        assertEquals(newAnswer, queriedAnswer);
    }

    @Test
    public void deleteQuestion() {
        // GIVEN a question that is already present
        long id = questionDao.insertQuestion(testQuestion);
        // WHEN the question is deleted
        QuestionEntity question = questionDao.getQuestionById(id);
        questionDao.deleteQuestion(question);
        // THEN that question should no longer be there
        List<QuestionEntity> questions = questionDao.getQuestionsByQuizId(testQuestion.getQuizId());
        assertEquals(0, questions.size());
    }

    @Test
    public void deleteQuestions() {
        //GIVEN multiple questions that are present
        questionDao.insertQuestion(testQuestion);
        questionDao.insertQuestion(testQuestion);
        // WHEN all questions are deleted
        questionDao.deleteQuestions(testQuestion.getQuizId());
        // THEN there should be no more questions
        List<QuestionEntity> questions = questionDao.getQuestionsByQuizId(testQuestion.getQuizId());
        assertEquals(0, questions.size());
    }
}
