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

import static org.junit.Assert.*;

import de.kulturbremen.memorize.model.QuestionEntity;
import de.kulturbremen.memorize.model.QuizEntity;
import de.kulturbremen.memorize.persistence.AppDatabase;
import de.kulturbremen.memorize.persistence.QuestionRepository;

@RunWith(AndroidJUnit4.class)
public class TestQuestionRepository {
    private AppDatabase db;
    private QuestionRepository questionRepository;
    private QuestionEntity testQuestion = new QuestionEntity(
            "why?", "that's why", (long) 1);
    private QuizEntity testQuiz = new QuizEntity("a quiz name");

    @Before
    public void createAndFillDb() {
        Context context = ApplicationProvider.getApplicationContext();
        db = Room.inMemoryDatabaseBuilder(context, AppDatabase.class).build();
        questionRepository = new QuestionRepository(db.QuestionDao());
        questionRepository.addQuestion(testQuestion);
        questionRepository.addQuestion(testQuestion);
    }

    @After
    public void closeDb() {
        db.close();
    }

    @Test
    public void getQuestionsReturnsQuestions(){
        // GIVEN a QuizEntity
        testQuiz.setId(testQuestion.getQuizId());
        // WHEN questionRepository.getQuestions is called
        List<QuestionEntity> questions = questionRepository.getQuestions(testQuiz);
        // THEN questions should be retrieved
        assertTrue(questions.size() > 1);
    }

    @Test
    public void addQuestionReturnsLong(){
        long id = questionRepository.addQuestion(testQuestion);
        assertTrue(id > 0 );
    }

    public void addQuestionAdds(){
        // GIVEN a new QuestionEntity and corresponding QuizEntity
        QuestionEntity newQuestion = new QuestionEntity(
                "animal","specific bird", (long) 6);
        testQuiz.setId(newQuestion.getQuizId());
        // WHEN the Question is added
        questionRepository.addQuestion(newQuestion);
        // THEN the exact question string should be retrieved again
        List<QuestionEntity> questions = questionRepository.getQuestions(testQuiz);
        List<String> questionNames= new ArrayList<>();
        for (QuestionEntity question: questions) {
            questionNames.add(question.getQuestion());
        }
        assertTrue(questionNames.contains(newQuestion.getQuestion()));
    }

    @Test
    public void deleteQuestionDeletes(){
        // GIVEN a known question added to the db
        QuizEntity newQuiz = new QuizEntity("boeh");
        newQuiz.setId((long) 5);
        QuestionEntity newQuestion = new QuestionEntity(
                "ha", "nah", newQuiz.getId());
        long id = questionRepository.addQuestion(newQuestion);
        newQuestion.setId(id);
        // WHEN it is deleted
        questionRepository.deleteQuestion(newQuestion);
        //THEN it should no longer be there
        List<QuestionEntity> questions = questionRepository.getQuestions(newQuiz);
        assertEquals(0, questions.size());
    }

    @Test
    public void deleteQuestionsDeletes(){
        // GIVEN QuestionEntities of a given quiz are added to the db
        QuizEntity newQuiz = new QuizEntity("boeh");
        newQuiz.setId((long) 2);
        QuestionEntity newQuestion = new QuestionEntity(
                "h", "a", newQuiz.getId());
        questionRepository.addQuestion(newQuestion);
        questionRepository.addQuestion(newQuestion);
        // WHEN those questions are deleted
        questionRepository.deleteQuestions(newQuiz);
        // THEN there should be no more questions of that quiz
        List<QuestionEntity> questions = questionRepository.getQuestions(newQuiz);
        assertEquals(0, questions.size());
    }
}
