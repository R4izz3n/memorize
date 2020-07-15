package de.kulturbremen.memorize;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import de.kulturbremen.memorize.model.QuestionEntity;
import de.kulturbremen.memorize.manager.QuestionManager;

public class TestFullQuestionManager {

    ArrayList<QuestionEntity> QUESTIONS;
    QuestionManager questionManager = QuestionManager.getInstance();

    public TestFullQuestionManager() {
        ArrayList<QuestionEntity> questions = new ArrayList<>();
        questions.add(new QuestionEntity("why?", "because", 1));
        questions.add(new QuestionEntity("why?2", "because2", 2));
        questions.add(new QuestionEntity("why?3", "because3", 3));
        this.QUESTIONS = questions;
    }

    @BeforeEach
    void initQuizManager() {
        questionManager.setQuestions(QUESTIONS);
    }

    @Test
    void setQuestionsAddsQuestions() {
        // GIVEN that QuestionManager already has questions and a new set of questions
        ArrayList<QuestionEntity> questions = new ArrayList<>();
        questions.add(new QuestionEntity("why?3", "because3", 4));
        // WHEN new questions are set
        questionManager.setQuestions(questions);
        // THEN only the new questions should be present
        assertEquals(questionManager.getQuestionEntity(), questions.get(0));
        questionManager.removeLastQuestion();
        assertFalse(questionManager.hasQuestions());
    }

    @Test
    void getQuestionReturnsQuestion() {
        QuestionEntity question =  questionManager.getQuestionEntity();
        assertTrue(QUESTIONS.contains(question));
    }

    @Test
    void checkAnswerCorrectAnswerReturnsTrue() {
        // GIVEN QuestionManager with questions
        // WHEN using the correct answer String
        String correctAnswer = questionManager.getQuestionEntity().getAnswer();
        // THEN checkAnswer() should return true
        assertTrue(questionManager.checkAnswer(correctAnswer));
    }

    @Test
    void checkAnswerFalseAnswerReturnsFalse() {
        // GIVEN QuestionManager with questions
        // WHEN using a false answer String
        String falseAnswer = "nothing";
        // THEN checkAnswer() should return true
        assertFalse(questionManager.checkAnswer(falseAnswer));
    }

    @Test
    void removeLastQuestionRemovesQuestions() {
        // GIVEN QuestionManager with questions
        // WHEN all questions are removed with removeQuestion()
        questionManager.removeLastQuestion();
        questionManager.removeLastQuestion();
        questionManager.removeLastQuestion();
        // THEN no more questions should be left
        assertFalse(questionManager.hasQuestions());
    }

    @Test
    void removeLastQuestionChangesQuestion() {
        // GIVEN QuestionManager with questions
        // WHEN one questions is queried and then removed
        QuestionEntity firstQuestion = questionManager.getQuestionEntity();
        questionManager.removeLastQuestion();
        // THEN the next call to getQuestion() should return a different question
        QuestionEntity secondQuestion = questionManager.getQuestionEntity();
        assertNotEquals(firstQuestion, secondQuestion);
    }

    @Test
    void removeAllQuestionsRemovesAll() {
        questionManager.removeAllQuestions();
        assertFalse(questionManager.hasQuestions());
    }

    @RepeatedTest(10)
    void reshuffleLastQuestionShuffles() {
        // GIVEN QuestionManager with questions
        // WHEN one question is queried and then reshuffled
        QuestionEntity firstQuestion = questionManager.getQuestionEntity();
        questionManager.reshuffleLastQuestion();
        // THEN the next question should be different
        QuestionEntity secondQuestion = questionManager.getQuestionEntity();
        assertNotEquals(firstQuestion, secondQuestion);
    }

    @Test
    void hasQuestionsReturnsTrue() {
        assertTrue(questionManager.hasQuestions());
    }

}
