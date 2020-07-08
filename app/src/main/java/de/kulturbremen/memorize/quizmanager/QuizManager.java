package de.kulturbremen.memorize.quizmanager;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import de.kulturbremen.memorize.model.QuestionContainer;


/**
 * A singleton class that holds QuestionContainers
 *
 * Questions will always be shuffled, and can sequentially be retrieved, removed or reshuffled.
 * The most recent question can be compared to a user answer.
 *
 * A typical use scenario would work like so:
 * QuizManager qm = QuizManager.getInstance();
 * qm.setQuestions(myQuestions);
 * if (qm.hasQuestions()) {...}
 * String question = qm.getQuestionContainer().getQuestion();
 * // (...)
 * boolean answerIsCorrect = qm.checkAnswer(userAnswer);
 * // and then
 * qm.removeLastQuestion();
 * // or
 * qm.reshuffleLastQuestion()
 *
 * hasQuestions() and setQuestions() can always be called, the other methods will throw a
 * noMoreQuestions RuntimeException when there are no questions left.
 */
public class QuizManager{
    private static QuizManager instance = new QuizManager();
    private static ArrayList<QuestionContainer> QUESTIONS = new ArrayList<>();
    private static ArrayList<Integer> unansweredQuestions = new ArrayList<>();  // with indexes from QUESTIONS

    /**
     * private constructor so it cannot be instantiated
     */
    private QuizManager() {}

    public static QuizManager getInstance(){
        return instance;
    }

    public boolean hasQuestions(){
        return !unansweredQuestions.isEmpty();
    }

    public void setQuestions(ArrayList<QuestionContainer> questionContainers) {
        QUESTIONS = questionContainers;
        unansweredQuestions = new ArrayList<>();

        for (int i = 0; i < QUESTIONS.size(); i++){
            unansweredQuestions.add(i);
        }
        Collections.shuffle(unansweredQuestions);
    }

    public QuestionContainer getQuestionContainer() throws NoMoreQuestions {
        if (unansweredQuestions.isEmpty()) {
            throw new NoMoreQuestions();
        }

        int questionIndex = unansweredQuestions.get(0);
        return QUESTIONS.get(questionIndex);
    }

    /**
     * Compares an answer String to the actual answer of the most recent QuestionContainer
     *
     * @param userAnswer a String with the answer that needs to be checked
     * @return true if the answer is correct, false if the answer is wrong
     * @throws NoMoreQuestions when QuizContainer has no more questions
     */
    public boolean checkAnswer(String userAnswer) throws NoMoreQuestions {
        if (unansweredQuestions.isEmpty()) {
            throw new NoMoreQuestions();
        }

        String savedAnswer = QUESTIONS.get(unansweredQuestions.get(0)).getAnswer();
        return savedAnswer.equals(userAnswer.trim());
    }

    public void removeLastQuestion() throws NoMoreQuestions {
        if (unansweredQuestions.isEmpty()) {
            throw new NoMoreQuestions();
        }

        unansweredQuestions.remove(0);

        if (unansweredQuestions.isEmpty()) { // then also remove QUESTIONS
            QUESTIONS = new ArrayList<>();
        }
    }

    public void removeAllQuestions() {
        QUESTIONS = new ArrayList<>();
        unansweredQuestions = new ArrayList<>();
    }

    public void reshuffleLastQuestion() throws NoMoreQuestions {
        if (unansweredQuestions.isEmpty()) {
            throw new NoMoreQuestions();
        }

        int lastQuestion = unansweredQuestions.remove(0);
        if (unansweredQuestions.size() > 1) {
            int newIndex = new Random().nextInt(unansweredQuestions.size()) + 1;  // never at 0
            unansweredQuestions.add(newIndex, lastQuestion);
        } else {
            unansweredQuestions.add(0, lastQuestion);
        }
    }

    public static class NoMoreQuestions extends RuntimeException {
    }

}
