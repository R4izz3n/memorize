package de.kulturbremen.memorize.data;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import de.kulturbremen.memorize.data.converter.DateConverter;
import de.kulturbremen.memorize.data.dao.QuestionDao;
import de.kulturbremen.memorize.data.dao.QuizDao;
import de.kulturbremen.memorize.data.entity.QuestionEntity;
import de.kulturbremen.memorize.data.entity.QuizEntity;

@Database(entities = {QuizEntity.class, QuestionEntity.class}, version = 1)
@TypeConverters({DateConverter.class})
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
