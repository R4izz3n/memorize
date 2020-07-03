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

    QuizManager quizManager = QuizManager.getInstance();

    @BeforeEach
    void emptyQuizManagerBefore() {
        quizManager.removeAllQuestions();
    }

    @Test
    void setQuestionsAddsQuestions() {
        // GIVEN an empty QuizManager
        // WHEN new questions are added
        ArrayList<QuestionContainer> questions = new ArrayList<>();
        QuestionContainer questionAdded = new QuestionContainer("new q 1", "new a 1");
        questions.add(questionAdded);
        quizManager.setQuestions(questions);
        // THEN getQuestion should return a question
        QuestionContainer questionRetrieved = quizManager.getQuestionContainer();
        assertEquals(questionAdded, questionRetrieved);
    }

    @Test
    void getQuestionThrows() {
        assertThrows(QuizManager.NoMoreQuestions.class, quizManager::getQuestionContainer);
    }

    @Test
    void checkAnswerThrows() {
        assertThrows(QuizManager.NoMoreQuestions.class, () ->
                quizManager.checkAnswer("nothing"));
    }

    @Test
    void removeLastQuestionThrows() {
        assertThrows(QuizManager.NoMoreQuestions.class, quizManager::removeLastQuestion);
    }

    @Test
    void reshuffleLastQuestionThrows() {
        assertThrows(QuizManager.NoMoreQuestions.class, quizManager::reshuffleLastQuestion);
    }

    @Test
    void hasQuestionsReturnsFalse() {
        assertFalse(quizManager.hasQuestions());
    }
}
