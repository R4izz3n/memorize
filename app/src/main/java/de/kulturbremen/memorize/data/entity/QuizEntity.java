package de.kulturbremen.memorize.data.entity;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import java.time.ZonedDateTime;

import de.kulturbremen.memorize.model.Quiz;


@Entity(indices = {@Index("dateLastAltered"), @Index("name")})
public class QuizEntity implements Quiz {
    @PrimaryKey(autoGenerate = true)
    private int id;
    @NonNull
    private String name;
    @NonNull
    private ZonedDateTime creationDate;
    @NonNull
    private ZonedDateTime dateLastAltered;

    public QuizEntity(QuizEntity quizEntity) {
        this.id = quizEntity.getId();
        this.name = quizEntity.getName();
        this.creationDate = quizEntity.getCreationDate();
        this.dateLastAltered = quizEntity.getDateLastAltered();
    }

    public QuizEntity(@NonNull String name){
        this.name = name;
        this.creationDate = ZonedDateTime.now();
        this.dateLastAltered = ZonedDateTime.now();
    }

    public int getId(){
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @NonNull
    @Override
    public String getName() {
        return name;
    }

    public void setName(@NonNull String name) {
        this.name = name;
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
    public ZonedDateTime getDateLastAltered() {
        return dateLastAltered;
    }

    public void setDateLastAltered(@NonNull ZonedDateTime dateLastAltered) {
        this.dateLastAltered = dateLastAltered;
    }
}
