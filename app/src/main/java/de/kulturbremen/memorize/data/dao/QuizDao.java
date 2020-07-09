package de.kulturbremen.memorize.data.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import de.kulturbremen.memorize.data.entity.QuizEntity;

@Dao
public interface QuizDao {
    @Query("SELECT * FROM QuizEntity ORDER BY dateLastAltered DESC")
    List<QuizEntity> getQuizzes();

    @Query("SELECT * FROM QuizEntity WHERE id = :id")
    QuizEntity getQuizById(long id);

    @Insert
    long insertQuiz(QuizEntity quizEntity);

    @Update
    void updateQuiz(QuizEntity quizEntity);

    @Delete
    void deleteQuiz(QuizEntity quizEntity);

}
