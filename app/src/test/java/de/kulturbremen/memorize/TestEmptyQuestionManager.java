package de.kulturbremen.memorize;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;

import de.kulturbremen.memorize.model.QuestionEntity;
import de.kulturbremen.memorize.manager.QuestionManager;

public class TestEmptyQuestionManager {

    QuestionManager questionManager = QuestionManager.getInstance();

    @BeforeEach
    void emptyQuizManagerBefore() {
        questionManager.removeAllQuestions();
    }

    @Test
    void setQuestionsAddsQuestions() {
        // GIVEN an empty QuestionManager and a QuestionContainer
        QuestionEntity questionAdded = new QuestionEntity("what?", "so",
                1);
        // WHEN new questions are added
        ArrayList<QuestionEntity> questions = new ArrayList<>();
        questions.add(questionAdded);
        questionManager.setQuestions(questions);
        // THEN getQuestion should return a question
        QuestionEntity questionRetrieved = questionManager.getQuestionEntity();
        assertEquals(questionAdded, questionRetrieved);
    }

    @Test
    void getQuestionThrows() {
        assertThrows(QuestionManager.NoMoreQuestions.class, questionManager::getQuestionEntity);
    }

    @Test
    void checkAnswerThrows() {
        assertThrows(QuestionManager.NoMoreQuestions.class, () ->
                questionManager.checkAnswer("nothing"));
    }

    @Test
    void removeLastQuestionThrows() {
        assertThrows(QuestionManager.NoMoreQuestions.class, questionManager::removeLastQuestion);
    }

    @Test
    void reshuffleLastQuestionThrows() {
        assertThrows(QuestionManager.NoMoreQuestions.class, questionManager::reshuffleLastQuestion);
    }

    @Test
    void hasQuestionsReturnsFalse() {
        assertFalse(questionManager.hasQuestions());
    }
}
