package de.kulturbremen.memorize.model;

import java.time.ZonedDateTime;

public interface QuestionContainer {
    int getId();
    String getQuestion();
    String getAnswer();
    ZonedDateTime getCreationDate();
    String getQuizId();
}
