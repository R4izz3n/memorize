package de.kulturbremen.memorize;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;

import de.kulturbremen.memorize.quizmanager.QuestionContainer;
import de.kulturbremen.memorize.quizmanager.QuizManager;

public class TestEmptyQuestionContainer {

    @BeforeEach
    void emptyQuizManagerBefore() {
        QuizManager.removeAllQuestions();
    }

    @Test
    void setQuestionsAddsQuestions() {
        // GIVEN an empty QuizManager
        // WHEN new questions are added
        ArrayList<QuestionContainer> questions = new ArrayList<>();
        QuestionContainer questionAdded = new QuestionContainer("new q 1", "new a 1");
        questions.add(questionAdded);
        QuizManager.setQuestions(questions);
        // THEN getQuestion should return a question
        QuestionContainer questionRetrieved = QuizManager.getQuestionContainer();
        assertEquals(questionAdded, questionRetrieved);
    }

    @Test
    void getQuestionThrows() {
        assertThrows(QuizManager.NoMoreQuestions.class, QuizManager::getQuestionContainer);
    }

    @Test
    void checkAnswerThrows() {
        assertThrows(QuizManager.NoMoreQuestions.class, () ->
                QuizManager.checkAnswer("nothing"));
    }

    @Test
    void removeLastQuestionThrows() {
        assertThrows(QuizManager.NoMoreQuestions.class, QuizManager::removeLastQuestion);
    }

    @Test
    void reshuffleLastQuestionThrows() {
        assertThrows(QuizManager.NoMoreQuestions.class, QuizManager::reshuffleLastQuestion);
    }

    @Test
    void hasQuestionsReturnsFalse() {
        assertFalse(QuizManager.hasQuestions());
    }
}
