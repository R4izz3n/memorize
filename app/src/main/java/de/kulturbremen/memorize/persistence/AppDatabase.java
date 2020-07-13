package de.kulturbremen.memorize.persistence;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import de.kulturbremen.memorize.persistence.converter.DateConverter;
import de.kulturbremen.memorize.persistence.dao.QuestionDao;
import de.kulturbremen.memorize.persistence.dao.QuizDao;
import de.kulturbremen.memorize.model.QuestionEntity;
import de.kulturbremen.memorize.model.QuizEntity;

@Database(entities = {QuizEntity.class, QuestionEntity.class}, version = 1)
@TypeConverters({DateConverter.class})
public abstract class AppDatabase extends RoomDatabase {

    public abstract QuizDao QuizDao();
    public abstract QuestionDao QuestionDao();

    public static final String DATABASE_NAME = "memorize";
    private static AppDatabase instance;

    public static AppDatabase getInstance(final Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context, AppDatabase.class, DATABASE_NAME)
                    .createFromAsset("database/memorize.db")
                    .allowMainThreadQueries()  // TODO: implement async calls
                    .build();
        }
        return instance;
    }
}
