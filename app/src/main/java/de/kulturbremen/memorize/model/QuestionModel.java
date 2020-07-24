package de.kulturbremen.memorize.model;

import androidx.annotation.NonNull;

/**
 * A simple class to contain questions
 *
 * Useful when the meta information that comes with QuestionEntity
 * is not required or available
 */
public class QuestionModel {
    private String question;
    private String answer;

    public QuestionModel(String question, String answer){
        this.question = question;
        this.answer = answer;
    }

    public QuestionModel(){
        this.question = "";
        this.answer = "";
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    @NonNull
    @Override
    public String toString() {
        return "QuestionModel{" +
                "question='" + question + '\'' +
                ", answer='" + answer + '\'' +
                '}';
    }
}
