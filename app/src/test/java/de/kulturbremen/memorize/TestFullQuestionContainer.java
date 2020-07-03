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
    QuizManager quizManager = QuizManager.getInstance();

    public TestFullQuestionContainer() {
        ArrayList<QuestionContainer> questions = new ArrayList<>();
        questions.add(new QuestionContainer("why?", "because"));
        questions.add(new QuestionContainer("why2?", "because2"));
        questions.add(new QuestionContainer("why3?", "because3"));
        this.QUESTIONS = questions;
    }

    @BeforeEach
    void initQuizManager() {
        quizManager.setQuestions(QUESTIONS);
    }

    @Test
    void setQuestionsAddsQuestions() {
        // GIVEN that QuizManager already has questions
        // WHEN new questions are set
        ArrayList<QuestionContainer> questions = new ArrayList<>();
        questions.add(new QuestionContainer("new q 1", "new a 1"));
        quizManager.setQuestions(questions);
        // THEN only the new questions should be present
        assertEquals(quizManager.getQuestionContainer(), questions.get(0));
        quizManager.removeLastQuestion();
        assertFalse(quizManager.hasQuestions());
    }

    @Test
    void getQuestionReturnsQuestion() {
        QuestionContainer question =  quizManager.getQuestionContainer();
        assertTrue(QUESTIONS.contains(question));
    }

    @Test
    void checkAnswerCorrectAnswerReturnsTrue() {
        // GIVEN QuizManager with questions
        // WHEN using the correct answer String
        String correctAnswer = quizManager.getQuestionContainer().getAnswer();
        // THEN checkAnswer() should return true
        assertTrue(quizManager.checkAnswer(correctAnswer));
    }

    @Test
    void checkAnswerFalseAnswerReturnsFalse() {
        // GIVEN QuizManager with questions
        // WHEN using a false answer String
        String falseAnswer = "nothing";
        // THEN checkAnswer() should return true
        assertFalse(quizManager.checkAnswer(falseAnswer));
    }

    @Test
    void removeLastQuestionRemovesQuestions() {
        // GIVEN QuizManager with questions
        // WHEN all questions are removed with removeQuestion()
        quizManager.removeLastQuestion();
        quizManager.removeLastQuestion();
        quizManager.removeLastQuestion();
        // THEN no more questions should be left
        assertFalse(quizManager.hasQuestions());
    }

    @Test
    void removeLastQuestionChangesQuestion() {
        // GIVEN QuizManager with questions
        // WHEN one questions is queried and then removed
        QuestionContainer firstQuestion = quizManager.getQuestionContainer();
        quizManager.removeLastQuestion();
        // THEN the next call to getQuestion() should return a different question
        QuestionContainer secondQuestion = quizManager.getQuestionContainer();
        assertNotEquals(firstQuestion, secondQuestion);
    }

    @Test
    void removeAllQuestionsRemovesAll() {
        quizManager.removeAllQuestions();
        assertFalse(quizManager.hasQuestions());
    }

    @RepeatedTest(10)
    void reshuffleLastQuestionShuffles() {
        // GIVEN QuizManager with questions
        // WHEN one question is queried and then reshuffled
        QuestionContainer firstQuestion = quizManager.getQuestionContainer();
        quizManager.reshuffleLastQuestion();
        // THEN the next question should be different
        QuestionContainer secondQuestion = quizManager.getQuestionContainer();
        assertNotEquals(firstQuestion, secondQuestion);
    }

    @Test
    void hasQuestionsReturnsTrue() {
        assertTrue(quizManager.hasQuestions());
    }

}
