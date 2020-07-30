package de.kulturbremen.memorize;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import de.kulturbremen.memorize.manager.QuestionsHolder;
import de.kulturbremen.memorize.model.QuestionEntity;

public class TestFullQuestionsHolder {

    ArrayList<QuestionEntity> QUESTIONS;
    QuestionsHolder questionsHolder = QuestionsHolder.getInstance();

    public TestFullQuestionsHolder() {
        ArrayList<QuestionEntity> questions = new ArrayList<>();
        questions.add(new QuestionEntity("why?", "because", (long) 1));
        questions.add(new QuestionEntity("why?2", "because2", (long) 2));
        questions.add(new QuestionEntity("why?3", "because3", (long) 3));
        this.QUESTIONS = questions;
    }

    @BeforeEach
    void initQuizManager() {
        questionsHolder.setQuestions(QUESTIONS);
    }

    @Test
    void setQuestionsAddsQuestions() {
        // GIVEN that QuestionsHolder already has questions and a new set of questions
        ArrayList<QuestionEntity> questions = new ArrayList<>();
        questions.add(new QuestionEntity("why?3", "because3", (long) 4));
        // WHEN new questions are set
        questionsHolder.setQuestions(questions);
        // THEN only the new questions should be present
        assertEquals(questionsHolder.getQuestionEntity(), questions.get(0));
        questionsHolder.removeLastQuestion();
        assertFalse(questionsHolder.hasQuestions());
    }

    @Test
    void getQuestionReturnsQuestion() {
        QuestionEntity question =  questionsHolder.getQuestionEntity();
        assertTrue(QUESTIONS.contains(question));
    }

    @Test
    void checkAnswerCorrectAnswerReturnsTrue() {
        // GIVEN QuestionsHolder with questions
        // WHEN using the correct answer String
        String correctAnswer = questionsHolder.getQuestionEntity().getAnswer();
        // THEN checkAnswer() should return true
        assertTrue(questionsHolder.checkAnswer(correctAnswer));
    }

    @Test
    void checkAnswerFalseAnswerReturnsFalse() {
        // GIVEN QuestionsHolder with questions
        // WHEN using a false answer String
        String falseAnswer = "nothing";
        // THEN checkAnswer() should return true
        assertFalse(questionsHolder.checkAnswer(falseAnswer));
    }

    @Test
    void removeLastQuestionRemovesQuestions() {
        // GIVEN QuestionsHolder with questions
        // WHEN all questions are removed with removeQuestion()
        questionsHolder.removeLastQuestion();
        questionsHolder.removeLastQuestion();
        questionsHolder.removeLastQuestion();
        // THEN no more questions should be left
        assertFalse(questionsHolder.hasQuestions());
    }

    @Test
    void removeLastQuestionChangesQuestion() {
        // GIVEN QuestionsHolder with questions
        // WHEN one questions is queried and then removed
        QuestionEntity firstQuestion = questionsHolder.getQuestionEntity();
        questionsHolder.removeLastQuestion();
        // THEN the next call to getQuestion() should return a different question
        QuestionEntity secondQuestion = questionsHolder.getQuestionEntity();
        assertNotEquals(firstQuestion, secondQuestion);
    }

    @Test
    void removeAllQuestionsRemovesAll() {
        questionsHolder.removeAllQuestions();
        assertFalse(questionsHolder.hasQuestions());
    }

    @RepeatedTest(10)
    void reshuffleLastQuestionShuffles() {
        // GIVEN QuestionsHolder with questions
        // WHEN one question is queried and then reshuffled
        QuestionEntity firstQuestion = questionsHolder.getQuestionEntity();
        questionsHolder.reshuffleLastQuestion();
        // THEN the next question should be different
        QuestionEntity secondQuestion = questionsHolder.getQuestionEntity();
        assertNotEquals(firstQuestion, secondQuestion);
    }

    @Test
    void hasQuestionsReturnsTrue() {
        assertTrue(questionsHolder.hasQuestions());
    }

}
