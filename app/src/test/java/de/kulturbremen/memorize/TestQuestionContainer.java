package de.kulturbremen.memorize;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import de.kulturbremen.memorize.quizmanager.QuestionContainer;

public class TestQuestionContainer {

    QuestionContainer question;

    public TestQuestionContainer() {
        this.question = new QuestionContainer("a question", "a answer");
    }

    @Test
    void changeQuestion() {
        String newQuestion = "another question";
        question.setQuestion(newQuestion);
        assertEquals(newQuestion, question.getQuestion());
    }

    @Test
    void changeAnswer() {
        String newAnswer = "another answer";
        question.setAnswer(newAnswer);
        assertEquals(newAnswer, question.getAnswer());
    }

    @Test
    void constructorFunctionsAndTrims() {
        QuestionContainer newQuestion = new QuestionContainer(" q ", " a ");
        assertEquals("q", newQuestion.getQuestion());
        assertEquals("a", newQuestion.getAnswer());
    }
}
