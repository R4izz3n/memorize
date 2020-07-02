package de.kulturbremen.memorize.quizmanager;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class QuizManager{
    private static ArrayList<QuestionContainer> QUESTIONS;
    private static ArrayList<Integer> unansweredQuestions;  // with indexes from QUESTIONS

    public static void setQUESTIONS(ArrayList<QuestionContainer> questions) {
        QUESTIONS = questions;
        unansweredQuestions = new ArrayList<>();

        for (int i = 0; i < QUESTIONS.size(); i++){
            unansweredQuestions.add(i);
        }
        Collections.shuffle(unansweredQuestions);
    }

    public static QuestionContainer getQuestion(){
        int questionIndex = unansweredQuestions.get(0);
        return QUESTIONS.get(questionIndex);
    }

    public static boolean checkAnswer(String userAnswer){
        String savedAnswer = QUESTIONS.get(unansweredQuestions.get(0)).getAnswer();
        return savedAnswer.equals(userAnswer.trim());
    }

    public static void removeLastQuestion(){unansweredQuestions.remove(0);}

    public static void reshuffleLastQuestion(){
        int lastQuestion = unansweredQuestions.remove(0);
        if (unansweredQuestions.size() > 1) {
            int newIndex = new Random().nextInt(unansweredQuestions.size()) + 1;  // never at 0
            unansweredQuestions.add(newIndex, lastQuestion);
        } else {
            unansweredQuestions.add(0, lastQuestion);
        }
    }

    public static boolean hasQuestions(){
        return unansweredQuestions.size() != 0;
    }

}
