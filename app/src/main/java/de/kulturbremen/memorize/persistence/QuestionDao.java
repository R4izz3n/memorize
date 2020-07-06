package de.kulturbremen.memorize.persistence;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import de.kulturbremen.memorize.models.Question;

@Dao
public interface QuestionDao {
    @Query("SELECT * FROM question WHERE quiz = (:quiz)")
    List<Question> getQuestions(String quiz);

    @Insert
    void insertQuestion(Question question);

    @Update
    void updateQuestion(Question question);

    @Delete
    void deleteQuestion(Question question);
}
