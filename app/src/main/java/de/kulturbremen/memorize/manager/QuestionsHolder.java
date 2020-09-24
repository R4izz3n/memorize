package de.kulturbremen.memorize.manager;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import de.kulturbremen.memorize.model.QuestionEntity;


/**
 * A singleton class that holds QuestionEntities
 *
 * Questions will always be shuffled, and can sequentially be retrieved, removed or reshuffled.
 * The most recent question can be compared to a user answer.
 *
 * A typical use scenario would work like so:
 * QuestionsHolder qH = QuestionsHolder.getInstance();
 * qm.setQuestions(myQuestions);
 * if (qm.hasQuestions()) {...}
 * String question = qH.getQuestionEntity().getQuestion();
 * // (...)
 * boolean answerIsCorrect = qH.checkAnswer(userAnswer);
 * // and then
 * qH.removeLastQuestion();
 * // or
 * qH.reshuffleLastQuestion()
 *
 * hasQuestions() and setQuestions() can always be called, the other methods will throw a
 * noMoreQuestions RuntimeException when there are no questions left.
 */
public class QuestionsHolder {
    private static QuestionsHolder instance = new QuestionsHolder();
    private List<QuestionEntity> questions = new ArrayList<>();
    private ArrayList<Integer> unansweredQuestions = new ArrayList<>();  // with indexes from QUESTIONS

    /**
     * private constructor so it cannot be instantiated
     */
    private QuestionsHolder() {}

    public static QuestionsHolder getInstance(){
        return instance;
    }

    public boolean hasQuestions(){
        return !unansweredQuestions.isEmpty();
    }

    public void setQuestions(List<QuestionEntity> questions) {
        this.questions = questions;
        setUnansweredQuestions();
    }

    private void setUnansweredQuestions() {
        unansweredQuestions = new ArrayList<>();


        for (int i = 0; i < questions.size(); i++){
            unansweredQuestions.add(i);
        }
        Collections.shuffle(unansweredQuestions);
    }

    public QuestionEntity getQuestionEntity() throws NoMoreQuestions {
        if (unansweredQuestions.isEmpty()) {
            throw new NoMoreQuestions();
        }

        int questionIndex = unansweredQuestions.get(0);
        return questions.get(questionIndex);
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

        String savedAnswer = questions.get(unansweredQuestions.get(0)).getAnswer();
        return savedAnswer.equals(userAnswer.trim());
    }

    public void removeLastQuestion() throws NoMoreQuestions {
        if (unansweredQuestions.isEmpty()) {
            questions = new ArrayList<>();  // also remove questions
            throw new NoMoreQuestions();
        }

        unansweredQuestions.remove(0);
    }

    public void removeAllQuestions() {
        questions = new ArrayList<>();
        unansweredQuestions = new ArrayList<>();
    }

    public void reshuffleLastQuestion() throws NoMoreQuestions {
        if (unansweredQuestions.isEmpty()) {
            throw new NoMoreQuestions();
        }

        int lastQuestion = unansweredQuestions.remove(0);
        if (unansweredQuestions.size() > 0) {
            int newIndex = new Random().nextInt(unansweredQuestions.size()) + 1;  // never at 0
            unansweredQuestions.add(newIndex, lastQuestion);
        } else {
            unansweredQuestions.add(lastQuestion);
        }
    }

    public static class NoMoreQuestions extends RuntimeException {
    }

}
