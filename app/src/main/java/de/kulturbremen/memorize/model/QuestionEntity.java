package de.kulturbremen.memorize.model;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import java.time.ZonedDateTime;

/**
 * A class that represent a Question Entity
 * Contains more meta information than QuestionModel
 */
@Entity(indices = {@Index("quizId")})
public class QuestionEntity {
    @PrimaryKey(autoGenerate = true)
    private Long id;
    @NonNull
    private String question;
    @NonNull
    private String answer;
    @NonNull
    private ZonedDateTime creationDate;
    private Long quizId;

    public QuestionEntity(QuestionEntity questionEntity) {
        this.id = questionEntity.getId();
        this.question = questionEntity.getQuestion();
        this.answer = questionEntity.getAnswer();
        this.creationDate = questionEntity.getCreationDate();
        this.quizId = questionEntity.getQuizId();
    }

    public QuestionEntity(@NonNull String question, @NonNull String answer,
                          @NonNull Long quizId) {
        this.question = question;
        this.answer = answer;
        this.creationDate = ZonedDateTime.now();
        this.quizId = quizId;
    }

    public QuestionEntity(){
        this.question = "";
        this.answer = "";
        this.creationDate = ZonedDateTime.now();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @NonNull
    public String getQuestion() {
        return question;
    }

    public void setQuestion(@NonNull String question) {
        this.question = question.trim();
    }

    @NonNull
    public String getAnswer() {
        return answer;
    }

    public void setAnswer(@NonNull String answer) {
        this.answer = answer.trim();
    }

    @NonNull
    public ZonedDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(@NonNull ZonedDateTime creationDate) {
        this.creationDate = creationDate;
    }
    @NonNull
    public Long getQuizId() {
        return quizId;
    }

    public void setQuizId(@NonNull Long quizId) {
        this.quizId = quizId;
    }

    @Override
    public String toString() {
        return "QuestionEntity{" +
                "question='" + question + '\'' +
                ", answer='" + answer + '\'' +
                ", quizId=" + quizId +
                '}';
    }
}
