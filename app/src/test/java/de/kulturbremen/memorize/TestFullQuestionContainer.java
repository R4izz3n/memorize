package de.kulturbremen.memorize;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import de.kulturbremen.memorize.quizmanager.QuestionContainer;
import de.kulturbremen.memorize.quizmanager.QuizManager;

public class TestFullQuestionContainer {

    ArrayList<QuestionContainer> QUESTIONS;

    public TestFullQuestionContainer() {
        ArrayList<QuestionContainer> questions = new ArrayList<>();
        questions.add(new QuestionContainer("why?", "because"));
        questions.add(new QuestionContainer("why2?", "because2"));
        questions.add(new QuestionContainer("why3?", "because3"));
        this.QUESTIONS = questions;
    }

    @BeforeEach
    void initQuizManager() {
        QuizManager.setQUESTIONS(QUESTIONS);
    }

    @Test
    void setQuestionsAddsQuestions() {
        // GIVEN that QuizManager already has questions
        // WHEN new questions are set
        ArrayList<QuestionContainer> questions = new ArrayList<>();
        questions.add(new QuestionContainer("new q 1", "new a 1"));
        QuizManager.setQUESTIONS(questions);
        // THEN only the new questions should be present
        assertEquals(QuizManager.getQuestion(), questions.get(0));
        QuizManager.removeLastQuestion();
        assertFalse(QuizManager.hasQuestions());
    }

    @Test
    void getQuestionReturnsQuestion() {
        QuestionContainer question =  QuizManager.getQuestion();
        assertTrue(QUESTIONS.contains(question));
    }

    @Test
    void checkAnswerCorrectAnswerReturnsTrue() {
        // GIVEN QuizManager with questions
        // WHEN using the correct answer String
        String correctAnswer = QuizManager.getQuestion().getAnswer();
        // THEN checkAnswer() should return true
        assertTrue(QuizManager.checkAnswer(correctAnswer));
    }

    @Test
    void checkAnswerFalseAnswerReturnsFalse() {
        // GIVEN QuizManager with questions
        // WHEN using a false answer String
        String falseAnswer = "nothing";
        // THEN checkAnswer() should return true
        assertFalse(QuizManager.checkAnswer(falseAnswer));
    }

    @Test
    void removeLastQuestionRemovesQuestions() {
        // GIVEN QuizManager with questions
        // WHEN all questions are removed with removeQuestion()
        QuizManager.removeLastQuestion();
        QuizManager.removeLastQuestion();
        QuizManager.removeLastQuestion();
        // THEN no more questions should be left
        assertFalse(QuizManager.hasQuestions());
    }

    @Test
    void removeLastQuestionChangesQuestion() {
        // GIVEN QuizManager with questions
        // WHEN one questions is queried and then removed
        QuestionContainer firstQuestion = QuizManager.getQuestion();
        QuizManager.removeLastQuestion();
        // THEN the next call to getQuestion() should return a different question
        QuestionContainer secondQuestion = QuizManager.getQuestion();
        assertNotEquals(firstQuestion, secondQuestion);
    }

    @Test
    void removeAllQuestionsRemovesAll() {
        QuizManager.removeAllQuestions();
        assertFalse(QuizManager.hasQuestions());
    }

    @RepeatedTest(10)
    void reshuffleLastQuestionShuffles() {
        // GIVEN QuizManager with questions
        // WHEN one question is queried and then reshuffled
        QuestionContainer firstQuestion = QuizManager.getQuestion();
        QuizManager.reshuffleLastQuestion();
        // THEN the next question should be different
        QuestionContainer secondQuestion = QuizManager.getQuestion();
        assertNotEquals(firstQuestion, secondQuestion);
    }

    @Test
    void hasQuestionsReturnsTrue() {
        assertTrue(QuizManager.hasQuestions());
    }

}
