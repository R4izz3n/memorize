package de.kulturbremen.memorize.quizmanager;

public class QuestionContainer {
    private String question;
    private String answer;

    public QuestionContainer(String question, String answer){
        this.question = question.trim();
        this.answer = answer.trim();
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
}
