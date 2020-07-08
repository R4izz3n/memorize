package de.kulturbremen.memorize.data.entity;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import java.time.ZonedDateTime;

import de.kulturbremen.memorize.model.QuestionContainer;

@Entity(indices = {@Index("quiz")})
public class QuestionEntity implements QuestionContainer {
    @PrimaryKey(autoGenerate = true)
    private int id;
    @NonNull
    private String question;
    @NonNull
    private String answer;
    @NonNull
    private ZonedDateTime creationDate;
    @NonNull
    private String quiz;

    public QuestionEntity(QuestionContainer questionContainer) {
        this.id = questionContainer.getId();
        this.question = questionContainer.getQuestion();
        this.answer = questionContainer.getAnswer();
        this.creationDate = questionContainer.getCreationDate();
        this.quiz = questionContainer.getQuiz();
    }

    public QuestionEntity(@NonNull String question, @NonNull String answer,
                          @NonNull String quiz) {
        this.question = question;
        this.answer = answer;
        this.creationDate = ZonedDateTime.now();
        this.quiz = quiz;
    }

    @Override
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @NonNull
    @Override
    public String getQuestion() {
        return question;
    }

    public void setQuestion(@NonNull String question) {
        this.question = question;
    }

    @NonNull
    @Override
    public String getAnswer() {
        return answer;
    }

    public void setAnswer(@NonNull String answer) {
        this.answer = answer;
    }

    @NonNull
    @Override
    public ZonedDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(@NonNull ZonedDateTime creationDate) {
        this.creationDate = creationDate;
    }
    @NonNull
    @Override
    public String getQuiz() {
        return quiz;
    }

    public void setQuiz(@NonNull String quiz) {
        this.quiz = quiz;
    }
}
