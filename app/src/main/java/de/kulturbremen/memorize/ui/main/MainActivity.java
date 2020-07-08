package de.kulturbremen.memorize.ui.main;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import java.time.ZonedDateTime;
import java.util.ArrayList;

import de.kulturbremen.memorize.data.entity.QuestionEntity;
import de.kulturbremen.memorize.model.QuestionContainer;
import de.kulturbremen.memorize.quizmanager.QuizManager;
import de.kulturbremen.memorize.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public static void setTestData(){
        ArrayList<QuestionContainer> questions = new ArrayList<>();
        ZonedDateTime dateTime = ZonedDateTime.now();
        questions.add(new QuestionEntity("nee", "nein", "name"));
        questions.add(new QuestionEntity("boek", "Buch", "name"));
        questions.add(new QuestionEntity("boom", "Baum", "name"));
        questions.add(new QuestionEntity("tv kijken", "fernsehen", "name"));
        QuizManager qm = QuizManager.getInstance();
        qm.setQuestions(questions);
    }
}