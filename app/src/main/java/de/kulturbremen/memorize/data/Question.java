package de.kulturbremen.memorize.data;

import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import java.time.ZonedDateTime;

@Entity(indices = {@Index("Quiz")})
public class Question {
    @PrimaryKey(autoGenerate = true)
    public int id;

    public String question;
    public String answer;
    public ZonedDateTime creationDate;
    public String Quiz;

}
