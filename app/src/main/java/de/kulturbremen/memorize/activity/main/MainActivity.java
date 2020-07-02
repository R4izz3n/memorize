package de.kulturbremen.memorize.activity.main;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import java.util.ArrayList;

import de.kulturbremen.memorize.quizmanager.QuestionContainer;
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
        questions.add(new QuestionContainer("nee", "nein"));
        questions.add(new QuestionContainer("boek", "Buch"));
        questions.add(new QuestionContainer("boom", "Baum"));
        questions.add(new QuestionContainer("tv kijken", "fernsehen"));
        QuizManager.setQUESTIONS(questions);
    }
}