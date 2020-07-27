package de.kulturbremen.memorize.persistence.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import de.kulturbremen.memorize.model.QuestionEntity;

@Dao
public interface QuestionDao {
    @Query("SELECT * FROM QuestionEntity WHERE quizId = :quizId")
    List<QuestionEntity> getQuestionsByQuizId(long quizId);

    @Query("SELECT * FROM QuestionEntity WHERE id = :id")
    QuestionEntity getQuestionById(long id);

    @Insert
    long insertQuestion(QuestionEntity questionEntity);

    @Update
    void updateQuestion(QuestionEntity questionEntity);

    @Delete
    void deleteQuestion(QuestionEntity questionEntity);

    @Query("DELETE FROM QuestionEntity WHERE quizId = :quizId")
    void deleteQuestions(long quizId);
}
