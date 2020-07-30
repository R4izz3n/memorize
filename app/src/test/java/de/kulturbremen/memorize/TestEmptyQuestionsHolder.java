package de.kulturbremen.memorize;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;

import de.kulturbremen.memorize.manager.QuestionsHolder;
import de.kulturbremen.memorize.model.QuestionEntity;

public class TestEmptyQuestionsHolder {

    QuestionsHolder questionsHolder = QuestionsHolder.getInstance();

    @BeforeEach
    void emptyQuizManagerBefore() {
        questionsHolder.removeAllQuestions();
    }

    @Test
    void setQuestionsAddsQuestions() {
        // GIVEN an empty QuestionsHolder and a QuestionContainer
        QuestionEntity questionAdded = new QuestionEntity("what?", "so",
                (long) 1);
        // WHEN new questions are added
        ArrayList<QuestionEntity> questions = new ArrayList<>();
        questions.add(questionAdded);
        questionsHolder.setQuestions(questions);
        // THEN getQuestion should return a question
        QuestionEntity questionRetrieved = questionsHolder.getQuestionEntity();
        assertEquals(questionAdded, questionRetrieved);
    }

    @Test
    void getQuestionThrows() {
        assertThrows(QuestionsHolder.NoMoreQuestions.class, questionsHolder::getQuestionEntity);
    }

    @Test
    void checkAnswerThrows() {
        assertThrows(QuestionsHolder.NoMoreQuestions.class, () ->
                questionsHolder.checkAnswer("nothing"));
    }

    @Test
    void removeLastQuestionThrows() {
        assertThrows(QuestionsHolder.NoMoreQuestions.class, questionsHolder::removeLastQuestion);
    }

    @Test
    void reshuffleLastQuestionThrows() {
        assertThrows(QuestionsHolder.NoMoreQuestions.class, questionsHolder::reshuffleLastQuestion);
    }

    @Test
    void hasQuestionsReturnsFalse() {
        assertFalse(questionsHolder.hasQuestions());
    }
}
