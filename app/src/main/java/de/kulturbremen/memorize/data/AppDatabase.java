package de.kulturbremen.memorize.data;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Quiz.class, Question.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract QuizDao QuizDao();
    public abstract QuestionDao QuestionDao();

    public static final String DATABASE_NAME = "memorize_db";
    private static AppDatabase instance;

    static AppDatabase getInstance(final Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(
                    context.getApplicationContext(),
                    AppDatabase.class,
                    DATABASE_NAME
            ).build();
        }
        return instance;
    }
}
