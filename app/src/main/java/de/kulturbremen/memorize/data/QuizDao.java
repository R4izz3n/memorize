package de.kulturbremen.memorize.data;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
interface QuizDao {
    @Query("SELECT name FROM quiz ORDER BY dateLastAltered DESC")
    List<Quiz> getQuizzes();

    @Insert(onConflict = OnConflictStrategy.ABORT)
    void insertQuiz(Quiz quiz);

    @Update
    void updateQuiz(Quiz quiz);

    @Delete
    void deleteQuiz(Quiz quiz);

}
