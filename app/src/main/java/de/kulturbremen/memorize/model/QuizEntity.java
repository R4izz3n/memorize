package de.kulturbremen.memorize.model;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import java.io.Serializable;
import java.time.ZonedDateTime;

@Entity(indices = {@Index("name")})
public class QuizEntity implements Serializable {
    @PrimaryKey(autoGenerate = true)
    private Long id;
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

    public Long getId(){
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @NonNull
    public String getName() {
        return name;
    }

    public void setName(@NonNull String name) {
        this.name = name.trim();
    }

    @NonNull
    public ZonedDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(@NonNull ZonedDateTime creationDate) {
        this.creationDate = creationDate;
    }

    @NonNull
    public ZonedDateTime getDateLastAltered() {
        return dateLastAltered;
    }

    public void setDateLastAltered(@NonNull ZonedDateTime dateLastAltered) {
        this.dateLastAltered = dateLastAltered;
    }

    @Override
    public String toString() {
        return "QuizEntity{" +
                "name='" + name + '\'' +
                '}';
    }
}
