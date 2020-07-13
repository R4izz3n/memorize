package de.kulturbremen.memorize.model;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import java.time.ZonedDateTime;

@Entity(indices = {@Index("quizId")})
public class QuestionEntity {
    @PrimaryKey(autoGenerate = true)
    private int id;
    @NonNull
    private String question;
    @NonNull
    private String answer;
    @NonNull
    private ZonedDateTime creationDate;
    @NonNull
    private Integer quizId;

    public QuestionEntity(QuestionEntity questionEntity) {
        this.id = questionEntity.getId();
        this.question = questionEntity.getQuestion();
        this.answer = questionEntity.getAnswer();
        this.creationDate = questionEntity.getCreationDate();
        this.quizId = questionEntity.getQuizId();
    }

    public QuestionEntity(@NonNull String question, @NonNull String answer,
                          @NonNull Integer quizId) {
        this.question = question;
        this.answer = answer;
        this.creationDate = ZonedDateTime.now();
        this.quizId = quizId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @NonNull
    public String getQuestion() {
        return question;
    }

    public void setQuestion(@NonNull String question) {
        this.question = question;
    }

    @NonNull
    public String getAnswer() {
        return answer;
    }

    public void setAnswer(@NonNull String answer) {
        this.answer = answer;
    }

    @NonNull
    public ZonedDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(@NonNull ZonedDateTime creationDate) {
        this.creationDate = creationDate;
    }
    @NonNull
    public Integer getQuizId() {
        return quizId;
    }

    public void setQuizId(@NonNull Integer quizId) {
        this.quizId = quizId;
    }
}
